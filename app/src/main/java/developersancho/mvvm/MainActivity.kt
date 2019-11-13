package developersancho.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import developersancho.mvvm.base.IBasePresenter
import developersancho.mvvm.extension.showLongToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), IBasePresenter {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.setPresenter(this)
        viewModel.repos.observe(this, Observer {
            showLongToast(it.first().fullName.toString())
        })

        viewModel.getRepos("developersancho")

    }
}
