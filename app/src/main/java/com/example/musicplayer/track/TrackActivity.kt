package com.example.musicplayer.track

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.nowplaying.NowPlayingActivity

class TrackActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ALBUM = "extra_album"
    }

    private lateinit var model: TrackModel
    private lateinit var adapter: TrackAdapter
    private lateinit var presenter: TrackPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        val albumId = intent.getIntExtra(EXTRA_ALBUM, -1)
        model = TrackModel(this, albumId)
        presenter = TrackPresenter(this, model)
        adapter = TrackAdapter(this, model, presenter)

        presenter.onCreate()

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun gotoNowPlayingActivity() {
        val intent = Intent(this, NowPlayingActivity::class.java)
        startActivity(intent)
    }
}