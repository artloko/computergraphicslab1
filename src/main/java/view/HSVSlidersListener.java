package view;

import controller.ColorConverter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HSVSlidersListener implements ChangeListener {

    private static final int SLIDER_COEFF = 100;

    private JTextArea taColor;

    private JFormattedTextField ftRed;
    private JFormattedTextField ftGreen;
    private JFormattedTextField ftBlue;

    private JSlider sRed;
    private JSlider sGreen;
    private JSlider sBlue;
    private JSlider sX;
    private JSlider sY;
    private JSlider sZ;
    private JSlider sHue;
    private JSlider sSaturation;
    private JSlider sValue;

    HSVSlidersListener(JTextArea taColor) {
        this.taColor = taColor;
    }

    void setComponents(JSlider sRed, JSlider sGreen, JSlider sBlue, JSlider sX, JSlider sY, JSlider sZ,
                       JSlider sHue, JSlider sSaturation, JSlider sValue,
                       JFormattedTextField ftRed, JFormattedTextField ftBlue, JFormattedTextField ftGreen) {
        this.sRed = sRed;
        this.sGreen = sGreen;
        this.sBlue = sBlue;
        this.sX = sX;
        this.sY = sY;
        this.sZ = sZ;
        this.sHue = sHue;
        this.sSaturation = sSaturation;
        this.sValue = sValue;

        this.ftRed = ftRed;
        this.ftGreen = ftGreen;
        this.ftBlue = ftBlue;
    }

    public void stateChanged(ChangeEvent e) {
        hsvChange(sHue.getValue(), sHue.getValue(), sHue.getValue());
    }

    private void hsvChange(int h, int s, int v) {

        int[] rgb = ColorConverter.HSVtoRGB(h, s / 100f, v / 100f);

        float[] xyz = ColorConverter.RGBtoXYZ(rgb[0], rgb[1], rgb[2]);

        setRGBValues(rgb);
        setXYZValues(xyz);
    }

    private void setRGBValues(int[] rgb) {
        sRed.setValue(rgb[0]);
        sGreen.setValue(rgb[1]);
        sBlue.setValue(rgb[2]);
    }

    private void setXYZValues(float[] xyz) {
        sX.setValue((int)(xyz[0]));
        sY.setValue((int)(xyz[1]));
        sZ.setValue((int)(xyz[2]));
    }
}
