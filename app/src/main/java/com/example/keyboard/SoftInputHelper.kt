package com.example.keyboard

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.samples.insetsanimation.RootViewDeferringInsetsCallback

/**
 * 设置输入法流畅弹出收起的动画
 * @param translateView 需要在界面移动的view，一般就是recyclerView和输入框，其余没设置的view会在最后动画结束的时候自动归位
 */
fun setWindowAnim(
    activity: FragmentActivity,
    rootView: View,
    translateView: List<View>
) {
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    val deferringInsetsListener = RootViewDeferringInsetsCallback(
        persistentInsetTypes = WindowInsetsCompat.Type.systemBars(),
        deferredInsetTypes = WindowInsetsCompat.Type.ime()
    )
    // RootViewDeferringInsetsCallback is both an WindowInsetsAnimation.Callback and an
    // OnApplyWindowInsetsListener, so needs to be set as so.
    // 动画结束的时候  会再次触发ApplyWindowInsets重置rootView的padding，然后子view的的位移动画置为0就可以完美适配动画
    ViewCompat.setWindowInsetsAnimationCallback(rootView, deferringInsetsListener)
    // 设置这个可以把默认的底部导航栏边距给忽略掉
    ViewCompat.setOnApplyWindowInsetsListener(rootView, deferringInsetsListener)

    for (view in translateView) {
        ViewCompat.setWindowInsetsAnimationCallback(
            view,
            TranslateDeferringInsetsAnimationCallback(
                view = view,
                persistentInsetTypes = WindowInsetsCompat.Type.systemBars(),
                deferredInsetTypes = WindowInsetsCompat.Type.ime())
        )
    }
}