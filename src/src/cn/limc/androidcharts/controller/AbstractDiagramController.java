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
         public int diagramBorderWidth;
         public int diagramBorderColor;
        
         public int axisLineIsHide;
         public int axisLineWidth;
         public int axisLineColor;
         public int axisTitleIsHide;
         public int axisTitleFontSize;
         public int axisTitleColor;
         public int axisDegreeNum;
         public int axisDegreeIsHide;
         public int axisDegreeFontSize;
         public int axisDegreeColor;
         
         public int verticalAxisWidth;
         public int verticalAxisType;
         public int horizontalAxisHeight;
         public int horizontalAxisType;
         
         public int leftAxisLineIsHide;
         public int leftAxisLineWidth;
         public int leftAxisLineColor;
         public int leftAxisTitleIsHide;
         public int leftAxisTitleFontSize;
         public int leftAxisTitleColor;
         public int leftAxisDegreeNum;
         public int leftAxisDegreeIsHide;
         public int leftAxisDegreeFontSize;
         public int leftAxisDegreeColor;
         
         public int bottomAxisLineIsHide;
         public int bottomAxisLineWidth;
         public int bottomAxisLineColor;
         public int bottomAxisTitleIsHide;
         public int bottomAxisTitleFontSize;
         public int bottomAxisTitleColor;
         public int bottomAxisDegreeNum;
         public int bottomAxisDegreeIsHide;
         public int bottomAxisDegreeFontSize;
         public int bottomAxisDegreeColor;
         
         public int topAxisLineIsHide;
         public int topAxisLineWidth;
         public int topAxisLineColor;
         public int topAxisTitleIsHide;
         public int topAxisTitleFontSize;
         public int topAxisTitleColor;
         public int topAxisDegreeNum;
         public int topAxisDegreeIsHide;
         public int topAxisDegreeFontSize;
         public int topAxisDegreeColor;
         
         public int rightAxisLineIsHide;
         public int rightAxisLineWidth;
         public int rightAxisLineColor;
         public int rightAxisTitleIsHide;
         public int rightAxisTitleFontSize;
         public int rightAxisTitleColor;
         public int rightAxisTilteNum;
         
         public int gridIsHide;
         public int gridLineStyle;
         public int gridLineWidth;
         public int gridLineColor;
         
         public int longitudeIsHide;
         public int longitudeLineStyle;
         public int longitudeLineWidth;
         public int longitudeLineColor;
         
         public int latitudeIsHide;
         public int latitudeLineStyle;
         public int latitudeLineWidth;
         public int latitudeLineColor;
         
         public int indicatorIsHide;
         public int indicatorLineStyle;
         public int indicatorLineWidth;
         public int indicatorLineColor;
         public int indicatorTitleFontSize;
         public int indicatorTitleColor;
         
         public int verticalIndicatorIsHide;
         public int verticalIndicatorLineStyle;
         public int verticalIndicatorLineWidth;
         public int verticalIndicatorLineColor;
         public int verticalIndicatorTitleFontSize;
         public int verticalIndicatorTitleColor;
         
         public int horizontalIndicatorIsHide;
         public int horizontalIndicatorLineStyle;
         public int horizontalIndicatorLineWidth;
         public int horizontalIndicatorLineColor;
         public int horizontalIndicatorTitleFontSize;
         public int horizontalIndicatorTitleColor;
         
         public int dividedLayoutToLeft;
         public int dividedLayoutToRight;
         public int dividedLayoutToBottom;
         public int dividedLayoutToTop;
         public int dividedLayoutPosition;
         
         public int kShapeSpacing;
         public int kShapeType;
         public int kShapeBorderColor;
         public int kShapeBorderWidth;
         public int kShapeBorderType;
         public int kShapeFillColor;
         public int kShapePositiveBorderColor;
         public int kShapePositiveFillColor;
         public int kShapeNagativeBorderColor;
         public int kShapeNagativeFillColor;
         public int kShapeCrossStarColor;
         
         public int barShapeSpacing;
         public int barShapeType;
         public int barShapeBorderColor;
         public int barShapeBorderWidth;
         public int barShapeBorderType;
         public int barShapeFillColor;
         
         public int pointsShapeType;
         public int pointsShapeLineColor;
         public int pointsShapeLineWidth;
         public int pointsShapeLineType;
         
         public int scatterShapeType;
         public int scatterShapeLineColor;
         public int scatterShapeLineWidth;
         public int scatterShapeLineType;
         
         public int areaShapeType;
         public int areaShapeBorderColor;
         public int areaShapeBorderWidth;
         public int areaShapeBorderType;
         public int areaShapeFillColor;
    }
}
