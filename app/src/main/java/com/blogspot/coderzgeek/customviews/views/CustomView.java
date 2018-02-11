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

public class CustomView extends View {


    private static final int SQUARE_SIZE_DEF = 50;
    private static final int SQUARE_ELEVATION = 10;
    private static final int SQUARE_TEXTSIZE = 15;
    private static final int SQUARE_DY = 10;
    private static final String TEXT_INPUT = "FILE";

    int indent, width, height, elevation, textOffset, textSize;

    Point a, b, c, d, e, f, m, o;
    Path path;

    private Rect mRectSquare;
    private Rect mRectSquare2;

    private Paint pObject;
    private Paint pTextBoundingBox;
    private Paint pText;
    private Paint pShadow;

    private int cSquareColor;
    private int cTextBoxColor;
    private int dSquareSize;
    private String textInput = "";


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        if (set == null)
            return;
        getAttributes(set);

        mRectSquare = new Rect();

        // paint object for the shadow effect
        pShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        pShadow.setColor(Color.GRAY);
        pShadow.setStyle(Paint.Style.FILL);
        pShadow.setAlpha(60);

        // paint object for main square path
        pObject = new Paint(Paint.ANTI_ALIAS_FLAG);
        pObject.setColor(cSquareColor);

        // paint object for the bounding box
        pTextBoundingBox = new Paint(Paint.ANTI_ALIAS_FLAG);
        pTextBoundingBox.setColor(cTextBoxColor);
        pTextBoundingBox.setStyle(Paint.Style.FILL);
        cTextBoxColor = Color.DKGRAY;

        // paint object for the text
        pText = new Paint(Paint.ANTI_ALIAS_FLAG);
        pText.setColor(Color.WHITE);
        pText.setTextSize(textSize);
        pText.setTextAlign(Paint.Align.CENTER);
        pText.setStyle(Paint.Style.FILL);

        // Initializing all the required dimenstions and calculations
        initDimens();
    }

    private void getAttributes(@Nullable AttributeSet set) {
        //Getting Attributes from xml
        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        cSquareColor = ta.getColor(R.styleable.CustomView_square_color, Color.BLACK);
        dSquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size, SQUARE_SIZE_DEF);
        elevation = ta.getDimensionPixelSize(R.styleable.CustomView_square_elevation, SQUARE_ELEVATION);
        textOffset = ta.getDimensionPixelSize(R.styleable.CustomView_square_dy, SQUARE_DY);
        textSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_text_size, SQUARE_TEXTSIZE);
        textInput = ta.getString(R.styleable.CustomView_square_text);
        cTextBoxColor = ta.getColor(R.styleable.CustomView_square_text_box_color, Color.GRAY);

        ta.recycle();
    }

    private void initDimens() {
        indent = dSquareSize / 4;
        width = dSquareSize;
        height = (dSquareSize * 3 / 2) / 1;

        // For the shape
        a = new Point(elevation, 0);
        b = new Point(width + elevation - indent, 0);
        c = new Point(width + elevation, indent);
        d = new Point(width + elevation, height);
        e = new Point(elevation, height);
        f = new Point(width + elevation - indent, indent);

        // For the Binding Box
        m = new Point(elevation + indent, height - indent - (3 * textSize / 2));
        o = new Point(width + elevation + indent, height - indent);
    }

    public void setmTextColor(int color, Context c) {
        cTextBoxColor = ContextCompat.getColor(c, color);
        pTextBoundingBox.setColor(cTextBoxColor);
    }

    public void setText(String txt) {
        textInput = txt;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v("[View name] onMeasure w", MeasureSpec.toString(widthMeasureSpec));
        Log.v("[View name] onMeasure h", MeasureSpec.toString(heightMeasureSpec));

        int desiredWidth = dSquareSize + indent + indent + elevation;
        int desiredHeight = (dSquareSize * 3 / 2) / 1 + elevation;

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

        canvas.drawRect(0, elevation, width, height + elevation, pShadow);

        // path for the object
        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        // Path Created for the shape
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(f.x, f.y);
        path.lineTo(c.x, c.y);
        path.lineTo(d.x, d.y);
        path.lineTo(e.x, e.y);
        path.close();

        // Draws the main shape --> Once with white fill and then with Black stroke
        pObject.setColor(Color.WHITE);
        pObject.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, pObject);
        pObject.setStyle(Paint.Style.STROKE);
        pObject.setStrokeWidth(2);
        pObject.setColor(Color.BLACK);
        canvas.drawPath(path, pObject);

        // Draws the bounding box for text
        canvas.drawRect(m.x, m.y, o.x, o.y, pTextBoundingBox);
        // Draws the text layer
        canvas.drawText(textInput, (m.x + o.x) / 2, ((m.y + o.y) / 2) - ((pText.descent() + pText.ascent()) / 2), pText);
    }
}
