/*
 * BaseActivity.java
 * Android-Charts Demo
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

package cn.limc.demo.common;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.MACDEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.entity.StickEntity;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class BaseActivity extends Activity {
    
    protected List<IStickEntity> ohlc;
    protected List<IStickEntity> vol;
    protected List<IStickEntity> volc;
    protected List<DateValueEntity> dv1;
    protected List<DateValueEntity> dv2;
    protected List<IStickEntity> macd;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVOL();
        initOHLC();
        initVOLC();
        initDV1();
        initDV2();
        initMACD();
    }
    
    protected void initVOLC() {
        List<IStickEntity> stick = new ArrayList<IStickEntity>();
        this.volc = new ArrayList<IStickEntity>();

        stick.add(new ColoredStickEntity(406000, 0, 20110825, Color.RED));
        stick.add(new ColoredStickEntity(232000, 0, 20110824, Color.RED));
        stick.add(new ColoredStickEntity(355000, 0, 20110823, Color.BLUE));
        stick.add(new ColoredStickEntity(437000, 0, 20110822, Color.RED));
        stick.add(new ColoredStickEntity(460000, 0, 20110819, Color.BLUE));
        stick.add(new ColoredStickEntity(422000, 0, 20110818, Color.LTGRAY));
        stick.add(new ColoredStickEntity(502000, 0, 20110817, Color.RED));
        stick.add(new ColoredStickEntity(509000, 0, 20110816, Color.RED));
        stick.add(new ColoredStickEntity(110000, 0, 20110815, Color.RED));
        stick.add(new ColoredStickEntity(110000, 0, 20110812, Color.BLUE));
        stick.add(new ColoredStickEntity(310000, 0, 20110811, Color.RED));
        stick.add(new ColoredStickEntity(210000, 0, 20110810, Color.BLUE));
        stick.add(new ColoredStickEntity(211000, 0, 20110809, Color.BLUE));
        stick.add(new ColoredStickEntity(577000, 0, 20110808, Color.RED));
        stick.add(new ColoredStickEntity(493000, 0, 20110805, Color.BLUE));
        stick.add(new ColoredStickEntity(433000, 0, 20110804, Color.LTGRAY));
        stick.add(new ColoredStickEntity(479000, 0, 20110803, Color.BLUE));
        stick.add(new ColoredStickEntity(360000, 0, 20110802, Color.RED));
        stick.add(new ColoredStickEntity(437000, 0, 20110801, Color.BLUE));
        stick.add(new ColoredStickEntity(504000, 0, 20110729, Color.BLUE));
        stick.add(new ColoredStickEntity(520000, 0, 20110728, Color.BLUE));
        stick.add(new ColoredStickEntity(494000, 0, 20110727, Color.BLUE));
        stick.add(new ColoredStickEntity(312000, 0, 20110726, Color.RED));
        stick.add(new ColoredStickEntity(424000, 0, 20110725, Color.BLUE));
        stick.add(new ColoredStickEntity(557000, 0, 20110722, Color.RED));
        stick.add(new ColoredStickEntity(596000, 0, 20110721, Color.RED));
        stick.add(new ColoredStickEntity(311000, 0, 20110720, Color.LTGRAY));
        stick.add(new ColoredStickEntity(312000, 0, 20110719, Color.BLUE));
        stick.add(new ColoredStickEntity(312000, 0, 20110718, Color.RED));
        stick.add(new ColoredStickEntity(312000, 0, 20110715, Color.BLUE));
        stick.add(new ColoredStickEntity(410000, 0, 20110714, Color.BLUE));
        stick.add(new ColoredStickEntity(311000, 0, 20110713, Color.BLUE));
        stick.add(new ColoredStickEntity(210000, 0, 20110712, Color.BLUE));
        stick.add(new ColoredStickEntity(410000, 0, 20110711, Color.RED));
        stick.add(new ColoredStickEntity(214000, 0, 20110708, Color.RED));
        stick.add(new ColoredStickEntity(312000, 0, 20110707, Color.BLUE));
        stick.add(new ColoredStickEntity(212000, 0, 20110706, Color.RED));
        stick.add(new ColoredStickEntity(414000, 0, 20110705, Color.RED));
        stick.add(new ColoredStickEntity(310000, 0, 20110704, Color.BLUE));
        stick.add(new ColoredStickEntity(210000, 0, 20110701, Color.RED));
        stick.add(new ColoredStickEntity(190000, 0, 20110630, Color.RED));
        stick.add(new ColoredStickEntity(210000, 0, 20110629, Color.BLUE));
        stick.add(new ColoredStickEntity(116000, 0, 20110628, Color.BLUE));
        stick.add(new ColoredStickEntity(142000, 0, 20110627, Color.BLUE));
        stick.add(new ColoredStickEntity(524000, 0, 20110624, Color.RED));
        stick.add(new ColoredStickEntity(246000, 0, 20110623, Color.BLUE));
        stick.add(new ColoredStickEntity(432000, 0, 20110622, Color.RED));
        stick.add(new ColoredStickEntity(352000, 0, 20110621, Color.RED));
        stick.add(new ColoredStickEntity(243000, 0, 20110620, Color.RED));
        stick.add(new ColoredStickEntity(165000, 0, 20110617, Color.BLUE));
        stick.add(new ColoredStickEntity(554000, 0, 20110616, Color.BLUE));
        stick.add(new ColoredStickEntity(552000, 0, 20110615, Color.BLUE));
        stick.add(new ColoredStickEntity(431000, 0, 20110614, Color.LTGRAY));
        stick.add(new ColoredStickEntity(317000, 0, 20110613, Color.BLUE));
        stick.add(new ColoredStickEntity(512000, 0, 20110610, Color.BLUE));
        stick.add(new ColoredStickEntity(283000, 0, 20110609, Color.RED));
        stick.add(new ColoredStickEntity(144000, 0, 20110608, Color.RED));
        stick.add(new ColoredStickEntity(273000, 0, 20110607, Color.RED));
        stick.add(new ColoredStickEntity(278000, 0, 20110603, Color.RED));
        stick.add(new ColoredStickEntity(624000, 0, 20110602, Color.RED));
        stick.add(new ColoredStickEntity(217000, 0, 20110601, Color.BLUE));
        stick.add(new ColoredStickEntity(116000, 0, 20110531, Color.BLUE));
        stick.add(new ColoredStickEntity(191000, 0, 20110530, Color.RED));
        stick.add(new ColoredStickEntity(204000, 0, 20110527, Color.BLUE));
        stick.add(new ColoredStickEntity(236000, 0, 20110526, Color.BLUE));
        stick.add(new ColoredStickEntity(421000, 0, 20110525, Color.LTGRAY));
        stick.add(new ColoredStickEntity(114000, 0, 20110524, Color.BLUE));
        stick.add(new ColoredStickEntity(403000, 0, 20110523, Color.RED));
        stick.add(new ColoredStickEntity(205000, 0, 20110520, Color.RED));
        stick.add(new ColoredStickEntity(328000, 0, 20110519, Color.BLUE));
        stick.add(new ColoredStickEntity(109000, 0, 20110518, Color.RED));
        stick.add(new ColoredStickEntity(286000, 0, 20110517, Color.RED));
        stick.add(new ColoredStickEntity(103000, 0, 20110516, Color.BLUE));
        stick.add(new ColoredStickEntity(114000, 0, 20110513, Color.BLUE));
        stick.add(new ColoredStickEntity(107000, 0, 20110512, Color.RED));
        stick.add(new ColoredStickEntity(106000, 0, 20110511, Color.BLUE));
        stick.add(new ColoredStickEntity(146000, 0, 20110510, Color.LTGRAY));
        stick.add(new ColoredStickEntity(105000, 0, 20110509, Color.RED));
        stick.add(new ColoredStickEntity(312000, 0, 20110506, Color.RED));
        stick.add(new ColoredStickEntity(530000, 0, 20110505, Color.RED));
        stick.add(new ColoredStickEntity(275000, 0, 20110504, Color.BLUE));
        stick.add(new ColoredStickEntity(432000, 0, 20110503, Color.BLUE));
        // stick.add(new StickEntity(157000,0,20110429));
        // stick.add(new StickEntity(148000,0,20110428));
        // stick.add(new StickEntity(224000,0,20110427));
        // stick.add(new StickEntity(405000,0,20110426));
        // stick.add(new StickEntity(374000,0,20110425));
        // stick.add(new StickEntity(473000,0,20110422));
        // stick.add(new StickEntity(437000,0,20110421));
        // stick.add(new StickEntity(121000,0,20110420));
        // stick.add(new StickEntity(208000,0,20110419));
        // stick.add(new StickEntity(486000,0,20110418));
        // stick.add(new StickEntity(486000,0,20110415));
        // stick.add(new StickEntity(473000,0,20110414));
        // stick.add(new StickEntity(256000,0,20110413));
        // stick.add(new StickEntity(275000,0,20110412));
        // stick.add(new StickEntity(471000,0,20110411));
        // stick.add(new StickEntity(529000,0,20110408));
        // stick.add(new StickEntity(564000,0,20110407));
        // stick.add(new StickEntity(257000,0,20110406));
        // stick.add(new StickEntity(344000,0,20110404));
        // stick.add(new StickEntity(525000,0,20110401));

        for (int i = stick.size(); i > 0; i--) {
            this.volc.add(stick.get(i - 1));
        }

        // this.volc.addAll(stick);
    }

    protected void initVOL() {
        List<IStickEntity> stick = new ArrayList<IStickEntity>();

        stick.add(new StickEntity(406000, 0, 20110825));
        stick.add(new StickEntity(232000, 0, 20110824));
        stick.add(new StickEntity(355000, 0, 20110823));
        stick.add(new StickEntity(437000, 0, 20110822));
        stick.add(new StickEntity(460000, 0, 20110819));
        stick.add(new StickEntity(422000, 0, 20110818));
        stick.add(new StickEntity(502000, 0, 20110817));
        stick.add(new StickEntity(509000, 0, 20110816));
        stick.add(new StickEntity(110000, 0, 20110815));
        stick.add(new StickEntity(110000, 0, 20110812));
        stick.add(new StickEntity(310000, 0, 20110811));
        stick.add(new StickEntity(210000, 0, 20110810));
        stick.add(new StickEntity(211000, 0, 20110809));
        stick.add(new StickEntity(577000, 0, 20110808));
        stick.add(new StickEntity(493000, 0, 20110805));
        stick.add(new StickEntity(433000, 0, 20110804));
        stick.add(new StickEntity(479000, 0, 20110803));
        stick.add(new StickEntity(360000, 0, 20110802));
        stick.add(new StickEntity(437000, 0, 20110801));
        stick.add(new StickEntity(504000, 0, 20110729));
        stick.add(new StickEntity(520000, 0, 20110728));
        stick.add(new StickEntity(494000, 0, 20110727));
        stick.add(new StickEntity(312000, 0, 20110726));
        stick.add(new StickEntity(424000, 0, 20110725));
        stick.add(new StickEntity(557000, 0, 20110722));
        stick.add(new StickEntity(596000, 0, 20110721));
        stick.add(new StickEntity(311000, 0, 20110720));
        stick.add(new StickEntity(312000, 0, 20110719));
        stick.add(new StickEntity(312000, 0, 20110718));
        stick.add(new StickEntity(312000, 0, 20110715));
        stick.add(new StickEntity(410000, 0, 20110714));
        stick.add(new StickEntity(311000, 0, 20110713));
        stick.add(new StickEntity(210000, 0, 20110712));
        stick.add(new StickEntity(410000, 0, 20110711));
        stick.add(new StickEntity(214000, 0, 20110708));
        stick.add(new StickEntity(312000, 0, 20110707));
        stick.add(new StickEntity(212000, 0, 20110706));
        stick.add(new StickEntity(414000, 0, 20110705));
        stick.add(new StickEntity(310000, 0, 20110704));
        stick.add(new StickEntity(210000, 0, 20110701));
        stick.add(new StickEntity(190000, 0, 20110630));
        stick.add(new StickEntity(210000, 0, 20110629));
        stick.add(new StickEntity(116000, 0, 20110628));
        stick.add(new StickEntity(142000, 0, 20110627));
        stick.add(new StickEntity(524000, 0, 20110624));
        stick.add(new StickEntity(246000, 0, 20110623));
        stick.add(new StickEntity(432000, 0, 20110622));
        stick.add(new StickEntity(352000, 0, 20110621));
        stick.add(new StickEntity(243000, 0, 20110620));
        stick.add(new StickEntity(165000, 0, 20110617));
        stick.add(new StickEntity(554000, 0, 20110616));
        stick.add(new StickEntity(552000, 0, 20110615));
        stick.add(new StickEntity(431000, 0, 20110614));
        stick.add(new StickEntity(317000, 0, 20110613));
        stick.add(new StickEntity(512000, 0, 20110610));
        stick.add(new StickEntity(283000, 0, 20110609));
        stick.add(new StickEntity(144000, 0, 20110608));
        stick.add(new StickEntity(273000, 0, 20110607));
        stick.add(new StickEntity(278000, 0, 20110603));
        stick.add(new StickEntity(624000, 0, 20110602));
        stick.add(new StickEntity(217000, 0, 20110601));
        stick.add(new StickEntity(116000, 0, 20110531));
        stick.add(new StickEntity(191000, 0, 20110530));
        stick.add(new StickEntity(204000, 0, 20110527));
        stick.add(new StickEntity(236000, 0, 20110526));
        stick.add(new StickEntity(421000, 0, 20110525));
        stick.add(new StickEntity(114000, 0, 20110524));
        stick.add(new StickEntity(403000, 0, 20110523));
        stick.add(new StickEntity(205000, 0, 20110520));
        stick.add(new StickEntity(328000, 0, 20110519));
        stick.add(new StickEntity(109000, 0, 20110518));
        stick.add(new StickEntity(286000, 0, 20110517));
        stick.add(new StickEntity(103000, 0, 20110516));
        stick.add(new StickEntity(114000, 0, 20110513));
        stick.add(new StickEntity(107000, 0, 20110512));
        stick.add(new StickEntity(106000, 0, 20110511));
        stick.add(new StickEntity(146000, 0, 20110510));
        stick.add(new StickEntity(105000, 0, 20110509));
        stick.add(new StickEntity(312000, 0, 20110506));
        stick.add(new StickEntity(530000, 0, 20110505));
        stick.add(new StickEntity(275000, 0, 20110504));
        stick.add(new StickEntity(432000, 0, 20110503));
        // stick.add(new StickEntity(157000,0,20110429));
        // stick.add(new StickEntity(148000,0,20110428));
        // stick.add(new StickEntity(224000,0,20110427));
        // stick.add(new StickEntity(405000,0,20110426));
        // stick.add(new StickEntity(374000,0,20110425));
        // stick.add(new StickEntity(473000,0,20110422));
        // stick.add(new StickEntity(437000,0,20110421));
        // stick.add(new StickEntity(121000,0,20110420));
        // stick.add(new StickEntity(208000,0,20110419));
        // stick.add(new StickEntity(486000,0,20110418));
        // stick.add(new StickEntity(486000,0,20110415));
        // stick.add(new StickEntity(473000,0,20110414));
        // stick.add(new StickEntity(256000,0,20110413));
        // stick.add(new StickEntity(275000,0,20110412));
        // stick.add(new StickEntity(471000,0,20110411));
        // stick.add(new StickEntity(529000,0,20110408));
        // stick.add(new StickEntity(564000,0,20110407));
        // stick.add(new StickEntity(257000,0,20110406));
        // stick.add(new StickEntity(344000,0,20110404));
        // stick.add(new StickEntity(525000,0,20110401));

        this.vol = new ArrayList<IStickEntity>();
        for (int i = stick.size(); i > 0; i--) {
            this.vol.add(stick.get(i - 1));
        }

        // this.vol.addAll(stick);
    }

    protected List<DateValueEntity> initVMA(int days) {
        if (days < 2) {
            return null;
        }

        List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < this.vol.size(); i++) {
            float close = (float) vol.get(i).getHigh();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close - (float) vol.get(i - days).getHigh();
                avg = sum / days;
            }
            MA5Values.add(new DateValueEntity(avg, vol.get(i).getDate()));
        }

        return MA5Values;
    }

    protected void initDV1() {
        List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

        this.dv1 = new ArrayList<DateValueEntity>();
        dv.add(new DateValueEntity(947.3056f, 20130424));
        dv.add(new DateValueEntity(952.2242f, 20130425));
        dv.add(new DateValueEntity(963.2635f, 20130426));
        dv.add(new DateValueEntity(961.9385f, 20130502));
        dv.add(new DateValueEntity(962.3391f, 20130503));
        dv.add(new DateValueEntity(961.9631f, 20130506));
        dv.add(new DateValueEntity(961.916f, 20130507));
        dv.add(new DateValueEntity(961.9375f, 20130508));
        dv.add(new DateValueEntity(962.1758f, 20130509));
        dv.add(new DateValueEntity(962.1837f, 20130510));
        dv.add(new DateValueEntity(962.1995f, 20130513));
        dv.add(new DateValueEntity(962.1158f, 20130514));
        dv.add(new DateValueEntity(962.2931f, 20130515));
        dv.add(new DateValueEntity(963.1225f, 20130516));
        dv.add(new DateValueEntity(965.0629f, 20130517));
        dv.add(new DateValueEntity(969.385f, 20130520));
        dv.add(new DateValueEntity(975.5116f, 20130521));
        dv.add(new DateValueEntity(974.0666f, 20130522));
        dv.add(new DateValueEntity(974.2079f, 20130523));
        dv.add(new DateValueEntity(977.2924f, 20130524));
        dv.add(new DateValueEntity(977.4907f, 20130527));
        dv.add(new DateValueEntity(976.429f, 20130528));
        dv.add(new DateValueEntity(977.8235f, 20130529));
        dv.add(new DateValueEntity(981.4609f, 20130530));
        dv.add(new DateValueEntity(983.0612f, 20130531));
        dv.add(new DateValueEntity(978.343f, 20130603));
        dv.add(new DateValueEntity(972.4412f, 20130604));
        dv.add(new DateValueEntity(965.072f, 20130605));
        dv.add(new DateValueEntity(954.1762f, 20130606));
        dv.add(new DateValueEntity(941.5963f, 20130607));
        dv.add(new DateValueEntity(921.8664f, 20130613));
        dv.add(new DateValueEntity(905.6599f, 20130614));
        dv.add(new DateValueEntity(891.2146f, 20130617));
        dv.add(new DateValueEntity(879.2878f, 20130618));
        dv.add(new DateValueEntity(865.2361f, 20130619));
        dv.add(new DateValueEntity(843.2399f, 20130620));
        dv.add(new DateValueEntity(821.4298f, 20130621));
        dv.add(new DateValueEntity(784.0339f, 20130624));
        dv.add(new DateValueEntity(759.5865f, 20130625));
        dv.add(new DateValueEntity(738.5209f, 20130626));
        dv.add(new DateValueEntity(723.5436f, 20130627));
        dv.add(new DateValueEntity(720.2877f, 20130628));
        dv.add(new DateValueEntity(718.5511f, 20130701));
        dv.add(new DateValueEntity(720.9672f, 20130702));
        dv.add(new DateValueEntity(725.9567f, 20130703));
        dv.add(new DateValueEntity(726.3284f, 20130704));
        dv.add(new DateValueEntity(728.0508f, 20130705));
        dv.add(new DateValueEntity(728.961f, 20130708));
        dv.add(new DateValueEntity(730.1062f, 20130709));
        dv.add(new DateValueEntity(734.6287f, 20130710));
        dv.add(new DateValueEntity(736.1662f, 20130711));
        dv.add(new DateValueEntity(739.5985f, 20130712));
        dv.add(new DateValueEntity(743.5045f, 20130715));
        dv.add(new DateValueEntity(749.4669f, 20130716));
        dv.add(new DateValueEntity(753.7623f, 20130717));
        dv.add(new DateValueEntity(753.6917f, 20130718));
        dv.add(new DateValueEntity(752.4678f, 20130719));
        dv.add(new DateValueEntity(760.7568f, 20130722));
        dv.add(new DateValueEntity(765.0131f, 20130723));
        dv.add(new DateValueEntity(768.8569f, 20130724));
        dv.add(new DateValueEntity(770.9514f, 20130725));
        dv.add(new DateValueEntity(768.5318f, 20130726));
        dv.add(new DateValueEntity(762.7225f, 20130729));
        dv.add(new DateValueEntity(759.3295f, 20130730));
        dv.add(new DateValueEntity(757.1793f, 20130731));
        dv.add(new DateValueEntity(756.1526f, 20130801));
        dv.add(new DateValueEntity(755.1125f, 20130802));
        dv.add(new DateValueEntity(756.6308f, 20130805));
        dv.add(new DateValueEntity(757.8153f, 20130806));
        dv.add(new DateValueEntity(757.0371f, 20130807));
        dv.add(new DateValueEntity(763.2288f, 20130808));
        dv.add(new DateValueEntity(764.5119f, 20130809));
        dv.add(new DateValueEntity(767.9202f, 20130812));
        dv.add(new DateValueEntity(770.146f, 20130813));
        dv.add(new DateValueEntity(772.2369f, 20130814));
        dv.add(new DateValueEntity(772.1298f, 20130815));
        dv.add(new DateValueEntity(771.5269f, 20130816));
        dv.add(new DateValueEntity(770.4365f, 20130819));
        dv.add(new DateValueEntity(767.9823f, 20130820));
        dv.add(new DateValueEntity(767.901f, 20130821));
        dv.add(new DateValueEntity(768.2333f, 20130822));
        dv.add(new DateValueEntity(769.7356f, 20130823));
        dv.add(new DateValueEntity(772.7566f, 20130826));
        dv.add(new DateValueEntity(771.9353f, 20130827));
        dv.add(new DateValueEntity(772.5748f, 20130828));
        dv.add(new DateValueEntity(774.17f, 20130829));
        dv.add(new DateValueEntity(776.6239f, 20130830));
        dv.add(new DateValueEntity(779.4005f, 20130902));
        dv.add(new DateValueEntity(782.8205f, 20130903));
        dv.add(new DateValueEntity(787.7852f, 20130904));
        dv.add(new DateValueEntity(795.1398f, 20130905));
        dv.add(new DateValueEntity(798.0329f, 20130906));
        dv.add(new DateValueEntity(777.0803f, 20130909));
        dv.add(new DateValueEntity(745.4303f, 20130910));
        dv.add(new DateValueEntity(733.794f, 20130911));
        dv.add(new DateValueEntity(713.0938f, 20130912));
        dv.add(new DateValueEntity(709.4212f, 20130913));
        dv.add(new DateValueEntity(715.0446f, 20130916));
        dv.add(new DateValueEntity(727.5064f, 20130917));
        dv.add(new DateValueEntity(742.578f, 20130918));
        dv.add(new DateValueEntity(759.8558f, 20130923));
        dv.add(new DateValueEntity(781.4722f, 20130924));
        dv.add(new DateValueEntity(799.6322f, 20130925));
        dv.add(new DateValueEntity(813.7519f, 20130926));
        dv.add(new DateValueEntity(828.4345f, 20130927));
        dv.add(new DateValueEntity(844.6599f, 20130930));
        dv.add(new DateValueEntity(861.8906f, 20131008));
        dv.add(new DateValueEntity(881.4863f, 20131009));
        dv.add(new DateValueEntity(897.0036f, 20131010));
        dv.add(new DateValueEntity(918.4781f, 20131011));
        dv.add(new DateValueEntity(940.6985f, 20131014));
        dv.add(new DateValueEntity(951.0224f, 20131015));
        dv.add(new DateValueEntity(942.7723f, 20131016));
        dv.add(new DateValueEntity(932.7551f, 20131017));
        dv.add(new DateValueEntity(924.7807f, 20131018));
        dv.add(new DateValueEntity(936.6127f, 20131021));
        dv.add(new DateValueEntity(945.5508f, 20131022));
        dv.add(new DateValueEntity(952.1615f, 20131023));
        dv.add(new DateValueEntity(950.4466f, 20131024));
        dv.add(new DateValueEntity(953.2289f, 20131025));
        dv.add(new DateValueEntity(963.9264f, 20131028));
        dv.add(new DateValueEntity(968.6712f, 20131029));
        dv.add(new DateValueEntity(972.3124f, 20131030));
        dv.add(new DateValueEntity(972.3439f, 20131031));
        dv.add(new DateValueEntity(971.8104f, 20131101));
        dv.add(new DateValueEntity(972.5886f, 20131104));

        // for (int i = dv.size(); i > 0; i--) {
        // this.dv1.add(dv.get(i - 1));
        // }

        this.dv1.addAll(dv);
    }

    protected void initDV2() {
        List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

        this.dv2 = new ArrayList<DateValueEntity>();
        dv.add(new DateValueEntity(1059.5943f, 20130424));
        dv.add(new DateValueEntity(1046.7757f, 20130425));
        dv.add(new DateValueEntity(1026.9364f, 20130426));
        dv.add(new DateValueEntity(1026.2614f, 20130502));
        dv.add(new DateValueEntity(1024.6608f, 20130503));
        dv.add(new DateValueEntity(1025.8368f, 20130506));
        dv.add(new DateValueEntity(1026.1839f, 20130507));
        dv.add(new DateValueEntity(1026.0624f, 20130508));
        dv.add(new DateValueEntity(1026.3241f, 20130509));
        dv.add(new DateValueEntity(1026.2162f, 20130510));
        dv.add(new DateValueEntity(1026.4004f, 20130513));
        dv.add(new DateValueEntity(1025.9841f, 20130514));
        dv.add(new DateValueEntity(1026.3068f, 20130515));
        dv.add(new DateValueEntity(1028.6774f, 20130516));
        dv.add(new DateValueEntity(1031.737f, 20130517));
        dv.add(new DateValueEntity(1035.6149f, 20130520));
        dv.add(new DateValueEntity(1036.5883f, 20130521));
        dv.add(new DateValueEntity(1040.2333f, 20130522));
        dv.add(new DateValueEntity(1039.392f, 20130523));
        dv.add(new DateValueEntity(1039.9075f, 20130524));
        dv.add(new DateValueEntity(1042.3092f, 20130527));
        dv.add(new DateValueEntity(1049.7709f, 20130528));
        dv.add(new DateValueEntity(1054.7764f, 20130529));
        dv.add(new DateValueEntity(1058.339f, 20130530));
        dv.add(new DateValueEntity(1061.3387f, 20130531));
        dv.add(new DateValueEntity(1063.1569f, 20130603));
        dv.add(new DateValueEntity(1065.8587f, 20130604));
        dv.add(new DateValueEntity(1069.2279f, 20130605));
        dv.add(new DateValueEntity(1074.9237f, 20130606));
        dv.add(new DateValueEntity(1080.7036f, 20130607));
        dv.add(new DateValueEntity(1090.4335f, 20130613));
        dv.add(new DateValueEntity(1097.14f, 20130614));
        dv.add(new DateValueEntity(1101.7853f, 20130617));
        dv.add(new DateValueEntity(1102.9121f, 20130618));
        dv.add(new DateValueEntity(1103.4638f, 20130619));
        dv.add(new DateValueEntity(1105.56f, 20130620));
        dv.add(new DateValueEntity(1106.7701f, 20130621));
        dv.add(new DateValueEntity(1115.766f, 20130624));
        dv.add(new DateValueEntity(1116.7134f, 20130625));
        dv.add(new DateValueEntity(1113.479f, 20130626));
        dv.add(new DateValueEntity(1104.3563f, 20130627));
        dv.add(new DateValueEntity(1084.9122f, 20130628));
        dv.add(new DateValueEntity(1063.1488f, 20130701));
        dv.add(new DateValueEntity(1036.8327f, 20130702));
        dv.add(new DateValueEntity(1007.5432f, 20130703));
        dv.add(new DateValueEntity(989.9715f, 20130704));
        dv.add(new DateValueEntity(971.9491f, 20130705));
        dv.add(new DateValueEntity(953.6389f, 20130708));
        dv.add(new DateValueEntity(937.1937f, 20130709));
        dv.add(new DateValueEntity(920.2712f, 20130710));
        dv.add(new DateValueEntity(917.1337f, 20130711));
        dv.add(new DateValueEntity(908.4014f, 20130712));
        dv.add(new DateValueEntity(899.9954f, 20130715));
        dv.add(new DateValueEntity(888.733f, 20130716));
        dv.add(new DateValueEntity(880.0376f, 20130717));
        dv.add(new DateValueEntity(877.9082f, 20130718));
        dv.add(new DateValueEntity(876.6321f, 20130719));
        dv.add(new DateValueEntity(872.6431f, 20130722));
        dv.add(new DateValueEntity(871.7868f, 20130723));
        dv.add(new DateValueEntity(870.243f, 20130724));
        dv.add(new DateValueEntity(869.5485f, 20130725));
        dv.add(new DateValueEntity(868.8681f, 20130726));
        dv.add(new DateValueEntity(870.3774f, 20130729));
        dv.add(new DateValueEntity(870.6704f, 20130730));
        dv.add(new DateValueEntity(871.0206f, 20130731));
        dv.add(new DateValueEntity(870.7473f, 20130801));
        dv.add(new DateValueEntity(870.4874f, 20130802));
        dv.add(new DateValueEntity(870.4691f, 20130805));
        dv.add(new DateValueEntity(870.3846f, 20130806));
        dv.add(new DateValueEntity(870.1628f, 20130807));
        dv.add(new DateValueEntity(855.2711f, 20130808));
        dv.add(new DateValueEntity(849.188f, 20130809));
        dv.add(new DateValueEntity(843.2797f, 20130812));
        dv.add(new DateValueEntity(839.8539f, 20130813));
        dv.add(new DateValueEntity(836.363f, 20130814));
        dv.add(new DateValueEntity(836.6701f, 20130815));
        dv.add(new DateValueEntity(840.073f, 20130816));
        dv.add(new DateValueEntity(846.1634f, 20130819));
        dv.add(new DateValueEntity(852.3176f, 20130820));
        dv.add(new DateValueEntity(856.8989f, 20130821));
        dv.add(new DateValueEntity(860.7666f, 20130822));
        dv.add(new DateValueEntity(863.7643f, 20130823));
        dv.add(new DateValueEntity(870.7433f, 20130826));
        dv.add(new DateValueEntity(882.6646f, 20130827));
        dv.add(new DateValueEntity(893.4251f, 20130828));
        dv.add(new DateValueEntity(901.1299f, 20130829));
        dv.add(new DateValueEntity(908.776f, 20130830));
        dv.add(new DateValueEntity(915.0994f, 20130902));
        dv.add(new DateValueEntity(922.2794f, 20130903));
        dv.add(new DateValueEntity(928.6147f, 20130904));
        dv.add(new DateValueEntity(932.6601f, 20130905));
        dv.add(new DateValueEntity(945.367f, 20130906));
        dv.add(new DateValueEntity(988.5196f, 20130909));
        dv.add(new DateValueEntity(1049.9696f, 20130910));
        dv.add(new DateValueEntity(1091.2059f, 20130911));
        dv.add(new DateValueEntity(1152.2061f, 20130912));
        dv.add(new DateValueEntity(1191.8787f, 20130913));
        dv.add(new DateValueEntity(1216.6553f, 20130916));
        dv.add(new DateValueEntity(1227.3935f, 20130917));
        dv.add(new DateValueEntity(1237.8219f, 20130918));
        dv.add(new DateValueEntity(1247.9441f, 20130923));
        dv.add(new DateValueEntity(1250.2277f, 20130924));
        dv.add(new DateValueEntity(1252.4677f, 20130925));
        dv.add(new DateValueEntity(1250.548f, 20130926));
        dv.add(new DateValueEntity(1248.2654f, 20130927));
        dv.add(new DateValueEntity(1243.74f, 20130930));
        dv.add(new DateValueEntity(1238.9093f, 20131008));
        dv.add(new DateValueEntity(1232.3136f, 20131009));
        dv.add(new DateValueEntity(1225.6963f, 20131010));
        dv.add(new DateValueEntity(1218.3218f, 20131011));
        dv.add(new DateValueEntity(1207.7014f, 20131014));
        dv.add(new DateValueEntity(1202.7775f, 20131015));
        dv.add(new DateValueEntity(1204.8276f, 20131016));
        dv.add(new DateValueEntity(1199.0448f, 20131017));
        dv.add(new DateValueEntity(1193.1192f, 20131018));
        dv.add(new DateValueEntity(1159.9872f, 20131021));
        dv.add(new DateValueEntity(1131.2491f, 20131022));
        dv.add(new DateValueEntity(1109.9384f, 20131023));
        dv.add(new DateValueEntity(1103.1533f, 20131024));
        dv.add(new DateValueEntity(1091.071f, 20131025));
        dv.add(new DateValueEntity(1070.0735f, 20131028));
        dv.add(new DateValueEntity(1062.0287f, 20131029));
        dv.add(new DateValueEntity(1056.4875f, 20131030));
        dv.add(new DateValueEntity(1058.056f, 20131031));
        dv.add(new DateValueEntity(1060.3895f, 20131101));
        dv.add(new DateValueEntity(1061.7113f, 20131104));

        // for (int i = dv.size(); i > 0; i--) {
        // this.dv2.add(dv.get(i - 1));
        // }

        this.dv2.addAll(dv);
    }

    protected void initOHLC() {
        List<IStickEntity> ohlc = new ArrayList<IStickEntity>();

        this.ohlc = new ArrayList<IStickEntity>();
        ohlc.add(new OHLCEntity(986, 1015, 977, 1003, 20130424));
        ohlc.add(new OHLCEntity(1000, 1007, 982, 991, 20130425));
        ohlc.add(new OHLCEntity(996, 1001, 985, 988, 20130426));
        ohlc.add(new OHLCEntity(977, 986, 966, 982, 20130502));
        ohlc.add(new OHLCEntity(987, 1017, 983, 1001, 20130503));
        ohlc.add(new OHLCEntity(1003, 1021, 997, 1013, 20130506));
        ohlc.add(new OHLCEntity(1009, 1010, 998, 1006, 20130507));
        ohlc.add(new OHLCEntity(1012, 1020, 1001, 1005, 20130508));
        ohlc.add(new OHLCEntity(1006, 1008, 989, 997, 20130509));
        ohlc.add(new OHLCEntity(993, 1006, 989, 1003, 20130510));
        ohlc.add(new OHLCEntity(1002, 1011, 993, 1002, 20130513));
        ohlc.add(new OHLCEntity(1003, 1005, 993, 997, 20130514));
        ohlc.add(new OHLCEntity(998, 1002, 993, 999, 20130515));
        ohlc.add(new OHLCEntity(999, 1016, 984, 1015, 20130516));
        ohlc.add(new OHLCEntity(1015, 1028, 1005, 1024, 20130517));
        ohlc.add(new OHLCEntity(1026, 1054, 1020, 1041, 20130520));
        ohlc.add(new OHLCEntity(1038, 1042, 1024, 1034, 20130521));
        ohlc.add(new OHLCEntity(1033, 1038, 1028, 1036, 20130522));
        ohlc.add(new OHLCEntity(1029, 1033, 1015, 1015, 20130523));
        ohlc.add(new OHLCEntity(1020, 1028, 1010, 1020, 20130524));
        ohlc.add(new OHLCEntity(1021, 1033, 1018, 1029, 20130527));
        ohlc.add(new OHLCEntity(1030, 1056, 1025, 1055, 20130528));
        ohlc.add(new OHLCEntity(1058, 1062, 1051, 1052, 20130529));
        ohlc.add(new OHLCEntity(1048, 1062, 1047, 1054, 20130530));
        ohlc.add(new OHLCEntity(1056, 1062, 1046, 1047, 20130531));
        ohlc.add(new OHLCEntity(997, 1001, 981, 984, 20130603));
        ohlc.add(new OHLCEntity(989, 989, 970, 974, 20130604));
        ohlc.add(new OHLCEntity(974, 977, 960, 965, 20130605));
        ohlc.add(new OHLCEntity(961, 967, 942, 945, 20130606));
        ohlc.add(new OHLCEntity(951, 957, 932, 935, 20130607));
        ohlc.add(new OHLCEntity(925, 925, 891, 902, 20130613));
        ohlc.add(new OHLCEntity(907, 907, 898, 902, 20130614));
        ohlc.add(new OHLCEntity(905, 910, 896, 901, 20130617));
        ohlc.add(new OHLCEntity(905, 912, 901, 907, 20130618));
        ohlc.add(new OHLCEntity(905, 905, 882, 889, 20130619));
        ohlc.add(new OHLCEntity(886, 886, 840, 842, 20130620));
        ohlc.add(new OHLCEntity(831, 847, 822, 828, 20130621));
        ohlc.add(new OHLCEntity(829, 829, 750, 752, 20130624));
        ohlc.add(new OHLCEntity(745, 784, 718, 780, 20130625));
        ohlc.add(new OHLCEntity(790, 795, 763, 777, 20130626));
        ohlc.add(new OHLCEntity(785, 792, 770, 788, 20130627));
        ohlc.add(new OHLCEntity(782, 830, 776, 828, 20130628));
        ohlc.add(new OHLCEntity(822, 827, 807, 817, 20130701));
        ohlc.add(new OHLCEntity(818, 822, 795, 815, 20130702));
        ohlc.add(new OHLCEntity(810, 811, 797, 804, 20130703));
        ohlc.add(new OHLCEntity(806, 828, 802, 812, 20130704));
        ohlc.add(new OHLCEntity(811, 822, 808, 811, 20130705));
        ohlc.add(new OHLCEntity(800, 805, 790, 791, 20130708));
        ohlc.add(new OHLCEntity(792, 796, 788, 792, 20130709));
        ohlc.add(new OHLCEntity(795, 813, 790, 811, 20130710));
        ohlc.add(new OHLCEntity(817, 892, 817, 886, 20130711));
        ohlc.add(new OHLCEntity(876, 885, 843, 849, 20130712));
        ohlc.add(new OHLCEntity(855, 871, 841, 856, 20130715));
        ohlc.add(new OHLCEntity(852, 855, 841, 854, 20130716));
        ohlc.add(new OHLCEntity(852, 855, 838, 845, 20130717));
        ohlc.add(new OHLCEntity(841, 845, 816, 820, 20130718));
        ohlc.add(new OHLCEntity(822, 824, 802, 803, 20130719));
        ohlc.add(new OHLCEntity(790, 799, 782, 795, 20130722));
        ohlc.add(new OHLCEntity(799, 823, 794, 814, 20130723));
        ohlc.add(new OHLCEntity(804, 809, 790, 800, 20130724));
        ohlc.add(new OHLCEntity(802, 811, 796, 802, 20130725));
        ohlc.add(new OHLCEntity(798, 801, 794, 797, 20130726));
        ohlc.add(new OHLCEntity(790, 790, 771, 774, 20130729));
        ohlc.add(new OHLCEntity(778, 796, 774, 784, 20130730));
        ohlc.add(new OHLCEntity(791, 802, 782, 786, 20130731));
        ohlc.add(new OHLCEntity(792, 802, 787, 799, 20130801));
        ohlc.add(new OHLCEntity(806, 812, 797, 798, 20130802));
        ohlc.add(new OHLCEntity(798, 807, 795, 806, 20130805));
        ohlc.add(new OHLCEntity(803, 808, 798, 803, 20130806));
        ohlc.add(new OHLCEntity(803, 814, 800, 801, 20130807));
        ohlc.add(new OHLCEntity(801, 807, 795, 799, 20130808));
        ohlc.add(new OHLCEntity(805, 808, 796, 801, 20130809));
        ohlc.add(new OHLCEntity(804, 832, 801, 831, 20130812));
        ohlc.add(new OHLCEntity(830, 843, 827, 842, 20130813));
        ohlc.add(new OHLCEntity(844, 853, 830, 831, 20130814));
        ohlc.add(new OHLCEntity(831, 837, 820, 822, 20130815));
        ohlc.add(new OHLCEntity(817, 904, 815, 831, 20130816));
        ohlc.add(new OHLCEntity(824, 850, 823, 845, 20130819));
        ohlc.add(new OHLCEntity(842, 878, 839, 851, 20130820));
        ohlc.add(new OHLCEntity(853, 858, 837, 845, 20130821));
        ohlc.add(new OHLCEntity(841, 862, 840, 844, 20130822));
        ohlc.add(new OHLCEntity(854, 863, 825, 842, 20130823));
        ohlc.add(new OHLCEntity(845, 878, 840, 874, 20130826));
        ohlc.add(new OHLCEntity(875, 905, 870, 895, 20130827));
        ohlc.add(new OHLCEntity(888, 915, 879, 900, 20130828));
        ohlc.add(new OHLCEntity(911, 921, 886, 892, 20130829));
        ohlc.add(new OHLCEntity(886, 905, 876, 899, 20130830));
        ohlc.add(new OHLCEntity(911, 929, 895, 897, 20130902));
        ohlc.add(new OHLCEntity(896, 912, 889, 909, 20130903));
        ohlc.add(new OHLCEntity(904, 924, 903, 914, 20130904));
        ohlc.add(new OHLCEntity(919, 919, 906, 913, 20130905));
        ohlc.add(new OHLCEntity(915, 987, 912, 957, 20130906));
        ohlc.add(new OHLCEntity(1028, 1053, 1018, 1053, 20130909));
        ohlc.add(new OHLCEntity(1100, 1149, 1077, 1140, 20130910));
        ohlc.add(new OHLCEntity(1121, 1147, 1120, 1127, 20130911));
        ohlc.add(new OHLCEntity(1130, 1240, 1116, 1225, 20130912));
        ohlc.add(new OHLCEntity(1208, 1227, 1173, 1191, 20130913));
        ohlc.add(new OHLCEntity(1200, 1202, 1123, 1149, 20130916));
        ohlc.add(new OHLCEntity(1141, 1148, 1077, 1083, 20130917));
        ohlc.add(new OHLCEntity(1095, 1119, 1083, 1100, 20130918));
        ohlc.add(new OHLCEntity(1105, 1120, 1080, 1118, 20130923));
        ohlc.add(new OHLCEntity(1119, 1120, 1057, 1081, 20130924));
        ohlc.add(new OHLCEntity(1074, 1118, 1069, 1078, 20130925));
        ohlc.add(new OHLCEntity(1075, 1076, 1007, 1017, 20130926));
        ohlc.add(new OHLCEntity(1011, 1033, 1005, 1024, 20130927));
        ohlc.add(new OHLCEntity(1034, 1037, 1002, 1009, 20130930));
        ohlc.add(new OHLCEntity(1003, 1033, 988, 1023, 20131008));
        ohlc.add(new OHLCEntity(1010, 1046, 1007, 1027, 20131009));
        ohlc.add(new OHLCEntity(1030, 1035, 993, 998, 20131010));
        ohlc.add(new OHLCEntity(1010, 1065, 1000, 1055, 20131011));
        ohlc.add(new OHLCEntity(1045, 1050, 1025, 1029, 20131014));
        ohlc.add(new OHLCEntity(1030, 1035, 1002, 1011, 20131015));
        ohlc.add(new OHLCEntity(1009, 1009, 982, 991, 20131016));
        ohlc.add(new OHLCEntity(1001, 1007, 981, 982, 20131017));
        ohlc.add(new OHLCEntity(982, 1006, 980, 988, 20131018));
        ohlc.add(new OHLCEntity(995, 1016, 980, 1012, 20131021));
        ohlc.add(new OHLCEntity(1011, 1011, 986, 993, 20131022));
        ohlc.add(new OHLCEntity(995, 1035, 991, 1002, 20131023));
        ohlc.add(new OHLCEntity(996, 1016, 982, 998, 20131024));
        ohlc.add(new OHLCEntity(1001, 1026, 999, 1007, 20131025));
        ohlc.add(new OHLCEntity(1008, 1022, 992, 1015, 20131028));
        ohlc.add(new OHLCEntity(1022, 1069, 1018, 1048, 20131029));
        ohlc.add(new OHLCEntity(1048, 1062, 1031, 1059, 20131030));
        ohlc.add(new OHLCEntity(1058, 1060, 1031, 1033, 20131031));
        ohlc.add(new OHLCEntity(1032, 1053, 1023, 1042, 20131101));
        ohlc.add(new OHLCEntity(1048, 1054, 1026, 1030, 20131104));

        // for (int i = ohlc.size(); i > 0; i--) {
        // this.ohlc.add(ohlc.get(i - 1));
        // }

        this.ohlc.addAll(ohlc);
    }

    protected List<DateValueEntity> initMA(int days) {

        if (days < 2) {
            return null;
        }

        List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < this.ohlc.size(); i++) {
            float close = (float) ((OHLCEntity) ohlc.get(i)).getClose();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close
                        - (float) ((OHLCEntity) ohlc.get(i - days)).getClose();
                avg = sum / days;
            }
            MA5Values.add(new DateValueEntity(avg, ohlc.get(i).getDate()));
        }

        return MA5Values;
    }

    protected void initMACD() {
        List<IStickEntity> macd = new ArrayList<IStickEntity>();
        macd.add(new MACDEntity(46934, 7297, -79272, 20130604));
        macd.add(new MACDEntity(30276, -36354, -133260, 20130605));
        macd.add(new MACDEntity(7002, -86094, -186192, 20130606));
        macd.add(new MACDEntity(-20810, -132060, -222500, 20130607));
        macd.add(new MACDEntity(-55227, -192894, -275332, 20130613));
        macd.add(new MACDEntity(-91853, -238357, -293008, 20130614));
        macd.add(new MACDEntity(-127894, -272058, -288326, 20130617));
        macd.add(new MACDEntity(-160430, -290575, -260288, 20130618));
        macd.add(new MACDEntity(-191570, -316130, -249118, 20130619));
        macd.add(new MACDEntity(-227264, -370042, -285554, 20130620));
        macd.add(new MACDEntity(-265658, -419232, -307148, 20130621));
        macd.add(new MACDEntity(-315250, -513620, -396738, 20130624));
        macd.add(new MACDEntity(-364077, -559382, -390610, 20130625));
        macd.add(new MACDEntity(-409512, -591253, -363482, 20130626));
        macd.add(new MACDEntity(-447752, -600711, -305918, 20130627));
        macd.add(new MACDEntity(-472075, -569366, -194582, 20130628));
        macd.add(new MACDEntity(-487079, -547095, -120032, 20130701));
        macd.add(new MACDEntity(-494664, -525007, -60684, 20130702));
        macd.add(new MACDEntity(-497830, -510493, -25324, 20130703));
        macd.add(new MACDEntity(-495648, -486922, 17452, 20130704));
        macd.add(new MACDEntity(-489260, -463704, 51110, 20130705));
        macd.add(new MACDEntity(-482644, -456183, 52922, 20130708));
        macd.add(new MACDEntity(-474974, -444294, 61358, 20130709));
        macd.add(new MACDEntity(-462931, -414760, 96342, 20130710));
        macd.add(new MACDEntity(-435758, -327065, 217386, 20130711));
        macd.add(new MACDEntity(-405436, -284146, 242578, 20130712));
        macd.add(new MACDEntity(-372688, -241698, 261980, 20130715));
        macd.add(new MACDEntity(-339607, -207282, 264648, 20130716));
        macd.add(new MACDEntity(-308713, -185136, 247154, 20130717));
        macd.add(new MACDEntity(-284094, -185617, 196952, 20130718));
        macd.add(new MACDEntity(-266763, -197441, 138644, 20130719));
        macd.add(new MACDEntity(-255578, -210836, 89482, 20130722));
        macd.add(new MACDEntity(-245216, -203771, 82890, 20130723));
        macd.add(new MACDEntity(-237590, -207082, 61014, 20130724));
        macd.add(new MACDEntity(-231216, -205721, 50988, 20130725));
        macd.add(new MACDEntity(-226232, -206298, 39866, 20130726));
        macd.add(new MACDEntity(-225535, -222748, 5574, 20130729));
        macd.add(new MACDEntity(-225452, -225119, 664, 20130730));
        macd.add(new MACDEntity(-224925, -222817, 4216, 20130731));
        macd.add(new MACDEntity(-221561, -208103, 26914, 20130801));
        macd.add(new MACDEntity(-216249, -195002, 42494, 20130802));
        macd.add(new MACDEntity(-208226, -176133, 64184, 20130805));
        macd.add(new MACDEntity(-198928, -161735, 74384, 20130806));
        macd.add(new MACDEntity(-189184, -150208, 77950, 20130807));
        macd.add(new MACDEntity(-179559, -141060, 76998, 20130808));
        macd.add(new MACDEntity(-169785, -130690, 78190, 20130809));
        macd.add(new MACDEntity(-155257, -97144, 116224, 20130812));
        macd.add(new MACDEntity(-136401, -60980, 150842, 20130813));
        macd.add(new MACDEntity(-117266, -40726, 153080, 20130814));
        macd.add(new MACDEntity(-100128, -31573, 137108, 20130815));
        macd.add(new MACDEntity(-83475, -16862, 133224, 20130816));
        macd.add(new MACDEntity(-65575, 6022, 143196, 20130819));
        macd.add(new MACDEntity(-46726, 28670, 150792, 20130820));
        macd.add(new MACDEntity(-29120, 41301, 140844, 20130821));
        macd.add(new MACDEntity(-13310, 49929, 126480, 20130822));
        macd.add(new MACDEntity(256, 54524, 108536, 20130823));
        macd.add(new MACDEntity(16811, 83030, 132438, 20130826));
        macd.add(new MACDEntity(37683, 121170, 166974, 20130827));
        macd.add(new MACDEntity(60878, 153659, 185562, 20130828));
        macd.add(new MACDEntity(82898, 170981, 176164, 20130829));
        macd.add(new MACDEntity(103956, 188187, 168462, 20130830));
        macd.add(new MACDEntity(122751, 197928, 150354, 20130902));
        macd.add(new MACDEntity(140776, 212877, 144202, 20130903));
        macd.add(new MACDEntity(157851, 226152, 136600, 20130904));
        macd.add(new MACDEntity(172916, 233177, 120520, 20130905));
        macd.add(new MACDEntity(192558, 271124, 157132, 20130906));
        macd.add(new MACDEntity(228915, 374346, 290860, 20130909));
        macd.add(new MACDEntity(287203, 520353, 466300, 20130910));
        macd.add(new MACDEntity(353452, 618446, 529988, 20130911));
        macd.add(new MACDEntity(436047, 766428, 660762, 20130912));
        macd.add(new MACDEntity(518140, 846512, 656744, 20130913));
        macd.add(new MACDEntity(587733, 866105, 556742, 20130916));
        macd.add(new MACDEntity(633973, 818935, 369922, 20130917));
        macd.add(new MACDEntity(664420, 786208, 243574, 20130918));
        macd.add(new MACDEntity(684729, 765966, 162472, 20130923));
        macd.add(new MACDEntity(690156, 711862, 43412, 20130924));
        macd.add(new MACDEntity(683918, 658968, -49900, 20130925));
        macd.add(new MACDEntity(659406, 561356, -196100, 20130926));
        macd.add(new MACDEntity(624338, 484066, -280542, 20130927));
        macd.add(new MACDEntity(580676, 406029, -349294, 20130930));
        macd.add(new MACDEntity(534827, 351430, -366792, 20131008));
        macd.add(new MACDEntity(489429, 307839, -363180, 20131009));
        macd.add(new MACDEntity(440952, 247044, -387816, 20131010));
        macd.add(new MACDEntity(401175, 242068, -318214, 20131011));
        macd.add(new MACDEntity(363874, 214670, -298408, 20131014));
        macd.add(new MACDEntity(326379, 176398, -299960, 20131015));
        macd.add(new MACDEntity(286793, 128449, -316686, 20131016));
        macd.add(new MACDEntity(245882, 82239, -327286, 20131017));
        macd.add(new MACDEntity(206682, 49883, -313598, 20131018));
        macd.add(new MACDEntity(173968, 43110, -261714, 20131021));
        macd.add(new MACDEntity(143606, 22156, -242898, 20131022));
        macd.add(new MACDEntity(117418, 12666, -209504, 20131023));
        macd.add(new MACDEntity(94313, 1895, -184836, 20131024));
        macd.add(new MACDEntity(75573, 614, -149918, 20131025));
        macd.add(new MACDEntity(61656, 5986, -111340, 20131028));
        macd.add(new MACDEntity(56615, 36451, -40328, 20131029));
        macd.add(new MACDEntity(59027, 68679, 19302, 20131030));
        macd.add(new MACDEntity(61703, 72405, 21404, 20131031));
        macd.add(new MACDEntity(65698, 81679, 31960, 20131101));
        macd.add(new MACDEntity(68247, 78442, 20388, 20131104));

        this.macd = macd;
    }

}
