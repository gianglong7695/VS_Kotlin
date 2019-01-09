package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentCategoryBinding
import com.vtv.sports.model.breakingnews.BreakingRespone
import com.vtv.sports.model.news.News
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.activity.MainActivity
import com.vtv.sports.view.adapter.BreakingAdapter
import com.vtv.sports.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class BreakingNewsFragment : BaseFragment() {

    private var zoneId: String = ""
    lateinit var binding: FragmentCategoryBinding
    private var newsList: List<News>? = listOf()
    private lateinit var adapter: BreakingAdapter
    private var title = ""

    companion object {
        fun newInstance(zoneId: String, title: String): BreakingNewsFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            args.putSerializable(Constant.KEY_TITLE, title)
            val fragment = BreakingNewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_category
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentCategoryBinding


        binding.layoutToolbar.img_back.setOnClickListener {
            (binding.root.context as MainActivity).onBackPressed()
        }

        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(context!!, R.color.red),
            ContextCompat.getColor(context!!, R.color.green),
            ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener {
            fetchBreakingNews()
        }

        binding.layoutBreaking.visibility = View.VISIBLE
        binding.recyclerCategory.layoutManager = Utils.getLayoutManagerVer(context!!)
        adapter = BreakingAdapter(context!!)
        binding.recyclerCategory.adapter = adapter
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        title = arguments!!.getString(Constant.KEY_TITLE)
        binding.layoutToolbar.text_title.text = title

        fetchBreakingNews()
    }


    private fun fetchBreakingNews() {
        showRefresh()
        val call = BaseService.getService().getBreakingNews(ApiConstant.SECRET_KEY)
        call.enqueue(object : Callback<BreakingRespone> {
            override fun onResponse(call: Call<BreakingRespone>, response: Response<BreakingRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.notify != null) {
                    newsList = response.body()!!.notify
                    adapter.insertData(newsList!!)
                }
            }

            override fun onFailure(call: Call<BreakingRespone>, t: Throwable) {
                hideRefresh()
                Logs.e(t.toString())
            }
        })
    }


    private fun showRefresh() {
        if (!binding.swipeRefresh.isRefreshing) 5
        binding.swipeRefresh.isRefreshing = true
    }

    private fun hideRefresh() {
        Utils.after(Constant.DELAY_REFRESH_DEFAULT) {
            binding.swipeRefresh.isRefreshing = false
        }
    }


}
