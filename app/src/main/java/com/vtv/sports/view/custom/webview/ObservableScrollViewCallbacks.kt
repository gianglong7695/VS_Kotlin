package com.vtv.sports.view.custom.webview

/**
 * Created by Giang Long on 05/01/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Callbacks for Scrollable widgets.
 */
interface ObservableScrollViewCallbacks {
    /**
     * Called when the scroll change events occurred.
     *
     * This won't be called just after the view is laid out, so if you'd like to
     * initialize the position of your views with this method, you should call this manually
     * or invoke scroll as appropriate.
     *
     * @param scrollY     Scroll position in Y axis.
     * @param firstScroll True when this is called for the first time in the consecutive motion events.
     * @param dragging    True when the view is dragged and false when the view is scrolled in the inertia.
     */
    fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean)

    /**
     * Called when the down motion event occurred.
     */
    fun onDownMotionEvent()

    /**
     * Called when the dragging ended or canceled.
     *
     * @param scrollState State to indicate the scroll direction.
     */
//    fun onUpOrCancelMotionEvent(scrollState: ScrollState)
}