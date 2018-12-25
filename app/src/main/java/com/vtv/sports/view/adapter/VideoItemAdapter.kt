package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemVideoBinding
import com.vtv.sports.model.video.VideoBox
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class VideoItemAdapter(c: Context) : RecyclerView.Adapter<VideoItemAdapter.ItemVH>() {
    val inflater: LayoutInflater = LayoutInflater.from(c)
    var listVideo: List<VideoBox> = listOf()

    fun setVideoList(listVideo: List<VideoBox>) {
        this.listVideo = listVideo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ItemVH {
        var binding: ItemVideoBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_video, viewGroup, false)
        return ItemVH(binding)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: ItemVH, pos: Int) {
        holder.setData(listVideo[pos])
    }


    class ItemVH(binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoBinding = binding


        fun setData(video: VideoBox) {
            Glide.with(binding.root.context).load(video.avatar).into(binding.imgAvatar)
            binding.textTitle.text = video.name
            binding.textTime.text = TimeDateUtils.convertToDate(video.distributionDate)


            binding.root.setOnClickListener{

            }
        }
    }
}