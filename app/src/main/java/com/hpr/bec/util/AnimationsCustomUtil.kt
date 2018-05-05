package br.com.iterative.abasteceai.Support.Util

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.ViewPropertyAnimator
import android.view.animation.Transformation
import android.view.animation.Animation





/**
 * Created by rafaelbarbosa on 29/12/17.
 */
object AnimationsCustomUtil {

    fun startAnimationSplash(view: View, delay: Long) {
        view.alpha = 0f
        view.translationY = 50f
        view.visibility = View.VISIBLE
        val animator2 = view.animate()
        val animationValue2 = if (view.alpha == 0f) 1f else 0f
        animator2.alpha(animationValue2)
        animator2.translationY(0f)
        animator2.interpolator = DecelerateInterpolator()
        animator2.startDelay = delay
        animator2.start()
    }

    fun startAnimationFade(view: View, delay: Long) {
        val animator2 = view.animate()
        val animationValue2 = if (view.alpha == 0f) 1f else 0f
        animator2.alpha(animationValue2)
        animator2.interpolator = DecelerateInterpolator()
        animator2.startDelay = delay
        animator2.setListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                if (view.alpha == 0f){
                    view.visibility = View.GONE
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
                if (view.alpha == 0f){
                    view.visibility = View.VISIBLE
                }
            }

        })
        animator2.start()
    }

    fun expand(v: View) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

}