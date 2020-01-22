package developersancho.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developersancho.local.entity.FavEntity
import com.developersancho.manager.IDataManager
import com.developersancho.remote.model.Repos
import com.developersancho.remote.network.NetworkState
import developersancho.mvvm.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(dataManager: IDataManager) : BaseViewModel<IMainPresenter>(dataManager) {

    private val _repos: MutableLiveData<ArrayList<Repos>> = MutableLiveData()

    var repos: LiveData<ArrayList<Repos>> = _repos

    fun getRepos(user: String) = viewModelScope.launch {
        getPresenter()?.showLoading()
        dataManager.getRepos(userName = user).collect { state ->
            when (state) {
                is NetworkState.Success -> {
                    getPresenter()?.hideLoading()
                    getPresenter()?.showRepoSize(state.response.size)
                    _repos.value = state.response
                }
                is NetworkState.Error -> {
                    getPresenter()?.hideLoading()
                    getPresenter()?.showError(state.exception.code, state.exception.message)
                }
            }

        }
    }

    fun insertFavData(favEntity: FavEntity) = viewModelScope.launch(Dispatchers.IO) {
        dataManager.insertFav(favEntity = favEntity)
    }

    fun getFavList() = viewModelScope.launch(Dispatchers.IO) {
        dataManager.favList()
    }

}