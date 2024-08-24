package com.survey.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;

public class StyleDefiner {
    public static JTextField defineFieldStyle(JTextField jTextField) {
        jTextField.setPreferredSize(new Dimension(20, 30));
        return jTextField;
    }

    public static JButton defineButtonStyle(JButton jButton) {
        jButton.setPreferredSize(new Dimension(120, 40));
        return jButton;
    }
}
