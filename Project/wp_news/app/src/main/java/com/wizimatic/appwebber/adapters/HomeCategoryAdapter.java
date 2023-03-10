package com.wizimatic.appwebber.adapters;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.api.models.category.Category;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SAIF-MCC on 10/4/2017.
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private final ArrayList<Category> arrayList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public HomeCategoryAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_screen_category_list, parent, false);
        return new ViewHolder(view, viewType, itemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Button btnCategories;
        private final ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;

            // Find all views ids
            btnCategories = (Button) itemView.findViewById(R.id.category_item_button);

            // Implement click listener over views that we need
            btnCategories.setOnClickListener(this);

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
    public void onBindViewHolder(HomeCategoryAdapter.ViewHolder mainHolder, int position) {
        final Category model = arrayList.get(position);

        // setting data over views
        mainHolder.btnCategories.setText(Html.fromHtml(String.valueOf(model.getName())));
        Random rand = new Random();
        int i = rand.nextInt(5) + 1;


        switch (i) {
            case 1:
                mainHolder.btnCategories.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 2:
                mainHolder.btnCategories.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;

            case 3:
                mainHolder.btnCategories.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;

            case 4:
                mainHolder.btnCategories.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;

            case 5:
                mainHolder.btnCategories.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;

            default:
                break;
        }

    }

}
