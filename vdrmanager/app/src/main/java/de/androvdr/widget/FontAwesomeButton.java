package de.androvdr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by lado on 04.05.15.
 */
public class FontAwesomeButton extends Button {


    public FontAwesomeButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFontAwesome();

    }

    public FontAwesomeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFontAwesome();

    }

    public FontAwesomeButton(Context context) {
        super(context);
        initFontAwesome();
    }

    private void initFontAwesome(){
        if(isInEditMode() == false) {
            setTypeface(FontAwesome.getFontAwesome(getContext().getApplicationContext().getApplicationContext()));
        }
    }

}
