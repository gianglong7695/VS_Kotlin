package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemVideoBinding
import com.vtv.sports.databinding.ItemVideoFooterBinding
import com.vtv.sports.databinding.ItemVideoHeaderBinding
import com.vtv.sports.model.video.VideoBox
import com.vtv.sports.model.video.VideoGroup
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Constant.Companion.TYPE_HEADER
import com.vtv.sports.util.Constant.Companion.TYPE_ITEM
import com.vtv.sports.util.FragmentUtil
import com.vtv.sports.util.TimeDateUtils
import com.vtv.sports.view.activity.MainActivity
import com.vtv.sports.view.fragment.MoreVideoFragment
import kotlinx.android.synthetic.main.item_video.view.*

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class VideoAdapter(c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val inflater: LayoutInflater = LayoutInflater.from(c)
    private var listVideo: List<VideoGroup> = listOf()

    fun setVideoList(listVideo: List<VideoGroup>) {
        this.listVideo = listVideo
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                var binding: ItemVideoHeaderBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_video_header, viewGroup, false)
                HeaderVH(binding)
            }
            TYPE_ITEM -> {
                var binding: ItemVideoBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_video, viewGroup, false)
                ItemVH(binding)
            }
            else -> {
                var binding: ItemVideoFooterBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_video_footer, viewGroup, false)
                FooterVH(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        (holder as? HeaderVH)?.setData(listVideo[pos])
        (holder as? ItemVH)?.setData(listVideo[pos])
        (holder as? FooterVH)?.setData(listVideo[pos])
    }

    override fun getItemViewType(position: Int): Int {
        return when (listVideo[position].type) {
            Constant.TYPE_HEADER -> Constant.TYPE_HEADER
            Constant.TYPE_ITEM -> Constant.TYPE_ITEM
            else -> Constant.TYPE_FOOTER
        }
    }


    inner class HeaderVH(binding: ItemVideoHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoHeaderBinding = binding


        init {
            binding.root.setOnClickListener {

            }
        }

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
        lateinit var first: VideoBox
        lateinit var second: VideoBox

        init {
            binding.itemFirst.setOnClickListener {

            }

            binding.itemSecond.setOnClickListener {

            }
        }

        fun setData(video: VideoGroup) {
            first = video.listVideo[1]
            second = video.listVideo[2]

            Glide.with(binding.root.context).load(first.avatar).into(binding.itemFirst.img_avatar_first)
            binding.itemFirst.text_title_first.text = first.name
            binding.itemFirst.text_time_first.text = TimeDateUtils.convertToDate(first.distributionDate)

            Glide.with(binding.root.context).load(second.avatar).into(binding.itemSecond.img_avatar_second)
            binding.itemSecond.text_title_second.text = second.name
            binding.itemSecond.text_time_second.text = TimeDateUtils.convertToDate(second.distributionDate)

        }
    }


    class FooterVH(binding: ItemVideoFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: ItemVideoFooterBinding = binding
        lateinit var video: VideoGroup

        init {
            binding.itemFirst.setOnClickListener {

            }

            binding.itemSecond.setOnClickListener {

            }

            binding.moreVideo.setOnClickListener {
                (binding.root.context as MainActivity).onAddFragment(MoreVideoFragment.newInstance(video.videoZone.id.toString(), video.videoZone.name), FragmentUtil.SLIDE_RIGHT)
            }
        }

        fun setData(video: VideoGroup) {
            this.video = video
            val first = video.listVideo[3]
            val second = video.listVideo[4]

            Glide.with(binding.root.context).load(first.avatar).into(binding.itemFirst.img_avatar_first)
            binding.itemFirst.text_title_first.text = first.name
            binding.itemFirst.text_time_first.text = TimeDateUtils.convertToDate(first.distributionDate)

            Glide.with(binding.root.context).load(second.avatar).into(binding.itemSecond.img_avatar_second)
            binding.itemSecond.text_title_second.text = second.name
            binding.itemSecond.text_time_second.text = TimeDateUtils.convertToDate(second.distributionDate)

        }
    }

}