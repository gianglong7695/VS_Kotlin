package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemNewsLoadingBinding
import com.vtv.sports.databinding.ItemVideoSimpleBinding
import com.vtv.sports.model.video.VideoBox
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 12/26/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class VideoItemAdapter(c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_SIMPLE = 2
    private val TYPE_LOADING = 3

    private var listNews: MutableList<VideoBox> = mutableListOf()
    private var inflater: LayoutInflater = LayoutInflater.from(c)

    fun insertData(data: List<VideoBox>) {
        if (data != null) {
            val size = listNews.size
            listNews.addAll(data)
            notifyItemRangeChanged(size, listNews.size)
        }
    }

    fun setData(data: List<VideoBox>) {
        if (data != null) {
            listNews = data.toMutableList()
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SIMPLE -> {
                var binding: ItemVideoSimpleBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_video_simple, viewGroup, false)
                SimpleVH(binding)
            }
            else -> {
                var binding: ItemNewsLoadingBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_news_loading, viewGroup, false)
                LoadingVH(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (holder) {
            is SimpleVH -> holder.setData(listNews[pos])
            is LoadingVH -> holder.setData()
        }
    }


    override fun getItemViewType(position: Int): Int {
//        return if (listNews[position].isLoading) {
//            TYPE_LOADING
//        } else {
//            TYPE_SIMPLE
//        }

        return TYPE_SIMPLE
    }


    inner class SimpleVH(binding: ItemVideoSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemVideoSimpleBinding = binding

        fun setData(video: VideoBox) {
            if (video != null) {
                Glide.with(binding.root.context).load(video.avatar).into(binding.imgAvatar)
                binding.textTitle.text = video.name
                binding.textDate.text = TimeDateUtils.convertToDate(video.distributionDate)
            }

            binding.root.setOnClickListener {
                //                val listNewsGson = Gson().toJson(listNews)
//                val intent = NewsDetailActivity.newIntent(binding.root.context, listNewsGson, adapterPosition)
//                binding.root.context.startActivity(intent)
            }

        }
    }


    class LoadingVH(binding: ItemNewsLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsLoadingBinding = binding

        fun setData() {
            Glide
                    .with(binding.root.context)
                    .load(R.drawable.img_loading)
//                .apply(RequestOptions().override(150, 100))
                    .into(binding.imgLoading)
        }
    }

}