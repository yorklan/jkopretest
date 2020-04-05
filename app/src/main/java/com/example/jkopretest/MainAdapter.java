package com.example.jkopretest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jkopretest.data.Chat;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Chat> mChatList = new ArrayList<>();

    private final int VIEW_TYPE_USER = 0, VIEW_TYPE_ME = 1;

    MainAdapter(){

    }

    private class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvatar;
        private TextView textMessage;
        private TextView textTime;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_user_avatar);
            textMessage = itemView.findViewById(R.id.text_user_message);
            textTime = itemView.findViewById(R.id.text_user_time);
        }
    }

    private class MeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgRead;
        private TextView textMessage;
        private TextView textTime;

        MeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRead = itemView.findViewById(R.id.img_me_read);
            textMessage = itemView.findViewById(R.id.text_me_message);
            textTime = itemView.findViewById(R.id.text_me_time);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mChatList.get(position).isMe()){
            return VIEW_TYPE_ME;
        }else {
            return VIEW_TYPE_USER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_ME:
                return new MeViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_me, parent, false));
            case VIEW_TYPE_USER:
            default:
                return new UserViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_user, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UserViewHolder){
            setUserViewHolder((UserViewHolder) holder, mChatList.get(position));
        } else if(holder instanceof MeViewHolder){
            setMeViewHolder((MeViewHolder) holder, mChatList.get(position));
        }
    }

    private void setUserViewHolder(UserViewHolder userViewHolder, Chat chat){
        Glide.with(userViewHolder.itemView.getContext())
                .load(chat.getAvatar())
                .circleCrop()
                .into(userViewHolder.imgAvatar);
        userViewHolder.textMessage.setText(chat.getMessage());
        userViewHolder.textTime.setText(chat.getTimeString());
    }

    private void setMeViewHolder(MeViewHolder meViewHolder, Chat chat){
        if(chat.isRead()){
            meViewHolder.imgRead.setImageDrawable(meViewHolder.imgRead.getContext().getDrawable(R.drawable.ic_tick));
        }
        meViewHolder.textMessage.setText(chat.getMessage());
        meViewHolder.textTime.setText(chat.getTimeString());
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }
}
