package com.example.jkopretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.Injection;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainPresenter mMainPresenter;

    private MainAdapter mMainAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;

    private ImageView mImgSend, mImgRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(Injection.provideChatRepository(this), this);
        buildRecyclerView();
        buildInputBar();
        mMainPresenter.getFakeData();
    }

    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler_view_main);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMainAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mMainAdapter);
    }

    private void buildInputBar(){
        mImgRecord = findViewById(R.id.img_record);
        mImgSend = findViewById(R.id.img_send);
        EditText editInput = findViewById(R.id.edit_input);
        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s==null || s.toString().isEmpty()){
                    mImgSend.setImageDrawable(getDrawable(R.drawable.ic_nut));
                    mImgRecord.setVisibility(View.VISIBLE);
                }else {
                    mImgSend.setImageDrawable(getDrawable(R.drawable.ic_send));
                    mImgRecord.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_delete){
            mMainPresenter.deleteChat(mMainAdapter.getChatChoose());
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void showChatMessage(@NonNull List<Chat> chatList) {
        mMainAdapter.updateData(chatList);
        mMainAdapter.notifyDataSetChanged();
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(mLinearLayoutManager.getItemCount()>=mLinearLayoutManager.findLastVisibleItemPosition()){
                    mRecyclerView.scrollToPosition(mMainAdapter.getItemCount()-1);
                }
            }
        });
    }

    @Override
    public void deleteChat(@NonNull Chat chat) {
        mMainAdapter.deleteData(chat);
    }
}
