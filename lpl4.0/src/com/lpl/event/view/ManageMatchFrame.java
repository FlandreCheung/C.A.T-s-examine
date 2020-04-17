/*
 * Created by JFormDesigner on Fri Apr 10 11:22:30 CST 2020
 */

package com.lpl.event.view;

import com.lpl.event.controller.MatchController;
import com.lpl.event.dao.BaseDao;
import com.lpl.event.dao.MatchDao;
import com.lpl.event.dao.MatchDaoImpl;
import com.lpl.event.entity.Match;
import com.lpl.event.util.StringUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class ManageMatchFrame extends JFrame {
    public ManageMatchFrame() {
        initComponents();
    }

    static MatchController matchController = new MatchController();
    static MatchDao matchDao = new MatchDaoImpl();

    private void addMatchButtonActionPerformed(ActionEvent e) {
        AddMatchFrame addMatchFrame = new AddMatchFrame();
        addMatchFrame.setVisible(true);
    }


    /**
     * 布置JTable表格模型
     */
    private void setTable(Match match){
        System.out.println("布置表格功能测试");
        DefaultTableModel dtm = (DefaultTableModel) matchInfoTable2.getModel();
        dtm.setRowCount(0);
        List<Match> matchList = matchController.getMatchList(match);
        for (Match m : matchList){
            Vector v = new Vector();
            v.add(m.getMatchID());
            v.add(m.getTeam1());
            v.add(m.getTeam2());
            v.add(m.getPrice());
            dtm.addRow(v);
        }
    }





    /**
     * 删除赛事信息
     */
    protected void deleteMatchAct(ActionEvent e){
        int index = matchInfoTable2.getSelectedRow();
        if(index == -1){
            JOptionPane.showMessageDialog(this,"您还没有选中要删除的数据！");

        }
        DefaultTableModel defaultTableModel = (DefaultTableModel)matchInfoTable2.getModel();
        int id = Integer.parseInt((defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),0)).toString());
        if(matchDao.deleteById(id)){
            JOptionPane.showMessageDialog(this,"删除成功");
        }else {
            JOptionPane.showMessageDialog(this,"删除失败");
        }
        setTable(new Match());
    }


    /**
     * 更新赛事信息
     */
    protected void updateMatchAct(ActionEvent e){
        int index = matchInfoTable2.getSelectedRow();
        if(index ==-1){
            JOptionPane.showMessageDialog(this,"您还没有选中要修改的数据");
        }

        DefaultTableModel defaultTableModel = (DefaultTableModel) matchInfoTable2.getModel();
        //获取表格中的参数
        String team1 = (String) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),1);
        String team2 = (String) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),2);
        int price = (int) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),3);
        String updateTeam1 = updateTeam1TF.getText();
        String updateTeam2 = updateTeam2TF.getText();
        int updatePrice = Integer.parseInt(updatePriceTF.getText());
        if(StringUtil.StringIsEmpty(updateTeam1)||StringUtil.StringIsEmpty(updateTeam2)){
            JOptionPane.showMessageDialog(this,"修改信息不能拿为空");
        }
        if(team1.equals(updateTeam1) && team2.equals(updateTeam2)){
            JOptionPane.showMessageDialog(this,"您还没有修改信息");
        }
        int id = Integer.parseInt(String.valueOf(defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),0)));
        Match match = new Match();
        match.setMatchID(id);
        match.setTeam1(updateTeam1);
        match.setTeam2(updateTeam2);
        match.setPrice(updatePrice);
        int result = matchController.updateTicket(match);
        if(1==result){
            JOptionPane.showMessageDialog(this,"赛事信息修改成功");
        }else{
            JOptionPane.showMessageDialog(this,"赛事信息修改失败");
        }
        //刷新赛事列表
        setTable(new Match());
    }
    /**
     * 鼠标选择赛事信息
     */
    protected void selectTableRow(MouseEvent me){
        DefaultTableModel defaultTableModel = (DefaultTableModel) matchInfoTable2.getModel();
        updateTeam1TF.setText((String) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),matchInfoTable2.getSelectedColumn()));
        updateTeam2TF.setText((String) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),matchInfoTable2.getSelectedColumn()+1));
        updatePriceTF.setText(String.valueOf((int) defaultTableModel.getValueAt(matchInfoTable2.getSelectedRow(),matchInfoTable2.getSelectedColumn()+2)));
    }

    private void updateMatchButtonActionPerformed(ActionEvent e) {
        updateMatchAct(e);
    }

    private void deleteMatchButtonActionPerformed(ActionEvent e) {
        deleteMatchAct(e);
    }

    private void matchInfoTable2MouseClicked(MouseEvent me) {
        selectTableRow(me);
    }

