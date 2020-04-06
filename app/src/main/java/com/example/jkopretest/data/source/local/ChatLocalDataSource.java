package com.example.jkopretest.data.source.local;


import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.source.ChatDataSource;
import com.example.jkopretest.executors.GlobalExecutors;

import java.util.List;

public class ChatLocalDataSource implements ChatDataSource {

    private static volatile ChatLocalDataSource mInstance;

    private ChatDao mChatDao;

    private GlobalExecutors mGlobalExecutors;

    private SharedPreferences mSharedPreferencesUser;
    private final String KEY_USER_NAME = "userName";

    private ChatLocalDataSource(@NonNull GlobalExecutors globalExecutors,
                                @NonNull ChatDao chatDao,
                                @NonNull SharedPreferences sharedPreferences) {
        mGlobalExecutors = globalExecutors;
        mChatDao = chatDao;
        mSharedPreferencesUser = sharedPreferences;
    }

    public static ChatLocalDataSource getInstance(@NonNull GlobalExecutors globalExecutors,
                                                    @NonNull ChatDao chatDao,
                                                    @NonNull SharedPreferences sharedPreferences) {
        if (mInstance == null) {
            synchronized (ChatLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new ChatLocalDataSource(globalExecutors, chatDao, sharedPreferences);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getChatList(@NonNull final LoadChatsCallback callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Chat> chatList = mChatDao.getChatsSort(new SimpleSQLiteQuery("SELECT * FROM chat ORDER BY createdat ASC"));
                mGlobalExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (chatList != null && !chatList.isEmpty()) {
                            callback.onChatsLoaded(chatList, mSharedPreferencesUser.getString(KEY_USER_NAME, ""));
                        } else {
                            callback.onDataNotAvailable(false);
                        }
                    }
                });
            }
        };

        mGlobalExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveChatList(final List<Chat> chatList, String userName) {
        mSharedPreferencesUser.edit().putString(KEY_USER_NAME, userName).apply();
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mChatDao.insertAllChats(chatList);
            }
        };
        mGlobalExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void deleteChat(@NonNull final Chat chat) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mChatDao.deleteChatByTime(chat.getCreatedAt());
            }
        };

        mGlobalExecutors.diskIO().execute(deleteRunnable);
    }

    @Override
    public void insertNewChat(@NonNull final Chat chat) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mChatDao.insertChat(chat);
            }
        };
        mGlobalExecutors.diskIO().execute(saveRunnable);
    }
}
