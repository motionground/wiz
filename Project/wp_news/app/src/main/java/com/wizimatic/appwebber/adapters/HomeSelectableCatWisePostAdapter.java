package com.wizimatic.appwebber.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.api.models.posts.post.Post;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;

/**
 * Created by SAIF-MCC on 11/21/2017.
 */

public class HomeSelectableCatWisePostAdapter extends RecyclerView.Adapter<HomeSelectableCatWisePostAdapter.ViewHolder> {

    private final ArrayList<Post> postList;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public HomeSelectableCatWisePostAdapter(Context context, ArrayList<Post> allPostList) {
        this.context = context;
        postList = allPostList;
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public HomeSelectableCatWisePostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selectable_cat_second_list, parent, false);
        return new HomeSelectableCatWisePostAdapter.ViewHolder(view, viewType, itemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgPost;
        private final TextView tvPostTitle;
        private final TextView tvPostCategory;
        private final TextView tvPostDate;
        private final CardView mCardView;
        private final ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            // Find all views ids
            imgPost = (ImageView) itemView.findViewById(R.id.post_img);
            tvPostTitle = (TextView) itemView.findViewById(R.id.title_text);
            tvPostCategory = (TextView) itemView.findViewById(R.id.post_category);
            tvPostDate = (TextView) itemView.findViewById(R.id.date_text);
            mCardView = (CardView) itemView.findViewById(R.id.card_view_top);

            mCardView.setOnClickListener(this);

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
        return (null != postList ? postList.size() : 0);

    }

    @Override
    public void onBindViewHolder(HomeSelectableCatWisePostAdapter.ViewHolder mainHolder, int position) {
        final Post model = postList.get(position);

        // setting data over views
        String title = model.getTitle().getRendered();
        mainHolder.tvPostTitle.setText(Html.fromHtml(title));


        String imgUrl = null;
        if (model.getEmbedded().getWpFeaturedMedias().size() > 0) {
            if (model.getEmbedded().getWpFeaturedMedias().get(0).getMediaDetails() != null) {
                if (model.getEmbedded().getWpFeaturedMedias().get(0).getMediaDetails().getSizes().getFullSize().getSourceUrl() != null) {
                    imgUrl = model.getEmbedded().getWpFeaturedMedias().get(0).getMediaDetails().getSizes().getFullSize().getSourceUrl();
                }
            }
        }

        if (imgUrl != null) {
            Glide.with(context)
                    .load(imgUrl)
                    .into(mainHolder.imgPost);
        } else {
            Glide.with(context)
                    .load(R.color.imgPlaceholder)
                    .into(mainHolder.imgPost);
        }

        String category = null;

        if (model.getEmbedded().getWpTerms().size() >= 1) {
            category = model.getEmbedded().getWpTerms().get(0).get(0).getName();
        }

        if (category == null) {
            category = context.getResources().getString(R.string.default_str);
        }
        mainHolder.tvPostCategory.setText(Html.fromHtml(category));
        mainHolder.tvPostDate.setText(model.getFormattedDate());

    }
}
