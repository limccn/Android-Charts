//
// AbstractDiagramController.java
// cn.limc.androidcharts.controller
//
// Created by limc on 2015-8-19.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.controller;

import cn.limc.androidcharts.diagram.GridChart;

/**
 * AbstractDiagramController
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-19 limc create v1.0 <br>
 *
 */
public class AbstractDiagramController implements DiagramController {
    
    protected GridChart gridChart;
    protected DiagramConfig diagramConfig = new DiagramConfig();
    
    /**
     * 
     */
    public AbstractDiagramController() {
        super();
    }
    
    public void applyController(GridChart chart){
        this.setGridChart(chart);
    }
    
    public void applyController(GridChart chart , DiagramConfig config){
        this.diagramConfig = config;
        this.setGridChart(chart);
    }
        
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.DiagramController#didApplyController()
     */
    @Override
    public void didApplyController() {
        
    }
    

    public void didSizeChanged(){
        
    }
    
    public void didLayoutChanged(){
        
    }
    
    public void willDraw(){
        
    }
    
    public void didDrawn(){
        
    }
    
    public void willPostInvalid(){
        
    }
    
    public void willInvalid(){
        
    }
    
    /**
     * @return the gridChart
     */
    public GridChart getGridChart() {
        return gridChart;
    }
    
    /**
     * @param gridChart the gridChart to set
     */
    public void setGridChart(GridChart gridChart) {
        this.gridChart = gridChart;
        if (gridChart != null) {
            gridChart.setDiagramController(this);
            didApplyController();
        } 
    }
    
    /**
     * @return the diagramConfig
     */
    public DiagramConfig getDiagramConfig() {
        return diagramConfig;
    }

    /**
     * @param diagramConfig the diagramConfig to set
     */
    public void setDiagramConfig(DiagramConfig diagramConfig) {
        this.diagramConfig = diagramConfig;
    }
    
    public class DiagramConfig{
         
    }
}
