package cn.limc.androidcharts.view;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;

/**
 * Created by limc on 16/4/6.
 */
public class TickChart extends SlipAreaChart {

    /*
	 * (non-Javadoc)
	 *
	 * @param context
	 *
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
    public TickChart(Context context) {
        super(context);
    }

    /*
     * (non-Javadoc)
     *
     * @param context
     *
     * @param attrs
     *
     * @param defStyle
     *
     * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
     * AttributeSet, int)
     */
    public TickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*
     * (non-Javadoc)
     *
     * @param context
     *
     * @param attrs
     *
     *
     *
     * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
     * AttributeSet)
     */
    public TickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    protected void calcValueRangeFormatForAxis() {
        int rate = getDataMultiple();

		if (this.maxValue < 3000) {
			rate = 1;
		} else if (this.maxValue >= 3000 && this.maxValue < 5000) {
			rate = 5;
		} else if (this.maxValue >= 5000 && this.maxValue < 30000) {
			rate = 10;
		} else if (this.maxValue >= 30000 && this.maxValue < 50000) {
			rate = 50;
		} else if (this.maxValue >= 50000 && this.maxValue < 300000) {
			rate = 100;
		} else if (this.maxValue >= 300000 && this.maxValue < 500000) {
			rate = 500;
		} else if (this.maxValue >= 500000 && this.maxValue < 3000000) {
			rate = 1000;
		} else if (this.maxValue >= 3000000 && this.maxValue < 5000000) {
			rate = 5000;
		} else if (this.maxValue >= 5000000 && this.maxValue < 30000000) {
			rate = 10000;
		} else if (this.maxValue >= 30000000 && this.maxValue < 50000000) {
			rate = 50000;
		} else {
			rate = 100000;
		}

        // 等分轴修正
        if (simpleGrid.getLatitudeNum() > 0 && rate > 1
                && (long) (this.minValue) % rate != 0) {
            // 最大值加上轴差
            this.minValue = (long) this.minValue
                    - (long) (this.minValue) % rate;
        }
        // 等分轴修正
        if (simpleGrid.getLatitudeNum() > 0
                && (long) (this.maxValue - this.minValue)
                % (simpleGrid.getLatitudeNum() * rate) != 0) {
            // 最大值加上轴差
            this.maxValue = (long) this.maxValue
                    + simpleGrid.getLatitudeNum() * rate
                    - (long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate);
        }
    }

    @Override
    protected void calcValueRange() {
        if (null == this.linesData) {
            this.maxValue = 0;
            this.minValue = 0;
            return;
        }
        if (this.linesData.size() > 0) {
            this.calcDataValueRange();
			this.calcValueRangePaddingZero();
        } else {
            this.maxValue = 0;
            this.minValue = 0;
        }

        this.calcValueRangeFormatForAxis();
        if (autoBalanceValueRange){
            this.balanceRange();
        }
    }

    @Override
    public void setLinesData(List<LineEntity<DateValueEntity>> linesData) {
        super.setLinesData(linesData);

        if (null == this.linesData) {
            return;
        }
        if (0 == this.linesData.size()) {
            return;
        }
        LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
                .get(0);
        if (line == null) {
            return;
        }
        if (line.isDisplay() == false) {
            return;
        }
        List<DateValueEntity> lineData = line.getLineData();
        if (lineData != null) {
            this.setMaxDisplayNumber(lineData.size());
            this.setDisplayNumber(lineData.size());
        }
    }
}
