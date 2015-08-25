package cn.limc.androidcharts.model;

import java.util.List;

import cn.limc.androidcharts.component.Axis;

public interface Degree {
    List<String> getDegrees(Axis axis);
    String valueForDegree(Axis axis, Object value);
    
    boolean isAutoFormatDegree();
    void setAutoFormatDegree(boolean autoFormatDegree);
    String getSourceFormat();
    void setSourceFormat(String format);
    String getTargetFormat();
    void setTargetFormat(String format);
}
