/*
 * Created by JFormDesigner on Fri Apr 10 11:04:40 CST 2020
 */

package com.lpl.event.view;

import java.awt.event.*;
import com.lpl.event.entity.Admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class AdminMainFrame extends JFrame {

    public static Admin admin;

    public AdminMainFrame(Admin admin) {
        this.admin = admin;
        initComponents();
    }

    private void userManageButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    /**
     * 管理用户
     */
    private void matchManageButtonActionPerformed(ActionEvent e) {
        ManageMatchFrame manageMatchFrame = new ManageMatchFrame();
        manageMatchFrame.setVisible(true);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        userManageButton = new JButton();
        matchManageButton = new JButton();

        //======== this ========
        setTitle("Admin Main Frame");
        Container contentPane = getContentPane();

        //---- userManageButton ----
        userManageButton.setText("\u7ba1\u7406\u7528\u6237");
        userManageButton.addActionListener(e -> userManageButtonActionPerformed(e));

        //---- matchManageButton ----
        matchManageButton.setText("\u7ba1\u7406\u8d5b\u4e8b");
        matchManageButton.addActionListener(e -> matchManageButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(userManageButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59)
                    .addComponent(matchManageButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(44, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userManageButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                        .addComponent(matchManageButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(77, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton userManageButton;
    private JButton matchManageButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
