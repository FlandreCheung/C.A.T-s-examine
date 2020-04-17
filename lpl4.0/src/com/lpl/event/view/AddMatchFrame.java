/*
 * Created by JFormDesigner on Mon Mar 30 19:58:32 CST 2020
 */

package com.lpl.event.view;

import com.lpl.event.controller.MatchController;
import com.lpl.event.dao.MatchDao;
import com.lpl.event.dao.MatchDaoImpl;
import com.lpl.event.entity.Match;

import com.lpl.event.util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class AddMatchFrame extends JFrame {
    public AddMatchFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        AddMatchFrame frame = new AddMatchFrame();
        frame.setVisible(true);
    }

    public static MatchController matchController;
    /**
     * 添加赛事信息
     */
    private void submitMatchInfo(ActionEvent e) {

        try{
            //读取文本框中数据
        String team1 = addTeam1TextField.getText();
        String team2 = addTeam2TextField.getText();
        int price = Integer.parseInt(setPriceTextField.getText());
        //判断文本框内容是否为空
        if(StringUtil.StringIsEmpty(team1)||StringUtil.StringIsEmpty(team2)){
            JOptionPane.showMessageDialog(this,"战队名不能为空！");
        }
        //创建赛事对象，调用获取赛事信息的方法
        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setPrice(price);
        //调用与数据库交互的方法
            int result = matchController.addTicket(match);
            if(1==result){
                JOptionPane.showMessageDialog(this,"赛事信息添加成功！");
            }else{
                JOptionPane.showMessageDialog(this,"赛事信息添加失败！");
            }
        }catch(NumberFormatException exception){
            JOptionPane.showMessageDialog(this,"请检查票价的格式！");
        }



    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        addTeam1 = new JLabel();
        addTeam2 = new JLabel();
        setPrice = new JLabel();
        addTeam1TextField = new JTextField();
        addTeam2TextField = new JTextField();
        setPriceTextField = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Add Match");
        setIconImage(new ImageIcon(getClass().getResource("/images/\u7968\u52a1\u7cfb\u7edf.png")).getImage());
        Container contentPane = getContentPane();

        //---- addTeam1 ----
        addTeam1.setText("\u6dfb\u52a0\u4e3b\u961f");
        addTeam1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));

        //---- addTeam2 ----
        addTeam2.setText("\u6dfb\u52a0\u526f\u961f");
        addTeam2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));

        //---- setPrice ----
        setPrice.setText("\u8bbe\u5b9a\u7968\u4ef7");
        setPrice.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.BOLD, 20));
        button1.addActionListener(e -> submitMatchInfo(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(107, 107, 107)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(addTeam1, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                .addComponent(addTeam2, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                .addComponent(setPrice, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(addTeam2TextField, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addComponent(addTeam1TextField, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                .addComponent(setPriceTextField, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(42, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(addTeam1, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(addTeam1TextField, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(addTeam2, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addComponent(addTeam2TextField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                    .addGap(31, 31, 31)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(setPrice, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addComponent(setPriceTextField, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                    .addGap(58, 58, 58)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel addTeam1;
    private JLabel addTeam2;
    private JLabel setPrice;
    private JTextField addTeam1TextField;
    private JTextField addTeam2TextField;
    private JTextField setPriceTextField;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
