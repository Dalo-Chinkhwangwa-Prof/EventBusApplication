package com.example.myeventbusapplicaiton.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myeventbusapplicaiton.R
import com.example.myeventbusapplicaiton.model.NewsTopic
import kotlinx.android.synthetic.main.news_fragment_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class NewsFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun receiveNews(myNews: NewsTopic){
        news_textview.text = "${myNews.title}\n${myNews.content}"
    }

}