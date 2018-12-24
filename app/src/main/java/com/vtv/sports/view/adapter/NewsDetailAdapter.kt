package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemDetailHeaderBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 12/24/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class NewsDetailAdapter(c: Context, news: News) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 1
    private val TYPE_WEBVIEW = 2
    private val TYPE_NEWS_ITEM = 3
    private var inflater: LayoutInflater
    private var news: News

    init {
        this.news = news
        inflater = LayoutInflater.from(c)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if(viewType == TYPE_HEADER){
//
//        }
        var binding: ItemDetailHeaderBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_detail_header, viewGroup, false)
        return HeaderVH(binding)
    }

    fun updateData(data: News){
        this.news = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if(holder is HeaderVH){
            holder.setData(news)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_HEADER
    }


    class HeaderVH(binding: ItemDetailHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemDetailHeaderBinding

        init {
            this.binding = binding
        }

        fun setData(news: News) {
            Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
            binding.textTitle.text = news.title
            binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
            binding.textSapo.text = news.sapo
            binding.textAuthor.text = news.author
        }
    }
}