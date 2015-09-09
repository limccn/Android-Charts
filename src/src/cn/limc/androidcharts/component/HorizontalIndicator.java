/*
 * HorizontalIndicator.java
 * Android-Charts
 *
 * Created by limc on 2014.
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
package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * HorizontalIndicator
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-10 limc create v1.0 <br>
 *
 */
public abstract class HorizontalIndicator extends AbstractIndicator{

    /**
     * 
     */
    public HorizontalIndicator() {
        // TODO Auto-generated constructor stub
    }
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.AbstractIndicator#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        drawLine(canvas);
    }
    
    protected void drawLine(Canvas canvas) {
        DataComponent bindComponent = componentForIndicator(this);

        if (bindComponent == null) {
            return;
        }
        if (bindComponent.getParent() == null) {
            return;
        }
        PointF touchPoint = bindComponent.getParent().getTouchPoint();
        if (touchPoint == null) {
            return;
        }
        if (!isValidTouchPoint(touchPoint)) {
            return;
        }
        Paint mPaint = new Paint();
        mPaint.setColor(lineColor);
        if (displayLine) {
            canvas.drawLine(getStartX(), bindComponent.getParent().getTouchPoint().y, getEndX(),
                    bindComponent.getParent().getTouchPoint().y, mPaint);
        }
    }
}
