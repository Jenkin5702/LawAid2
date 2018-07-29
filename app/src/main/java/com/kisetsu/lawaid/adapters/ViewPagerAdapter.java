package com.kisetsu.lawaid.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ViewPagerAdapter extends PagerAdapter {

    ImageView image;

    final String URL="http://h.hiphotos.baidu.com/image/pic/item/3c6d55fbb2fb43165a73bbab2ca4462308f7d3f7.jpg";
    static class LoadImageTask extends AsyncTask<String,Void,Bitmap> {

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
//        mimageView.setImageBitmap(bitmap);
        }
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }
}
