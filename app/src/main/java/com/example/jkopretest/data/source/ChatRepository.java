package com.example.jkopretest.data.source;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.Chat;

import java.util.List;

public class ChatRepository implements ChatDataSource{

    private static ChatRepository mInstance = null;

    private final ChatDataSource mChatRemoteDataSource;

    private final ChatDataSource mChatLocalDataSource;

    private ChatRepository(@NonNull ChatDataSource chatRemoteDataSource,
                             @NonNull ChatDataSource chatLocalDataSource){
        mChatRemoteDataSource = chatRemoteDataSource;
        mChatLocalDataSource = chatLocalDataSource;
    }

    public static ChatRepository getInstance(ChatDataSource chatRemoteDataSource,
                                             ChatDataSource chatLocalDataSource) {
        if (mInstance == null) {
            mInstance = new ChatRepository(chatRemoteDataSource, chatLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public void getChatList(@NonNull LoadChatsCallback callback) {
        mChatRemoteDataSource.getChatList(callback);
    }

    @Override
    public void saveChatList(List<Chat> chatList) {

    }

    @Override
    public void deleteChat(@NonNull Chat chat) {

    }
}
