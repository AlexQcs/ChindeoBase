package com.lazylibs.component.ui.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.lazylibs.R;


/**
 */

public class IconTextActionProvider extends BaseActionProvider {

    private TextView iconText;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public IconTextActionProvider(Context context) {
        super(context);
    }

    /**
     * get layout resource id
     *
     * @return layout Resource id;
     */
    @Override
    protected int layoutRes() {
        return R.layout.lazylibs_action_provider_icon_text;
    }

    /**
     * init view
     *
     * @param root action provider rootView
     * @param item action provider menuItem
     */
    @Override
    protected void initView(View root, MenuItem item) {
        iconText = root.findViewById(R.id.icon_text);
        iconText.setText(item.getTitle());
        iconText.setCompoundDrawablesWithIntrinsicBounds(
                item.getIcon(), null, null, null);
    }

    public void setText(String text) {
        if (iconText != null) {
            iconText.setText(text);
        }
    }

    public void setText(@StringRes int text) {
        if (iconText != null) {
            iconText.setText(text);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left,
                                                        @Nullable Drawable top,
                                                        @Nullable Drawable right,
                                                        @Nullable Drawable bottom) {
        if (iconText != null) {
            iconText.setCompoundDrawablesWithIntrinsicBounds(
                    left, top, right, bottom);
        }
    }

}
