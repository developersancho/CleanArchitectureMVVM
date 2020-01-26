package developersancho.mvvm.ui.databindingtest

import androidx.navigation.findNavController
import developersancho.mvvm.R
import developersancho.mvvm.base.BaseActivity
import developersancho.mvvm.base.dataBinding
import developersancho.mvvm.databinding.ActivityDataBindingTestBinding

class DataBindingTestActivity : BaseActivity() {

    private val binding by dataBinding<ActivityDataBindingTestBinding>()

    override val layoutId: Int?
        get() = R.layout.activity_data_binding_test

    override fun initPresenter() {

    }

    override fun initUI() {
        binding.textDataBindingDsc.text = "DataBindingTestActivity"
    }

    override fun initListener() {

    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.data_nav_host_fragment).navigateUp()

}
