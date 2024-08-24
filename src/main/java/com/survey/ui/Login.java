package com.survey.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import com.survey.survey.infraestructure.ui.UserForm;
import com.survey.user.application.AddUserUseCase;
import com.survey.user.application.SearchUserUseCase;
import com.survey.user.domain.entity.User;
import com.survey.user.domain.service.UserService;
import com.survey.user.infrastructure.repository.UserRepository;
import com.survey.users_roles.application.AddUserRoleUseCase;
import com.survey.users_roles.application.GetUserRolesUseCase;
import com.survey.users_roles.domain.entity.UserRol;
import com.survey.users_roles.domain.service.UserRolService;
import com.survey.users_roles.infrastructure.UserRolRepository;

public class Login extends JFrame {
    private User user;
    private UserService userService = new UserRepository();
    private AddUserUseCase addUserUseCase;
    private SearchUserUseCase searchUserUseCase;
    private UserRolService userRolService = new UserRolRepository();
    private AddUserRoleUseCase addUserRoleUseCase;
    private GetUserRolesUseCase getUserRolesUseCase;

    JPanel loginPanel;
    JPanel resgisterPanel;

    JTextField userField;
    JPasswordField passwordField;

    public Login() {
        addUserUseCase = new AddUserUseCase(userService);
        searchUserUseCase = new SearchUserUseCase(userService);

        addUserRoleUseCase = new AddUserRoleUseCase(userRolService);
        getUserRolesUseCase = new GetUserRolesUseCase(userRolService);

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
            String username = userField.getText();
            String password = new String(passwordField.getPassword());  

            User user = searchUserUseCase.execute(username, password).get();
            List<UserRol> userRols = getUserRolesUseCase.execute(user.getId());
            if (userRols.size() == 1) {
                UserRol userRol = userRols.get(0);
                if (userRol.getRolId() == 1) {
                    AdminJFrame adminJFrame = new AdminJFrame();
                    adminJFrame.setReturnActionListener(returnSetVisibleFunction(adminJFrame));
                    adminJFrame.setLocationRelativeTo(buttonsPanel);
                    setVisible(false);
                    adminJFrame.setVisible(true);
                } else {
                    UserForm userForm = new UserForm();
                    userForm.setReturnActionListener(returnSetVisibleFunction(userForm));
                    userForm.setLocationRelativeTo(buttonsPanel);
                    setVisible(false);
                    userForm.setVisible(true);
                }
            }
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
                    String username = userField.getText();
                    String password = new String(passwordField.getPassword());
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    boolean hasBeenAdded = addUserUseCase.execute(user);
                    if (hasBeenAdded) {
                        User newUser = searchUserUseCase.execute(username, password).get();
                        addUserRoleUseCase.execute(newUser.getId());
                    }

                    userField.setText("");
                    passwordField.setText("");
                });
            } else {
                title.setText("USER LOGIN");
                registerButton.setText("Register");
                List<ActionListener> actionListeners = Arrays.asList(enterButton.getActionListeners()); 
                actionListeners.forEach(actionListener -> {
                    enterButton.removeActionListener(actionListener);
                });
                enterButton.addActionListener(event -> {
                    String username = userField.getText();
                    String password = new String(passwordField.getPassword());  

                    User user = searchUserUseCase.execute(username, password).get();
                    List<UserRol> userRols = getUserRolesUseCase.execute(user.getId());
                    if (userRols.size() == 1) {
                        UserRol userRol = userRols.get(0);
                        if (userRol.getRolId() == 1) {
                            AdminJFrame adminJFrame = new AdminJFrame();
                            adminJFrame.setReturnActionListener(returnSetVisibleFunction(adminJFrame));
                            adminJFrame.setLocationRelativeTo(buttonsPanel);
                            setVisible(false);
                            adminJFrame.setVisible(true);
                        } else {
                            UserForm userForm = new UserForm();
                            userForm.setReturnActionListener(returnSetVisibleFunction(userForm));
                            userForm.setLocationRelativeTo(buttonsPanel);
                            setVisible(false);
                            userForm.setVisible(true);
                        }
                    }
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
