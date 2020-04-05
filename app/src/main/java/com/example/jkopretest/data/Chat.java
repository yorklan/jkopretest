package com.example.jkopretest.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private final String id;

    @NonNull
    @ColumnInfo(name = "message")
    @SerializedName("message")
    private final String message;

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
     * @param id          the user who sent this chat
     * @param avatar        url of the avatar
     * @param isRead        has the user read this chat
     */
    public Chat(Date createdAt, @NonNull String id,
                 @NonNull String message, @Nullable String avatar, boolean isRead) {
        this.createdAt = createdAt;
        this.id = id;
        this.message = message;
        this.avatar = avatar;
        this.isRead = isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isMe(){
        return id.isEmpty();
    }

    public String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        return simpleDateFormat.format(createdAt);
    }
}
