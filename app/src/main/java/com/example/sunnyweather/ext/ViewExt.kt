package com.ct.base.ext

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.view.ViewCompat

/**
 * 设置控件宽度
 */
fun View.setWidth(newWidth: Int){
    this.layoutParams.width = newWidth
}

/**
 * 设置控件宽度
 */
fun View.setHeight(newHeight: Int){
    this.layoutParams.height = newHeight
}


/**
 * 设置控件外边距
 */
fun View.margin(left: Int, top: Int, right: Int, bottom: Int){
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        requestLayout()
    }
}


// 重置动画属性
fun View.resetAllProperty(){
    this.alpha = 1f
    this.scaleX = 1f
    this.scaleY = 1f
    this.translationX = 0f
    this.translationY = 0f
    this.rotation = 0f
    this.rotationX = 0f
    this.rotationY = 0f
}

// 只重置旋转动画属性
fun View.resetRotation(){
    this.rotation = 0f
    this.rotationX = 0f
    this.rotationY = 0f
}

// 左右震动
fun View.shake(){
    ObjectAnimator.ofFloat(this, "translationX",
        0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f).setDuration(500).start()
}

// 缩放
fun View.pulse(){
    val animatorSet = AnimatorSet()
    val anim1 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.1f, 1f)
    val anim2 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.1f, 1f)
    anim1.repeatCount = ValueAnimator.INFINITE
    anim2.repeatCount = ValueAnimator.INFINITE
    animatorSet.playTogether(anim1, anim2)
    animatorSet.setDuration(500).start()
}

// 翻牌
fun View.flipOutY(){
    val animator = ObjectAnimator.ofFloat(this, "rotationY", 0f, -180f, -360f);
    animator.setDuration(1200).start()
}

fun View.rotate(){
    val animator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
    animator.repeatCount = ValueAnimator.INFINITE
    animator.interpolator = LinearInterpolator()
    animator.setDuration(4000).start()
}

fun View.alpha(){
    val animator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f);
    animator.setDuration(200).start()
}