package com.wizimatic.appwebber.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.activity.PostDetailsActivity;
import com.wizimatic.appwebber.api.models.posts.post.Post;
import com.wizimatic.appwebber.listeners.ListItemClickListener;
import com.wizimatic.appwebber.models.SelectableCategoryModel;
import com.wizimatic.appwebber.utility.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAIF-MCC on 11/21/2017.
 */

public class HomeSelectableCategoryAdapter extends RecyclerView.Adapter<HomeSelectableCategoryAdapter.ViewHolder> {

    private final ArrayList<SelectableCategoryModel> selectableCategoryList;
    private final List<List<Post>> categoryWisePostList;
    private final Context context;
    private final AppCompatActivity activity;
    private ListItemClickListener itemClickListener;

    public HomeSelectableCategoryAdapter(Context context, AppCompatActivity activity, ArrayList<SelectableCategoryModel> selectableCategoryList, List<List<Post>> categoryWisePostList) {
        this.context = context;
        this.activity = activity;
        this.selectableCategoryList = selectableCategoryList;
        this.categoryWisePostList = categoryWisePostList;
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public HomeSelectableCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selectable_cat_first_list, parent, false);
        return new HomeSelectableCategoryAdapter.ViewHolder(view, viewType, itemClickListener);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvCategoryTitle;
        private final TextView tvSeeMore;
        private final RecyclerView rvCatWisePosts;
        private final ListItemClickListener itemClickListener;

        private final HomeSelectableCatWisePostAdapter catWisePostAdapter;
        private final ArrayList<Post> posts;

        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            tvCategoryTitle = (TextView) itemView.findViewById(R.id.tv_category_title);
            tvSeeMore = (TextView) itemView.findViewById(R.id.txt_see_more_category);
            rvCatWisePosts = (RecyclerView) itemView.findViewById(R.id.rvSelectableCategories);

            posts = new ArrayList<>();
            catWisePostAdapter = new HomeSelectableCatWisePostAdapter(context, posts);
            rvCatWisePosts.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            rvCatWisePosts.setAdapter(catWisePostAdapter);

            // Implement click listener over layout
            catWisePostAdapter.setItemClickListener(new ListItemClickListener() {
                @Override
                public void onItemClick(int position, View view) {
                    int clickedPostId = posts.get(position).getID().intValue();
                    switch (view.getId()) {
                        case R.id.card_view_top:
                            ActivityUtils.getInstance().invokePostDetails(activity, PostDetailsActivity.class, clickedPostId, false);
                            break;
                        default:
                            break;
                    }
                }
            });

            // Implement click listener over views that we need
            tvSeeMore.setOnClickListener(this);

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
        return (null != selectableCategoryList ? selectableCategoryList.size() : 0);

    }

    @Override
    public void onBindViewHolder(HomeSelectableCategoryAdapter.ViewHolder mainHolder, int position) {
        final SelectableCategoryModel model = selectableCategoryList.get(position);


        // setting data over views
        mainHolder.tvCategoryTitle.setText(Html.fromHtml(model.getCategoryName()));


        //bind the horizontal post list for each category
        mainHolder.posts.clear();
        if (!categoryWisePostList.isEmpty()) {
            mainHolder.posts.addAll(categoryWisePostList.get(position));
        }
        mainHolder.catWisePostAdapter.notifyDataSetChanged();

    }

}
