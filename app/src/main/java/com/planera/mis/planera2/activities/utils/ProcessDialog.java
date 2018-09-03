package com.planera.mis.planera2.activities.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.planera.mis.planera2.R;


public class ProcessDialog {

    private Dialog progress;

    public void showDialog(Context context, boolean isCancellable) {

        progress = new Dialog(context);
        progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progress.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.setContentView(R.layout.loader_dialog);
        progress.setCancelable(isCancellable);
        progress.setCanceledOnTouchOutside(isCancellable);
//        ImageView imageView = (ImageView) progress.findViewById(R.id.spinnerImageView);
//        AnimationDrawable spinner = (AnimationDrawable) imageView
//                .getBackground();
//        spinner.start();
        if (!progress.isShowing())
            progress.show();

    }

    public void dismissDialog() {
        try {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
        } catch (IllegalArgumentException e) {
            // Do nothing.
        } catch (Exception e) {
            // Do nothing.
        } finally {
            progress = null;
        }
    }
}