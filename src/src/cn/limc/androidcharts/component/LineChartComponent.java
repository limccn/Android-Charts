//
// LineChartComponent.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-8-3.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Points;

/**
 * LineChartComponent
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-3 limc create v1.0 <br>
 *
 */
public class LineChartComponent extends DataComponent {
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        
        drawLines(canvas);

    }
    
//  protected void drawLines(Canvas canvas) {
//  if (null == linesData) {
//      return;
//  }
//  if (linesData.size() == 0) {
//      return;
//  }
//
//  float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//  for(int i=0; i< linesData.size() ; i++){
//      LineEntity table = (LineEntity)linesData.getChartTable(i);
//      if (null == table) {
//          continue;
//      }
//      if(table.size() == 0){
//          continue;
//      }
//      
//      Paint mPaint = new Paint();
//      mPaint.setColor(table.getLineColor());
//      mPaint.setAntiAlias(true);
//      
//      float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//      for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//          IMeasurable point = (IMeasurable)table.get(j-1);
//          IMeasurable nextpoint = (IMeasurable)table.get(j);
//          
//          Points lineMole = new Points();
//          lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
//          lineMole.setLinePaint(mPaint);
//          lineMole.draw(canvas);
//
//          // next x
//          stickX = stickX + stickWidth;
//      }
//  }
//}
    
    protected void drawLines(Canvas canvas) {
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }

        float stickWidth = getPaddingWidth() / dataCursor.getDisplayNumber();

        for(int i=0; i< chartData.size() ; i++){
            LineEntity table = (LineEntity)chartData.getChartTable(i);
            if (null == table) {
                continue;
            }
            if(table.size() == 0){
                continue;
            }
            
            Paint mPaint = new Paint();
            mPaint.setColor(table.getLineColor());
            mPaint.setAntiAlias(true);
            
            float stickX = getPaddingStartX() + stickWidth / 2;
            for (int j = dataCursor.getDisplayFrom()+1; j < dataCursor.getDisplayTo(); j++) {
                IMeasurable point = (IMeasurable)table.get(j-1);
                IMeasurable nextpoint = (IMeasurable)table.get(j);
                
                Points lineMole = new Points();
                lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
                lineMole.setLinePaint(mPaint);
                lineMole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
}
