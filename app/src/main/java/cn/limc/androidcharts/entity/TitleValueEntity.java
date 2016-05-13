/*
 * TitleValueEntity.java
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
 * 
 * <p>
 * Entity data which its value is titled
 * </p>
 * <p>
 * 表示用データです、値はタイトル設定は可能です。
 * </p>
 * <p>
 * 支持显示标题的值的实体对象
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/29 12:19:06
 */
public class TitleValueEntity {

	/**
	 * <p>
	 * Title
	 * </p>
	 * <p>
	 * タイトル
	 * </p>
	 * <p>
	 * 标题
	 * </p>
	 */
	private String title;

	/**
	 * <p>
	 * Value
	 * </p>
	 * <p>
	 * 値
	 * </p>
	 * <p>
	 * 值
	 * </p>
	 */
	private float value;

	/**
	 * 
	 * <p>
	 * Constructor of TitleValueEntity
	 * </p>
	 * <p>
	 * TitleValueEntity类对象的构造函数
	 * </p>
	 * <p>
	 * TitleValueEntityのコンストラクター
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
	 */
	public TitleValueEntity(String title, float value) {
		super();
		this.title = title;
		this.value = value;
	}

	/**
	 * 
	 * <p>
	 * Constructor of TitleValueEntity
	 * </p>
	 * <p>
	 * TitleValueEntity类对象的构造函数
	 * </p>
	 * <p>
	 * TitleValueEntityのコンストラクター
	 * </p>
	 * 
	 */
	public TitleValueEntity() {
		super();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}
}
