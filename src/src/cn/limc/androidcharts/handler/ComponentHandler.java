//
// ComponentHandler.java
// cn.limc.androidcharts.controller
//
// Created by limc on 2015-8-11.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.handler;

import cn.limc.androidcharts.component.Component;
import cn.limc.androidcharts.diagram.GridChart;

/**
 * ComponentHandler
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-11 limc create v1.0 <br>
 *
 */
public interface ComponentHandler {
    void load();
    void didLoad();
    void willDraw();
    void didDraw();
    
    GridChart getParent();
    void setParent(GridChart parent);
    
    Component getComponent();
    void setComponent(Component component);
}
