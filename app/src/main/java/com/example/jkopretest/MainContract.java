package com.example.jkopretest;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.Chat;

import java.util.List;

public interface MainContract {

    interface View {

        void showChatMessage(@NonNull List<Chat> chatList);
    }

    interface Presenter {

        void getFakeData();
    }
}
