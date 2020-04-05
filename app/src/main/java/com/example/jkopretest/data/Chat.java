package com.example.jkopretest.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Final model class for Chat
 */
@Entity(tableName = "chat")
public final class Chat {

    @PrimaryKey
    @ColumnInfo(name = "createdat")
    @SerializedName("created_at")
    private final Date createdAt;

    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private final String name;

    @Nullable
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    private final String avatar;

    @ColumnInfo(name = "isread")
    @SerializedName("is_read")
    private final boolean isRead;

    /**
     * The constructor to build the data
     *
     * @param createdAt     when this chat was sent
     * @param name          the user who sent this chat
     * @param avatar        url of the avatar
     * @param isRead        has the user read this chat
     */
    public Chat(Date createdAt, @NonNull String name,
                 @Nullable String avatar, boolean isRead) {
        this.createdAt = createdAt;
        this.name = name;
        this.avatar = avatar;
        this.isRead = isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    public boolean isRead() {
        return isRead;
    }
}
