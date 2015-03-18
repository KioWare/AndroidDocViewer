package org.ebookdroid.common.touch;

import android.view.MotionEvent;


public class DummyGestureDetector implements IGestureDetector {

    /**
     * {@inheritDoc}
     *
     * @see IGestureDetector#enabled()
     */
    @Override
    public boolean enabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see IGestureDetector#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

}
