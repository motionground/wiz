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
import com.wizimatic.appwebber.api.models.category.Category;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;

/**
 * Created by SAIF-MCC on 9/20/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private final ArrayList<Category> categoryList;
    private final ArrayList<Category> subCategoryList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public SubCategoryAdapter(Context context, ArrayList<Category> allCategoryList, ArrayList<Category> allSubCategoryList) {
        this.context = context;
        categoryList = allCategoryList;
        subCategoryList = allSubCategoryList;

    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_or_menu_list, parent, false);
        return new ViewHolder(view, viewType, itemClickListener);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgSubCategoryIcon;
        private final TextView tvSubCategoryTitle;
        private final RelativeLayout rlCategory;
        private final ListItemClickListener itemClickListener;

        //public RelativeLayout mPostListRelativeLayout;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            imgSubCategoryIcon = (ImageView) itemView.findViewById(R.id.img_category_icon);
            tvSubCategoryTitle = (TextView) itemView.findViewById(R.id.each_category_title);
            rlCategory = (RelativeLayout) itemView.findViewById(R.id.rl_menu_or_cat);

            // Implement click listener over views that we need
            rlCategory.setOnClickListener(this);

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
        return (null != subCategoryList ? subCategoryList.size() : 0);

    }

    @Override
    public void onBindViewHolder(SubCategoryAdapter.ViewHolder mainHolder, int position) {
        final Category model = subCategoryList.get(position);


        // setting data over views
        mainHolder.tvSubCategoryTitle.setText(Html.fromHtml(model.getName()));
        mainHolder.imgSubCategoryIcon.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_category));

    }

}
