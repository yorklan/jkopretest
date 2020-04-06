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
    public void getChatList(@NonNull final LoadChatsCallback callback) {
        mChatLocalDataSource.getChatList(new LoadChatsCallback() {
            @Override
            public void onChatsLoaded(List<Chat> chatList, String userName) {
                callback.onChatsLoaded(chatList, userName);
            }

            @Override
            public void onDataNotAvailable(boolean isNetworkError) {
                mChatRemoteDataSource.getChatList(callback);
            }
        });
    }

    @Override
    public void saveChatList(List<Chat> chatList, String userName) {
        mChatLocalDataSource.saveChatList(chatList, userName);
    }

    @Override
    public void deleteChat(@NonNull Chat chat) {
        mChatLocalDataSource.deleteChat(chat);
    }

    @Override
    public void insertNewChat(@NonNull Chat chat) {
        mChatLocalDataSource.insertNewChat(chat);
    }
}
