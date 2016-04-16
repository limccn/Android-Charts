/*
 * DataQuadrant.java
 * Android-Charts
 *
 * Created by limc on 2011/05/29.
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

package cn.limc.androidcharts.common;

import cn.limc.androidcharts.axis.IAxis;
import cn.limc.androidcharts.view.GridChart;

/**
 * DataQuadrant
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2014/09/01 limc create v1.0 <br>
 *
 */
public class DataQuadrant extends Quadrant {

    /**
     * @param inChart
     */
    public DataQuadrant(GridChart inChart) {
        super(inChart);
        // TODO Auto-generated constructor stub
    }

    public float getWidth() {
        return inChart.getWidth() - inChart.getAxisY().getWidth() - 2 * inChart.getBorderWidth()
                - inChart.getAxisY().getLineWidth();
    }

    public float getHeight() {
        return inChart.getHeight() - inChart.getAxisX().getHeight() - 2 * inChart.getBorderWidth()
                - inChart.getAxisX().getLineWidth();
    }

    public float getStartX() {
        if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
            return 2 * inChart.getBorderWidth() + inChart.getAxisY().getWidth() + inChart.getAxisY().getLineWidth();
        } else {
            return 2 * inChart.getBorderWidth();
        }
    }
    
    public float getStartY() {
        return inChart.getBorderWidth();
    }
}
