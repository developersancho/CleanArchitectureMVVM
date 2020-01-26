package developersancho.mvvm.base

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.developersancho.widget.ProgressLoadingDialog

abstract class BaseActivity : AppCompatActivity(), IBasePresenter {

    private val progress: ProgressLoadingDialog by lazy {
        ProgressLoadingDialog(context = this)
    }

    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initPresenter()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private fun initBinding() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.let {
            setContentView(it)
        } ?: run {
            initBinding()
        }

        initPresenter()
        initUI()
        initListener()
    }


    override fun showLoading() {
        progress.toggle(status = true)
    }

    override fun hideLoading() {
        progress.release()
    }

    override fun showError(code: Int, message: String) {

    }

    override fun showSuccess(title: String, message: String) {

    }
}

// Extension for data-binding and view-binding START

fun <T : ViewDataBinding> FragmentActivity.dataBinding(): Lazy<T> = object : Lazy<T> {
    private var binding: T? = null

    override fun isInitialized(): Boolean = binding != null

    override val value: T
        get() = binding ?: bind<T>(getContentView()).also {
            it.lifecycleOwner = this@dataBinding
            binding = it
        }

    private fun FragmentActivity.getContentView(): View {
        return checkNotNull(findViewById<ViewGroup>(R.id.content).getChildAt(0)) {
            "Call setContentView or Use Activity's secondary constructor passing layout res id."
        }
    }

    private fun <T : ViewDataBinding> bind(view: View): T = DataBindingUtil.bind(view)!!
}


fun <T : ViewBinding> FragmentActivity.viewBinding(bind: (View) -> T): Lazy<T> = object : Lazy<T> {
    private var binding: T? = null

    override fun isInitialized(): Boolean = binding != null

    override val value: T
        get() = binding ?: bind(getContentView()).also {
            binding = it
        }

    private fun FragmentActivity.getContentView(): View {
        return checkNotNull(findViewById<ViewGroup>(R.id.content).getChildAt(0)) {
            "Call setContentView or Use Activity's secondary constructor passing layout res id."
        }
    }
}

// Extension for data-binding and view-binding END