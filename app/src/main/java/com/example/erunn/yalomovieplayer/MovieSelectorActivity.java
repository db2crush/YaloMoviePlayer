package com.example.erunn.yalomovieplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by erunn on 2017-09-26.
 */

public class MovieSelectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movieselector);

        Button yaloButton = (Button)findViewById(R.id.button);
        Button movieYalo = (Button)findViewById(R.id.button);
        Button movieKiller = (Button)findViewById(R.id.button2);
        Button movieKings = (Button)findViewById(R.id.button3);

        TextView movieTitle = (TextView)findViewById(R.id.yaloMovieThe);

        yaloButton.setOnClickListener(listener);

        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(MovieSelectorActivity.this);
        Log.d("shared",sharedPrefUtil.getSharedTest());

        switch (sharedPrefUtil.getSharedTest()){
            case "korean" :
                movieTitle.setText(R.string.movieTitle);
                movieYalo.setText(R.string.movieYalo);
                movieKiller.setText(R.string.movieKiller);
                movieKings.setText(R.string.movieKingsman);
                break;
            case "english" :
                movieTitle.setText(R.string.emovieTitle);
                movieYalo.setText(R.string.emovieYalo);
                movieKiller.setText(R.string.emovieKiller);
                movieKings.setText(R.string.emovieKingsman);
                break;
            case "china" :
                movieTitle.setText(R.string.cmovieTitle);
                movieYalo.setText(R.string.cmovieYalo);
                movieKiller.setText(R.string.cmovieKiller);
                movieKings.setText(R.string.cmovieKingsman);
                break;
        }

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.button :
                    Intent toTime = new Intent(MovieSelectorActivity.this, TimeSelectorActivity.class);
                    startActivity(toTime);
                    break;

                default:
                    break;
            }
        }
    };
}