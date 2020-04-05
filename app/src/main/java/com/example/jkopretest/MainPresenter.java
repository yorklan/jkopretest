package com.example.jkopretest;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.source.ChatRepository;

class MainPresenter implements MainContract.Presenter{

    private final MainContract.View mMainView;

    private final ChatRepository mChatRepository;

    MainPresenter(@NonNull ChatRepository chatRepository, @NonNull MainContract.View view){
        mMainView  = view;
        mChatRepository = chatRepository;
    }

}
