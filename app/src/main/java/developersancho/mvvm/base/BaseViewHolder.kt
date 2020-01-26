package developersancho.mvvm.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun <T : ViewDataBinding> RecyclerView.ViewHolder.dataBinding(): Lazy<T> = object : Lazy<T> {
    private var binding: T? = null

    override val value: T
        get() = binding ?: bind<T>(getContentView()).also {
            binding = it
        }

    override fun isInitialized(): Boolean = binding != null

    private fun RecyclerView.ViewHolder.getContentView(): View {
        return checkNotNull(itemView.rootView) {
            "Call binding view holder"
        }
    }

    private fun <T : ViewDataBinding> bind(view: View): T = DataBindingUtil.bind(view)!!

}

fun <T : ViewBinding> RecyclerView.ViewHolder.viewBinding(bind: (View) -> T): Lazy<T> =
    object : Lazy<T> {
        private var binding: T? = null

        override val value: T
            get() = binding ?: bind(getContentView()).also {
                binding = it
            }

        override fun isInitialized(): Boolean = binding != null

        private fun RecyclerView.ViewHolder.getContentView(): View {
            return checkNotNull(itemView.rootView) {
                "Call binding view holder"
            }
        }

        private fun <T : ViewDataBinding> bind(view: View): T = DataBindingUtil.bind(view)!!

    }