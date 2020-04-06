package com.example.jkopretest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.source.ChatDataSource;
import com.example.jkopretest.data.source.ChatRepository;

import java.util.Calendar;
import java.util.List;

class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainView;

    private final ChatRepository mChatRepository;

    MainPresenter(@NonNull ChatRepository chatRepository, @NonNull MainContract.View view) {
        mMainView = view;
        mChatRepository = chatRepository;
    }

    @Override
    public void getFakeData() {
        mChatRepository.getChatList(new ChatDataSource.LoadChatsCallback() {
            @Override
            public void onChatsLoaded(List<Chat> chatList,@NonNull String userName) {
                mMainView.showUserName(userName);
                mMainView.showChatMessage(chatList);
                mChatRepository.saveChatList(chatList, userName);
            }

            @Override
            public void onDataNotAvailable(boolean isNetworkError) {

            }
        });
    }

    @Override
    public void deleteChat(@Nullable Chat chat) {
        if (chat != null) {
            mChatRepository.deleteChat(chat);
            mMainView.deleteChat(chat);
        }
    }

    @Override
    public void sendChat(@NonNull String message) {
        Chat newChat = new Chat(Calendar.getInstance().getTime(), message);
        mMainView.showChatMessage(newChat);
        mChatRepository.insertNewChat(newChat);
    }
}
