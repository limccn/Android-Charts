/*
 * DiagramController.java
 * Android-Charts
 *
 * Created by limc on 2014/04/29.
 *
 * Copyright 2014 limc.cn All rights reserved.
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
package cn.limc.androidcharts.controller;

import cn.limc.androidcharts.controller.DiagramConfig;
import cn.limc.androidcharts.diagram.GridChart;

public interface DiagramController {
    public void didApplyController();
    public void didSizeChanged();
    public void didLayoutChanged();
    public void willDraw();
    public void didDrawn();
    public void willPostInvalid();
    public void willInvalid();
    
    public GridChart getGridChart();
    public void applyController(GridChart chart);
    public void applyController(GridChart chart , DiagramConfig config);
}
