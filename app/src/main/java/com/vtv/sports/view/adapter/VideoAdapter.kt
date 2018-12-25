package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemNewsHeaderBinding
import com.vtv.sports.databinding.ItemVideoBoxBinding
import com.vtv.sports.model.video.VideoBox
import com.vtv.sports.model.video.VideoZone

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class VideoAdapter(c: Context, listVideo: List<VideoBox>, videoZone: VideoZone) : RecyclerView.Adapter<VideoAdapter.VideoVH>() {
    val listVideo: List<VideoBox> = listVideo
    val inflater: LayoutInflater = LayoutInflater.from(c)
    val videoZone: VideoZone = videoZone


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): VideoVH {
        var binding: ItemVideoBoxBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_video_box, viewGroup, false)
        return VideoVH(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: VideoVH, p1: Int) {
        holder.setData()
    }


    inner class VideoVH(binding: ItemVideoBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoBoxBinding = binding

        fun setData() {
            val first = listVideo[0]
            Glide.with(binding.root.context).load(first.avatar).into(binding.imgAvatar)
        }
    }

}