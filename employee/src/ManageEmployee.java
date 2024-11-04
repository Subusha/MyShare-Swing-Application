import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class ManageEmployee extends JFrame {

    private ArrayList<Employee> employees = new ArrayList<>();
    private DefaultListModel<Employee> employeeListModel;
    private JList<Employee> employeeList;
    private JTextField txtname;
    private JTextField txtposition;
    private JTextField txtemail;
    private JTextField txtcontactno;
    private JButton btnadd;
    private JButton btnedit;
    private JButton btndelete;
    private JLabel lblname;
    private JLabel lblposition;
    private JLabel lblemail;
    private JLabel lblcontactno;

    public ManageEmployee() {
        // Set up the JFrame
        setTitle("Employees");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());



        // Create the employee list model and JList
        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);

        // Create a scroll pane for the JList
        JScrollPane scrollPane = new JScrollPane(employeeList);

        // Create panels for buttons and fields
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnadd);
        buttonPanel.add(btnedit);
        buttonPanel.add(btndelete);

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2));
        fieldsPanel.add(lblname);
        fieldsPanel.add(txtname);
        fieldsPanel.add(lblposition);
        fieldsPanel.add(txtposition);
        fieldsPanel.add(lblemail);
        fieldsPanel.add(txtemail);
        fieldsPanel.add(lblcontactno);
        fieldsPanel.add(txtcontactno);

        // Add panels to the JFrame
        add(fieldsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtname.getText();
                String position = txtposition.getText();
                String email = txtemail.getText();
                String contactno = txtcontactno.getText();

                Employee employee = new Employee(name, position, email, contactno);
                employees.add(employee);
                updateEmployeeList();
                clearFields();

                try {

                        pst = con.prepareStatement("insert into employee(name,position,email,contactno)values(?,?,?,?)");
                        pst.setString(1, name);
                        pst.setString(2, position);
                        pst.setString(3, email);
                        pst.setString(4, contactno);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Added Successfully!!!!!");

                        txtname.setText("");
                        txtposition.setText("");
                        txtemail.setText("");
                        txtcontactno.setText("");
                        txtname.requestFocus();
                    }
                catch(SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });


        btnedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = employeeList.getSelectedValue();
                if (selectedEmployee != null) {
                    selectedEmployee.setName(txtname.getText());
                    selectedEmployee.setPosition(txtposition.getText());
                    selectedEmployee.setEmail(txtemail.getText());
                    selectedEmployee.setPhone(txtcontactno.getText());
                    updateEmployeeList();
                    clearFields();
                }
                String name = txtname.getText();
                String position = txtposition.getText();
                String email =txtemail.getText();
                String contactno =txtcontactno.getText();
                try {
                    pst = con.prepareStatement("update employee set name = ?,position = ?,email = ?,contactno = ? where id = ?");
                    pst.setString(1, name);
                    pst.setString(2, position);
                    pst.setString(3, email);
                    pst.setString(4, contactno);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated Successfully!!!!!");
                    tableload();
                    txtname.setText("");
                    txtposition.setText("");
                    txtemail.setText("");
                    txtcontactno.setText("");
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
                Employee selectedEmployee = employeeList.getSelectedValue();
                if (selectedEmployee != null) {
                    employees.remove(selectedEmployee);
                    updateEmployeeList();
                    clearFields();

                    int id = selectedEmployee.getId(); // Assuming you have a method to get the id of an Employee

                    try {
                        pst = con.prepareStatement("DELETE FROM employee WHERE id = ?");
                        pst.setInt(1, id);

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Record Delete Successfully!!!!!");
                        tableload();
                        txtname.setText("");
                        txtposition.setText("");
                        txtemail.setText("");
                        txtcontactno.setText("");
                        txtname.requestFocus();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });


    }
    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/oop_db", "root","");
            System.out.println("Success");
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

            pst = con.prepareStatement("select * from employee");
            ResultSet rs = pst.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void updateEmployeeList() {
        employeeListModel.clear();
        for (Employee employee : employees) {
            employeeListModel.addElement(employee);
        }
    }

    private void clearFields() {
        txtname.setText("");
        txtposition.setText("");
        txtemail.setText("");
        txtcontactno.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ManageEmployee().setVisible(true);
            }
        });
    }
}
