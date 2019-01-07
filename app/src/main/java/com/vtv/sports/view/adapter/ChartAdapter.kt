package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemChartBinding
import com.vtv.sports.model.chart.Standing

/**
 * Created by Giang Long on 1/6/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ChartAdapter(c: Context, listScore: List<Standing>) : RecyclerView.Adapter<ChartAdapter.ViewHolder>() {
    private val data = listScore
    private val inflater: LayoutInflater = LayoutInflater.from(c)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var binding: ItemChartBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_chart, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.setData(data[pos])
    }


    class ViewHolder(binding: ItemChartBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ItemChartBinding = binding

        fun setData(item: Standing) {
            if (item == null) return

            binding.textNum.text = (adapterPosition + 1).toString()
            binding.textName.text = item.team
            binding.textMatch.text = item.match.toString()
            binding.textDiff.text = item.diff.toString()
            binding.textScore.text = item.scrope.toString()
        }
    }
}