//
// DiagramController.java
// cn.limc.androidcharts.controller
//
// Created by limc on 2015-8-18.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.controller;

import cn.limc.androidcharts.controller.AbstractDiagramController.DiagramConfig;
import cn.limc.androidcharts.diagram.GridChart;

/**
 * DiagramController
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-18 limc create v1.0 <br>
 *
 */
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
