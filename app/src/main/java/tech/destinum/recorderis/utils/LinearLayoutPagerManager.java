package tech.destinum.recorderis.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class LinearLayoutPagerManager extends LinearLayoutManager {

    private int mItemsPerPage;

    public int getItemsPerPage() {
        return mItemsPerPage;
    }


    public LinearLayoutPagerManager(Context context, int orientation, boolean reverseLayout, int itemsPerPage) {
        super(context, orientation, reverseLayout);

        mItemsPerPage = itemsPerPage;
    }

    @Override
    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        return super.checkLayoutParams(lp) && lp.width == getItemSize();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return setProperItemSize(super.generateDefaultLayoutParams());
    }

    @Override
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return setProperItemSize(super.generateLayoutParams(lp));
    }

    private RecyclerView.LayoutParams setProperItemSize(RecyclerView.LayoutParams lp) {
        int itemSize = getItemSize();
        if (getOrientation() == HORIZONTAL) {
            lp.width = itemSize;
        } else {
            lp.height = itemSize;
        }
        return lp;
    }

    private int getItemSize() {
        int pageSize = getOrientation() == HORIZONTAL ? getWidth() : getHeight();
        return Math.round((float) pageSize / mItemsPerPage);
    }

//    @Override
//    public int getPaddingLeft() {
//        return Math.round( / 2f - getWidth() / 2f);
//    }
//
//    @Override
//    public int getPaddingRight() {
//        return getPaddingLeft();
//    }

//    @Override
//    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
//        RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
//        smoothScroller.setTargetPosition(position);
//        startSmoothScroll(smoothScroller);
//    }
//
//    private static class CenterSmoothScroller extends LinearSmoothScroller {
//
//        CenterSmoothScroller(Context context) {
//            super(context);
//        }
//
//        @Override
//        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
//            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
//        }
//    }
}