//          设置监听器
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowActivated(WindowEvent e) {
//                try {
//                    queryData();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        matchInfoTable2 = new JTable();
        addMatchButton = new JButton();
        updateMatchButton = new JButton();
        deleteMatchButton = new JButton();
        updateTeam1TF = new JTextField();
        updateTeam2TF = new JTextField();
        updatePriceTF = new JTextField();
        updateTeam1Label = new JLabel();
        updateTeam2Label = new JLabel();
        updatePriceLabel = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- matchInfoTable2 ----
            matchInfoTable2.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                },
                new String[] {
                    "\u8d5b\u4e8b\u7f16\u53f7", "\u4e3b\u961f", "\u526f\u961f", "\u7968\u4ef7"
                }
            ));
            matchInfoTable2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    matchInfoTable2MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(matchInfoTable2);
            //          设置监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    setTable(new Match());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        }

        //---- addMatchButton ----
        addMatchButton.setText("\u6dfb\u52a0\u8d5b\u4e8b");
        addMatchButton.addActionListener(e -> {
			addMatchButtonActionPerformed(e);
			addMatchButtonActionPerformed(e);
		});

        //---- updateMatchButton ----
        updateMatchButton.setText("\u4fee\u6539\u8d5b\u4e8b");
        updateMatchButton.addActionListener(e -> updateMatchButtonActionPerformed(e));

        //---- deleteMatchButton ----
        deleteMatchButton.setText("\u5220\u9664\u8d5b\u4e8b");
        deleteMatchButton.addActionListener(e -> deleteMatchButtonActionPerformed(e));

        //---- updateTeam1Label ----
        updateTeam1Label.setText("\u4fee\u6539\u4e3b\u961f");

        //---- updateTeam2Label ----
        updateTeam2Label.setText("\u4fee\u6539\u526f\u961f");

        //---- updatePriceLabel ----
        updatePriceLabel.setText("\u4fee\u6539\u7968\u4ef7");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 61, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(addMatchButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(updateTeam2Label)
                        .addComponent(updateTeam1Label)
                        .addComponent(updatePriceLabel))
                    .addGap(30, 30, 30)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(updateTeam1TF, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(updateTeam2TF, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(updatePriceTF, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                    .addGap(84, 84, 84)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(updateMatchButton)
                        .addComponent(deleteMatchButton))
                    .addGap(76, 76, 76))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(61, 61, 61)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(updateMatchButton)
                        .addComponent(updateTeam1TF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateTeam1Label))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(updateTeam2TF, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateTeam2Label))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addMatchButton)
                        .addComponent(deleteMatchButton)
                        .addComponent(updatePriceTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updatePriceLabel))
                    .addGap(32, 32, 32))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane1;
    private JTable matchInfoTable2;
    private JButton addMatchButton;
    private JButton updateMatchButton;
    private JButton deleteMatchButton;
    private JTextField updateTeam1TF;
    private JTextField updateTeam2TF;
    private JTextField updatePriceTF;
    private JLabel updateTeam1Label;
    private JLabel updateTeam2Label;
    private JLabel updatePriceLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
