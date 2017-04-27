package com.riteshb.stockautoscroll;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderHolder> {
    Context mContext;

    public HeaderAdapter(Context mContext) {
        this.mContext = mContext;

    }


    @Override
    public HeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_child_menu, parent, false);
        HeaderHolder headerHolder = new HeaderHolder(view);
        return headerHolder;
    }



    @Override
    public void onBindViewHolder(HeaderHolder holder, int position) {
        try {
                try {
                    if (((position+1)%2) == 0) {
                        Drawable img = mContext.getResources().getDrawable(R.drawable.diffdown);
                        holder.difficon.setBackground(img);
                        holder.menuitems.setText("Data - " + (position+1/**10+position*/));
                    } else {
                        Drawable img = mContext.getResources().getDrawable(R.drawable.diffup);
                        holder.difficon.setBackground(img);
                        holder.menuitems.setText("Data - " + (position+1/**10-position*/));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        private TextView menuitems;
        private ImageView difficon;

        public HeaderHolder(View itemView) {
            super(itemView);

            menuitems = (TextView) itemView.findViewById(R.id.menuitems);
            difficon = (ImageView) itemView.findViewById(R.id.difficon);
        }
    }




}
