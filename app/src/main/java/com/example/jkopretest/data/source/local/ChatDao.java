package com.example.jkopretest.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.jkopretest.data.Chat;

import java.util.Date;
import java.util.List;

/**
 * DAO for the chat table.
 */
@Dao
public interface ChatDao {

    @Query("SELECT * FROM chat")
    List<Chat> getChats();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChats(List<Chat> chatList);

    @Query("DELETE FROM chat WHERE createdat = :createdAt")
    int deleteChatByTime(Date createdAt);
}
