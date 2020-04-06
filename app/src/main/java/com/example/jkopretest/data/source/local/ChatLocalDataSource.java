package com.example.jkopretest.data.source.local;


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
    public void getChatList(@NonNull LoadChatsCallback callback) {

    }

    @Override
    public void saveChatList(List<Chat> chatList) {

    }

    @Override
    public void deleteChat(@NonNull Chat chat) {

    }
}
