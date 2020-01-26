package developersancho.mvvm.ui.databindingtest

import android.view.View
import developersancho.mvvm.R
import developersancho.mvvm.base.BaseFragment
import developersancho.mvvm.base.dataBinding
import developersancho.mvvm.databinding.FragmentDataBindingTestBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DataBindingTestFragment : BaseFragment() {

    private val viewModel by sharedViewModel<DViewModel>()

    private val binding by dataBinding<FragmentDataBindingTestBinding>()

    override val layoutId: Int?
        get() = R.layout.fragment_data_binding_test

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initUI(view: View) {
        binding.textFragDataBinding.text = "DataBindingFragment"
    }

    override fun initListener() {

    }

}
