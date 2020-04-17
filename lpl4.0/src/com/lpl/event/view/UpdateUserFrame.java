/*
 * Created by JFormDesigner on Tue Apr 07 20:15:40 CST 2020
 */

package com.lpl.event.view;

import com.lpl.event.controller.UserController;
import com.lpl.event.entity.LPLUser;
import com.lpl.event.util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class UpdateUserFrame extends JFrame {

    public static LPLUser user;

    public UpdateUserFrame(LPLUser user) {
        this.user = user;
        initComponents();
    }




    static UserController userController = new UserController();
    /**
     * 修改用户信息
     */
    private void updateButtonActionPerformed(ActionEvent e) {
        String userName = updateUMTextField.getText();
        String password = updatePWTextField.getText();
        String rePassword = updateRPWTextField.getText();

        int result = userController.update(user);
        user.setUserName(userName);
        user.setUserPassword(password);

        if(StringUtil.StringIsEmpty(userName)){
            JOptionPane.showMessageDialog(this,"用户名不能为空！");

        }
        if(StringUtil.StringIsEmpty(password)){
            JOptionPane.showMessageDialog(this,"密码不能为空！");
        }

        if(!rePassword.equals(password)){
            JOptionPane.showMessageDialog(this,"确认密码与密码不符！");
        }
        else if(1==result){
            JOptionPane.showMessageDialog(this,"用户信息修改成功！");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this,"用户信息修改失败！");}
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        updateUserNameLabel = new JLabel();
        updatePasswordLabel = new JLabel();
        updateRePasswordLabel = new JLabel();
        updateButton = new JButton();
        updateUMTextField = new JTextField();
        updatePWTextField = new JTextField();
        updateRPWTextField = new JTextField();

        //======== this ========
        setTitle("Update User");
        Container contentPane = getContentPane();

        //---- updateUserNameLabel ----
        updateUserNameLabel.setText("\u8bf7\u8f93\u5165\u65b0\u7528\u6237\u540d");

        //---- updatePasswordLabel ----
        updatePasswordLabel.setText("\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801");

        //---- updateRePasswordLabel ----
        updateRePasswordLabel.setText("\u8bf7\u8f93\u5165\u786e\u8ba4\u5bc6\u7801");

        //---- updateButton ----
        updateButton.setText("\u4fee\u6539");
        updateButton.addActionListener(e -> updateButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(updateUserNameLabel, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addComponent(updatePasswordLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateRePasswordLabel, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(updateUMTextField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(updatePWTextField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(updateRPWTextField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(144, 144, 144)
                            .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(98, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(updateUserNameLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateUMTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(updatePWTextField, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(updatePasswordLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(updateRePasswordLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(updateRPWTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(updateButton)
                    .addContainerGap(36, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel updateUserNameLabel;
    private JLabel updatePasswordLabel;
    private JLabel updateRePasswordLabel;
    private JButton updateButton;
    private JTextField updateUMTextField;
    private JTextField updatePWTextField;
    private JTextField updateRPWTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
