package com.developersancho.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.android.synthetic.main.dialog_progress_loading.*

class ProgressLoadingDialog(context: Context) : Dialog(context), LifecycleObserver {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_progress_loading)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = lp
        progressBar.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(context, android.R.color.holo_red_dark),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
    }

    fun toggle(status: Boolean = true) {
        if (status) {
            if (!isShowing) {
                show()
            }
        } else {
            cancel()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun release() {
        cancel()
    }
}