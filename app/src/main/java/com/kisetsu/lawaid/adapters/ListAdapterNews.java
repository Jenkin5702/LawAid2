package com.kisetsu.lawaid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisetsu.lawaid.R;
import com.kisetsu.lawaid.utilities.ImageLoader;
import com.kisetsu.lawaid.utilities.ItemBeanNews;

import java.util.List;

public class ListAdapterNews extends BaseAdapter {
    private List<ItemBeanNews> listItem;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    class ViewHolder{
        public TextView title;
        public TextView description;
        public ImageView image;
    }

    public ListAdapterNews(Context context,List<ItemBeanNews> items){
        this.listItem=items;
        inflater=LayoutInflater.from(context);
        imageLoader=new ImageLoader();
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view=inflater.inflate(R.layout.item_list_news,null);
            viewHolder.image=view.findViewById(R.id.iv_item_practice);
            viewHolder.title=view.findViewById(R.id.tv_item_title);
            viewHolder.description=view.findViewById(R.id.tv_item_abstract);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
//        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        String imageUrl=listItem.get(i).imageUrl;
        imageLoader.showImageByAsyncTask(viewHolder.image,imageUrl);
        viewHolder.image.setTag(imageUrl);
        viewHolder.title.setText(listItem.get(i).title);
        viewHolder.description.setText(listItem.get(i).content);
        return view;
    }
}
