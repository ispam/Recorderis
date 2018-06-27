package tech.destinum.recorderis.activities

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.VideoView

import tech.destinum.recorderis.R

class Splash : AppCompatActivity(), MediaPlayer.OnErrorListener {

    private val video_view by lazy { findViewById(R.id.video_view) as VideoView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        try {

            val path = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_screen)
            video_view.setVideoURI(path)

            video_view.setOnCompletionListener { mp ->

                val intent = Intent(this@Splash, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()

            }

            video_view.start()

            video_view.setOnErrorListener(this)

        } catch (e: Exception) {

            Log.d(TAG, "Paila tuki tuki")
        }

    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        when (what) {
            100 -> {
                video_view.stopPlayback()
                val inn = Intent(this@Splash, Login::class.java)
                startActivity(inn)
            }
            1 -> {
                Log.i("My Error ", "handled here")
                video_view.stopPlayback()
                val inn2 = Intent(this@Splash, Login::class.java)
                startActivity(inn2)
            }
            800 -> {
                video_view.stopPlayback()
                val inn3 = Intent(this@Splash, Login::class.java)
                startActivity(inn3)
            }
            701 -> {
                video_view.stopPlayback()
                val inn4 = Intent(this@Splash, Login::class.java)
                startActivity(inn4)
            }
            700 -> {
                video_view.stopPlayback()

                Toast.makeText(applicationContext, "Bad Media format ", Toast.LENGTH_SHORT).show()
                val inn5 = Intent(this@Splash, Login::class.java)
                startActivity(inn5)
            }
            -38 -> {
                video_view.stopPlayback()
                val inn6 = Intent(this@Splash, Login::class.java)
                startActivity(inn6)
            }
        }
        return false
    }

    companion object {
        private val TAG = Splash::class.java.simpleName
    }

}
