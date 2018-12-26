package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemVideoBinding
import com.vtv.sports.databinding.ItemVideoHeaderBinding
import com.vtv.sports.model.video.VideoGroup
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class VideoAdapter(c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TYPE_HEADER = 1
    val TYPE_ITEM = 2
    val TYPE_FOOTER = 3

    val inflater: LayoutInflater = LayoutInflater.from(c)
    var listVideo: List<VideoGroup> = listOf()

    fun setVideoList(listVideo: List<VideoGroup>) {
        this.listVideo = listVideo
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            var binding: ItemVideoHeaderBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_video_header, viewGroup, false)
            return HeaderVH(binding)
        } else {
            var binding: ItemVideoBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_video, viewGroup, false)
            return ItemVH(binding)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if (holder is HeaderVH)
            holder.setData(listVideo[0])
//        if (holder is ItemVH)
//            holder.setData(listVideo[pos],)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_HEADER
        return TYPE_ITEM
    }


    inner class HeaderVH(binding: ItemVideoHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoHeaderBinding = binding

        fun setData(video: VideoGroup) {
            val first = video.listVideo[0]
            binding.textCategory.text = video.videoZone.name
            Glide.with(binding.root.context).load(first.avatar).into(binding.imgAvatar)
            binding.textTitle.text = first.name
            binding.textDate.text = TimeDateUtils.getScheduleSport(first.distributionDate)
            binding.textTime.text = TimeDateUtils.convertToDate(first.distributionDate)
            binding.textDuration.text = TimeDateUtils.convertToDuration(first.duration)
        }
    }


    class ItemVH(binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoBinding = binding


        fun setData(video: VideoGroup, pos: Int) {
            val first = video.listVideo[pos]
            val second = video.listVideo[2]
//            binding.textCategory.text = video.videoZone.name
//            Glide.with(binding.root.context).load(first.avatar).into(binding.imgAvatar)
//            binding.textTitle.text = first.name
//            binding.textDate.text = TimeDateUtils.getScheduleSport(first.distributionDate)
//            binding.textTime.text = TimeDateUtils.convertToDate(first.distributionDate)
//            binding.textDuration.text = TimeDateUtils.convertToDuration(first.duration)
        }
    }

}