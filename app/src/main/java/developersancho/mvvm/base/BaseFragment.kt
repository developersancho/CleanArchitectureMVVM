package developersancho.mvvm.base

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.developersancho.util.helper.ConnectivityChecker
import com.developersancho.widget.ProgressLoadingDialog
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseFragment : Fragment(), IBasePresenter {

    private val connectivityChecker: ConnectivityChecker? by lazy {
        val connectivityManager = activity?.getSystemService<ConnectivityManager>()
        connectivityManager?.let { ConnectivityChecker(it) } ?: run { null }
    }

    private val progress: ProgressLoadingDialog by lazy {
        ProgressLoadingDialog(context = requireContext())
    }

    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initPresenter()

    protected abstract fun initUI(view: View)

    protected abstract fun initListener()

    open fun initBinding(inflater: LayoutInflater, container: ViewGroup?): View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPresenter()

        return layoutId?.let {
            inflater.inflate(it, container, false)
        } ?: run {
            initBinding(inflater, container)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkConnection()
        initUI(view)
        initListener()
    }

    private fun checkConnection() {
        connectivityChecker?.apply {
            viewLifecycleOwner.lifecycle.addObserver(this)
            connectedStatus.observe(viewLifecycleOwner, Observer<Boolean> {
                if (it) {
                    handleNetworkConnected()
                } else {
                    handleNoNetworkConnection()
                }
            })
        } ?: handleNoNetworkConnection()
    }

    open fun handleNetworkConnected() {}

    open fun handleNoNetworkConnection() {}

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

fun <T : ViewBinding> Fragment.viewBinding(bind: (View) -> T): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            (requireView().getTag(property.name.hashCode()) as? T)?.let { return it }
            return bind(requireView()).also {
                requireView().setTag(property.name.hashCode(), it)
            }
        }
    }

fun <T : ViewDataBinding> Fragment.dataBinding(): ReadOnlyProperty<Fragment, T> {
    return object : ReadOnlyProperty<Fragment, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            (requireView().getTag(property.name.hashCode()) as? T)?.let { return it }
            return bind<T>(requireView()).also {
                it.lifecycleOwner = thisRef.viewLifecycleOwner
                it.root.setTag(property.name.hashCode(), it)
            }
        }

        private fun <T : ViewDataBinding> bind(view: View): T = DataBindingUtil.bind(view)!!
    }
}

// Extension for data-binding and view-binding END