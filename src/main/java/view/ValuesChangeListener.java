package view;

import controller.ColorConverter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ValuesChangeListener extends KeyAdapter implements ChangeListener {

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

    ValuesChangeListener(JTextArea taColor) {
        this.taColor = taColor;
    }

    public void stateChanged(ChangeEvent e) {

        JSlider jSlider = (JSlider) e.getSource();

        removeSliderListeners();

        switch (jSlider.getName()) {
            case "RED":
            case "GREEN":
            case "BLUE": {
                rgbChanged(sRed.getValue(), sGreen.getValue(), sBlue.getValue());
                break;
            }
            case "X":
            case "Y":
            case "Z": {
                xyzChanged(sX.getValue(), sY.getValue(), sZ.getValue());
                break;
            }
            case "HUE":
            case "SATURATION":
            case "VALUE": {
                hsvChanged(sHue.getValue(), sSaturation.getValue(), sValue.getValue());
                break;
            }
        }

        setInputFieldsValues(sRed.getValue(), sGreen.getValue(), sBlue.getValue());

        taColor.setBackground(new Color(sRed.getValue(), sGreen.getValue(), sBlue.getValue()));

        addSliderListeners();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyPressed(e);

        try {
            int r = Integer.parseInt(ftRed.getText());
            int g = Integer.parseInt(ftGreen.getText());
            int b = Integer.parseInt(ftBlue.getText());

            setRGBValues(new int[]{r, g, b});
            rgbChanged(r, g, b);

            System.out.println("R: " + ftRed.getText());
            System.out.println("G: " + ftGreen.getText());
            System.out.println("B: " + ftBlue.getText());

            taColor.setBackground(new Color(sRed.getValue(), sGreen.getValue(), sBlue.getValue()));
        }catch (NumberFormatException numberFormatException) {

        }
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

    private void rgbChanged(int r, int g, int b) {
        float[] hsv = ColorConverter.RGBtoHSV(r, g, b);

        float[] xyz = ColorConverter.RGBtoXYZ(r, g, b);

        setHSVValues(hsv);
        setXYZValues(xyz);
    }

    private void hsvChanged(int h, int s, int v) {

        int[] rgb = ColorConverter.HSVtoRGB(h, s / 100f, v / 100f);

        float[] xyz = ColorConverter.RGBtoXYZ(rgb[0], rgb[1], rgb[2]);

        setRGBValues(rgb);
        setXYZValues(xyz);
    }

    private void xyzChanged(int x, int y, int z) {

        int[] rbg = ColorConverter.XYZtoRGB((float)x, (float)y, (float)z);

        float[] hsv = ColorConverter.RGBtoHSV(rbg[0], rbg[1], rbg[2]);

        setRGBValues(rbg);
        setHSVValues(hsv);
    }

    private void setInputFieldsValues(int r, int g, int b) {
        ftRed.setValue(r);
        ftGreen.setValue(g);
        ftBlue.setValue(b);
    }

    private void setHSVValues(float[] hsv) {
        sHue.setValue((int)hsv[0]);
        sSaturation.setValue((int)(hsv[1] * SLIDER_COEFF));
        sValue.setValue((int)(hsv[2] * SLIDER_COEFF));
    }

    private void setXYZValues(float[] xyz) {
        sX.setValue((int)xyz[0]);
        sY.setValue((int)xyz[1]);
        sZ.setValue((int)xyz[2]);
    }

    private void setRGBValues(int[] rgb) {
        sRed.setValue(rgb[0]);
        sGreen.setValue(rgb[1]);
        sBlue.setValue(rgb[2]);
    }

    private void removeSliderListeners() {
        sRed.removeChangeListener(this);
        sGreen.removeChangeListener(this);
        sBlue.removeChangeListener(this);
        sX.removeChangeListener(this);
        sY.removeChangeListener(this);
        sZ.removeChangeListener(this);
        sHue.removeChangeListener(this);
        sSaturation.removeChangeListener(this);
        sValue.removeChangeListener(this);
    }

    private void addSliderListeners() {
        sRed.addChangeListener(this);
        sGreen.addChangeListener(this);
        sBlue.addChangeListener(this);
        sX.addChangeListener(this);
        sY.addChangeListener(this);
        sZ.addChangeListener(this);
        sHue.addChangeListener(this);
        sSaturation.addChangeListener(this);
        sValue.addChangeListener(this);
    }
}
