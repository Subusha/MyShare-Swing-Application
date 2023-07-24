package views;

import javax.swing.*;
import controller.odercontroller;
import models.oder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class oderview extends JFrame{
    private JLabel title;
    private JLabel OderId;
    private JTextField txtOderId;
    private JLabel CoustomerId;
    private JTextField txtCustomerId;
    private JLabel OderDtae;
    private JTextField txtOderDate;
    private JLabel OderCategory;
    private JTextField txtOderCategory;
    private JLabel Quantity;
    private JTextField txtQuantity;
    private JLabel TotalPrice;
    private JTextField txtTotalPrice;
    private JLabel OderDescription;
    private JLabel Status;
    private JTextField txtOderDescription;
    private JTextField txtStatus;
    private JButton btnAddOder;
    private JButton btnUpdateOder;
    private JButton btnDeleteOder;
    private JPanel backpanel;

    odercontroller oderC;
    oder[] oderArray;
    int oderCount;
    int updatecount;
    int deletecount;


    public oderview(){
        oderC=new odercontroller();
        oderArray=new oder[100];
        oderCount=0;
        updatecount=0;
        deletecount=0;
        btnAddOder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   String oderId=txtOderId.getText();
                   String customerId=txtCustomerId.getText();
                   String oderDate=txtOderDate.getText();
                   String oderCategory=txtOderCategory.getText();
                   int quantity=Integer.parseInt(txtQuantity.getText());
                   double totalPrice=Double.parseDouble(txtTotalPrice.getText());
                   String oderDescription=txtOderDescription.getText();
                   String status=txtStatus.getText();

                   oder oderOb=oderC.addOder(oderId,customerId,oderDate,oderCategory,quantity,totalPrice,oderDescription,status);
                   if(oderC.addOderToDatabase()){
                       JOptionPane.showMessageDialog(backpanel,"Successfully adda oder to database","Sucess",0);
                   }
                   else{
                       JOptionPane.showMessageDialog(backpanel,"Cannot insert a oder to database","Error",0);
                   }
                   oderArray[oderCount]=oderOb;
                   oderCount++;
                   JOptionPane.showMessageDialog(backpanel, "Added oder " + oderCount, "Success", 0);
               }catch (NumberFormatException ex)
               {
                   JOptionPane.showMessageDialog(backpanel, "Please Provide Correct Inputs", "Error", 0);
               }
            }
        });


        btnUpdateOder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String oderId=txtOderId.getText();
                    String customerId=txtCustomerId.getText();
                    String oderDate=txtOderDate.getText();
                    String oderCategory=txtOderCategory.getText();
                    int quantity=Integer.parseInt(txtQuantity.getText());
                    double totalPrice=Double.parseDouble(txtTotalPrice.getText());
                    String oderDescription=txtOderDescription.getText();
                    String status=txtStatus.getText();

                    oder oderOb=oderC.updateOder(oderId,customerId,oderDate,oderCategory,quantity,totalPrice,oderDescription,status);
                    if(oderC.updateOderToDatabase()){
                        JOptionPane.showMessageDialog(backpanel,"Successfully update oder to database","Sucess",0);
                    }
                    else{
                        JOptionPane.showMessageDialog(backpanel,"Cannot update a oder to database","Error",0);
                    }
                    oderArray[updatecount]=oderOb;
                    updatecount++;
                    JOptionPane.showMessageDialog(backpanel, "update oder " + updatecount, "Success", 0);
                }catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(backpanel, "Please Provide Correct Inputs", "Error", 0);
                }
            }
        });
        btnDeleteOder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String oderId=txtOderId.getText();

                    oder oderOb=oderC.deleteOder(oderId);
                    if(oderC.deleteOderToDatabase()){
                        JOptionPane.showMessageDialog(backpanel,"Successfully delete oder to database","Sucess",0);
                    }
                    else{
                        JOptionPane.showMessageDialog(backpanel,"Cannot delete a oder to database","Error",0);
                    }
                    oderArray[deletecount]=oderOb;
                    deletecount++;
                    JOptionPane.showMessageDialog(backpanel, "delete oder " + deletecount, "Success", 0);
                }catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(backpanel, "Please Provide Correct Inputs", "Error", 0);
                }
            }
        });
        

        txtOderId.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    String orderId = txtOderId.getText();
                    odercontroller oc = new odercontroller();
                    oder order =  oc.getOderDetail(orderId);


                if (order != null) {
                    txtOderId.setText(String.valueOf(order.getOderId()));
                    txtCustomerId.setText(String.valueOf(order.getCustomerId()));
                    txtOderDate.setText(order.getOderDate());
                    txtOderCategory.setText(order.getOderCategory());
                    txtQuantity.setText(String.valueOf(order.getQuantity()));
                    txtTotalPrice.setText(String.valueOf(order.getTotalPrice()));
                    txtOderDescription.setText(order.getOderDescription());
                    txtStatus.setText(order.getStatus());
                } else {
                    // If the order is null, clear the text fields
                    txtOderId.setText("");
                    txtCustomerId.setText("");
                    txtOderDate.setText("");
                    txtOderCategory.setText("");
                    txtQuantity.setText("");
                    txtTotalPrice.setText("");
                    txtOderDescription.setText("");
                    txtStatus.setText("");
                }



            }
        });
    }

    public static void main(String[] args) {
        oderview ui=new oderview();
        ui.setContentPane(ui.backpanel);
        ui.setTitle("Oder Manager 1.0");
        ui.setSize(600,600);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }

}
