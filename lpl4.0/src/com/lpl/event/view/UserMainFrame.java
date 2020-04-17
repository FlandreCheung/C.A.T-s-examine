/*
 * Created by JFormDesigner on Sun Mar 29 10:13:26 CST 2020
 */

package com.lpl.event.view;

import java.awt.event.*;

import com.lpl.event.controller.MatchController;
import com.lpl.event.controller.UserController;
import com.lpl.event.dao.*;
import com.lpl.event.entity.LPLUser;
import com.lpl.event.entity.Match;


import java.awt.*;
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
public class UserMainFrame extends JFrame {

    public static LPLUser user;
    static UserController userController = new UserController();
    static MatchController matchController = new MatchController();
    static UserDao userDao = new UserDaoImpl();
    static MatchDao matchDao = new MatchDaoImpl();


    public UserMainFrame(LPLUser user){
        this.user = user;
        initComponents();
    }

    public static void main(String[] args){
        //将登录界面的用户对象传递给主界面
        UserMainFrame frame = new UserMainFrame(user);
        frame.setVisible(true);


    }


    //布置JTable表格模型，将数据库中的表格加载到JTable中
    private void setTable(Match match){
        DefaultTableModel dtm = (DefaultTableModel) matchInfoTable.getModel();
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

    private void searchButtonActionPerformed(ActionEvent e) {
        Match m = new Match();
        m.setTeam1(SearhForTeam.getText());
        setTable(m);

    }

    private void setTableFromBookedMatch(Match match){

        DefaultTableModel dtm = (DefaultTableModel) bookedMatchInfoTable.getModel();
        dtm.setRowCount(0);
        List<Match> matchList = matchController.getBookedMatchList(match,user.getUserID());
        for (Match m : matchList){
            Vector v = new Vector();
            v.add(m.getMatchID());
            v.add(m.getTeam1());
            v.add(m.getTeam2());
            v.add(m.getPrice());
            dtm.addRow(v);
        }
        int userId = user.getUserID();
        user.setUserID(userId);
    }


//
//        rs.close();
//          设置监听器
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowActivated(WindowEvent e) {
//                try {
//                    setTable(new Match());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }

//    protected Match getMatchByTableRow(int row){
//        return((DefaultTableModel)matchInfoTable.getModel()).getInstance(row);
//    }

//    protected void selectedTableRow(MouseEvent me){
//        DefaultTableModel defaultTableModel = (DefaultTableModel) matchInfoTable.getModel();
//
//    }



    /**
     * 充值余额
     */
    private void rechargeMenuActionPerformed(ActionEvent e) {
        RechargeFrame frame = new RechargeFrame(user);
        frame.setVisible(true);
    }

    /**
     * 查询余额
     */
    private void searchBalanceMenuActionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog(this,"目前您的余额为："+user.getUserBalance());
    }

    /**
     * 更改用户信息
     */

    private void resetInfoItemActionPerformed(ActionEvent e) {
        UpdateUserFrame frame = new UpdateUserFrame(user);
        frame.setVisible(true);
    }

    private void bookTicketButtonActionPerformed(ActionEvent e) {
        bookTicketAct(e);
    }

    /**
     * 预定赛事
     */
    private void bookTicketAct(ActionEvent e){
        DefaultTableModel defaultTableModel = (DefaultTableModel)matchInfoTable.getModel();
        int userId = user.getUserID();
        int matchId = Integer.parseInt(matchIdTF.getText());
        int price = Integer.parseInt(priceTF.getText());
        int balance = user.getUserBalance();
        Match match = new Match();
        match.setMatchID(matchId);
        user.setUserID(userId);
        System.out.println(matchId);
        System.out.println(userId);
        if(userDao.bookTicket(userId,matchId)){
            if(balance>=price){
                JOptionPane.showMessageDialog(this,"赛事预定成功");
                int newBalance = balance-price;
                user.setUserBalance(newBalance);
                userController.updateBalance(user);
            }else if(balance<price){
                JOptionPane.showMessageDialog(this,"您的余额不足！请充值");
            }

        }else{
            JOptionPane.showMessageDialog(this,"赛事预定失败！");
        }
    }

    private void matchInfoTableMouseClicked(MouseEvent e) {
        selectTableRow(e);
    }

    /**
     * 获取选择的赛事的信息
     */
    protected void selectTableRow(MouseEvent me){
        DefaultTableModel defaultTableModel = (DefaultTableModel) matchInfoTable.getModel();
        matchIdTF.setText(String.valueOf((int) defaultTableModel.getValueAt(matchInfoTable.getSelectedRow(),matchInfoTable.getSelectedColumn())));
        priceTF.setText(String.valueOf((int) defaultTableModel.getValueAt(matchInfoTable.getSelectedRow(),matchInfoTable.getSelectedColumn()+3)));

    }

    private void bookedMatchInfoTableMouseClicked(MouseEvent e) {
        selectTableRowFromBookedTable(e);
    }
    /**
     * 获取已预订的赛事的信息
     */
    protected void selectTableRowFromBookedTable(MouseEvent me){
        DefaultTableModel defaultTableModel = (DefaultTableModel) bookedMatchInfoTable.getModel();
        matchIdTF.setText(String.valueOf((int) defaultTableModel.getValueAt(bookedMatchInfoTable.getSelectedRow(),bookedMatchInfoTable.getSelectedColumn())));
        priceTF.setText(String.valueOf((int) defaultTableModel.getValueAt(bookedMatchInfoTable.getSelectedRow(),bookedMatchInfoTable.getSelectedColumn()+3)));
    }

    /**
     * 退订赛事
     */
    private void cancelMatchAct(ActionEvent e){
        int matchId = Integer.parseInt(matchIdTF.getText());
        int price = Integer.parseInt(priceTF.getText());
        //退订后返还票价
        int balance = user.getUserBalance();
        int newBalance = balance+price;
        if (matchDao.cancelMatchById(matchId)) {
            JOptionPane.showMessageDialog(this,"退订成功！");
            user.setUserBalance(newBalance);
            userController.updateBalance(user);
        }else{
            JOptionPane.showMessageDialog(this,"退订失败！");
        }


    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        cancelMatchAct(e);
    }



    private void initComponents(){
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        userInfoBar = new JMenuBar();
        operateMenu = new JMenu();
        resetInfoItem = new JMenuItem();
        searchBalanceMenu = new JMenuItem();
        rechargeMenu = new JMenuItem();
        manageMenu = new JMenuItem();
        menuItem4 = new JMenuItem();
        Search = new JLabel();
        SearhForTeam = new JTextField();
        searchButton = new JButton();
        scrollPane1 = new JScrollPane();
        matchInfoTable = new JTable();
        bookTicketButton = new JButton();
        matchIdLabel = new JLabel();
        priceLabel = new JLabel();
        matchIdTF = new JTextField();
        priceTF = new JTextField();
        scrollPane2 = new JScrollPane();
        bookedMatchInfoTable = new JTable();
        cancelButton = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/images/\u7968\u52a1\u7cfb\u7edf.png")).getImage());
        setTitle("Main Frame");
        Container contentPane = getContentPane();

        //======== userInfoBar ========
        {
            userInfoBar.setToolTipText("\u64cd\u4f5c");

            //======== operateMenu ========
            {
                operateMenu.setText("\u64cd\u4f5c");

                //---- resetInfoItem ----
                resetInfoItem.setText("\u4fee\u6539\u7528\u6237\u540d/\u5bc6\u7801");
                resetInfoItem.addActionListener(e -> resetInfoItemActionPerformed(e));
                operateMenu.add(resetInfoItem);

                //---- searchBalanceMenu ----
                searchBalanceMenu.setText("\u67e5\u8be2\u4f59\u989d");
                searchBalanceMenu.addActionListener(e -> searchBalanceMenuActionPerformed(e));
                operateMenu.add(searchBalanceMenu);

                //---- rechargeMenu ----
                rechargeMenu.setText("\u5145\u503c\u4f59\u989d");
                rechargeMenu.addActionListener(e -> {
			rechargeMenuActionPerformed(e);
			rechargeMenuActionPerformed(e);
		});
                operateMenu.add(rechargeMenu);

                //---- manageMenu ----
                manageMenu.setText("\u7ba1\u7406\u5df2\u9884\u8ba2\u7684\u6bd4\u8d5b");
                operateMenu.add(manageMenu);

                //---- menuItem4 ----
                menuItem4.setText("\u5347\u7ea7\u4e3aVIP\u7528\u6237");
                operateMenu.add(menuItem4);
            }
            userInfoBar.add(operateMenu);
        }
        setJMenuBar(userInfoBar);

        //---- Search ----
        Search.setText("\u8bf7\u8f93\u5165\u6218\u961f\u540d\u67e5\u8be2\u8d5b\u4e8b\u4fe1\u606f");
        Search.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 22));
        Search.setIcon(new ImageIcon(getClass().getResource("/images/\u67e5\u8be2.png")));

