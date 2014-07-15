package com.apptitive.content_display.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.apptitive.content_display.R;
import com.apptitive.content_display.utilities.Utilities;
import com.dibosh.experiments.android.support.customfonthelper.AndroidCustomFontSupport;

/**
 * Created by Sharif on 5/26/2014.
 */
public class BanglaTextView extends JustifiedTextView {

    TypedArray typedArray;
    Typeface myTypeface;
    String banglaText;
    String fontName;
    boolean justify;

    public BanglaTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public BanglaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BanglaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setBanglaText(String banglaText) {
        setBanglaSupportedText(banglaText, justify);
        if (typedArray != null) {
            typedArray.recycle();
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        if (attrs != null) {
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
                fontName = typedArray.getString(R.styleable.CustomTextView_fontName);
                myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                banglaText = typedArray.getString(R.styleable.CustomTextView_banglaText);
                justify = Boolean.parseBoolean(typedArray.getString(R.styleable.CustomTextView_justify));

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (fontName != null) {
               setUpTypeFace(myTypeface);
            }

            setBanglaSupportedText(banglaText, justify);

            typedArray.recycle();
        }
    }

    private void setUpTypeFace(Typeface myTypeface) {
        if (!Utilities.isBanglaAvailable()&Utilities.isBuildAboveThirteen()){
            //nothing just add default font
        }else {
            setTypeface(myTypeface);
        }
    }

    private void setBanglaSupportedText(String banglaText, boolean justify) {
        if (banglaText != null) {
            setText(Utilities.isBuildAboveThirteen() ? banglaText : AndroidCustomFontSupport.getCorrectedBengaliFormat(banglaText, myTypeface, -1));
        }
    }
}
