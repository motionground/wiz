package com.wizimatic.appwebber.webengine;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.wizimatic.appwebber.R;


/**
 * Created by Ashiq on 8/30/16.
 */
public class VideoView {

    private AlertDialog dialog;
    private FrameLayout videoLayout;
    private ProgressBar progressBar;

    private static VideoView videoView = null;

    public static VideoView getInstance() {
        if (videoView == null) {
            videoView = new VideoView();
        }
        return videoView;
    }


    public void show(final AppCompatActivity activity) {

        dismiss();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.DialogTheme);

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View promptsView = layoutInflater.inflate(R.layout.layout_video_view, null);
        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(true);

        videoLayout = (FrameLayout) promptsView.findViewById(R.id.videoView);
        progressBar = (ProgressBar) promptsView.findViewById(R.id.progressBar);


        dialog = alertDialogBuilder.create();

        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        View v = window.getDecorView();
        v.setBackgroundResource(android.R.color.black);

        if (Build.VERSION.SDK_INT < 19) {
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            v.setSystemUiVisibility(uiOptions);
        }

    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void setVideoLayout(View view) {
        if (videoLayout != null) {
            videoLayout.addView(view);
        }
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }


}
