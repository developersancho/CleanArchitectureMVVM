package developersancho.mvvm.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import developersancho.mvvm.helper.SingleEvent
import developersancho.mvvm.helper.SingleEventObserver

inline fun <T> LiveData<SingleEvent<T>>.observeSingleEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner, SingleEventObserver<T> {
        onEventUnhandledContent.invoke(it)
    })
}