package com.example.jkopretest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jkopretest.data.Injection;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainPresenter mMainPresenter;

    private ImageView mImgSend, mImgRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(Injection.provideChatRepository(this), this);
        buildInputBar();
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
                    Log.e("test","1");
                    mImgSend.setImageDrawable(getDrawable(R.drawable.ic_nut));
                    mImgRecord.setVisibility(View.VISIBLE);
                }else {
                    Log.e("test","2");
                    mImgSend.setImageDrawable(getDrawable(R.drawable.ic_send));
                    mImgRecord.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
