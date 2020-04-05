package com.example.jkopretest.data.source.remote;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.source.ChatDataSource;

import java.util.List;

public class ChatRemoteDataSource implements ChatDataSource {

    private static ChatRemoteDataSource mInstance;

    private ChatRemoteDataSource(){
    }

    public static synchronized ChatRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new ChatRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void getChatList(@NonNull LoadChatsCallback callback) {

    }

    @Override
    public void saveChatList(List<Chat> chatList) {

    }
}
