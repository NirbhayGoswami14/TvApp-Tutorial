package com.ncode.test2tvapp.view.fragment

import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaControllerAdapter
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.media.PlayerAdapter
import com.ncode.test2tvapp.R

class PlayerFragment():VideoSupportFragment()
{
    private lateinit var transportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val host=VideoSupportFragmentGlueHost(this@PlayerFragment)
        val playerAdapter=MediaPlayerAdapter(requireContext())
        transportControlGlue= PlaybackTransportControlGlue(requireContext(),playerAdapter)
        transportControlGlue.host=host
        transportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource(Uri.parse("android.resource://"+requireActivity().packageName+"/"+ R.raw.tom_and_jerry))

    }

    override fun onPause() {
        super.onPause()
        transportControlGlue.pause()
    }
}