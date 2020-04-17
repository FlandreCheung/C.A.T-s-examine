/*
 * Created by JFormDesigner on Wed Apr 08 13:30:12 CST 2020
 */

package com.lpl.event.view;

import java.awt.event.*;

import com.lpl.event.controller.UserController;
import com.lpl.event.entity.LPLUser;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class RechargeFrame extends JFrame {

    public static LPLUser user;
    public static UserController userController;

    public RechargeFrame(LPLUser user) {
        this.user = user;
        initComponents();
    }

    /**
     * 用户充值余额
     */
    private void rechargeButtonActionPerformed(ActionEvent e) {
        int oldBalance = user.getUserBalance();
        int rechargeNumber = Integer.parseInt(rechargeTextField.getText());
        int newBalance = oldBalance+rechargeNumber;
        user.setUserBalance(newBalance);
        try{

            userController.updateBalance(user);
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(this,"请检查您输入的是否为数字！");
        }
        JOptionPane.showMessageDialog(this,"充值成功！目前您的余额为"+newBalance+"元");
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        rechargeLabel = new JLabel();
        rechargeTextField = new JTextField();
        rechargeButton = new JButton();

        //======== this ========
        setTitle("Recharge");
        Container contentPane = getContentPane();

        //---- rechargeLabel ----
        rechargeLabel.setText("\u8bf7\u8f93\u5165\u8981\u5145\u503c\u7684\u91d1\u989d");

        //---- rechargeButton ----
        rechargeButton.setText("\u5145\u503c");
        rechargeButton.addActionListener(e -> rechargeButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(rechargeLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rechargeTextField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(rechargeButton)))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(rechargeLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(rechargeTextField, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addComponent(rechargeButton)
                    .addGap(25, 25, 25))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel rechargeLabel;
    private JTextField rechargeTextField;
    private JButton rechargeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
