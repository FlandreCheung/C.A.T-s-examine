/*
 * Created by JFormDesigner on Thu Mar 26 10:13:21 CST 2020
 */

package com.lpl.event.view;

import com.lpl.event.controller.AdminController;
import com.lpl.event.controller.UserController;
import com.lpl.event.entity.Admin;
import com.lpl.event.entity.LPLUser;
import com.lpl.event.util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;


public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    static UserController userController = new UserController();
    static AdminController adminController = new AdminController();

    //在main方法里添加弹出可视化界面的代码
    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);

    }
    /**
     * 登录按钮
     */

    private void LoginAction(ActionEvent e){
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        //判断用户名和密码是否为空
        if(StringUtil.StringIsEmpty(userName)){
            JOptionPane.showMessageDialog(this,"用户名不能为空！");

        }
        if(StringUtil.StringIsEmpty(password)){
            JOptionPane.showMessageDialog(this,"密码不能为空！");
        }
        //召唤师玩家登陆，对应的是ComboBox中的召唤师玩家
        if(0==(userTypeComboBox.getSelectedIndex())){


            //为用户创建对象，执行登录功能
            LPLUser user = new LPLUser();
            user.setUserName(userName);
            user.setUserPassword(password);
            LPLUser result = userController.login(user);

            //验证登录信息
            if(result == null){

                JOptionPane.showMessageDialog(this,"用户名或密码错误！");
            }else {

                JOptionPane.showMessageDialog(this,"欢迎"+user.getUserName()+"登录LPL票务系统！");
                //登陆成功后将登录界面隐藏
                this.dispose();
                //显示主页面，并将user参数传给用户主界面

                new UserMainFrame(result).setVisible(true);
                new UpdateUserFrame(result);
            }
        }else if(1==(userTypeComboBox.getSelectedIndex())){
            //管理员登录
            Admin admin = new Admin();
            admin.setAdminUserName(userName);
            admin.setAdminPassword(password);
            Admin result = adminController.login(admin);

            if(result == null){

                JOptionPane.showMessageDialog(this,"用户名或密码错误！");
            }else {

                JOptionPane.showMessageDialog(this,"欢迎"+admin.getAdminUserName() +"登录LPL票务系统！");
                //登陆成功后将登录界面隐藏

                this.dispose();

                new AdminMainFrame(result).setVisible(true);
                //显示主页面

            }
        }
    }

    //注册按钮弹出注册界面
    private void Register(ActionEvent e) {
        new RegisterFrame().setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        welcomeLabel = new JLabel();
        userNameLabel = new JLabel();
        userNameTextField = new JTextField();
        passwordLabel = new JLabel();
        userTypeLabel = new JLabel();
        userTypeComboBox = new JComboBox<>();
        loginButton = new JButton();
        registerButton = new JButton();
        passwordTextField = new JTextField();

        //======== this ========
        setTitle("LPL Ticket Management System");
        setIconImage(new ImageIcon(getClass().getResource("/images/\u7968\u52a1\u7cfb\u7edf.png")).getImage());
        Container contentPane = getContentPane();

        //---- welcomeLabel ----
        welcomeLabel.setText("\u82f1\u96c4\u8054\u76df\u8d5b\u4e8b\u7968\u52a1\u7ba1\u7406\u7cfb\u7edf\u767b\u5f55\u754c\u9762");
        welcomeLabel.setIcon(new ImageIcon(getClass().getResource("/images/\u95e8\u7968.png")));
        welcomeLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 28));

        //---- userNameLabel ----
        userNameLabel.setText("\u7528\u6237\u540d");
        userNameLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 18));
        userNameLabel.setIcon(new ImageIcon(getClass().getResource("/images/\u7528\u6237.png")));

        //---- passwordLabel ----
        passwordLabel.setText("\u5bc6 \u7801");
        passwordLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));
        passwordLabel.setIcon(new ImageIcon(getClass().getResource("/images/\u5bc6\u7801.png")));

        //---- userTypeLabel ----
        userTypeLabel.setText("\u7528\u6237\u7c7b\u578b");
        userTypeLabel.setIcon(new ImageIcon(getClass().getResource("/images/\u7c7b\u578b.png")));
        userTypeLabel.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));

        //---- userTypeComboBox ----
        userTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u53ec\u5524\u5e08\u73a9\u5bb6",
            "\u7ba1\u7406\u5458"
        }));

        //---- loginButton ----
        loginButton.setText("\u767b\u9646");
        loginButton.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));
        loginButton.addActionListener(this::LoginAction);

        //---- registerButton ----
        registerButton.setText("\u5982\u679c\u60a8\u8fd8\u4e0d\u662f\u662f\u53ec\u5524\u5e08\u73a9\u5bb6\uff0c\u8bf7\u70b9\u51fb\u6b64\u5904\u6ce8\u518c");
        registerButton.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));
        registerButton.addActionListener(e -> Register(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userNameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(userTypeLabel)
                        .addComponent(loginButton))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(registerButton))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(userNameTextField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                    .addContainerGap(62, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 36, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 772, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(welcomeLabel)
                    .addGap(58, 58, 58)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(userNameLabel)
                        .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(passwordLabel)
                        .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userTypeLabel)
                        .addComponent(userTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(42, 42, 42)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginButton)
                        .addComponent(registerButton))
                    .addContainerGap(43, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel welcomeLabel;
    private JLabel userNameLabel;
    private JTextField userNameTextField;
    private JLabel passwordLabel;
    private JLabel userTypeLabel;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField passwordTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
