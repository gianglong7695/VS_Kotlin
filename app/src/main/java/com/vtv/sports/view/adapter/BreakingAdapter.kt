package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemNewsHeaderBinding
import com.vtv.sports.databinding.ItemNewsLoadingBinding
import com.vtv.sports.databinding.ItemNewsSimpleBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.Constant.Companion.TYPE_HEADER
import com.vtv.sports.util.TimeDateUtils
import com.vtv.sports.view.activity.NewsDetailActivity

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class BreakingAdapter(c: Context) : RecyclerView.Adapter<BreakingAdapter.SimpleVH>() {
    private var listNews: MutableList<News> = mutableListOf()
    private var inflater: LayoutInflater = LayoutInflater.from(c)

    fun insertData(data: List<News>) {
        if (data != null) {
            val size = listNews.size
            listNews.addAll(data)
            if (data != listNews) {
                notifyItemRangeChanged(size, listNews.size)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SimpleVH {
        var binding: ItemNewsSimpleBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_news_simple, viewGroup, false)
        return SimpleVH(binding)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: SimpleVH, pos: Int) {
        holder.setData(listNews[pos])
    }


    inner class SimpleVH(binding: ItemNewsSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemNewsSimpleBinding = binding

        fun setData(news: News) {
            if (news != null) {
                Glide.with(binding.root.context).load(news.avatar).apply(RequestOptions.centerCropTransform())
                    .into(binding.imgAvatar)
                binding.textTitle.text = news.title
                binding.textDate.text = TimeDateUtils.convertToDate(news.publishDate)

                if(TextUtils.isEmpty(news.avatar5)){
                    binding.imgPlay.visibility = View.GONE
                }else{
                    binding.imgPlay.visibility = View.VISIBLE
                }
            }

            binding.root.setOnClickListener {
                //                val listNewsGson = Gson().toJson(listNews)
//                val intent = NewsDetailActivity.newIntent(binding.root.context, listNewsGson, adapterPosition)
//                binding.root.context.startActivity(intent)
            }

        }
    }

}