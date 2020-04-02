package com.jordanmadrigal.notesapp.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import com.jordanmadrigal.notesapp.R;

public class LinedEditText extends AppCompatEditText {

    private Rect rect;
    private Paint paint;

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(getResources().getColor(R.color.darkYellow));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = ((View)this.getParent()).getHeight();

        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = this.rect;
        Paint paint = this.paint;

        int baseline = getLineBounds(0, r);

        for (int i = 0; i < numberOfLines; i++) {

            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);

            baseline += lineHeight;
        }

        super.onDraw(canvas);
    }
}
