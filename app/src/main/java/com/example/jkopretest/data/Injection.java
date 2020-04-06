package com.example.jkopretest.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.jkopretest.data.source.ChatRepository;
import com.example.jkopretest.data.source.local.ChatLocalDataSource;
import com.example.jkopretest.data.source.local.JkoTestDatabase;
import com.example.jkopretest.data.source.remote.ChatRemoteDataSource;
import com.example.jkopretest.executors.GlobalExecutors;

public class Injection {

    public static ChatRepository provideChatRepository(@NonNull Context context) {
        JkoTestDatabase database = JkoTestDatabase.getInstance(context);
        return ChatRepository.getInstance(
                ChatRemoteDataSource.getInstance(context),
                ChatLocalDataSource.getInstance(new GlobalExecutors(), database.chatDao(), context.getSharedPreferences("JkoTest.User", Context.MODE_PRIVATE)));
    }
}
