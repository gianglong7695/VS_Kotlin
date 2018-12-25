package com.vtv.sports.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vtv.sports.R
import com.vtv.sports.databinding.ActivityNewsDetailBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.util.Constant
import com.vtv.sports.util.ToastUtil
import com.vtv.sports.view.adapter.PagerDetailAdapter
import kotlinx.android.synthetic.main.layout_detail_toolbar.view.*

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding
    lateinit var pagerAdapter: PagerDetailAdapter
    lateinit var listNews: List<News>
    var currentPos: Int = 0

    companion object {
        fun newIntent(context: Context, listNews: String, pos: Int): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(Constant.KEY_LIST_NEWS, listNews)
            intent.putExtra(Constant.KEY_POSITION, pos)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        init()
    }

    fun init() {
        currentPos = intent.extras.getInt(Constant.KEY_POSITION)
        var obj = intent.extras.getString(Constant.KEY_LIST_NEWS)
        listNews = Gson().fromJson<List<News>>(obj)

        pagerAdapter = PagerDetailAdapter(supportFragmentManager, listNews)
        binding.pagerNewsDetail.offscreenPageLimit = 1
        binding.pagerNewsDetail.adapter = pagerAdapter
        binding.pagerNewsDetail.currentItem = currentPos

        binding.layoutToolbar.img_back.setOnClickListener {
            onBackPressed()
        }
        binding.layoutToolbar.img_more.setOnClickListener {
            ToastUtil(applicationContext, "Click")
        }
        binding.layoutToolbar.text_copy.setOnClickListener {
            ToastUtil(applicationContext, "Click")
        }

    }


    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

}
