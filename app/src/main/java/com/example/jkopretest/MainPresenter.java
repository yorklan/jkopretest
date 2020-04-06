package com.example.jkopretest;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.source.ChatDataSource;
import com.example.jkopretest.data.source.ChatRepository;

import java.util.List;

class MainPresenter implements MainContract.Presenter{

    private final MainContract.View mMainView;

    private final ChatRepository mChatRepository;

    MainPresenter(@NonNull ChatRepository chatRepository, @NonNull MainContract.View view){
        mMainView  = view;
        mChatRepository = chatRepository;
    }

    @Override
    public void getFakeData() {
        mChatRepository.getChatList(new ChatDataSource.LoadChatsCallback() {
            @Override
            public void onChatsLoaded(List<Chat> chatList) {
                mMainView.showChatMessage(chatList);
            }

            @Override
            public void onDataNotAvailable(boolean isNetworkError) {

            }
        });
    }
}