        //---- searchButton ----
        searchButton.setText("\u67e5\u8be2");
        searchButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        searchButton.addActionListener(e -> searchButtonActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- matchInfoTable ----
            matchInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            matchInfoTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u8d5b\u4e8b\u7f16\u53f7", "\u4e3b\u961f", "\u5ba2\u961f", "\u7968\u4ef7"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    Integer.class, String.class, String.class, Integer.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = matchInfoTable.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(2).setResizable(false);
                cm.getColumn(3).setResizable(false);
            }
            matchInfoTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    matchInfoTableMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(matchInfoTable);
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

        //---- bookTicketButton ----
        bookTicketButton.setText("\u9884\u5b9a\u9009\u4e2d\u8d5b\u4e8b");
        bookTicketButton.addActionListener(e -> bookTicketButtonActionPerformed(e));

        //---- matchIdLabel ----
        matchIdLabel.setText("\u8d5b\u4e8b\u7f16\u53f7");

        //---- priceLabel ----
        priceLabel.setText("\u7968\u4ef7");

        //---- matchIdTF ----
        matchIdTF.setEditable(false);

        //---- priceTF ----
        priceTF.setEditable(false);

        //======== scrollPane2 ========
        {

            //---- bookedMatchInfoTable ----
            bookedMatchInfoTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                },
                new String[] {
                    "\u8d5b\u4e8b\u7f16\u53f7", "\u4e3b\u961f", "\u526f\u961f", "\u7968\u4ef7"
                }
            ));
            bookedMatchInfoTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    bookedMatchInfoTableMouseClicked(e);
                }
            });
            scrollPane2.setViewportView(bookedMatchInfoTable);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowActivated(WindowEvent e) {
                    try {
                        setTableFromBookedMatch(new Match());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        //---- cancelButton ----
        cancelButton.setText("\u9000\u8ba2\u9009\u4e2d\u8d5b\u4e8b");
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(scrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(Search)
                                    .addGap(18, 18, 18)
                                    .addComponent(SearhForTeam, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
                                .addComponent(scrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE))
                            .addGap(0, 39, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(matchIdLabel)
                            .addGap(18, 18, 18)
                            .addComponent(matchIdTF, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addGap(84, 84, 84)
                            .addComponent(priceLabel)
                            .addGap(18, 18, 18)
                            .addComponent(priceTF, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                            .addComponent(bookTicketButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                            .addGap(178, 178, 178))))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(713, Short.MAX_VALUE)
                    .addComponent(cancelButton)
                    .addGap(164, 164, 164))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(Search, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addComponent(SearhForTeam, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(matchIdLabel)
                                .addComponent(matchIdTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(priceLabel)
                                .addComponent(priceTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(bookTicketButton)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cancelButton)
                    .addGap(15, 15, 15))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar userInfoBar;
    private JMenu operateMenu;
    private JMenuItem resetInfoItem;
    private JMenuItem searchBalanceMenu;
    private JMenuItem rechargeMenu;
    private JMenuItem manageMenu;
    private JMenuItem menuItem4;
    private JLabel Search;
    private JTextField SearhForTeam;
    private JButton searchButton;
    private JScrollPane scrollPane1;
    private JTable matchInfoTable;
    private JButton bookTicketButton;
    private JLabel matchIdLabel;
    private JLabel priceLabel;
    private JTextField matchIdTF;
    private JTextField priceTF;
    private JScrollPane scrollPane2;
    private JTable bookedMatchInfoTable;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
