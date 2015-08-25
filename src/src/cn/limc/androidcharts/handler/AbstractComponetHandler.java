//
// AbstractComponetHandler.java
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
 * AbstractComponetHandler
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
public class AbstractComponetHandler implements ComponentHandler {
    
    GridChart parent;
    Component component;

    public AbstractComponetHandler(){
        super();
    }
    
    public AbstractComponetHandler(Component component) {
        super();
        if (component != null) {
            this.component = component;
            this.component.setComponentHandler(this);
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.ComponentController#load()
     */
    @Override
    public void load() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.ComponentController#didLoad()
     */
    @Override
    public void didLoad() {
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.ComponentController#willDraw()
     */
    @Override
    public void willDraw() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.ComponentController#didDraw()
     */
    @Override
    public void didDraw() {
        // TODO Auto-generated method stub
        
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

    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(Component component) {
        this.component = component;
        this.component.setComponentHandler(this);
    }

}
