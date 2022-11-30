package com.chindeo.util;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class ClickTimesUtil {


    /***
     *
     * @param view        要设置点击效果的View
     * @param times       点击的次数
     * @param timeBetween 点击完成规定次数的时间范围
     */
    public static void setClickTimes(View view, final int times, final long timeBetween, final IClick click) {
        final long[] mHits = new long[times];//存储多次点击的时间戳
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.arraycopy(mHits, 1, mHits, 0, times - 1);//自己拷贝自己，只不过错位拷贝【第二个元素拷贝到第一个元素，第一个元素拷贝到第零个元素】
                mHits[times - 1] = SystemClock.uptimeMillis();//给数组的最后一个元素赋值

                if (mHits[times - 1] - mHits[0] <= timeBetween) {//当第mHits[lengt-1]点击的时间戳减去mHits[0]的时间戳小于指定时间则该多击事件生效
                    Log.e("TAG", timeBetween + "毫秒内点击" + times + "次");
                    Arrays.fill(mHits, 0);   //数据全部置零

                    if (click != null) {
                        click.onClickListen();    //设置事件的回调
                    }


                }

            }
        });
    }

   public interface IClick {
        void onClickListen();
    }

}
