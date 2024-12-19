package com.example.keyboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keyboard.databinding.ActivityMainBinding
import com.google.android.samples.insetsanimation.RootViewDeferringInsetsCallback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tell the Window that our app is going to responsible for fitting for any system windows.
        // This is similar to the now deprecated:
        // view.setSystemUiVisibility(LAYOUT_STABLE | LAYOUT_FULLSCREEN | LAYOUT_FULLSCREEN)
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.conversationRecyclerview.adapter = ConversationAdapter()
        binding.conversationRecyclerview.setOnTouchListener { v, _ ->
            hideSoftInput() // 隐藏键盘
            false
        }
        setWindowAnim(this, binding.root, listOf(binding.messageHolder, binding.conversationRecyclerview))

//
//        val deferringInsetsListener = RootViewDeferringInsetsCallback(
//            persistentInsetTypes = WindowInsetsCompat.Type.systemBars(),
//            deferredInsetTypes = WindowInsetsCompat.Type.ime()
//        )
//        // RootViewDeferringInsetsCallback is both an WindowInsetsAnimation.Callback and an
//        // OnApplyWindowInsetsListener, so needs to be set as so.
//        // 动画结束的时候  会再次触发ApplyWindowInsets重置rootView的padding，然后子view的的位移动画置为0就可以完美适配动画
//        ViewCompat.setWindowInsetsAnimationCallback(binding.root, deferringInsetsListener)
//        // 设置这个可以把默认的底部导航栏边距给忽略掉
//        ViewCompat.setOnApplyWindowInsetsListener(binding.root, deferringInsetsListener)

        /**
         * 2种思路：
         * 1.google实现：setOnApplyWindowInsetsListener忽略导航栏，再WindowInsetsAnimationCallback中再次触发ApplyWindowInsets重置rootView的padding，子view的动画结束后需要重置位移为0
         * 2.只设置setWindowInsetsAnimationCallback，子view正常做位移动画即可
         */

//        ViewCompat.setWindowInsetsAnimationCallback(
//            binding.messageHolder,
//            TranslateDeferringInsetsAnimationCallback(
//                view = binding.messageHolder,
//                persistentInsetTypes = WindowInsetsCompat.Type.systemBars(),
//                deferredInsetTypes = WindowInsetsCompat.Type.ime(),
//                // We explicitly allow dispatch to continue down to binding.messageHolder's
//                // child views, so that step 2.5 below receives the call
//                dispatchMode = WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
//            )
//        )
//        ViewCompat.setWindowInsetsAnimationCallback(
//            binding.conversationRecyclerview,
//            TranslateDeferringInsetsAnimationCallback(
//                view = binding.conversationRecyclerview,
//                persistentInsetTypes = WindowInsetsCompat.Type.systemBars(),
//                deferredInsetTypes = WindowInsetsCompat.Type.ime()
//            )
//        )

        // editText的动画---恢复默认的焦点
        // 2.5) We also want to make sure that our EditText is focused once the IME
//        ViewCompat.setWindowInsetsAnimationCallback(
//            binding.messageEdittext,
//            ControlFocusInsetsAnimationCallback(binding.messageEdittext)
//        )

    }
}