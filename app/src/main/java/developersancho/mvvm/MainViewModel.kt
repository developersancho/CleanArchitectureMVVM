package developersancho.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import developersancho.mvvm.base.BaseViewModel
import developersancho.mvvm.base.IBasePresenter
import developersancho.mvvm.entity.FavEntity
import developersancho.mvvm.model.Repos
import developersancho.mvvm.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(dataManager: IDataManager) : BaseViewModel<IBasePresenter>(dataManager) {
    val repos: MutableLiveData<ArrayList<Repos>> = MutableLiveData()
    val reposFromAPI = MutableLiveData<ArrayList<Repos>>()

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

    fun getRepoList(user: String) = viewModelScope.launch {
        runCatching {
            dataManager.getRepoList(user)
        }.onSuccess {

        }
    }

    fun getReposFromAPI(user: String) = viewModelScope.launch {
        dataManager.getReposFromAPI(user).collect {
            when (it) {
                is ResponseWrapper.Success -> {
                    reposFromAPI.value = it.data
                    //getPresenter().hideLoading()
                }

                is ResponseWrapper.Error -> {
                    /*getPresenter().hideLoading()
                    getPresenter().onServerError(result.exception.message, result.exception.code)*/
                }
            }

        }
    }

    fun getReposFromAPI2(user: String) = viewModelScope.launch {
        dataManager.getReposFromAPI2(user).collect {
            when (it) {
                is ResponseWrapper.Success -> {
                    reposFromAPI.value = it.data
                }

                is ResponseWrapper.Error -> {
                }
            }
        }
    }

    fun getReposFromAPICombines(user: String) {
       /* combine(
            transform = dataManager.getReposFromAPI(user),
            transform = dataManager.getReposFromAPI2(user),
            ::combineRadiosPage
        ).onEach {

        }.launchIn(viewModelScope)*/
    }

    private fun combineRadiosPage(
        popular: ResponseWrapper<ArrayList<Repos>>,
        popular2: ResponseWrapper<ArrayList<Repos>>
    ): ArrayList<Repos> {
        val list : ArrayList<Repos> = arrayListOf()
        return list
    }

    fun insertFavData(favEntity: FavEntity) = viewModelScope.launch(Dispatchers.IO) {
        dataManager.insertFav(favEntity = favEntity)
    }

    fun getFavList() = viewModelScope.launch(Dispatchers.IO) {
        dataManager.favList()
    }

}