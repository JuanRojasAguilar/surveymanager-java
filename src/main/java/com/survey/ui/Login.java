package com.survey.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import com.survey.rol.application.SearchRolByIdUseCase;

public class Login extends JFrame {
    // private User user;
    // private rolInitializer
    // private userInitializer

    JPanel loginPanel;
    JPanel resgisterPanel;

    JTextField userField;
    JPasswordField passwordField;

    public Login() {
        

        createLoginFrame();

        setVisible(true);
    }

    private void createLoginFrame() {
        setTitle("Survey login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setLayout(new BorderLayout());

        addLoginPanel();
    }

    private void addLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        int row = 0;
        gbc.gridx = 0;
        gbc.gridwidth= 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel title = new JLabel("USER LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        loginPanel.add(title, gbc);

        row++;
        gbc.gridwidth= 1;
        gbc.gridy = row;
        JLabel userLabel = new JLabel("usuario: "); 
        loginPanel.add(userLabel, gbc);

        gbc.gridx= 1;
        userField = new JTextField(20);
        userField.setPreferredSize(new Dimension(20, 40));
        loginPanel.add(userField, gbc);

        row++;
        gbc.gridx= 0;
        gbc.gridy = row;
        JLabel passwordLabel = new JLabel("contraseña: "); 
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx= 1;
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(20, 40));
        loginPanel.add(passwordField, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        JButton enterButton = new JButton("  Enter  ");
        enterButton.addActionListener(e -> {
            String user = userField.getText();
            String password = new String(passwordField.getPassword());

            /*
            try {
                User user = (searchUserByIdUseCase.execute(user, password)).get();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(loginPanel, "el usuario no existe", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             */

            // Rol userRol = (searchRolByIdUseCase.execute(user.getIdRol)).get();

            /* 
            if(userRol == 1) {
                evoqueAdminJFrame();
            } else {
                evoqueUserJFrame();
            }
        */
        });
        buttonsPanel.add(enterButton);

        buttonsPanel.add(Box.createHorizontalStrut(10));

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            if (registerButton.getText().equals("Register")) {
                title.setText("USER REGISTER");
                registerButton.setText("GoBack");
                List<ActionListener> actionListeners = Arrays.asList(enterButton.getActionListeners()); 
                actionListeners.forEach(actionListener -> {
                    enterButton.removeActionListener(actionListener);
                });
                enterButton.addActionListener(event -> {
                    String user = userField.getText();
                    String password = new String(passwordField.getPassword());

                    // User user = (AddUserUseCase.execute(new User(user, password, 1)));
                    // evoqueUserJframe();
                });
            } else {
                title.setText("USER LOGIN");
                registerButton.setText("Register");
                List<ActionListener> actionListeners = Arrays.asList(enterButton.getActionListeners()); 
                actionListeners.forEach(actionListener -> {
                    enterButton.removeActionListener(actionListener);
                });
                enterButton.addActionListener(event -> {
                    String user = userField.getText();
                    String password = new String(passwordField.getPassword());
        
                    /*
                    try {
                        User user = (searchUserByIdUseCase.execute(user, password)).get();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(loginPanel, "el usuario no existe", "error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                     */
        
                    // Rol userRol = (searchRolByIdUseCase.execute(user.getIdRol)).get();
        
                    /* 
                    if(userRol == 1) {
                        evoqueAdminJFrame();
                    } else {
                        evoqueUserJFrame();
                    }
                */
                });
            }
        });
        buttonsPanel.add(registerButton);

        loginPanel.add(buttonsPanel, gbc);

        add(loginPanel, BorderLayout.CENTER);


    }    

    private ActionListener returnSetVisibleFunction(JFrame panelToDispose) {
        return new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    panelToDispose.dispose();
                    setVisible(true);
                }
        };
    }
}
