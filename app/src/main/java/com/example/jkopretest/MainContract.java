package com.example.jkopretest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jkopretest.data.Chat;

import java.util.List;

public interface MainContract {

    interface View {

        void showChatMessage(@NonNull List<Chat> chatList);

        void showChatMessage(@NonNull Chat chat);

        void deleteChat(@NonNull Chat chat);
    }

    interface Presenter {

        void getFakeData();

        void deleteChat(@Nullable Chat chat);

        void sendChat(@NonNull String message);
    }
}
