package com.example.jkopretest.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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

    // local attribute
    private boolean isGroup;

    /**
     * The constructor to build the data
     *
     * @param createdAt     when this chat was sent
     * @param id            the user who sent this message
     *                      if this message was sent by myself, set this String empty
     * @param avatar        url of the avatar
     *                      if this message was sent by myself, set this String null
     * @param isRead        has the user read this chat, set all true
     * @param isGroup       has the view group divider
     */
    public Chat(Date createdAt, @NonNull String id,
                @NonNull String message, @Nullable String avatar,
                boolean isRead, boolean isGroup) {
        this.createdAt = createdAt;
        this.id = id;
        this.message = message;
        this.avatar = avatar;
        this.isRead = isRead;
        this.isGroup = isGroup;
    }

    @Ignore
    public Chat(Date createdAt, @NonNull String message){
        this(createdAt, "", message, null, true, false);
    }

    @Ignore
    public Chat(Date createdAt){
        this(createdAt, "", "", null, true, true);
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

    public boolean isGroup() {
        return isGroup;
    }

    public String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return simpleDateFormat.format(createdAt);
    }

    public String getTimeGroupString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM / dd", Locale.getDefault());
        return simpleDateFormat.format(createdAt);
    }
}
