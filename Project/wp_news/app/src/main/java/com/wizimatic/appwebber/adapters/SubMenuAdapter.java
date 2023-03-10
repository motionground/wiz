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
import com.wizimatic.appwebber.api.models.menus.SubMenuItem;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;

/**
 * Created by SAIF-MCC on 10/2/2017.
 */

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.ViewHolder> {

    private final ArrayList<SubMenuItem> menuItemList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public SubMenuAdapter(Context context, ArrayList<SubMenuItem> allMenuItemList) {
        this.context = context;
        menuItemList = allMenuItemList;
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
        private final RelativeLayout rlSubMenu;
        private final ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            imgMenuIcon = (ImageView) itemView.findViewById(R.id.img_category_icon);
            tvMenuTitle = (TextView) itemView.findViewById(R.id.each_category_title);
            rlSubMenu = (RelativeLayout) itemView.findViewById(R.id.rl_menu_or_cat);

            // Implement click listener over views that we need
            rlSubMenu.setOnClickListener(this);

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
        return (null != menuItemList ? menuItemList.size() : 0);

    }

    @Override
    public void onBindViewHolder(SubMenuAdapter.ViewHolder mainHolder, int position) {
        final SubMenuItem model = menuItemList.get(position);


        // setting data over views
        mainHolder.tvMenuTitle.setText(Html.fromHtml(model.getTitle()));

        mainHolder.imgMenuIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_menu));


    }

}
