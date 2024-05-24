package com.sinc.esigndemo2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                initializePlayer()
            } else {
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        playerView = findViewById(R.id.playerView)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            initializePlayer()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        // 로컬에 저장된 동영상 파일 경로 지정
        val filePath = Environment.getExternalStorageDirectory().path + "/Download/148288-793718093_small.mp4"
        val mediaItem = MediaItem.fromUri("file://$filePath")

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

/*import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView*/

 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout);

        //PlayerView = findViewById(R.id.playerView)

        val player = ExoPlayer.Builder(this).build();

        //playerView.player = player

        // 로컬에 저장된 동영상 파일 경로 지정
        val filePath = "file:///C:/Users/EMART-000000/Downloads/148288-793718093_small.mp4";

        // Build the media item.
        val mediaItem = MediaItem.fromUri(filePath)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()

    }*/

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        playerView = findViewById(R.id.playerView) // layout 파일에 PlayerView 추가 필요

        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        // 로컬에 저장된 동영상 파일 경로 지정
        val filePath = "file:///sdcard/Download/148288-793718093_small.mp4"

        // Build the media item.
        val mediaItem = MediaItem.fromUri(filePath)

        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }*/
}