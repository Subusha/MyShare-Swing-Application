package view;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MOrders extends JFrame {
    private JPanel backpanel;
    private JTextField txtOderID;
    private JTextField txtCategory;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnRemove;
    private JTable tblOrder;
    private JLabel lblOderID;
    private JLabel lblCategory;
    private JLabel lblQuantity;
    private JLabel lblPrice;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JLabel lblEmployeeID;
    private JTextField txtEmployeeID;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MOrders");
        frame.setContentPane(new MOrders().backpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/myshare","root","");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    void table(){
        try {
            pst = con.prepareStatement("select*from orders");
            ResultSet rs = pst.executeQuery();
            tblOrder.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public MOrders() {
        connect();
        table();
    btnAdd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String orderid,category,quantity,price,employeeid;

            orderid = txtOderID.getText();
            category = txtCategory.getText();
            quantity = txtQuantity.getText();
            price = txtPrice.getText();
            employeeid = txtEmployeeID.getText();

            try{
                pst = con.prepareStatement("INSERT INTO orders(orderid,category,quantity,unitprice,employeeid) VALUES (?,?,?,?,?)");
                pst.setString(1,orderid);
                pst.setString(2,category);
                pst.setString(3,quantity);
                pst.setString(4,price);
                pst.setString(5,employeeid);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Record Added");
                table();
                txtOderID.setText("");
                txtCategory.setText("");
                txtQuantity.setText("");
                txtPrice.setText("");
                txtEmployeeID.setText("");
                txtOderID.requestFocus();
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderid,category,quantity,price,employeeid;

                category = txtCategory.getText();
                quantity = txtQuantity.getText();
                price = txtPrice.getText();
                orderid = txtSearch.getText();
                orderid = txtOderID.getText();
                employeeid = txtEmployeeID.getText();

                try{
                    pst = con.prepareStatement("update orders set category = ?,quantity = ?,unitprice = ?,employeeid = ? where orderid = ?");
                    pst.setString(1,category);
                    pst.setString(2,quantity);
                    pst.setString(3,price);
                    pst.setString(4,employeeid);
                    pst.setString(5,orderid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated");
                    table();
                    txtOderID.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    txtSearch.setText("");
                    txtCategory.setText("");
                    txtEmployeeID.setText("");
                    txtCategory.requestFocus();
                }
                catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String orderid = txtSearch.getText();

                    pst = con.prepareStatement("select * from orders where orderid = ?");
                    pst.setString(1,orderid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        orderid = rs.getString(1);
                        String category = rs.getString(2);
                        String quantity = rs.getString(3);
                        String price = rs.getString(4);
                        String employeeid = rs.getString(5);

                        txtOderID.setText(orderid);
                        txtCategory.setText(category);
                        txtQuantity.setText(quantity);
                        txtPrice.setText(price);
                        txtEmployeeID.setText(employeeid);
                    }
                    else {
                        txtOderID.setText("");
                        txtCategory.setText("");
                        txtQuantity.setText("");
                        txtPrice.setText("");
                        txtEmployeeID.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid OrderID");
                    }
                }
                catch (SQLException e2){
                    e2.printStackTrace();
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderid;

                orderid = txtSearch.getText();

                try {
                    pst = con.prepareStatement("delete from orders where orderid = ?");

                    pst.setString(1,orderid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    table();
                    txtOderID.setText("");
                    txtCategory.setText("");
                    txtPrice.setText("");
                    txtQuantity.setText("");
                    txtEmployeeID.setText("");
                    txtSearch.setText("");
                }
                catch (SQLException e4){
                    e4.printStackTrace();
                }
            }
        });
    }

}
