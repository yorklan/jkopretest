package com.example.jkopretest.data.source.remote;

import com.example.jkopretest.data.Chat;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FakeData {
    @SerializedName("name")
    private String name;

    @SerializedName("data")
    private List<Chat> data = null;

    public String getName() {
        return name;
    }

    public List<Chat> getData() {
        return data;
    }
}
