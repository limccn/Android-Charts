/*
 * AbstractDegree.java
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
package cn.limc.androidcharts.model;


public abstract class AbstractDegree implements Degree {

    public static final boolean DEFAULT_AUTO_FORMAT_DEGREE = true;
    
    protected boolean autoFormatDegree = DEFAULT_AUTO_FORMAT_DEGREE;
    
    protected String sourceFormat;
    protected String targetFormat;


    /**
     * @return the autoFormatDegree
     */
    public boolean isAutoFormatDegree() {
        return autoFormatDegree;
    }

    /**
     * @param autoFormatDegree the autoFormatDegree to set
     */
    public void setAutoFormatDegree(boolean autoFormatDegree) {
        this.autoFormatDegree = autoFormatDegree;
    }

    /**
     * @return the sourceFormat
     */
    public String getSourceFormat() {
        return sourceFormat;
    }

    /**
     * @param sourceFormat the sourceFormat to set
     */
    public void setSourceFormat(String sourceFormat) {
        this.sourceFormat = sourceFormat;
    }

    /**
     * @return the targetFormat
     */
    public String getTargetFormat() {
        return targetFormat;
    }

    /**
     * @param targetFormat the targetFormat to set
     */
    public void setTargetFormat(String targetFormat) {
        this.targetFormat = targetFormat;
    }
}
