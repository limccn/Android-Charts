/*
 * ComponentHandler.java
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
