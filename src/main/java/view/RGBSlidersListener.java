package view;

import controller.ColorConverter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGBSlidersListener implements ChangeListener {

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

    RGBSlidersListener(JTextArea taColor) {
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
        rgbChanged(sRed.getValue(), sGreen.getValue(), sBlue.getValue());
    }

    private void rgbChanged(int r, int g, int b) {
        float[] hsv = ColorConverter.RGBtoHSV(r, g, b);

        float[] xyz = ColorConverter.RGBtoXYZ(r, g, b);

        System.out.println(" R: " + r + "; G: " + g +  "; B: " + b);
        System.out.println(" H: " + hsv[0] + "; S: " + hsv[1] + "; V: " + hsv[2]);
        System.out.println();

        setHSVValues(hsv);
        setXYZValues(xyz);
    }

    private void setHSVValues(float[] hsv) {
        sHue.setValue((int)hsv[0]);
        sSaturation.setValue((int)(hsv[1] * SLIDER_COEFF));
        sValue.setValue((int)(hsv[2] * SLIDER_COEFF));
    }

    private void setXYZValues(float[] xyz) {
        sX.setValue((int)(xyz[0]));
        sY.setValue((int)(xyz[1]));
        sZ.setValue((int)(xyz[2]));
    }

}
