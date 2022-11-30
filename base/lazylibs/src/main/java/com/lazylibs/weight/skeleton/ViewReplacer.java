/*
 * Copyright 2017, ethanhua
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lazylibs.weight.skeleton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 */
public class ViewReplacer {

    private static final String TAG = ViewReplacer.class.getName();
    private final View mSourceView;
    private View mTargetView;
    private int mTargetViewResID = -1;
    private View mCurrentView;
    private ViewGroup mSourceParentView;
    private final ViewGroup.LayoutParams mSourceViewLayoutParams;
    private int mSourceViewIndexInParent = 0;
    private final int mSourceViewId;

    public ViewReplacer(View sourceView) {
        mSourceView = sourceView;
        mSourceViewLayoutParams = mSourceView.getLayoutParams();
        mCurrentView = mSourceView;
        mSourceViewId = mSourceView.getId();
    }

    public void replace(int targetViewResID) {
        if (mTargetViewResID == targetViewResID) {
            return;
        }
        if (init()) {
            mTargetViewResID = targetViewResID;
            replace(LayoutInflater.from(mSourceView.getContext()).inflate(mTargetViewResID, mSourceParentView, false));
        }
    }

    public void replace(View targetView) {
        if (mCurrentView == targetView) {
            return;
        }
        if (targetView.getParent() != null) {
            ((ViewGroup) targetView.getParent()).removeView(targetView);
        }
        if (init()) {
            mTargetView = targetView;
            mSourceParentView.removeView(mCurrentView);
            mTargetView.setId(mSourceViewId);
            mSourceParentView.addView(mTargetView, mSourceViewIndexInParent, mSourceViewLayoutParams);
            mCurrentView = mTargetView;
        }
    }

    public void restore() {
        if (mSourceParentView != null) {
            mSourceParentView.removeView(mCurrentView);
            mSourceParentView.addView(mSourceView, mSourceViewIndexInParent, mSourceViewLayoutParams);
            mCurrentView = mSourceView;
            mTargetView = null;
            mTargetViewResID = -1;
        }
    }

    public View getSourceView() {
        return mSourceView;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public View getCurrentView() {
        return mCurrentView;
    }

    private boolean init() {
        if (mSourceParentView == null) {
            mSourceParentView = (ViewGroup) mSourceView.getParent();
            if (mSourceParentView == null) {
                Log.e(TAG, "the source view have not attach to any view");
                return false;
            }
            int count = mSourceParentView.getChildCount();
            for (int index = 0; index < count; index++) {
                if (mSourceView == mSourceParentView.getChildAt(index)) {
                    mSourceViewIndexInParent = index;
                    break;
                }
            }
        }
        return true;
    }

}
