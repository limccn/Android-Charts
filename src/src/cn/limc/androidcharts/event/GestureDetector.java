/*
 * GestureDetector.java
 * Android-Charts
 *
 * Created by limc on 2013.
 *
 * Copyright 2011 limc.cn All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
     * @return the parent
     */
    public GridChart getInChart() {
        return inChart;
    }

    /**
     * @param parent the parent to set
     */
    public void setInChart(GridChart inChart) {
        this.inChart = inChart;
    }
    
    public interface OnGestureListener{
        
    }
}
