package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemLiveScoreBinding
import com.vtv.sports.model.score.Schedule
import com.vtv.sports.util.TimeDateUtils

/**
 * Created by Giang Long on 1/6/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class LiveScoreAdapter(c: Context, listScore: List<Schedule>) : RecyclerView.Adapter<LiveScoreAdapter.ViewHolder>() {
    private val data = listScore
    private val inflater: LayoutInflater = LayoutInflater.from(c)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var binding: ItemLiveScoreBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_live_score, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.setData(data[pos])
    }


    class ViewHolder(binding: ItemLiveScoreBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ItemLiveScoreBinding = binding

        fun setData(item: Schedule) {
            if (item == null) return
            Glide.with(binding.root.context).load(item.homeLogo).into(binding.imgPlayerA)
            binding.textPlayerA.text = item.homeName

            Glide.with(binding.root.context).load(item.awayLogo).into(binding.imgPlayerB)
            binding.textPlayerB.text = item.awayName

            binding.textScore.text = "${item.homeScore} - ${item.awayScore}"
            binding.textDateTime.text = TimeDateUtils.getScheduleSport(item.date)
        }
    }
}