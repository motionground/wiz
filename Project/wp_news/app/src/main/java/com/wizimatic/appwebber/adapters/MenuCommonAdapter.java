package com.wizimatic.appwebber.adapters;


import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wizimatic.appwebber.R;

import com.wizimatic.appwebber.api.models.menus.AnyMenu;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by SAIF-MCC on 10/24/2017.
 */


public class MenuCommonAdapter extends RecyclerView.Adapter<MenuCommonAdapter.ViewHolder> {

    private final ArrayList<AnyMenu> arrayList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public MenuCommonAdapter(Context context, ArrayList<AnyMenu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_screen_menu_list, parent, false);
        return new ViewHolder(view, viewType, itemClickListener);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvMenus;
        private final TextView tvMenuItem;
        private final ListItemClickListener itemClickListener;

        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;

            // Find all views ids
            tvMenus = (TextView) itemView.findViewById(R.id.tv_menu);
            tvMenuItem = (TextView) itemView.findViewById(R.id.tv_menu_item);


            // Implement click listener over views that we need
            tvMenus.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }
        }
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    @Override
    public void onBindViewHolder(MenuCommonAdapter.ViewHolder mainHolder, int position) {

        final AnyMenu model = arrayList.get(position);

        // setting data over views
        mainHolder.tvMenus.setText(Html.fromHtml(String.valueOf(model.getName().charAt(0))));
        mainHolder.tvMenuItem.setText(Html.fromHtml(model.getName()));


        Random rand = new Random();
        int i = rand.nextInt(5) + 1;
        switch (i) {
            case 1:
                mainHolder.tvMenus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_red));
                break;
            case 2:
                mainHolder.tvMenus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_blue));
                break;

            case 3:
                mainHolder.tvMenus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_orange));
                break;

            case 4:
                mainHolder.tvMenus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_purple));
                break;

            case 5:
                mainHolder.tvMenus.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_green));
                break;

            default:
                break;
        }
    }

}