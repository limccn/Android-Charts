package cn.limc.androidcharts.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.view.DataGridChart;

/**
 * Created by limc on 16/9/13.
 */
public class CustomLines {
    protected DataGridChart inChart;

    public static final boolean DEFAULT_DISPLAY_HORIZONTAL_LINES = true;
    public static final boolean DEFAULT_DISPLAY_VERTICAL_LINES = true;


    private boolean displayHorizontalLines = DEFAULT_DISPLAY_HORIZONTAL_LINES;
    private boolean displayVerticalLines = DEFAULT_DISPLAY_VERTICAL_LINES;

    protected List<CustomLine> horizontalLines = new ArrayList();
    protected List<CustomLine> verticalLines =new ArrayList();


    public CustomLines(DataGridChart inChart){
        super();
        this.inChart = inChart;
    }
    /**
     * <p>
     * draw cross line ,called when graph is touched
     * </p>
     * <p>
     * 十字線を書く、グラプをタッチたら、メソードを呼び
     * </p>
     * <p>
     * 在图表被点击后绘制十字线
     * </p>
     *
     * @param canvas
     */
    protected void drawVerticalLine(Canvas canvas) {
    }

    protected void drawHorizontalLine(Canvas canvas) {

        if (!displayHorizontalLines) {
            return;
        }
        if (horizontalLines == null) {
            return;
        }

        for(int i=0; i < horizontalLines.size();i++){
            CustomLine line = horizontalLines.get(i);
            if (line == null){
                continue;
            }

            Paint mPaint = new Paint();
            mPaint.setColor(line.getLineColor());
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(line.getLineWidth());

            if (line.isLineDash() && line.getLineDashEffect() != null) {
                mPaint.setPathEffect(line.getLineDashEffect());
            }

            float lineHLength = inChart.getDataQuadrant().getWidth() + inChart.getAxisY().getLineWidth();


            float ptY = (float) inChart.computeValueY(line.getLineValue());

            Path path = new Path();
            path.moveTo(inChart.getBorderWidth(), ptY);
            path.lineTo(inChart.getBorderWidth() + lineHLength, ptY);
            canvas.drawPath(path, mPaint);


            int fontSize = inChart.getSimpleGrid().getLatitudeFontSize();
            String textToDraw = inChart.formatAxisYDegree(line.getLineValue());

            Paint mPaintBox = new Paint();
            mPaintBox.setColor(line.getLabelBackgroudColor());
            // mPaintBox.setAlpha(80);
            mPaintBox.setStyle(Paint.Style.FILL);

            Paint mPaintBoxText = new Paint();
            mPaintBoxText.setColor(line.getLabelForegroudColor());
            mPaintBoxText.setAntiAlias(true);
            mPaintBoxText.setTextSize(fontSize);

            mPaintBoxText.setTextAlign(Paint.Align.LEFT);

            float textWidth = mPaintBoxText.measureText(textToDraw);

            if (line.getLabelPosition() == CustomLine.LABEL_POSITION_BOTH
                    || line.getLabelPosition() == CustomLine.LABEL_POSITION_LEFT
                    ) {

                PointF boxHS = new PointF(inChart.getBorderWidth(), ptY - fontSize / 2.f - 4);
                PointF boxHE = new PointF(inChart.getBorderWidth() + textWidth, ptY + fontSize / 2.f + 4);

                // draw a rectangle
                canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBox);
                // draw text
                canvas.drawText(textToDraw, boxHS.x, boxHS.y + fontSize, mPaintBoxText);
            }

            float startFromRight = inChart.getWidth() - inChart.getBorderWidth() ;
            if (line.getLabelPosition() == CustomLine.LABEL_POSITION_BOTH
                    || line.getLabelPosition() == CustomLine.LABEL_POSITION_RIGHT
                    ) {

                PointF boxHS = new PointF(startFromRight - textWidth, ptY - fontSize / 2.f - 4);
                PointF boxHE = new PointF(startFromRight, ptY + fontSize / 2.f + 4);

                // draw a rectangle
                canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBox);
                // draw text
                canvas.drawText(textToDraw, boxHS.x, boxHS.y + fontSize, mPaintBoxText);
            }
        }

    }

    public void draw(Canvas canvas){
        if (displayHorizontalLines) {
            drawHorizontalLine(canvas);
        }

        if (displayVerticalLines) {
            drawVerticalLine(canvas);
        }
    }

    public List<CustomLine> getVerticalLines() {
        return verticalLines;
    }

    public void setVerticalLines(List<CustomLine> verticalLines) {
        this.verticalLines = verticalLines;
    }

    public List<CustomLine> getHorizontalLines() {
        return horizontalLines;
    }

    public void setHorizontalLines(List<CustomLine> horizontalLines) {
        this.horizontalLines = horizontalLines;
    }

    public boolean isDisplayVerticalLines() {
        return displayVerticalLines;
    }

    public void setDisplayVerticalLines(boolean displayVerticalLines) {
        this.displayVerticalLines = displayVerticalLines;
    }

    public boolean isDisplayHorizontalLines() {
        return displayHorizontalLines;
    }

    public void setDisplayHorizontalLines(boolean displayHorizontalLines) {
        this.displayHorizontalLines = displayHorizontalLines;
    }

    public static final class CustomLine{
        public static final int LABEL_POSITION_NONE = 0;
        public static final int LABEL_POSITION_BOTH = 1;
        public static final int LABEL_POSITION_LEFT = 2;
        public static final int LABEL_POSITION_RIGHT = 3;

        private double lineValue = 0;
        private int labelPosition = LABEL_POSITION_BOTH;
        private int labelBackgroudColor = Color.LTGRAY;
        private int labelForegroudColor = Color.WHITE;

        private int lineColor = Color.LTGRAY;
        private float lineWidth = 2.0f;
        private boolean lineDash = true;
        private DashPathEffect lineDashEffect = new DashPathEffect(
                new float[] { 6, 3, 6, 3 }, 1);

        public double getLineValue() {
            return lineValue;
        }

        public void setLineValue(double lineValue) {
            this.lineValue = lineValue;
        }

        public int getLabelPosition() {
            return labelPosition;
        }

        public void setLabelPosition(int labelPosition) {
            this.labelPosition = labelPosition;
        }

        public int getLineColor() {
            return lineColor;
        }

        public void setLineColor(int lineColor) {
            this.lineColor = lineColor;
        }

        public float getLineWidth() {
            return lineWidth;
        }

        public void setLineWidth(float lineWidth) {
            this.lineWidth = lineWidth;
        }

        public boolean isLineDash() {
            return lineDash;
        }

        public void setLineDash(boolean lineDash) {
            this.lineDash = lineDash;
        }

        public DashPathEffect getLineDashEffect() {
            return lineDashEffect;
        }

        public void setLineDashEffect(DashPathEffect lineDashEffect) {
            this.lineDashEffect = lineDashEffect;
        }

        public int getLabelForegroudColor() {
            return labelForegroudColor;
        }

        public void setLabelForegroudColor(int labelForegroudColor) {
            this.labelForegroudColor = labelForegroudColor;
        }

        public int getLabelBackgroudColor() {
            return labelBackgroudColor;
        }

        public void setLabelBackgroudColor(int labelBackgroudColor) {
            this.labelBackgroudColor = labelBackgroudColor;
        }
    }
}
