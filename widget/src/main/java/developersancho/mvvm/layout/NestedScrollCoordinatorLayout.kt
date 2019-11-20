package developersancho.mvvm.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.annotation.Nullable
import androidx.annotation.Size
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat


/**
 * A [CoordinatorLayout] that implements [NestedScrollingChild].
 * This means it can act as a nested scrolling child, and thus forward scroll events to parents.
 *
 * This is useful for nested Coordinators, e.g. for inner fragments in a parent activity. If this is used,
 * scrolls to the inner fragment will trigger scroll animations (e.g. `AppBarLayout`s, fabs, ...)
 * to the parent activity as well.
 *
 * This works by *not* reinventing the wheel and reusing the same nested scrolling logic implemented
 * by behaviors. There is a dummy view inside the sheet that is capable of getting nested scrolling
 * callbacks, and forward them to the *outer* behavior that they normally would never reach.
 */
class NestedScrollCoordinatorLayout : CoordinatorLayout, NestedScrollingChild {
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        PASS_MODE_BOTH,
        PASS_MODE_PARENT_FIRST
    )
    annotation class PassMode

    private var helper: NestedScrollingChildHelper? = null
    private var dummyBehavior: DummyBehavior<*>? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        helper = NestedScrollingChildHelper(this)
        isNestedScrollingEnabled = true
        // Add a dummy view that will receive inner touch events.
        val dummyView = View(context)
        dummyBehavior =
            DummyBehavior<View?>()
        // I *think* this is needed for dummyView to be identified as "topmost" and receive events
        // before any other view.
        ViewCompat.setElevation(dummyView, ViewCompat.getElevation(this))
        // Make sure it does not fit windows, or it will consume insets before the AppBarLayout.
        dummyView.fitsSystemWindows = false
        val params = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        params.behavior = dummyBehavior
        addView(dummyView, params)
    }

    /**
     * Sets the pass mode for this coordinator.
     * @see .PASS_MODE_BOTH
     *
     * @see .PASS_MODE_PARENT_FIRST
     *
     *
     * @param mode desired pass mode for scroll events.
     */
    fun setPassMode(@PassMode mode: Int) {
        if (dummyBehavior != null) dummyBehavior!!.setPassMode(mode)
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        helper!!.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return helper!!.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return helper!!.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        helper!!.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return helper!!.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int, @Nullable @Size(value = 2) offsetInWindow: IntArray?
    ): Boolean {
        return helper!!.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int, @Nullable @Size(value = 2) consumed: IntArray?, @Nullable @Size(
            value = 2
        ) offsetInWindow: IntArray?
    ): Boolean {
        return helper!!.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return helper!!.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return helper!!.dispatchNestedPreFling(velocityX, velocityY)
    }

    /**
     * This behavior is assigned to our dummy, MATCH_PARENT view inside this bottom sheet layout.
     * Through this behavior the dummy view can listen to touch/scroll events.
     * Our goal is to propagate them to the parent stream.
     *
     * It has to be done manually because by default CoordinatorLayouts don't propagate scroll events
     * to their parent. This is bad for CoordinatorLayouts inside other CoordinatorLayouts, since
     * the coordination works relies heavily on scroll events.
     *
     * @param <DummyView> make sure it's not a nested-scrolling-enabled view or this will break.
    </DummyView> */
    private class DummyBehavior<DummyView : View?> internal constructor() :
        Behavior<DummyView>() {
        @PassMode
        private var mode =
            PASS_MODE_BOTH
        private val cache = IntArray(2)
        fun setPassMode(@PassMode mode: Int) {
            this.mode = mode
        }

        override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: DummyView,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
        ): Boolean {
            val sheet =
                coordinatorLayout as NestedScrollCoordinatorLayout
            // If we want to catch, catch.
            return sheet.startNestedScroll(axes)
        }

        override fun onStopNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: DummyView,
            target: View,
            type: Int
        ) {
            val sheet =
                coordinatorLayout as NestedScrollCoordinatorLayout
            sheet.stopNestedScroll()
        }

        override fun onNestedPreScroll(
            coordinatorLayout: CoordinatorLayout,
            child: DummyView,
            target: View,
            dx: Int,
            dy: Int,
            consumed: IntArray,
            type: Int
        ) {
            val sheet =
                coordinatorLayout as NestedScrollCoordinatorLayout
            if (mode == PASS_MODE_PARENT_FIRST) {
                sheet.dispatchNestedPreScroll(dx, dy, consumed, null)
            } else if (mode == PASS_MODE_BOTH) { // Don't let sheet consume the original int.
                cache[0] = consumed[0]
                cache[1] = consumed[1]
                sheet.dispatchNestedPreScroll(dx, dy, cache, null)
            }
        }

        override fun onNestedPreFling(
            coordinatorLayout: CoordinatorLayout,
            child: DummyView,
            target: View,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val sheet =
                coordinatorLayout as NestedScrollCoordinatorLayout
            val s = sheet.dispatchNestedPreFling(velocityX, velocityY)
            return if (mode == PASS_MODE_PARENT_FIRST) {
                s
            } else false
        } // onNestedScroll and onNestedFling are not needed.
    }

    companion object {
        private val TAG = NestedScrollCoordinatorLayout::class.java.simpleName
        /**
         * Constant for [.setPassMode]. When this is selected, scroll events are
         * passed to the parent stream and, at the same time, to this Coordinator childs.
         */
        const val PASS_MODE_BOTH = 0
        /**
         * Constant for [.setPassMode]. When this is selected, scroll events are
         * passed to the parent stream and, if not consumed, they go on to this Coordinator childs.
         */
        const val PASS_MODE_PARENT_FIRST = 1
    }
}