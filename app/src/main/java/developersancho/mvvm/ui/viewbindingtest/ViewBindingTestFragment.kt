package developersancho.mvvm.ui.viewbindingtest

import android.view.View
import developersancho.mvvm.R
import developersancho.mvvm.base.BaseFragment
import developersancho.mvvm.base.viewBinding
import developersancho.mvvm.databinding.FragmentViewBindingTestBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ViewBindingTestFragment : BaseFragment() {

    private val viewModel by sharedViewModel<VViewModel>()

    private val binding by viewBinding { FragmentViewBindingTestBinding.bind(it) }

    override val layoutId: Int?
        get() = R.layout.fragment_view_binding_test

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initUI(view: View) {
        binding.textFragViewBinding.text = "ViewBindingFragment"
    }

    override fun initListener() {

    }

}
