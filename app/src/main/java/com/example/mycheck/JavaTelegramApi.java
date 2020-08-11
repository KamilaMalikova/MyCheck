package com.example.mycheck;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JavaTelegramApi extends AsyncTask<Void,Void,Void> {
    private String message;

    public JavaTelegramApi(String message) {
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email
        //  mProgressDialog = ProgressDialog.show(mContext,"Sending message", "Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismiss progress dialog when message successfully send
//        mProgressDialog.dismiss();

        //Show success toast
  //      Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "yourTelegramJavaApi";

        String chatId = "chatID";
        String text = null;
        try {
            text = URLEncoder.encode(this.message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ;
        urlString = String.format(urlString, apiToken, chatId, text);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" ); // not sure it is important
            InputStream is = new BufferedInputStream(conn.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
