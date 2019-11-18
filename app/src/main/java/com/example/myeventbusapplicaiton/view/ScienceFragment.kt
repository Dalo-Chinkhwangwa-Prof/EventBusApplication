package com.example.myeventbusapplicaiton.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myeventbusapplicaiton.R
import com.example.myeventbusapplicaiton.model.Feedback
import com.example.myeventbusapplicaiton.model.NewsTopic
import com.example.myeventbusapplicaiton.model.ScienceTopic
import kotlinx.android.synthetic.main.science_fragment_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ScienceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.science_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        science_textview.setOnClickListener {
            EventBus.getDefault().post(Feedback("Great information!"))
        }

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
    fun getScienceTopic(scienceTopic: ScienceTopic) {
        science_textview.text =
            "${scienceTopic.title}\n${scienceTopic.content} ${MainActivity.getSquare(
                7
            )}"
    }

    @Subscribe
    fun getNewsTopic(newsTopic: NewsTopic) {
        Toast.makeText(context, "${newsTopic.title} from Science", Toast.LENGTH_SHORT).show()
    }
}