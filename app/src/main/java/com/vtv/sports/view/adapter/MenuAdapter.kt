package com.vtv.sports.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vtv.sports.R
import com.vtv.sports.databinding.ItemMenuBinding
import com.vtv.sports.model.menu.MenuLeft
import com.vtv.sports.util.FragmentUtil
import com.vtv.sports.util.ToastUtil
import com.vtv.sports.view.fragment.CategoryFragment
import com.vtv.sports.view.listener.IFragmentCallBack

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class MenuAdapter(c: Context, data: List<MenuLeft>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var data: List<MenuLeft> = data
    private var inflater: LayoutInflater = LayoutInflater.from(c)
    private val iFragmentCallBack: IFragmentCallBack = c as IFragmentCallBack
    private var onClick: View.OnClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): ViewHolder {
        var binding: ItemMenuBinding = DataBindingUtil.inflate(inflater, R.layout.item_menu, viewGroup, false)
        val holder = ViewHolder(binding)

        holder.itemView.setOnClickListener {
            if (onClick != null) onClick?.onClick(it)
            val item = data[holder.adapterPosition]
            when (data[holder.adapterPosition].zoneId) {
                393, 394, 395, 396, 397 -> {
                    iFragmentCallBack.onAddFragment(
                        CategoryFragment.newInstance(
                            item.zoneId.toString(),
                            item.zoneName
                        ), FragmentUtil.SLIDE_RIGHT
                    )
                }

                else -> {
                    ToastUtil(binding.root.context, item.zoneName)
                }
            }
        }
        return holder
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.onClick = listener
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.setData(data[pos])
    }

    inner class ViewHolder(binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ItemMenuBinding = binding


        fun setData(item: MenuLeft) {
            if (item.zoneName != null)
                binding.textName.text = item.zoneName

            if (item.imageRes == 0) {
                var imgRes = when (item.zoneId) {
                    393 -> R.drawable.ic_menu_flag
                    394 -> R.drawable.ic_menu_earth
                    395 -> R.drawable.ic_menu_tennis
                    396 -> R.drawable.ic_menu_other
                    397 -> R.drawable.ic_menu_benle
                    else -> R.drawable.ic_arrow_forward_black_24dp
                }
                binding.imgIcon.setImageResource(imgRes)
            } else {
                binding.imgIcon.setImageResource(item.imageRes)
            }
        }
    }
}