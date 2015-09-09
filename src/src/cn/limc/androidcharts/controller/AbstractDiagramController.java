/*
 * AbstractDiagramController.java
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

import cn.limc.androidcharts.diagram.GridChart;

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
}
