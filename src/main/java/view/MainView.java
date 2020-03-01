package view;

import model.SliderIDsEnum;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class MainView {

    private static final int DELAY = 30;

    private JPanel pMain;
    private JTextArea taColor;
    private JPanel pColor;
    private JButton bSelect;
    private JSlider sRed;
    private JSlider sGreen;
    private JSlider sBlue;
    private JSlider sX;
    private JSlider sY;
    private JSlider sZ;
    private JSlider sHue;
    private JSlider sSaturation;
    private JSlider sValue;
    private JLabel lRed;
    private JLabel lGreen;
    private JLabel lBlue;
    private JLabel lX;
    private JLabel lY;
    private JLabel lZ;
    private JLabel lHue;
    private JLabel lSaturation;
    private JLabel lValue;
    private JFormattedTextField ftRed;
    private JFormattedTextField ftGreen;
    private JFormattedTextField ftBlue;

    public MainView() {
        ValuesChangeListener valuesChangeListener = new ValuesChangeListener(taColor);
        valuesChangeListener.setComponents(sRed, sGreen, sBlue, sX, sY, sZ, sHue, sSaturation, sValue, ftRed, ftGreen, ftBlue);

        sRed.setName(SliderIDsEnum.RED.name());
        sGreen.setName(SliderIDsEnum.GREEN.name());
        sBlue.setName(SliderIDsEnum.BLUE.name());
        sX.setName(SliderIDsEnum.X.name());
        sY.setName(SliderIDsEnum.Y.name());
        sZ.setName(SliderIDsEnum.Z.name());
        sHue.setName(SliderIDsEnum.HUE.name());
        sSaturation.setName(SliderIDsEnum.SATURATION.name());
        sValue.setName(SliderIDsEnum.VALUE.name());

        ftRed.addKeyListener(valuesChangeListener);
        ftBlue.addKeyListener(valuesChangeListener);
        ftGreen.addKeyListener(valuesChangeListener);

        sRed.addChangeListener(valuesChangeListener);
        sGreen.addChangeListener(valuesChangeListener);
        sBlue.addChangeListener(valuesChangeListener);
        sX.addChangeListener(valuesChangeListener);
        sY.addChangeListener(valuesChangeListener);
        sZ.addChangeListener(valuesChangeListener);
        sHue.addChangeListener(valuesChangeListener);
        sSaturation.addChangeListener(valuesChangeListener);
        sValue.addChangeListener(valuesChangeListener);

        bSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame();
                jFrame.setSize(610, 270);
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                jFrame.getContentPane().setBackground(Color.BLACK);
                jFrame.setLayout(null);

                JPanel jPanel = new JPanel();
                jPanel.setBounds(0, 0, 600, 350);
                jPanel.setBackground(Color.BLACK);
                jFrame.add(jPanel);

                JColorChooser jColorChooser = new JColorChooser();
                jPanel.add(jColorChooser);

                jColorChooser.setPreviewPanel(new JPanel());
                jColorChooser.removeChooserPanel(jColorChooser.getChooserPanels()[2]);
                jColorChooser.removeChooserPanel(jColorChooser.getChooserPanels()[3]);

                jFrame.setVisible(true);


                jColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        sRed.setValue(jColorChooser.getColor().getRed());
                        sGreen.setValue(jColorChooser.getColor().getGreen());
                        sBlue.setValue(jColorChooser.getColor().getBlue());
                    }
                });
            }
        });

    }

    private void createUIComponents() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(255);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        ftRed = new JFormattedTextField(formatter);
        ftGreen = new JFormattedTextField(formatter);
        ftBlue = new JFormattedTextField(formatter);

        taColor = new JTextArea() {
            Timer timer = new Timer(DELAY, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
        };
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("MainView");
        jFrame.setContentPane(new MainView().pMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
