package cn.limc.androidcharts.event;

import cn.limc.androidcharts.diagram.GridChart;
import android.view.MotionEvent;



public abstract class GestureDetector {

    protected GridChart inChart;
    
    protected OnGestureListener onGestureListener;
    
    public GestureDetector(GridChart inChart, OnGestureListener listener) {
        this.inChart = inChart;
        this.onGestureListener = listener;
    }
    public abstract boolean onTouchEvent(MotionEvent event);

    protected float calcDistance(MotionEvent event) {
        if(event.getPointerCount() <= 1) {
            return 0f;
        }else{
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }
    }

    /**
     * @return the inChart
     */
    public GridChart getInChart() {
        return inChart;
    }

    /**
     * @param inChart the inChart to set
     */
    public void setInChart(GridChart inChart) {
        this.inChart = inChart;
    }
    
    public interface OnGestureListener{
        
    }
}
