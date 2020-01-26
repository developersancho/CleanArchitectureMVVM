package developersancho.mvvm.ui.main

import android.content.Intent
import androidx.lifecycle.Observer
import com.developersancho.util.extensions.longToast
import developersancho.mvvm.R
import developersancho.mvvm.base.BaseActivity
import developersancho.mvvm.base.viewBinding
import developersancho.mvvm.databinding.ActivityMainBinding
import developersancho.mvvm.ui.databindingtest.DataBindingTestActivity
import developersancho.mvvm.ui.viewbindingtest.ViewBindingTestActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), IMainPresenter {

    private val binding by viewBinding { ActivityMainBinding.bind(it) }

    private val viewModel by viewModel<MainViewModel>()

    override val layoutId: Int?
        get() = R.layout.activity_main

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun initUI() {
        observeViewModel()
        initData()
    }

    private fun initData() {
        viewModel.getRepos(user = "developersancho")
    }

    private fun observeViewModel() {
        viewModel.repos.observe(this, Observer {
            longToast("repo adı: --> ${it.first().fullName}")
        })
    }

    override fun initListener() {
        binding.btnD.setOnClickListener {
            startActivity(Intent(this, DataBindingTestActivity::class.java))
        }

        binding.btnV.setOnClickListener {
            startActivity(Intent(this, ViewBindingTestActivity::class.java))
        }
    }


    override fun showRepoSize(size: Int) {
        binding.textCenter.text = "Toplam repo $size tanedir."
    }
}
