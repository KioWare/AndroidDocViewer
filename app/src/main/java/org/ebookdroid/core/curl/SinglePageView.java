package org.ebookdroid.core.curl;

import org.ebookdroid.common.settings.AppSettings;
import org.ebookdroid.core.EventGLDraw;
import org.ebookdroid.core.Page;
import org.ebookdroid.core.SinglePageController;
import org.ebookdroid.core.ViewState;
import org.ebookdroid.ui.viewer.views.DragMark;

import android.view.MotionEvent;

import org.emdev.common.log.LogContext;
import org.emdev.common.log.LogManager;

public class SinglePageView implements PageAnimator {

    protected static final LogContext LCTX = LogManager.root().lctx("View", false);

    protected final PageAnimationType type;

    protected final SinglePageController view;

    protected boolean bViewDrawn;

    protected int foreIndex = -1;

    protected int backIndex = -1;

    public SinglePageView(final SinglePageController view) {
        this(PageAnimationType.NONE, view);
    }

    protected SinglePageView(final PageAnimationType type, final SinglePageController view) {
        this.type = type;
        this.view = view;
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#init()
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#getType()
     */
    @Override
    public final PageAnimationType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.ebookdroid.common.touch.IGestureDetector#enabled()
     */
    @Override
    public boolean enabled() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.ebookdroid.common.touch.IGestureDetector#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#isPageVisible(org.ebookdroid.core.Page, org.ebookdroid.core.ViewState)
     */
    @Override
    public boolean isPageVisible(final Page page, final ViewState viewState) {
        final int pageIndex = page.index.viewIndex;
        return pageIndex == viewState.model.getCurrentViewPageIndex();
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#draw(org.ebookdroid.core.EventGLDraw)
     */
    @Override
    public void draw(final EventGLDraw event) {
        final Page page = event.viewState.model.getCurrentPageObject();
        if (page != null) {
            event.process(page);
            if (AppSettings.current().showAnimIcon) {
                DragMark.DRAG.draw(event.canvas, event.viewState);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#resetPageIndexes(int)
     */
    @Override
    public final void resetPageIndexes(final int currentIndex) {
        if (foreIndex != currentIndex) {
            foreIndex = backIndex = currentIndex;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#flipAnimationStep()
     */
    @Override
    public void flipAnimationStep() {
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#setViewDrawn(boolean)
     */
    @Override
    public final void setViewDrawn(final boolean bViewDrawn) {
        this.bViewDrawn = bViewDrawn;
    }

    public boolean isViewDrawn() {
        return bViewDrawn;
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#pageUpdated(org.ebookdroid.core.ViewState, org.ebookdroid.core.Page)
     */
    @Override
    public void pageUpdated(final ViewState viewState, final Page page) {
    }

    /**
     * {@inheritDoc}
     *
     * @see PageAnimator#animate(int)
     */
    @Override
    public void animate(final int direction) {
        view.goToPage(view.model.getCurrentViewPageIndex() + direction);
    }

}
