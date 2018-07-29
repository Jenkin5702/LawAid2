package com.kisetsu.lawaid.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.kisetsu.lawaid.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FragmentHomepage extends Fragment {
    ImageView image;

    final String URL="http://h.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb43165a73bbab2ca4462308f7d3f7.jpg";
    class LoadImageTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];//获取传递进来的参数
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;

            try {
                connection=new URL(url).openConnection();
                is=connection.getInputStream();
                BufferedInputStream bis=new BufferedInputStream(is);
                bitmap= BitmapFactory.decodeStream(bis);//解析输入流
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
//        mprogressBar.setVisibility(View.GONE);
        image.setImageBitmap(bitmap);
        }
    }
    public static FragmentHomepage newInstance(Bundle args){
        FragmentHomepage homepage=new FragmentHomepage();
        homepage.setArguments(args);
        return homepage;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_homepage,container,false);
        image=convertView.findViewById(R.id.iv_homepage);
        new LoadImageTask().execute(URL);
        return convertView;
    }
}
