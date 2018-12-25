package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemVideoBoxBinding
import com.vtv.sports.model.video.VideoGroup
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class VideoAdapter(c: Context) : RecyclerView.Adapter<VideoAdapter.VideoVH>() {
    val inflater: LayoutInflater = LayoutInflater.from(c)
    var listVideo: List<VideoGroup> = listOf()

    fun setVideoList(listVideo: List<VideoGroup>) {
        this.listVideo = listVideo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): VideoVH {
        var binding: ItemVideoBoxBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_video_box, viewGroup, false)
        return VideoVH(binding)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: VideoVH, pos: Int) {
        holder.setData(listVideo[pos])
    }


    inner class VideoVH(binding: ItemVideoBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoBoxBinding = binding
//        var itemAdapter: VideoItemAdapter
//        lateinit var listItemVideo: List<VideoBox>


        init {
//            binding.recyclerVideo.layoutManager =
//                    GridLayoutManager(binding.root.context, 2, GridLayoutManager.VERTICAL, false)
//            binding.recyclerVideo.setHasFixedSize(true)
//            binding.recyclerVideo.isNestedScrollingEnabled = false
//            itemAdapter = VideoItemAdapter(binding.root.context)
//            binding.recyclerVideo.adapter = itemAdapter
        }

        fun setData(video: VideoGroup) {
            val first = video.listVideo[0]
            binding.textCategory.text = video.videoZone.name

            Glide.with(binding.root.context).load(first.avatar).into(binding.imgAvatar)
            binding.textTitle.text = first.name
            binding.textDate.text = TimeDateUtils.getScheduleSport(first.distributionDate)
            binding.textTime.text = TimeDateUtils.convertToDate(first.distributionDate)
            binding.textDuration.text = TimeDateUtils.convertToDuration(first.duration)

            //Item first
            Glide.with(binding.root.context).load(video.listVideo[1].avatar).into(binding.itemFirst.imgAvatar)
            binding.itemFirst.textTitle.text = video.listVideo[1].name
            binding.itemFirst.textTime.text = TimeDateUtils.convertToDate(video.listVideo[1].distributionDate)

            //Item second
            Glide.with(binding.root.context).load(video.listVideo[2].avatar).into(binding.itemSecond.imgAvatar)
            binding.itemSecond.textTitle.text = video.listVideo[2].name
            binding.itemSecond.textTime.text = TimeDateUtils.convertToDate(video.listVideo[2].distributionDate)

            //Item third
            Glide.with(binding.root.context).load(video.listVideo[3].avatar).into(binding.itemThird.imgAvatar)
            binding.itemThird.textTitle.text = video.listVideo[3].name
            binding.itemThird.textTime.text = TimeDateUtils.convertToDate(video.listVideo[3].distributionDate)

            //Item fourth
            Glide.with(binding.root.context).load(video.listVideo[4].avatar).into(binding.itemFourth.imgAvatar)
            binding.itemFourth.textTitle.text = video.listVideo[4].name
            binding.itemFourth.textTime.text = TimeDateUtils.convertToDate(video.listVideo[4].distributionDate)


//            listItemVideo = video.listVideo.subList(1, 5)
//            itemAdapter.setVideoList(listItemVideo)
        }
    }

}