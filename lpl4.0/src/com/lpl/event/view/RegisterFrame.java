/*
 * Created by JFormDesigner on Tue Mar 31 23:25:21 CST 2020
 */

package com.lpl.event.view;

import com.lpl.event.controller.UserController;
import com.lpl.event.entity.LPLUser;
import com.lpl.event.dao.UserDao;
import com.lpl.event.util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        initComponents();
    }

    private static UserController userController = new UserController();

    public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
        frame.setVisible(true);
    }
    /**
     * 用户注册
     */
    private void confirmedButtonActionPerformed(ActionEvent e) {
        //获取文本框中内容
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        String rePassword = rePasswordTextField.getText();
        //为新用户创建实体对象
        LPLUser user = new LPLUser();
        user.setUserName(userName);
        user.setUserPassword(password);
        int result = userController.register(user);
        //判断用户名和密码是否为空
        if(StringUtil.StringIsEmpty(userName)||StringUtil.StringIsEmpty(password)){
            JOptionPane.showMessageDialog(this,"用户名或密码不能为空！");
        //判断密码和确认密码是否一致
        }else if(!rePassword.equals(password)){
            JOptionPane.showMessageDialog(this,"确认密码与密码不符！");
        }
        else if(1==result){
            JOptionPane.showMessageDialog(this,"用户注册成功！");
            this.dispose();
            new LoginFrame().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this,"用户名已存在！");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        infoLabel = new JLabel();
        userNameLabel = new JLabel();
        passwordLabel = new JLabel();
        rePasswordLabel = new JLabel();
        userNameTextField = new JTextField();
        passwordTextField = new JTextField();
        rePasswordTextField = new JTextField();
        confirmedButton = new JButton();

        //======== this ========
        setTitle("RegisterFrame");
        setIconImage(new ImageIcon(getClass().getResource("/images/\u7968\u52a1\u7cfb\u7edf.png")).getImage());
        Container contentPane = getContentPane();

        //---- infoLabel ----
        infoLabel.setText("\u65b0\u53ec\u5524\u5e08\u73a9\u5bb6\u6ce8\u518c");
        infoLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));
        infoLabel.setIcon(new ImageIcon(getClass().getResource("/images/\u6ce8\u518c.png")));

        //---- userNameLabel ----
        userNameLabel.setText("\u8bf7\u8f93\u5165\u7528\u6237\u540d");
        userNameLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 18));

        //---- passwordLabel ----
        passwordLabel.setText("\u8bf7\u8f93\u5165\u5bc6\u7801");
        passwordLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 18));

        //---- rePasswordLabel ----
        rePasswordLabel.setText("\u8bf7\u8f93\u5165\u786e\u8ba4\u5bc6\u7801");
        rePasswordLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 18));

        //---- confirmedButton ----
        confirmedButton.setText("\u63d0\u4ea4");
        confirmedButton.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 18));
        confirmedButton.addActionListener(e -> confirmedButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(userNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rePasswordLabel, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(rePasswordTextField)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmedButton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(passwordTextField))
                    .addGap(125, 125, 125))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                    .addGap(202, 202, 202))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(userNameLabel, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(userNameTextField, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGap(41, 41, 41)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(29, 29, 29)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(rePasswordLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addComponent(rePasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(57, 57, 57)
                    .addComponent(confirmedButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel infoLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel rePasswordLabel;
    private JTextField userNameTextField;
    private JTextField passwordTextField;
    private JTextField rePasswordTextField;
    private JButton confirmedButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
