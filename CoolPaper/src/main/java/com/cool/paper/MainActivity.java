package com.cool.paper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;


public class MainActivity extends BaseActivity implements MyImageView.setonClick {
    LinearLayout widgetContainer;
    private MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_anim);
        widgetContainer = (LinearLayout) findViewById(R.id.widgetContainer);
        myImageView = (MyImageView) findViewById(R.id.img);
        myImageView.setListener(this);
    }

    public void SetPaper(View view) {
//        WallpaperManager manager = WallpaperManager.getInstance(this);
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SET_WALLPAPER)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SET_WALLPAPER}, 1);
//        } else {
//            try {
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//                }else{
//                    Bitmap bitmap = CompressHelper.getDefault(this).compressToBitmap(new File("/sdcard/Pictures/Mysplash/aaa.jpg"));
//                    manager.setBitmap(bitmap);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }
        changeTheme(this);
        reboot();
        showWidget();
    }

    private void showWidget() {
        TranslateAnimation show = new TranslateAnimation(
                0, 0,
                0, widgetContainer.getMeasuredHeight());
        show.setFillEnabled(true);
        show.setFillAfter(true);
        show.setDuration(200);
        widgetContainer.clearAnimation();
        widgetContainer.startAnimation(show);
    }

    private void hideWidget() {
        TranslateAnimation hide = new TranslateAnimation(
                0, 0,
                widgetContainer.getMeasuredHeight(), 0);
        hide.setFillEnabled(true);
        hide.setFillAfter(true);
        hide.setDuration(200);
        widgetContainer.clearAnimation();
        widgetContainer.startAnimation(hide);
    }

    private void reboot() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        int enter_anim = android.R.anim.fade_in;
        int exit_anim = android.R.anim.fade_out;
        startActivity(intent);
        Log.d("wwq", "reboot....");
        overridePendingTransition(enter_anim, exit_anim);
    }

    public static void changeTheme(Context c) {
        ThemeManager.getInstance(c)
                .setLightTheme(
                        c,
                        !ThemeManager.getInstance(c).isLightTheme());
    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }

    private boolean isshow = false;

    @Override
    public void onclick() {
        if (!isshow) {
            showWidget();
            isshow = true;
        } else {
            isshow = false;
            hideWidget();
        }
    }
}
