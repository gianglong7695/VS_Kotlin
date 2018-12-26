package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemNewsHeaderBinding
import com.vtv.sports.databinding.ItemNewsLoadingBinding
import com.vtv.sports.databinding.ItemNewsSimpleBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.TimeDateUtils
import com.vtv.sports.view.activity.NewsDetailActivity

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class NewsAdapter(c: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 1
    private val TYPE_SIMPLE = 2
    private val TYPE_LOADING = 3

    private var listNews: MutableList<News> = mutableListOf()
    private var inflater: LayoutInflater = LayoutInflater.from(c)
    private var isHeaderEnable = true

    fun insertData(data: List<News>) {
        if (data != null) {
            val size = listNews.size
            listNews.addAll(data)
            if (data != listNews) {
                notifyItemRangeChanged(size, listNews.size)
            }
        }
    }


    fun setHeaderEnable(isHeader: Boolean){
        this.isHeaderEnable = isHeader
    }

    open fun showLoading() {
        var itemLoading: News = listNews[0]
        itemLoading.isLoading = true
        listNews.add(itemLoading)
        notifyItemChanged(listNews.size - 1)
    }


    open fun hideLoading() {
        var loadingPos = listNews.size - 1
        listNews.removeAt(listNews.size - 1)
//        notifyItemChanged(listNews.size - 1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_HEADER -> {
                var binding: ItemNewsHeaderBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_news_header, viewGroup, false)
                return HeaderVH(binding)
            }
            TYPE_SIMPLE -> {
                var binding: ItemNewsSimpleBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_news_simple, viewGroup, false)
                return SimpleVH(binding)
            }
            else -> {
                var binding: ItemNewsLoadingBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_news_loading, viewGroup, false)
                return LoadingVH(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (holder) {
            is HeaderVH -> holder.setData(listNews[pos])
            is SimpleVH -> holder.setData(listNews[pos])
            is LoadingVH -> holder.setData()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && isHeaderEnable) {
            TYPE_HEADER
        } else if (listNews[position].isLoading) {
            TYPE_LOADING
        } else {
            TYPE_SIMPLE
        }
    }


    inner class HeaderVH(binding: ItemNewsHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsHeaderBinding = binding

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
                binding.textSapo.text = news.sapo
            }

            binding.root.setOnClickListener{
                val listNewsGson = Gson().toJson(listNews)
                val intent = NewsDetailActivity.newIntent(binding.root.context, listNewsGson, adapterPosition)
                binding.root.context.startActivity(intent)
            }

        }
    }


    inner class SimpleVH(binding: ItemNewsSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsSimpleBinding = binding

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
            }

            binding.root.setOnClickListener {
                val listNewsGson = Gson().toJson(listNews)
                val intent = NewsDetailActivity.newIntent(binding.root.context, listNewsGson, adapterPosition)
                binding.root.context.startActivity(intent)
            }

        }
    }

    class LoadingVH(binding: ItemNewsLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsLoadingBinding = binding

        fun setData() {
            Glide.with(binding.root.context).asGif().load(R.drawable.img_loading).into(binding.imgLoading)
        }
    }
}