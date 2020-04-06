package com.example.jkopretest.data.source.local;


import android.util.Log;

import androidx.annotation.NonNull;

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

        Log.e("chatData","3");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Chat> chatList = mChatDao.getChats();
                mGlobalExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (chatList != null && !chatList.isEmpty()) {
                            Log.e("chatData","4");
                            callback.onChatsLoaded(chatList);
                        } else {
                            Log.e("chatData","5");
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
}
