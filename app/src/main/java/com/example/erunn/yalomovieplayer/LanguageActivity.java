package com.example.erunn.yalomovieplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by erunn on 2017-10-09.
 */

public class LanguageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_language);

        Button koreanButton = (Button)findViewById(R.id.koreanbutton);
        Button englishButton = (Button)findViewById(R.id.englishbutton);
        Button chinaButton = (Button)findViewById(R.id.chinabutton);

        koreanButton.setOnClickListener(listener);
        englishButton.setOnClickListener(listener);
        chinaButton.setOnClickListener(listener);

    }
    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.koreanbutton:
                    Intent toTime = new Intent(LanguageActivity.this, MovieSelectorActivity.class);
                    startActivity(toTime);
                    break;
                case R.id.englishbutton:
                    Intent toMovie = new Intent(LanguageActivity.this, MovieSelectorActivity.class);
                    startActivity(toMovie);
                    break;
                case R.id.chinabutton:
                    Intent toc = new Intent(LanguageActivity.this, MovieSelectorActivity.class);
                    startActivity(toc);
                    break;

                default:
                    break;
            }
        }
    };
}
