package views;

//import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageSupplier {
    private JTextField txtitemname;
    private JTextField txtquantity;
    private JTextField txtphone;
    private JTextField txtcity;
    private JTextField txtage;
    private JButton btnupdate;
    private JButton btnadd;
    private JButton btndelete;
    private JTextField txtname;
    private JButton btnsearch;
    private JTextField txtserch;
    private JLabel lblname;
    private JLabel lblage;
    private JLabel lblcity;
    private JLabel lblitemname;
    private JLabel lblquantity;
    private JLabel lblcontact;
    private JPanel main;
    private JTable tblinfo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManageSupplier");
        frame.setContentPane(new ManageSupplier().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/oop_db", "root","");
            System.out.println("Successes");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }


    void tableload()
    {
        try
        {
            pst = con.prepareStatement("select * from suppliernew");
            ResultSet rs = pst.executeQuery();
//            tblinfo.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ManageSupplier() {
//        connect();
//        tableload();
    btnadd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String name,age,city,itemname,quantity,telephone;

            name = txtname.getText();
            age = txtage.getText();
            city=txtcity.getText();
            itemname=txtitemname.getText();
            quantity=txtquantity.getText();
            telephone=txtphone.getText();

            try {

                if ((name.equals("")) || (Integer.parseInt(age) < 20) || (city.equals("")) || (itemname.equals("")) || (Integer.parseInt(quantity) < 0) || (Integer.parseInt(telephone) < 0)) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Details(Supplier Age Must Above 20 Years Old)");
                } else {
                    pst = con.prepareStatement("insert into suppliernew(name,age,city,productname,quantity,telephone)values(?,?,?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, age);
                    pst.setString(3, city);
                    pst.setString(4, itemname);
                    pst.setString(5, quantity);
                    pst.setString(6, telephone);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added Successfully!!!!!");

                    txtname.setText("");
                    txtage.setText("");
                    txtcity.setText("");
                    txtitemname.setText("");
                    txtquantity.setText("");
                    txtphone.setText("");
                    txtname.requestFocus();
                }
            }
            catch(SQLException e1)
                {

                    e1.printStackTrace();
                }


        }
    });
        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String supplierid = txtserch.getText();

                    pst = con.prepareStatement("select name,age,city,productname,quantity,telephone from suppliernew where id = ?");
                    pst.setString(1, supplierid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String suppliername = rs.getString(1);
                        String supplierage = rs.getString(2);
                        String suppliercity = rs.getString(3);
                        String supplieritemname = rs.getString(4);
                        String supplieritemquantity = rs.getString(5);
                        String supplierphonenumber = rs.getString(6);

                        txtname.setText(suppliername);
                        txtage.setText(supplierage);
                        txtcity.setText(suppliercity);
                        txtitemname.setText(supplieritemname);
                        txtquantity.setText(supplieritemquantity);
                        txtphone.setText(supplierphonenumber);

                    }
                    else
                    {
                        txtname.setText("");
                        txtage.setText("");
                        txtcity.setText("");
                        txtitemname.setText("");
                        txtquantity.setText("");
                        txtphone.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Supplier No");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

        });
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String supplierid,name,age,city,itemname,quantity,telephone;

                name = txtname.getText();
                age = txtage.getText();
                city=txtcity.getText();
                itemname=txtitemname.getText();
                quantity=txtquantity.getText();
                telephone=txtphone.getText();
                supplierid=txtserch.getText();

                try {
                    pst = con.prepareStatement("update suppliernew set name = ?,age = ?,city = ?,productname = ?,quantity = ?,telephone = ? where id = ?");
                    pst.setString(1, name);
                    pst.setString(2, age);
                    pst.setString(3, city);
                    pst.setString(4, itemname);
                    pst.setString(5, quantity);
                    pst.setString(6, telephone);
                    pst.setString(7,supplierid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated Successfully!!!!!");
                    tableload();
                    txtname.setText("");
                    txtage.setText("");
                    txtcity.setText("");
                    txtitemname.setText("");
                    txtquantity.setText("");
                    txtphone.setText("");
                    txtname.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        });
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String supplierID;
                supplierID = txtserch.getText();

                try {
                    pst = con.prepareStatement("delete from suppliernew  where id = ?");

                    pst.setString(1, supplierID);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Record Delete Successfully!!!!!");
                    tableload();
                    txtname.setText("");
                    txtage.setText("");
                    txtcity.setText("");
                    txtitemname.setText("");
                    txtquantity.setText("");
                    txtphone.setText("");
                    txtname.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }


            }
        });
    }
}
