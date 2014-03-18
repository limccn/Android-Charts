/*
 * TitleValueColorEntity.java
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

package cn.limc.androidcharts.entity;

/**
 * <p>
 * Entity data which its value is titled and with color
 * </p>
 * <p>
 * 表示用データです、値はタイトルや色の設定は可能です。
 * </p>
 * <p>
 * 支持显示标题的值的实体对象
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/29 12:19:06
 * @see TitleValueEntity
 * 
 */
public class TitleValueColorEntity extends TitleValueEntity implements
		IHasColor {

	/**
	 * <p>
	 * Color
	 * </p>
	 * <p>
	 * 色
	 * </p>
	 * <p>
	 * 颜色
	 * </p>
	 * 
	 * @see Color
	 */
	private int color;

	/**
	 * 
	 * <p>
	 * Constructor of TitleValueColorEntity
	 * </p>
	 * <p>
	 * TitleValueColorEntity类对象的构造函数
	 * </p>
	 * <p>
	 * TitleValueColorEntityのコンストラクター
	 * </p>
	 * 
	 * @param title
	 *            <p>
	 *            Title
	 *            </p>
	 *            <p>
	 *            タイトル
	 *            </p>
	 *            <p>
	 *            标题
	 *            </p>
	 * @param value
	 *            <p>
	 *            Value
	 *            </p>
	 *            <p>
	 *            値
	 *            </p>
	 *            <p>
	 *            值
	 *            </p>
	 * @param color
	 *            <p>
	 *            Color
	 *            </p>
	 *            <p>
	 *            色
	 *            </p>
	 *            <p>
	 *            颜色
	 *            </p>
	 */
	public TitleValueColorEntity(String title, float value, int color) {
		super(title, value);
		this.color = color;
	}

	/**
	 * 
	 * <p>
	 * Constructor of TitleValueColorEntity
	 * </p>
	 * <p>
	 * TitleValueColorEntity类对象的构造函数
	 * </p>
	 * <p>
	 * TitleValueColorEntityのコンストラクター
	 * </p>
	 * 
	 */
	public TitleValueColorEntity() {
		super();
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}
}
