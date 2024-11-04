package view;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MSuppliers {
    private JTable tblSupplier;
    private JTextField txtSupplierID;
    private JTextField txtSupplierName;
    private JTextField txtCity;
    private JTextField txtContactNumber;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnRemove;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JLabel lblSupplierID;
    private JLabel lblSupplierName;
    private JLabel lblCity;
    private JLabel lblCantactNumber;
    private JPanel backpanel;
    private JLabel lblEmployeeID;
    private JTextField txtEmployeeID;


    public static void main(String[] args) {
        JFrame frame = new JFrame("MSuppliers");
        frame.setContentPane(new MSuppliers().backpanel);
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
            pst = con.prepareStatement("select*from suppliers");
            ResultSet rs = pst.executeQuery();
            tblSupplier.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public MSuppliers() {
        connect();
        table();
    btnAdd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String supplierid,suppliername,city,contactnumber;

            supplierid = txtSupplierID.getText();
            suppliername = txtSupplierName.getText();
            city = txtCity.getText();
            contactnumber = txtContactNumber.getText();

            try{
                pst = con.prepareStatement("INSERT INTO suppliers(supplierid,suppliername,city,contactnumber) VALUES (?,?,?,?)");
                pst.setString(1,supplierid);
                pst.setString(2,suppliername);
                pst.setString(3,city);
                pst.setString(4,contactnumber);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Record Added");
                table();
                txtSupplierID.setText("");
                txtSupplierName.setText("");
                txtCity.setText("");
                txtContactNumber.setText("");
                txtSupplierID.requestFocus();
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
                    String supplierid = txtSearch.getText();

                    pst = con.prepareStatement("select * from suppliers where supplierid = ?");
                    pst.setString(1,supplierid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        supplierid = rs.getString(1);
                        String suppliername = rs.getString(2);
                        String city = rs.getString(3);
                        String contactnumber = rs.getString(4);

                        txtSupplierID.setText(supplierid);
                        txtSupplierName.setText(suppliername);
                        txtCity.setText(city);
                        txtContactNumber.setText(contactnumber);
                    }
                    else {
                        txtSupplierID.setText("");
                        txtSupplierName.setText("");
                        txtCity.setText("");
                        txtContactNumber.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid OrderID");
                    }
                }
                catch (SQLException e2){
                    e2.printStackTrace();
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierid,suppliername,city,contactnumber;

                supplierid = txtSupplierID.getText();
                suppliername = txtSupplierName.getText();
                city = txtCity.getText();
                contactnumber = txtContactNumber.getText();
                supplierid = txtSupplierID.getText();

                try{
                    pst = con.prepareStatement("update suppliers set suppliername = ?,city = ?,contactnumber = ? where supplierid = ?");
                    pst.setString(1,suppliername);
                    pst.setString(2,city);
                    pst.setString(3,contactnumber);
                    pst.setString(4,supplierid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated");
                    table();
                    txtSupplierID.setText("");
                    txtSupplierName.setText("");
                    txtCity.setText("");
                    txtContactNumber.setText("");
                    txtSearch.setText("");
                    txtSupplierName.requestFocus();
                }
                catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierid;

                supplierid = txtSearch.getText();

                try {
                    pst = con.prepareStatement("delete from suppliers where supplierid = ?");

                    pst.setString(1,supplierid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    table();
                    txtSupplierID.setText("");
                    txtSupplierName.setText("");
                    txtCity.setText("");
                    txtContactNumber.setText("");
                    txtSearch.setText("");
                }
                catch (SQLException e4){
                    e4.printStackTrace();
                }
            }
        });
    }
}
