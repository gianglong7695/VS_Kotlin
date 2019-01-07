package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentNewsZoneBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.model.news.NewsRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class LiveScoreZoneFragment : BaseFragment() {
    private var zoneId: String = ""
    lateinit var binding: FragmentNewsZoneBinding
    private var newsList: List<News>? = listOf()
    private lateinit var newsAdapter: NewsAdapter
    private var pageIndex = 2
    private var isLoadMore = false

    companion object {
        fun newInstance(zoneId: String): LiveScoreZoneFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            val fragment = LiveScoreZoneFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_news_zone
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentNewsZoneBinding
        binding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(context!!, R.color.red),
                ContextCompat.getColor(context!!, R.color.green),
                ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener { fetchData(zoneId) }

        binding.recyclerNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.recyclerNews.canScrollVertically(1)) {
                    if (!isLoadMore) {
                        isLoadMore = true
                        fetchDataWithPaging()
                    }
                }
            }
        })


        binding.recyclerNews.layoutManager = Utils.getLayoutManagerVer(context!!)
        newsAdapter = NewsAdapter(context!!)
        binding.recyclerNews.adapter = newsAdapter
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        fetchData(zoneId)
    }


    private fun fetchData(id: String) {
        showRefresh()
        val call = BaseService.getService().getNewsZone(ApiConstant.SECRET_KEY, id)
        call.enqueue(object : Callback<NewsRespone> {
            override fun onResponse(call: Call<NewsRespone>, response: Response<NewsRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.news != null) {
                    newsList = response.body()!!.news
                    newsAdapter.insertData(newsList!!)
                }
            }

            override fun onFailure(call: Call<NewsRespone>, t: Throwable) {
                hideRefresh()
                Logs.e(t.toString())
            }
        })
    }


    private fun fetchDataWithPaging() {
        val call = BaseService.getService().getNewsZonePaging(ApiConstant.SECRET_KEY, zoneId, pageIndex.toString())
        call.enqueue(object : Callback<NewsRespone> {
            override fun onResponse(call: Call<NewsRespone>, response: Response<NewsRespone>) {
                if (response.isSuccessful && response.body()?.news != null) {
                    if (newsAdapter != null && isLoadMore) {
                        pageIndex++
                        newsAdapter.insertData(response.body()!!.news)
                    }
                }

                isLoadMore = false
            }

            override fun onFailure(call: Call<NewsRespone>, t: Throwable) {
                Logs.e(t.toString())
                isLoadMore = false
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

