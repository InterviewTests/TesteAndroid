package com.galdino.testandroid.plataform.views.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.support.annotation.AnimRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.Toast
import com.galdino.testandroid.mvp.Contract
import com.galdino.testandroid.plataform.views.BaseActivity


abstract class BaseFragment: Fragment(), Contract.View {
    protected lateinit var root: View

    private var mToastController: ToastController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(getLayoutResource(), container, false)

        mToastController = getToastController()
        return root
    }

    open fun getToastController(): ToastController {
        if (activity is BaseActivity) {
            return (activity as BaseActivity).getToastController()
        }
        return ToastController(context!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun onInitView()

    fun setTitle(stringResource: Int) {
        activity?.setTitle(stringResource)
    }

    fun showShortToast(resId: Int) {
        mToastController?.showShortToast(resId)
    }

    fun showShortToast(text: String?) {
        mToastController?.showToast(text, Toast.LENGTH_SHORT)
    }

    fun showLongToast(resId: Int) {
        mToastController?.showLongToast(resId)
    }

    fun showLongToast(text: String?) {
        mToastController?.showToast(text, Toast.LENGTH_LONG)
    }

    open fun onBackPressed() {

    }

    fun animateView(layout: View?, duration: Long, delayMillis: Long,
                    @AnimRes animBottom: Int) {
        if (context == null) {
            return
        }

        Handler().postDelayed({
            context?.let {
                layout?.clearAnimation()
                layout?.visibility = View.INVISIBLE
                val animationBottom = AnimationUtils.loadAnimation(context,
                        animBottom)
                animationBottom.duration = duration
                animationBottom.interpolator = DecelerateInterpolator()
                animationBottom.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        layout?.visibility = View.VISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                layout?.startAnimation(animationBottom)
            }
        }, delayMillis)
    }

    fun animateOpen(layoutTop: View?, layoutBottom: View?,
                    durationTop: Long, durationBottom: Long,
                    @AnimRes animTop: Int, @AnimRes animBottom: Int) {
        if (context == null) {
            return
        }

        layoutTop?.clearAnimation()
        layoutTop?.visibility = View.INVISIBLE

        val animationTop = AnimationUtils.loadAnimation(context, animTop)
        animationTop.duration = durationTop
        animationTop.interpolator = DecelerateInterpolator()
        animationTop.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                layoutTop?.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        layoutTop?.startAnimation(animationTop)

        layoutBottom?.clearAnimation()
        layoutBottom?.visibility = View.INVISIBLE
        val animationBottom = AnimationUtils.loadAnimation(context,
                animBottom)
        animationBottom.duration = durationBottom
        animationBottom.interpolator = DecelerateInterpolator()
        animationBottom.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                layoutBottom?.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        layoutBottom?.startAnimation(animationBottom)
    }

    private fun changeViewOrientation() {
        val activityOrientation = getActivityOrientation()
        activityOrientation?.let {
            if (activity?.requestedOrientation != it) {
                activity?.requestedOrientation = it
            }
        }
    }

    fun onLoading(progress: ProgressBar, isVisible: Boolean)
    {
        var visibility = View.VISIBLE
        if(!isVisible)
        {
            visibility = View.GONE
        }
        progress.visibility = visibility
    }

    open fun getActivityOrientation(): Int? = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

}