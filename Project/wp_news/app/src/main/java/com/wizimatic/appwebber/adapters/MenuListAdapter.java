package com.wizimatic.appwebber.adapters;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.api.models.menus.MainMenu;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;

/**
 * Created by SAIF-MCC on 10/2/2017.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private final ArrayList<MainMenu> menuList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public MenuListAdapter(Context context, ArrayList<MainMenu> allMenuList) {
        this.context = context;
        menuList = allMenuList;
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_or_menu_list, parent, false);
        return new ViewHolder(view, viewType, itemClickListener);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgMenuIcon;
        private final TextView tvMenuTitle;
        private final RelativeLayout rlMenu;
        private final ListItemClickListener itemClickListener;

        //public RelativeLayout mPostListRelativeLayout;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            imgMenuIcon = (ImageView) itemView.findViewById(R.id.img_category_icon);
            tvMenuTitle = (TextView) itemView.findViewById(R.id.each_category_title);
            rlMenu = (RelativeLayout) itemView.findViewById(R.id.rl_menu_or_cat);

            // Implement click listener over views that we need
            rlMenu.setOnClickListener(this);

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
        return (null != menuList ? menuList.size() : 0);

    }

    @Override
    public void onBindViewHolder(MenuListAdapter.ViewHolder mainHolder, int position) {
        final MainMenu model = menuList.get(position);


        // setting data over views
        mainHolder.tvMenuTitle.setText(Html.fromHtml(model.getName()));

        mainHolder.imgMenuIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_menu));

    }

}
