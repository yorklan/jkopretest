package com.example.jkopretest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jkopretest.data.Injection;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(Injection.provideChatRepository(this), this);
    }
}
