package developersancho.mvvm.helper

import androidx.lifecycle.Observer

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class SingleEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {
    override fun onChanged(event: SingleEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

// for using
/*
viewModel.observeMe.observe(this, SingleEventObserver{ model ->
    // Every Event instance observed here once
})*/

// with extension
/*viewModel.observeMe.observeSingleEvent(this){ model ->
    // Every Event instance observed here once
}*/
