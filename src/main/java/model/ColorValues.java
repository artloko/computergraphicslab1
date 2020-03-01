package model;

public class ColorValues {

    private int[] rgb;
    private float[] xyz;
    private float[] hsv;

    private static ColorValues instance = null;

    public static synchronized ColorValues getInstance() {
        if (instance == null)
            instance = new ColorValues();
        return instance;
    }

    private ColorValues() {
        rgb = new int[3];
        xyz = new float[3];
        hsv = new float[3];
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public float[] getXyz() {
        return xyz;
    }

    public void setXyz(float[] xyz) {
        this.xyz = xyz;
    }

    public float[] getHsv() {
        return hsv;
    }

    public void setHsv(float[] hsv) {
        this.hsv = hsv;
    }
}
