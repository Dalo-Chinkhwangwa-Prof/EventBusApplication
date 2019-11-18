package com.example.myeventbusapplicaiton.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myeventbusapplicaiton.R
import com.example.myeventbusapplicaiton.model.Feedback
import com.example.myeventbusapplicaiton.model.NewsTopic
import com.example.myeventbusapplicaiton.model.ScienceTopic
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    companion object {

        fun getSquare(number: Int): Int {
            return (number * number)
        }
    }

    val newsFragment = NewsFragment()
    val scienceFragment = ScienceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayFragments()
        publish_button.setOnClickListener {
            EventBus.getDefault()
                .post(
                    NewsTopic(
                        "Monday is Here",
                        "This is the beginning of the week."
                    )
                )
            EventBus.getDefault().post(
                ScienceTopic(
                    "Mathematics is not science",
                    "Rumor has it mathematics is not science."
                )
            )
        }

        EventBus.getDefault().register(this)

    }

    private fun displayFragments() {

        supportFragmentManager.beginTransaction()
            .add(R.id.news_fragment_layout, newsFragment)
            .addToBackStack(newsFragment.tag)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.science_fragment_layout, scienceFragment)
            .addToBackStack(scienceFragment.tag)
            .commit()
    }

    @Subscribe
    fun getFeedBack(feedback: Feedback) {
        Toast.makeText(this, feedback.actualFeedback, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
