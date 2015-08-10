//
// AbstractLayer.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-7-30.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import cn.limc.androidcharts.diagram.GridChart;

/**
 * AbstractLayer
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-30 limc create v1.0 <br>
 *
 */
public abstract class AbstractLayer implements Layer{
    GridChart parent;
    
    public AbstractLayer() {
        super();
    }

    /**
     * @return the parent
     */
    public GridChart getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(GridChart parent) {
        this.parent = parent;
    }
    
}
