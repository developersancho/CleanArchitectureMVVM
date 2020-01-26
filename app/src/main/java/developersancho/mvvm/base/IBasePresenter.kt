package developersancho.mvvm.base

interface IBasePresenter {

    fun showLoading()
    fun hideLoading()
    fun showError(code: Int, message: String)
    fun showSuccess(title: String, message: String)
}