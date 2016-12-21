package com.giou.bikerideview;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.Target;
import com.giou.bikerideview.utils.CollectionSortUtils;
import com.giou.bikerideview.view.MiniMapView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
//    private String objUrl = "ride/multi_person_minimap_cuanzang.obj";
//    private String objUrl = "ride/bike.obj";
    private String objUrl = "ride/bike.obj";
    private MiniMapView mMiniMapView;
    String url = "http://km1930.oss-cn-shanghai.aliyuncs.com/Gif/gif004.gif";
    protected static final int WHAT_INCREASE = 1;
    private int progress;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progress++;


            if(mAllPoints != null){

                float progressFloat = progress / 100.0f;
                int current = (int) (mSize * progressFloat);

                if(current > mSize){
                    return;
                }
                if(current == 0){
                    TranslateAnimation animation = new TranslateAnimation(
                            Animation.ABSOLUTE, 0,
                            Animation.ABSOLUTE, mAllPoints.get(current).x,
                            Animation.ABSOLUTE, 0,
                            Animation.ABSOLUTE, mAllPoints.get(current).y);
                    animation.setFillAfter(true);
                    animation.setDuration(1000);
                    mRider.startAnimation(animation);
                }else{
                    TranslateAnimation animation = new TranslateAnimation(
                            Animation.ABSOLUTE, mAllPoints.get(current-1).x,
                            Animation.ABSOLUTE, mAllPoints.get(current).x,
                            Animation.ABSOLUTE, mAllPoints.get(current-1).y,
                            Animation.ABSOLUTE, mAllPoints.get(current).y);
                    animation.setFillAfter(true);
                    animation.setDuration(1000);
                    mRider.startAnimation(animation);
                }



            }

            handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 200));
            if (progress >= 100) {
                handler.removeMessages(WHAT_INCREASE);
            }
        }
    };
    private ImageView mRider;
    private ArrayList<Point> mAllPoints;
    private int mSize;

    public int getRadomNumber(int start, int end) {
        return (int) (start + Math.random() * (end - start));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMiniMapView = (MiniMapView) findViewById(R.id.minimap_view);
        mRider = (ImageView) findViewById(R.id.iv_rider);

        Glide.with(this).load(R.drawable.aa).asGif().into(mRider);




        mMiniMapView.loadObjFile(objUrl);
        mAllPoints = mMiniMapView.getAllPoints();
        mAllPoints = CollectionSortUtils.byX(mAllPoints);

        if(mAllPoints != null){
            Log.d(TAG,"allPoints="+ mAllPoints.size());
            mSize = mAllPoints.size()-1;
        }

        Button click = (Button) findViewById(R.id.btn_click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();

            }
        });

    }

    private void increase() {
        progress = 0;
        handler.removeMessages(WHAT_INCREASE);
        handler.sendEmptyMessage(WHAT_INCREASE);
    }
}
