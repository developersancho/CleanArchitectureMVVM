package developersancho.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import developersancho.mvvm.base.BaseViewModel
import developersancho.mvvm.base.IBasePresenter
import developersancho.mvvm.entity.FavEntity
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(dataManager: IDataManager) : BaseViewModel<IBasePresenter>(dataManager) {
    val repos: MutableLiveData<ArrayList<Repos>> = MutableLiveData()

    fun getRepos(user: String) = viewModelScope.launch {
        //getPresenter().showLoading()
        when (val result = dataManager.getRepos(user)) {
            is ResponseWrapper.Success -> {
                val repoList = result.data
                repos.value = repoList
                //getPresenter().hideLoading()
            }

            is ResponseWrapper.Error -> {
                /*getPresenter().hideLoading()
                getPresenter().onServerError(result.exception.message, result.exception.code)*/
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