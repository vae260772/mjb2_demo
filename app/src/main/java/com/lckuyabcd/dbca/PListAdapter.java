package com.lckuyabcd.dbca;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class PListAdapter extends BaseAdapter {
    private List<Bitmap> picList;
    private Context context;

    public PListAdapter(Context context, List<Bitmap> picList) {
        this.context = context;
        this.picList = picList;
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup arg2) {
        ImageView iv_pic_item = null;
        int density = (int) SUtil.getDeviceDensity(context);
        if (convertView == null) {
            iv_pic_item = new ImageView(context);
            iv_pic_item.setLayoutParams(new GridView.LayoutParams(
                    80 * density,
                    100 * density));
            iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            iv_pic_item = (ImageView) convertView;
        }
        iv_pic_item.setBackgroundColor(Color.BLACK);
        iv_pic_item.setImageBitmap(picList.get(position));
        return iv_pic_item;
    }
}
