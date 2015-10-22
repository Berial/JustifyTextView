package berial.ml.justifytextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 两端对齐的TextView, 如:
 * ----------------------
 * |文字1            文字2|
 * ----------------------
 * Created by Berial on 15/10/22.
 */
public class JustifyTextView extends LinearLayout {

    private TextView mLeftText, mRightText;

    public JustifyTextView(Context context) {
        this(context, null, 0);
    }

    public JustifyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustifyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);

        final TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.JustifyTextView);
        float mLeftTextSize;
        float mRightTextSize;
        int mLeftIconPadding;
        int mRightIconPadding;
        int mLeftTextColor;
        int mRightTextColor;
        Drawable mLeftIcon;
        Drawable mRightIcon;
        CharSequence strLeftText;
        CharSequence strRightText;
        try {
            strLeftText = t.getText(R.styleable.JustifyTextView_leftText);
            strRightText = t.getText(R.styleable.JustifyTextView_rightText);

            mLeftIcon = t.getDrawable(R.styleable.JustifyTextView_leftIcon);
            mRightIcon = t.getDrawable(R.styleable.JustifyTextView_rightIcon);

            final int defaultColor = Color.BLACK;

            mLeftTextColor = t.getColor(R.styleable.JustifyTextView_leftTextColor, defaultColor);
            mRightTextColor = t.getColor(R.styleable.JustifyTextView_rightTextColor, defaultColor);

            final float defaultTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                    getResources().getDisplayMetrics());

            mLeftTextSize = t.getDimension(R.styleable.JustifyTextView_leftTextSize, defaultTextSize);
            mRightTextSize = t.getDimension(R.styleable.JustifyTextView_rightTextSize, defaultTextSize);


            mLeftIconPadding = t.getDimensionPixelSize(R.styleable.JustifyTextView_leftIconPadding, 0);
            mRightIconPadding = t.getDimensionPixelSize(R.styleable.JustifyTextView_rightIconPadding, 0);
        } finally {
            t.recycle();
        }
        mLeftText = new TextView(context);
        mRightText = new TextView(context);

        mLeftText.setGravity(Gravity.CENTER_VERTICAL);
        mRightText.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        mLeftText.setText(strLeftText);
        mRightText.setText(strRightText);

        mLeftText.setTextColor(mLeftTextColor);
        mRightText.setTextColor(mRightTextColor);

        mLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        mRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);

        if(mLeftIcon != null) {
            mLeftIcon.setBounds(0, 0, mLeftIcon.getIntrinsicWidth(),
                    mLeftIcon.getIntrinsicHeight());
            mLeftText.setCompoundDrawables(mLeftIcon, null, null, null);
        }

        if(mRightIcon != null) {
            mRightIcon.setBounds(0, 0, mRightIcon.getIntrinsicWidth(),
                    mRightIcon.getIntrinsicHeight());
            mRightText.setCompoundDrawables(null, null, mRightIcon, null);
        }

        mLeftText.setCompoundDrawablePadding(mLeftIconPadding);
        mRightText.setCompoundDrawablePadding(mRightIconPadding);

        addView(mLeftText, -2, -1);//-2: wrap_content, -1: match_parent
        addView(mRightText, -1, -1);
    }

    public void setLeftText(CharSequence text) {
        mLeftText.setText(text);
    }

    public void setLeftText(int stringRes) {
        mLeftText.setText(stringRes);
    }

    public void setRightText(CharSequence text) {
        mRightText.setText(text);
    }

    public void setRightText(int stringRes) {
        mRightText.setText(stringRes);
    }

    //TODO 方法拓展

}
