package developersancho.mvvm.ui.viewbindingtest

import androidx.navigation.findNavController
import developersancho.mvvm.R
import developersancho.mvvm.base.BaseActivity
import developersancho.mvvm.base.viewBinding
import developersancho.mvvm.databinding.ActivityViewBindingTestBinding

class ViewBindingTestActivity : BaseActivity() {


    private val binding by viewBinding { ActivityViewBindingTestBinding.bind(it) }

    override val layoutId: Int?
        get() = R.layout.activity_view_binding_test

    override fun initPresenter() {

    }

    override fun initUI() {
        binding.textViewBindingDsc.text = "ViewBindingTestActivity"
    }

    override fun initListener() {

    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.view_nav_host_fragment).navigateUp()

}
