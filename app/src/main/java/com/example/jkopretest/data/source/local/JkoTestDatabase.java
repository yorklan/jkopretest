package com.example.jkopretest.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.Converters;

/**
 * The Room Database that contains the Chat table.
 */
@Database(entities = {Chat.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class JkoTestDatabase extends RoomDatabase {

    private static JkoTestDatabase INSTANCE;

    public abstract ChatDao chatDao();

    private static final Object lock = new Object();

    public static JkoTestDatabase getInstance(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        JkoTestDatabase.class, "Chats.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
