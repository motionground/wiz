package com.wizimatic.appwebber.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.api.models.posts.post.CommentsAndReplies;
import com.wizimatic.appwebber.data.constant.AppConstant;
import com.wizimatic.appwebber.listeners.ListItemClickListener;

import java.util.ArrayList;

/**
 * Created by SAIF-MCC on 10/9/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final ArrayList<CommentsAndReplies> commentList;
    private final ArrayList<CommentsAndReplies> commentsOfLatestParent;
    private final Context context;
    private ListItemClickListener itemClickListener;

    public CommentsAdapter(Context context, ArrayList<CommentsAndReplies> allComment, ArrayList<CommentsAndReplies> allCommentOfLatestParent) {
        this.context = context;
        this.commentList = allComment;
        this.commentsOfLatestParent = allCommentOfLatestParent;
    }

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false);
        return new ViewHolder(view, viewType, itemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgCommentAuthor;
        private final TextView tvAuthorName;
        private final TextView tvCommentDate;
        private final TextView tvCommentDetail;
        private final TextView tvReplyCount;
        private final TextView tvReplyPrompt;
        private final RelativeLayout rlyListItem;

        private final ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;

            // Find all views ids
            imgCommentAuthor = (ImageView) itemView.findViewById(R.id.comnt_author_img);
            tvAuthorName = (TextView) itemView.findViewById(R.id.comnt_author_name);
            tvCommentDate = (TextView) itemView.findViewById(R.id.comnt_date);
            tvCommentDetail = (TextView) itemView.findViewById(R.id.comnt_details);
            tvReplyCount = (TextView) itemView.findViewById(R.id.reply_count);
            tvReplyPrompt = (TextView) itemView.findViewById(R.id.reply_text);
            rlyListItem = (RelativeLayout) itemView.findViewById(R.id.list_item);

            // Implement click listener over views that we need
            tvReplyPrompt.setOnClickListener(this);
            rlyListItem.setOnClickListener(this);

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
        return (null != commentsOfLatestParent ? commentsOfLatestParent.size() : 0);

    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder mainHolder, int position) {
        final CommentsAndReplies model = commentsOfLatestParent.get(position);

        // setting data over views

        String imgUrl = null;
        if (model.getAuthorAvaterUrl().getSourceUrl() != null) {
            imgUrl = model.getAuthorAvaterUrl().getSourceUrl();
        }

        if (imgUrl != null) {
            Glide.with(context)
                    .load(imgUrl)
                    .into(mainHolder.imgCommentAuthor);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_user1)
                    .into(mainHolder.imgCommentAuthor);
        }


        mainHolder.tvAuthorName.setText(Html.fromHtml(model.getAuthorName()));


        mainHolder.tvCommentDate.setText(model.getFormattedDate());


        mainHolder.tvCommentDetail.setText(Html.fromHtml(model.getContent().getRendered()));

        int replyCount = 0;
        for (CommentsAndReplies commentsAndReplies : commentList) {
            if (model.getID().equals(commentsAndReplies.getParent())) {
                replyCount++;
            }
        }
        if (replyCount == 1) {
            mainHolder.tvReplyCount.setText(replyCount + AppConstant.EMPTY + context.getResources().getString(R.string.comment_reply));
        } else {
            mainHolder.tvReplyCount.setText(replyCount + AppConstant.EMPTY + context.getResources().getString(R.string.comment_replies));
        }
    }

}
