package com.example.jkopretest.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.example.jkopretest.data.Chat;

import java.util.Date;
import java.util.List;

/**
 * DAO for the chat table.
 */
@Dao
public interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChats(List<Chat> chatList);

    @Query("DELETE FROM chat WHERE createdat = :createdAt")
    void deleteChatByTime(Date createdAt);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChat(Chat chat);

    @RawQuery
    List<Chat> getChatsSort(SupportSQLiteQuery sortQuery);
}
