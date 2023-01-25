package com.wizimatic.appwebber.utility;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.activity.NotificationDetailsActivity;
import com.wizimatic.appwebber.api.models.category.Category;
import com.wizimatic.appwebber.api.models.menus.SubMenuItem;
import com.wizimatic.appwebber.api.models.posts.post.CommentsAndReplies;
import com.wizimatic.appwebber.data.constant.AppConstant;

import java.util.ArrayList;


/**
 * Created by Ashiq on 5/11/16.
 */
public class ActivityUtils {

    private static ActivityUtils sActivityUtils = null;

    public static ActivityUtils getInstance() {
        if (sActivityUtils == null) {
            sActivityUtils = new ActivityUtils();
        }
        return sActivityUtils;
    }

    public void invokeActivity(AppCompatActivity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeSubCategoryList(AppCompatActivity activity, Class<?> tClass, ArrayList<Category> allCategoryList, ArrayList<Category> allSubCategoryList, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_CATEGORY_LIST, (ArrayList) allCategoryList);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_SUB_CATEGORY_LIST, (ArrayList) allSubCategoryList);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeSubMenuList(AppCompatActivity activity, Class<?> tClass, ArrayList<Category> allCategoryList, int clickedPosition, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_CATEGORY_LIST, (ArrayList) allCategoryList);
        intent.putExtra(AppConstant.BUNDLE_KEY_MENU_ID, clickedPosition);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }


    public void invokeSubSubMenuList(AppCompatActivity activity, Class<?> tClass, ArrayList<Category> allCategoryList, SubMenuItem clickedSubMenu, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_CATEGORY_LIST, (ArrayList) allCategoryList);
        intent.putExtra(AppConstant.BUNDLE_KEY_SUB_MENU, clickedSubMenu);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokePostDetails(AppCompatActivity activity, Class<?> tClass, int clickedPosition, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstant.BUNDLE_KEY_POST_ID, clickedPosition);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokePostDetailsForAppLink(AppCompatActivity activity, Class<?> tClass, int clickedPosition, boolean fromAppLink, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstant.BUNDLE_KEY_POST_ID, clickedPosition);
        intent.putExtra(AppConstant.BUNDLE_FROM_APP_LINK, fromAppLink);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCustomPostAndLink(AppCompatActivity activity, Class<?> tClass, String pageTitle, String pageUrl, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstant.BUNDLE_KEY_TITLE, pageTitle);
        intent.putExtra(AppConstant.BUNDLE_KEY_URL, pageUrl);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }


    public void invokeCommentList(AppCompatActivity activity, Class<?> tClass, ArrayList<CommentsAndReplies> allComment, ArrayList<CommentsAndReplies> allZeroParentComment, int clickedPostId, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_ALL_COMMENT, allComment);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_ALL_ZERO_PARENT_COMMENT, allZeroParentComment);
        intent.putExtra(AppConstant.BUNDLE_KEY_POST_ID, clickedPostId);
        activity.startActivityForResult(intent, AppConstant.REQUEST_CODE_COMMENT);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCommentDetails(AppCompatActivity activity, Class<?> tClass, ArrayList<CommentsAndReplies> allComment, int clickedPostId, CommentsAndReplies clickedComment, boolean shouldDialogOpen, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putParcelableArrayListExtra(AppConstant.BUNDLE_KEY_ALL_COMMENT, allComment);
        intent.putExtra(AppConstant.BUNDLE_KEY_POST_ID, clickedPostId);
        intent.putExtra(AppConstant.BUNDLE_KEY_CLICKED_COMMENT, clickedComment);
        intent.putExtra(AppConstant.BUNDLE_KEY_SHOULD_DIALOG_OPEN, shouldDialogOpen);
        activity.startActivityForResult(intent, AppConstant.REQUEST_CODE_COMMENT);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokePostList(AppCompatActivity activity, Class<?> tClass, int categoryId, String categoryTitle, boolean isFromFeatured, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstant.BUNDLE_KEY_CATEGORY_ID, categoryId);
        intent.putExtra(AppConstant.BUNDLE_KEY_CATEGORY_TITLE, categoryTitle);
        intent.putExtra(AppConstant.BUNDLE_KEY_FEATURED, isFromFeatured);
        activity.startActivityForResult(intent, AppConstant.REQUEST_CODE_COMMENT);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeNotificationDetails(AppCompatActivity activity, String title, String message, String postId){
        Intent intent = new Intent(activity, NotificationDetailsActivity.class);
        intent.putExtra(AppConstant.BUNDLE_KEY_TITLE, title);
        intent.putExtra(AppConstant.BUNDLE_KEY_MESSAGE, message);
        intent.putExtra(AppConstant.BUNDLE_KEY_POST_ID, postId);
        activity.startActivity(intent);
    }

    public void invokeLeftToRightActivityAnim(AppCompatActivity activity) {
        activity.overridePendingTransition(R.anim.slide_out_left, R.anim.slide_out_right);
    }

}
