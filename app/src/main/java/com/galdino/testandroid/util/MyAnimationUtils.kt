package com.galdino.testandroid.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import com.galdino.testandroid.R

/**
 * Created by Galdino Rodrigues on 25/03/2018.
 */
class MyAnimationUtils {
    companion object {
        fun translateShow(view: View, context: Context?, listener: Animation.AnimationListener?, startOffset: Long?, duration: Int?) {
            if (context != null && context.resources != null) {
                val animationTranslate = AnimationUtils.loadAnimation(context, R.anim.anim_translate_in)
                if (listener != null) {
                    animationTranslate.setAnimationListener(listener)
                }
                if (startOffset != null) {
                    animationTranslate.startOffset = startOffset
                }
                if (duration != null) {
                    animationTranslate.duration = duration.toLong()
                }
                view.animation = animationTranslate
                view.visibility = View.VISIBLE
                animationTranslate.start()
            } else {
                view.visibility = View.VISIBLE
            }
        }

        fun translateHide(view: View, context: Context?, listener: Animation.AnimationListener?, duration: Int?, startOffset: Long?) {
            if (context != null && context.resources != null) {
                val animationTranslate = AnimationUtils.loadAnimation(context, R.anim.anim_translate_out)
                if (listener != null) {
                    animationTranslate.setAnimationListener(listener)
                }
                if (duration != null) {
                    animationTranslate.duration = duration.toLong()
                }
                startOffset?.let {
                    animationTranslate.startOffset = it
                }
                view.animation = animationTranslate
                view.visibility = View.INVISIBLE
                animationTranslate.start()
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        fun upShowView(view: View, context: Context?, listener: Animation.AnimationListener?, startOffset: Long?) {
            if (context != null && context.resources != null) {
                val animacaoIn = AnimationUtils.loadAnimation(context, R.anim.anim_in_up)
                if (listener != null) {
                    animacaoIn.setAnimationListener(listener)
                }
                if (startOffset != null) {
                    animacaoIn.startOffset = startOffset
                }
                animacaoIn.interpolator = DecelerateInterpolator()
                view.animation = animacaoIn
                view.visibility = View.VISIBLE
                animacaoIn.start()
            } else {
                view.visibility = View.VISIBLE
            }
        }

        fun leftShowView(view: View, context: Context?, listener: Animation.AnimationListener?) {
            if (context != null && context.resources != null) {
                val animLeft = AnimationUtils.loadAnimation(context, R.anim.anim_in_left)
                if (listener != null) {
                    animLeft.setAnimationListener(listener)
                }
                view.animation = animLeft
                view.visibility = View.VISIBLE
                animLeft.start()
            } else {
                view.visibility = View.VISIBLE
            }
        }

        fun rightShow(view: View, context: Context?, listener: Animation.AnimationListener?, startOffset: Long?)
        {
            if (context != null && context.resources != null) {
                val animRight = AnimationUtils.loadAnimation(context, R.anim.anim_in_right)

                if (listener != null) {
                    animRight.setAnimationListener(listener)
                }
                startOffset?.let {
                    animRight.startOffset = it
                }

                view.animation = animRight
                view.visibility = View.VISIBLE
                animRight.start()
            } else {
                view.visibility = View.VISIBLE
            }
        }

        fun downHideView(view: View, context: Context?) {
            if (context != null && context.resources != null) {
                val animacaoIn = AnimationUtils.loadAnimation(context, R.anim.anim_out_down)
                view.animation = animacaoIn
                view.visibility = View.INVISIBLE
                animacaoIn.start()
            } else {
                view.visibility = View.INVISIBLE
            }
        }

        fun circularRevelShow(viewShow: View) {
            viewShow.post {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    // get the center for the clipping circle
                    // get the center for the clipping circle
                    val cx = viewShow.measuredWidth / 2
                    val cy = viewShow.measuredHeight / 2

                    // get the final radius for the clipping circle
                    val finalRadius = Math.max(viewShow.width, viewShow.height) / 2

                    // create the animator for this view (the start radius is zero)
                    val anim = ViewAnimationUtils.createCircularReveal(viewShow, cx, cy, 0f, finalRadius.toFloat())
                    //            anim.setInterpolator(new LinearOutSlowInInterpolator());
                    anim.duration = 250

                    // make the view visible and start the animation
                    viewShow.visibility = View.VISIBLE
                    anim.start()
                } else {
                    viewShow.visibility = View.VISIBLE
                }
            }
        }

        fun circularRevelHide(viewHide: View) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                // get the center for the clipping circle
                // get the center for the clipping circle
                val cx = viewHide.measuredWidth / 2
                val cy = viewHide.measuredHeight / 2

                // get the initial radius for the clipping circle
                val initialRadius = viewHide.width / 2

                // create the animation (the final radius is zero)
                val anim = ViewAnimationUtils.createCircularReveal(viewHide, cx, cy, initialRadius.toFloat(), 0f)
                //            anim.setInterpolator(new LinearOutSlowInInterpolator());
                anim.duration = 250

                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        viewHide.visibility = View.INVISIBLE
                    }
                })
                // start the animation
                anim.start()
            } else {
                viewHide.visibility = View.INVISIBLE
            }
        }
    }
}