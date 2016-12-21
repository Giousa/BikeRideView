package com.giou.bikerideview.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;


import com.giou.bikerideview.objparser.BuilderInterface;
import com.giou.bikerideview.objparser.ObjectParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djf on 2016/7/29.
 */
public class PointsUtils {
    private ObjectParser mObjectParser;
    private List<Point> allPoints = new ArrayList<>();
    private static ArrayList<double[]> locations;
    private static int currentIndex;

    /**
     * 根据文件点集文件名称获取所有点
     *
     * @param context
     * @param fileName
     * @return
     */
    public List<Point> getPoints(Context context, String fileName) {
        mObjectParser = new ObjectParser(context, new BuilderInterface() {
            public void setObjFilename(String filename) {
            }

            public void addVertexGeometric(float x, float y, float z) {
                Point point = new Point((int) x, (int) -y);
                allPoints.add(point);
            }

            public void addVertexTexture(float u, float v) {

            }

            public void addVertexNormal(float x, float y, float z) {

            }

            public void addPoints(int values[]) {

            }

            public void addLine(int values[]) {

            }

            public void addFace(int vertexIndices[]) {

            }

            public void addObjectName(String name) {

            }

            public void addMapLib(String[] names) {

            }

            public void setCurrentGroupNames(String[] names) {

            }

            public void setCurrentSmoothingGroup(int groupNumber) {

            }

            public void setCurrentUseMap(String name) {

            }

            public void setCurrentUseMaterial(String name) {

            }

            public void newMtl(String name) {

            }

            public void setXYZ(int type, float x, float y, float z) {

            }

            public void setRGB(int type, float r, float g, float b) {

            }

            public void setIllum(int illumModel) {

            }

            public void setD(boolean halo, float factor) {

            }

            public void setNs(float exponent) {

            }

            public void setSharpness(float value) {

            }

            public void setNi(float opticalDensity) {

            }

            public void setMapDecalDispBump(int type, String filename) {

            }

            public void setRefl(int type, String filename) {

            }

            public void doneParsingMaterial() {

            }

            public void doneParsingObj(String filename) {
                Log.d("PointsUtils", "PaintView allPoints is : " + allPoints.size());
            }
        });

        try {
            mObjectParser.parse(fileName);
        } catch (Exception e) {
            Log.d("PointsUtils", "PaintView allPoints is : " + e.getLocalizedMessage());
        }

        return allPoints;
    }

    //获取点数组
    public static ArrayList<float[]> getLocationList(List<Point> allPoints, int count, int index, int rank) {
        //制造随机点
        ArrayList<float[]> locations = new ArrayList<>();
        int currentIndex = 0;
        for (int i = 0; i < count; i++) {
            currentIndex = index + rank * i;

            if (currentIndex >= allPoints.size())
                currentIndex = allPoints.size() - 1;
            float x = allPoints.get(currentIndex).x;
            float y = allPoints.get(currentIndex).y;
            float[] xy = new float[2];
            xy[0] = x;
            xy[1] = y;
            locations.add(xy);
        }
        return locations;
    }

    /**
     * 获取点集合
     *
     * @param allPoints 小地图所有点
     * @param percent   每个点在所有点中占据的百分比位置
     * @param id        每个点所代表的icon的id
     * @return
     */
    public static ArrayList<double[]> getLocationPointList(List<Point> allPoints, double[] percent, int[] id) {

        if (locations ==null){
            locations = new ArrayList<>();
        }else
            locations.clear();
        int currentIndex = 0;
        for (int i = 0; i < id.length; i++) {
            Log.d("PointsUtils  ","allPoints  "+allPoints.get(i));
            Log.d("PointsUtils  ","====================");
            currentIndex = (int) (allPoints.size() * percent[i]);
            if (currentIndex >= allPoints.size())
                currentIndex = allPoints.size() - 1;
                Log.d("PointsUtils  ","currentIndex="+currentIndex);
            float x = allPoints.get(currentIndex).x;
            float y = allPoints.get(currentIndex).y;
            double[] xy = new double[3];
            xy[0] = x;
            xy[1] = y;
            xy[2] = id[i];
            locations.add(xy);
        }
        return locations;
    }

    /**
     * 获取点
     *
     * @param allPoints 小地图所有点
     * @param percent   每个点在所有点中占据的百分比位置
     * @param id        每个点所代表的icon的id
     * @return
     */
    public static float[] getLocationPoint(List<Point> allPoints, float percent, int id) {
        currentIndex = (int) (allPoints.size() * percent);
        if (currentIndex >= allPoints.size())
            currentIndex = allPoints.size() - 1;
        float x = allPoints.get(currentIndex).x;
        float y = allPoints.get(currentIndex).y;
        float[] xy = new float[3];
        xy[0] = x;
        xy[1] = y;
        xy[2] = id;

        return xy;
    }
}
