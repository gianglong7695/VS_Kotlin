package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemNewsHeaderBinding
import com.vtv.sports.databinding.ItemNewsSimpleBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.TimeDateUtils
import com.vtv.sports.util.ToastUtil

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class NewsAdapter(c: Context, listNews: List<News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 1
    private val TYPE_SIMPLE = 2
    private val TYPE_LOADING = 3
    private var listNews: MutableList<News>
    private var inflater: LayoutInflater
    private var isHeaderEnable = true

    init {
        this.listNews = listNews.toMutableList()
        this.inflater = LayoutInflater.from(c)
    }

    fun insertData(data: List<News>){
        if(data != null){
            val size = listNews.size
            listNews.addAll(data)
            notifyItemRangeChanged(size, listNews.size)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            var binding: ItemNewsHeaderBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_news_header, viewGroup, false)
            return HeaderVH(binding)
        } else {
            var binding: ItemNewsSimpleBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_news_simple, viewGroup, false)
            return SimpleVH(binding)
        }
    }

    override fun getItemCount(): Int {
        if (listNews != null)
            return listNews.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if (holder is HeaderVH) {
            holder.setData(listNews.get(pos))
        } else if (holder is SimpleVH) {
            holder.setData(listNews.get(pos))
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0 && isHeaderEnable) {
            return TYPE_HEADER
        } else {
            return TYPE_SIMPLE
        }
    }


    class HeaderVH(binding: ItemNewsHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsHeaderBinding

        init {
            this.binding = binding

        }

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
                binding.textSapo.text = news.sapo
            }

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    ToastUtil.show(binding.root.context, news.title)
                }
            })

        }
    }


    class SimpleVH(binding: ItemNewsSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsSimpleBinding

        init {
            this.binding = binding

        }

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.distributionDate)
            }

            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    ToastUtil.show(binding.root.context, news.title)
                }
            })

        }
    }
}