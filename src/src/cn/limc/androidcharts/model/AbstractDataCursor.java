//
// AbstractDataCursor.java
// cn.limc.androidcharts.model
//
// Created by limc on 2015-8-5.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

/**
 * AbstractDataCursor
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-5 limc create v1.0 <br>
 *
 */
public abstract class AbstractDataCursor implements DataCursor{
    protected DataCursorChangedListener dataCursorChangedListener;

    /**
     * @return the dataCursorChangedListener
     */
    public DataCursorChangedListener getDataCursorChangedListener() {
        return dataCursorChangedListener;
    }

    /**
     * @param dataCursorChangedListener the dataCursorChangedListener to set
     */
    public void setDataCursorChangedListener(DataCursorChangedListener dataCursorChangedListener) {
        this.dataCursorChangedListener = dataCursorChangedListener;
    }
}
