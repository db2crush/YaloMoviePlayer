package com.example.erunn.yalomovieplayer;

/**
 * Created by erunn on 2017-09-28.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.erunn.yalomovieplayer.R;

import java.io.ByteArrayOutputStream;

public class DetectEdgesActivity extends BaseActivity {
    SharedPrefUtil sharedPrefUtil;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.image_view)
    ImageView imageView;

    @Bind(R.id.detect_edges_image_view)
    ImageView detectEdgesImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detect_edges);
        ButterKnife.bind(this);
        ActivityHelper.setupToolbar(this, toolbar);

        sharedPrefUtil = new SharedPrefUtil(DetectEdgesActivity.this);

        //
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("bitmap");
        detectEdges(bitmap);
        final ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override

            public void run() {
                String check, inner;
                pb.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetectEdgesActivity.this);
                switch (sharedPrefUtil.getSharedTest()){
                    case "korean":
                        alertDialogBuilder
                                .setMessage(R.string.edgeInner)
                                .setCancelable(false)
                                .setPositiveButton(R.string.edgeCheck,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        });
                        break;
                    case "english":
                        alertDialogBuilder
                                .setMessage(R.string.eedgeInner)
                                .setCancelable(false)
                                .setPositiveButton(R.string.eedgeCheck,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        });
                        break;
                    case "china":
                        alertDialogBuilder
                                .setMessage(R.string.cedgeInner)
                                .setCancelable(false)
                                .setPositiveButton(R.string.cedgeCheck,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        });
                        break;
                }
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();

            }
        },2500);

//
//        Uri path = getIntent().getExtras().getParcelable(KEY_BITMAP);
//        Log.d("path ", path+"");
//        try {
//            detectEdges(BitmapHelper.readBitmapFromPath(this, path));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void detectEdges(Bitmap bitmap) {
        Mat rgba = new Mat();
        Utils.bitmapToMat(bitmap, rgba);

        Mat edges = new Mat(rgba.size(), CvType.CV_8UC1);
        Imgproc.cvtColor(rgba, edges, Imgproc.COLOR_RGB2GRAY, 4);
        Imgproc.Canny(edges, edges, 80, 100);

        // Don't do that at home or work it's for visualization purpose.
        BitmapHelper.showBitmap(this, bitmap, imageView);
        Bitmap resultBitmap = Bitmap.createBitmap(edges.cols(), edges.rows(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        byte[] byteArray = stream.toByteArray();
        Log.d("bitmap", byteArray+"");
        Log.d("bitmap", edges.cols()+"");
        Log.d("bitmap", edges.rows()+"");
        Log.d("bitmap", resultBitmap+"");
        Utils.matToBitmap(edges, resultBitmap);
        BitmapHelper.showBitmap(this, resultBitmap, detectEdgesImageView);
    }


}
