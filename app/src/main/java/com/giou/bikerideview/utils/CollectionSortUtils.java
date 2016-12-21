package com.giou.bikerideview.utils;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/7/25 0025 12:09.
 * 工程名称： DoubleListView
 * 包名称：   com.ut.doublelistview
 * 文件名称： CollectionSortUtils
 */
public class CollectionSortUtils {


    /**
     * 根据里程从高到低排序集合
     * @param points
     * @return
     */
    public static ArrayList<Point> byX(ArrayList<Point> points){
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return (lhs.x >= rhs.x ? 0 :1);
            }
        });
        return points;
    }
//
//    /**
//     * 根据成绩时间从低到高排序集合
//     * @param riderInfos
//     * @return
//     */
//    public static ArrayList<RiderInfo> byScoreTime(ArrayList<RiderInfo> riderInfos){
//        Collections.sort(riderInfos, new Comparator<RiderInfo>() {
//            @Override
//            public int compare(RiderInfo lhs, RiderInfo rhs) {
//                return (lhs.getScoreTime() < rhs.getScoreTime() ? -1 : (lhs.getScoreTime() == rhs.getScoreTime() ? 0 : 1));
//            }
//        });
//        return riderInfos;
//    }
//
//    /**
//     * 根据速度从高到底排序集合
//     * @param riderInfos
//     * @return
//     */
//    public static ArrayList<RiderInfo> byCalorie(ArrayList<RiderInfo> riderInfos){
//        Collections.sort(riderInfos, new Comparator<RiderInfo>() {
//            @Override
//            public int compare(RiderInfo lhs, RiderInfo rhs) {
//                return (lhs.getCalorie() >= rhs.getCalorie() ? -1 : 1);
//            }
//        });
//
//        ArrayList<RiderInfo> riderInfo = setLevel(riderInfos);
//
//        return riderInfo;
//    }
//
//    public static ArrayList<RiderInfo> setLevel(ArrayList<RiderInfo> riderInfos){
//
//        for (int i = 0; i < riderInfos.size(); i++) {
//            riderInfos.get(i).setLevel(i + 1);
//        }
//
//        return riderInfos;
//    }
}
