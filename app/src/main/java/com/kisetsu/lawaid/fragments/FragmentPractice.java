package com.kisetsu.lawaid.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kisetsu.lawaid.ActivityWebNews;
import com.kisetsu.lawaid.R;
import com.kisetsu.lawaid.adapters.ListAdapterNews;
import com.kisetsu.lawaid.utilities.ItemBeanNews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentPractice extends Fragment {

    public final String URL="http://www.imooc.com/api/teacher?type=4&num=30";
    public ListView listNews;

    class NewsAsyncTask extends AsyncTask<String,Void,List<ItemBeanNews>>{

        @Override
        protected List<ItemBeanNews> doInBackground(String... strings) {
            return getNewsItem(strings[0]);
        }

        @Override
        protected void onPostExecute(List<ItemBeanNews> itemBeanNews) {
            super.onPostExecute(itemBeanNews);
            ListAdapterNews adapterNews=new ListAdapterNews(getContext(),itemBeanNews);
            listNews.setAdapter(adapterNews);
        }
    }

    private List<ItemBeanNews> getNewsItem(String url){
        List<ItemBeanNews> list=new ArrayList<>();
        try {
            String jsonString=readString(new URL(url).openStream());
            JSONObject jsonObject;
            ItemBeanNews itemBeanNews;
            jsonObject=new JSONObject(jsonString);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);
                itemBeanNews=new ItemBeanNews(jsonObject.getString("picSmall"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description"));
                list.add(itemBeanNews);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String readString(InputStream is){
        InputStreamReader isr;
        StringBuilder result= new StringBuilder();
        try {
            String line="";
            isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            while ((line=br.readLine())!=null){
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static FragmentPractice newInstance(Bundle args){
        FragmentPractice homepage=new FragmentPractice();
        homepage.setArguments(args);
        return homepage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NewsAsyncTask().execute(URL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_practice,container,false);
        listNews=convertView.findViewById(R.id.lv_practice);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), ActivityWebNews.class));
            }
        });
        return convertView;
    }
}
