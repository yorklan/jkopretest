package com.example.jkopretest.data.source;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.Chat;

import java.util.List;

public interface ChatDataSource {

    interface LoadChatsCallback {

        void onChatsLoaded(List<Chat> chatList);

        void onDataNotAvailable(boolean isNetworkError);
    }

    void getChatList(@NonNull LoadChatsCallback callback);

    void saveChatList(List<Chat> chatList);
}
