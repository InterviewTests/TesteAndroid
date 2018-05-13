package com.anabhomasi.androidapp

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_form.*


class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        sendButton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.background=ContextCompat.getDrawable(this, R.drawable.button_pressed)
                    val reducer = AnimatorInflater.loadAnimator(this, R.animator.reduce_size) as AnimatorSet
                    reducer.setTarget(v)
                    reducer.start()

                }

                MotionEvent.ACTION_UP -> {
                    val regainer = AnimatorInflater.loadAnimator(this, R.animator.regain_size) as AnimatorSet
                    regainer.setTarget(v)
                    regainer.start()
                    v.background=ContextCompat.getDrawable(this, R.drawable.button_normal)

                }
            }
            true
        }

    }
}
