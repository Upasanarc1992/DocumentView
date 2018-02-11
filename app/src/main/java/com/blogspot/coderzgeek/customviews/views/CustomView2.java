package com.blogspot.coderzgeek.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.blogspot.coderzgeek.customviews.R;

/**
 * Created by Levi on 1/13/2017.
 */

public class CustomView2 extends View {


    private static final int SQUARE_SIZE_DEF = 200;
    private static final int SQUARE_ELEVATION = 25;
    private static final int SQUARE_TEXTSIZE = 30;
    private static final int SQUARE_DY = 40;
    int i, x, y, dx, dy, size;
    Point a, b, c, d, e, f, m, o;
    Path path;

    private Rect mRectSquare;
    private Rect mRectSquare2;
    private Paint mPaintSquare;
    private Paint mPaintSquaree;
    private int mSquareColor;
    private int mTextColor;
    private int mSquareSize;
    private String s="";
    public CustomView2(Context context) {
        super(context);

        init(null);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSquaree = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (set == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        mSquareColor = ta.getColor(R.styleable.CustomView_square_color, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE_DEF);
        dx = ta.getDimensionPixelSize(R.styleable.CustomView_square_elevation, SQUARE_ELEVATION);
        dy = ta.getDimensionPixelSize(R.styleable.CustomView_square_dy, SQUARE_DY);
        size = ta.getDimensionPixelSize(R.styleable.CustomView_square_text_size, 40);

        mPaintSquare.setColor(mSquareColor);
        mTextColor=Color.DKGRAY;
        mPaintSquaree.setColor(mTextColor);


        i = mSquareSize / 4;
        x = mSquareSize;
        y = (mSquareSize * 3 / 2) / 1;

        a = new Point(dx, 0);
        b = new Point(x + dx - i, 0);
        c = new Point(x + dx, i);
        d = new Point(x + dx, y);
        e = new Point(dx, y);
        f = new Point(x + dx - i, i);


        m = new Point(dx + i, y - i - (3 * size / 2));
        o = new Point(x + dx + i, y - i);


        ta.recycle();
    }

    public void setmTextColor(int color, Context c){
        mTextColor=ContextCompat.getColor(c, color);
        mPaintSquaree.setColor(mTextColor);
    }

    public void setText(String txt){
        s=txt;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v("[View name] onMeasure w", MeasureSpec.toString(widthMeasureSpec));
        Log.v("[View name] onMeasure h", MeasureSpec.toString(heightMeasureSpec));

        int desiredWidth = mSquareSize + i + i + dx;
        int desiredHeight = (mSquareSize * 3 / 2) / 1 + dx;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this textSize
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this textSize
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaintSquare.setColor(Color.GRAY);
        mPaintSquare.setStyle(Paint.Style.FILL);
        mPaintSquare.setAlpha(60);
        canvas.drawRect(0, dx, x, y + dx, mPaintSquare);
        mPaintSquare.setAlpha(100);
        path=new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(f.x, f.y);
        path.lineTo(c.x, c.y);
        path.lineTo(d.x, d.y);
        path.lineTo(e.x, e.y);
        path.close();
        mPaintSquare.setColor(Color.WHITE);
        canvas.drawPath(path, mPaintSquare);
        mPaintSquare.setStyle(Paint.Style.STROKE);
        mPaintSquare.setStrokeWidth(2);

        mPaintSquare.setColor(Color.BLACK);

        canvas.drawPath(path, mPaintSquare);

        mPaintSquaree.setColor(mTextColor);
        mPaintSquaree.setStyle(Paint.Style.FILL);

        canvas.drawRect(m.x, m.y, o.x, o.y, mPaintSquaree);

        mPaintSquare.setColor(Color.WHITE);
        mPaintSquare.setTextSize(size);
        mPaintSquare.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(s, (m.x + o.x) / 2, ((m.y + o.y) / 2) - ((mPaintSquare.descent() + mPaintSquare.ascent()) / 2), mPaintSquare);


    }
}
