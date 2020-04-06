package com.example.jkopretest.data.source;

import android.util.Log;

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
            public void onChatsLoaded(List<Chat> chatList) {
                Log.e("chatData","1");
                callback.onChatsLoaded(chatList);
            }

            @Override
            public void onDataNotAvailable(boolean isNetworkError) {
                Log.e("chatData","2");
                mChatRemoteDataSource.getChatList(callback);
            }
        });
    }

    @Override
    public void saveChatList(List<Chat> chatList) {
        mChatLocalDataSource.saveChatList(chatList);
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
