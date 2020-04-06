package com.example.jkopretest.data.source.remote;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.jkopretest.R;
import com.example.jkopretest.data.Chat;
import com.example.jkopretest.data.source.ChatDataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatRemoteDataSource implements ChatDataSource {

    private static ChatRemoteDataSource mInstance;
    private InputStream XmlFileInputStream;

    private ChatRemoteDataSource(Context context){
        XmlFileInputStream = context.getResources().openRawResource(R.raw.fakedata);
    }

    public static synchronized ChatRemoteDataSource getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ChatRemoteDataSource(context);
        }
        return mInstance;
    }

    @Override
    public void getChatList(@NonNull LoadChatsCallback callback) {
        try {
            String jsonString = readTextFile(XmlFileInputStream);
            GsonBuilder b = new GsonBuilder();
            Gson gson = b.registerTypeAdapter(Date.class, new DateDeserializer()).create();
            FakeData fakeData = gson.fromJson(jsonString, FakeData.class);
            callback.onChatsLoaded(fakeData.getData());
        }catch (Exception e){
            callback.onDataNotAvailable(true);
        }
    }

    private String readTextFile(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toString();
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {

        private final String[] DATE_FORMATS = new String[]{
                "yyyy-MM-dd'T'HH:mm:ss.SSSX",
        };

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            for (String format : DATE_FORMATS) {
                try {
                    return new SimpleDateFormat(format, Locale.getDefault()).parse(jsonElement.getAsString());
                } catch (ParseException ignored) {
                }
            }
            throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                    + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
        }
    }

    @Override
    public void saveChatList(List<Chat> chatList) {

    }
}
