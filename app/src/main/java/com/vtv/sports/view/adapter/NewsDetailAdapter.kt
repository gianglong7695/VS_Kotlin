package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemDetailHeaderBinding
import com.vtv.sports.databinding.ItemDetailWebviewBinding
import com.vtv.sports.databinding.ItemNewsSimpleBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.TimeDateUtils
import com.vtv.sports.util.Utils

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
    private lateinit var lastestNews: List<News>

    init {
        this.news = news
        inflater = LayoutInflater.from(c)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                var binding: ItemDetailHeaderBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_detail_header, viewGroup, false)
                HeaderVH(binding)
            }
            TYPE_WEBVIEW -> {
                var binding: ItemDetailWebviewBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_detail_webview, viewGroup, false)
                WebViewVH(binding)
            }

            else -> {
                var binding: ItemNewsSimpleBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_news_simple, viewGroup, false)
                ItemVH(binding)
            }
        }

    }

    fun updateData(data: News, lastestNews: List<News>) {
//        this.news = data
//        this.lastestNews = las
//        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (lastestNews != null) {
            return lastestNews.size + 2
        }
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if (holder is HeaderVH) {
            holder.setData(news)
        }

        if (holder is WebViewVH) {
            holder.setData(news.url)
        }

        if (holder is ItemVH) {
            holder.setData(news)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            1 -> TYPE_WEBVIEW
            else -> TYPE_NEWS_ITEM
        }
    }


    class HeaderVH(binding: ItemDetailHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemDetailHeaderBinding = binding

        fun setData(news: News) {
            Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
            binding.textTitle.text = news.title
            binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
            binding.textSapo.text = news.sapo
            binding.textAuthor.text = news.author
            binding.textShareCount.text = Utils.getCounter(news.shareCount.toLong())
//            binding.textCommentCount.text = Utils.getCounter(news.commentCount.toLong())
            binding.textCommentCount.text = Utils.getCounter(1500)
        }
    }

    class WebViewVH(binding: ItemDetailWebviewBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemDetailWebviewBinding = binding

        fun setData(url: String) {
//            Logs.e("Webview load url: http://$url")
//            binding.webView.loadUrl("http://$url")
        }
    }


    class ItemVH(binding: ItemNewsSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsSimpleBinding = binding

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
            }

            binding.root.setOnClickListener {
                //                val listNewsGson = Gson().toJson(listNews)
//                val intent = NewsDetailActivity.newIntent(binding.root.context, listNewsGson, adapterPosition)
//                binding.root.context.startActivity(intent)
            }
        }
    }
}