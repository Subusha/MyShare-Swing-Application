package view;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MEmployees {
    private JTextField txtEmployeeID;
    private JTextField txtEmployeeName;
    private JTextField txtContactNumber;
    private JTable tblEmployee;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton searchButton;
    private JTextField txtSearch;
    private JLabel lblEmployeeID;
    private JLabel lblEmployeeName;
    private JLabel lblContactNumber;
    private JPanel backpanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MEmployees");
        frame.setContentPane(new MEmployees().backpanel);
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
            pst = con.prepareStatement("select*from employees");
            ResultSet rs = pst.executeQuery();
            tblEmployee.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public MEmployees() {
        connect();
        table();
    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String employeeid,employeename,contactnumber;

            employeeid = txtEmployeeID.getText();
            employeename = txtEmployeeName.getText();
            contactnumber = txtContactNumber.getText();

            try{
                pst = con.prepareStatement("INSERT INTO employees(employeeid,employeename,contactnumber) VALUES (?,?,?)");
                pst.setString(1,employeeid);
                pst.setString(2,employeename);
                pst.setString(3,contactnumber);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Record Added");
                table();
                txtEmployeeID.setText("");
                txtEmployeeName.setText("");
                txtContactNumber.setText("");
                txtEmployeeID.requestFocus();
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
        }
    });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String employeeid = txtSearch.getText();

                    pst = con.prepareStatement("select * from employees where employeeid = ?");
                    pst.setString(1,employeeid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        employeeid = rs.getString(1);
                        String employeename = rs.getString(2);
                        String contactnumber = rs.getString(3);

                        txtEmployeeID.setText(employeeid);
                        txtEmployeeName.setText(employeename);
                        txtContactNumber.setText(contactnumber);
                    }
                    else {
                        txtEmployeeID.setText("");
                        txtEmployeeName.setText("");
                        txtContactNumber.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid OrderID");
                    }
                }
                catch (SQLException e2){
                    e2.printStackTrace();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeid,employeename,contactnumber;

                employeeid = txtEmployeeID.getText();
                employeename = txtEmployeeName.getText();
                contactnumber = txtContactNumber.getText();
                employeeid = txtEmployeeID.getText();

                try{
                    pst = con.prepareStatement("update employees set employeename = ?,contactnumber = ? where employeeid = ?");
                    pst.setString(1,employeename);
                    pst.setString(2,contactnumber);
                    pst.setString(3,employeeid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Updated");
                    table();
                    txtEmployeeID.setText("");
                    txtEmployeeName.setText("");
                    txtContactNumber.setText("");
                    txtSearch.setText("");
                    txtEmployeeName.requestFocus();
                }
                catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeid;

                employeeid = txtSearch.getText();

                try {
                    pst = con.prepareStatement("delete from employees where employeeid = ?");

                    pst.setString(1,employeeid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    table();
                    txtEmployeeID.setText("");
                    txtEmployeeName.setText("");
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
