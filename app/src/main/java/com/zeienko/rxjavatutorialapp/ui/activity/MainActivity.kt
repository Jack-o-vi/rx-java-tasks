package com.zeienko.rxjavatutorialapp.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.zeienko.rxjavatutorialapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setProceedClickListener()
        spTasks?.let {
            it.onItemSelectedListener = this
            val dataAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                TaskActivity.activitiesList
            )
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // attaching data adapter to spinner
            it.adapter = dataAdapter
        }
    }

    private fun setProceedClickListener() {
        btnProceed.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx = it.width / 2
                val cy = it.height / 2
                val radius = it.width
                val anim = ViewAnimationUtils
                    .createCircularReveal(it, cx, cy, radius.toFloat(), 0f)
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        it.visibility = View.INVISIBLE
                        startTaskOneActivity(index)
                        Handler().postDelayed({
                            btnProceed.visibility = View.VISIBLE
                        }, 2_000)
                    }
                })
                anim.start()
            }
        }
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        index = position
    }

    private fun startTaskOneActivity(index: Int) {
        startActivity(TaskActivity.newInstance(this, TaskActivity.activitiesList[index]))
    }

}
