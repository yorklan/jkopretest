package com.example.jkopretest.data.source.local;


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

    private ChatLocalDataSource(@NonNull GlobalExecutors globalExecutors,
                                @NonNull ChatDao chatDao) {
        mGlobalExecutors = globalExecutors;
        mChatDao = chatDao;
    }

    public static ChatLocalDataSource getInstance(@NonNull GlobalExecutors globalExecutors,
                                                    @NonNull ChatDao chatDao) {
        if (mInstance == null) {
            synchronized (ChatLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new ChatLocalDataSource(globalExecutors, chatDao);
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
                            callback.onChatsLoaded(chatList);
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
    public void saveChatList(final List<Chat> chatList) {
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
