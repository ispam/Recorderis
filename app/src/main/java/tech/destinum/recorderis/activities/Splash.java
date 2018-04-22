package tech.destinum.recorderis.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import tech.destinum.recorderis.R;

public class Splash extends AppCompatActivity implements MediaPlayer.OnErrorListener{

    private VideoView video_view;
    private static final String TAG = Splash.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        video_view =findViewById(R.id.video_view);

        try {

            Uri path = Uri.parse("android.resource://"+ getPackageName()+"/"+ R.raw.splash_screen);
            video_view.setVideoURI(path);

            video_view.setOnCompletionListener(mp -> {

                Intent intent = new Intent(Splash.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            });

            video_view.start();

            video_view.setOnErrorListener(this);

        } catch (Exception e){

            Log.d(TAG, "Paila tuki tuki");
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        switch (what){
            case 100:
                video_view.stopPlayback();
                Intent inn = new Intent(Splash.this,Login.class);
                startActivity(inn);

                break;
            case 1:
                Log.i("My Error ", "handled here");
                video_view.stopPlayback();
                Intent inn2 = new Intent(Splash.this,Login.class);
                startActivity(inn2);

                break;
            case 800:
                video_view.stopPlayback();
                Intent inn3 = new Intent(Splash.this,Login.class);
                startActivity(inn3);

                break;
            case 701:
                video_view.stopPlayback();
                Intent inn4 = new Intent(Splash.this,Login.class);
                startActivity(inn4);

                break;
            case 700:
                video_view.stopPlayback();

                Toast.makeText(getApplicationContext(), "Bad Media format ", Toast.LENGTH_SHORT).show();
                Intent inn5 = new Intent(Splash.this,Login.class);
                startActivity(inn5);

                break;
            case -38:
                video_view.stopPlayback();
                Intent inn6 = new Intent(Splash.this,Login.class);
                startActivity(inn6);

                break;
        }
        return false;
    }

}
