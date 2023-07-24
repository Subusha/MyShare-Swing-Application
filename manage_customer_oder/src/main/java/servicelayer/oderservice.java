package servicelayer;

import dblayer.DatabaseConnection;
import models.oder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class oderservice {
    private DatabaseConnection singleConn;


    public oderservice(){
        singleConn=DatabaseConnection.getSingleInstance();
    }

    public boolean addOder(oder Oder){

        try{
            String query="insert into oder values('"+Oder.OderId+"','"+Oder.CustomerId+"','"+Oder.OderDate+"','"+Oder.OderCategory+"',"+Oder.Quantity+","+Oder.TotalPrice+",'"+Oder.OderDescription+"','"+Oder.Status+"')";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex){
            System.out.println("cannot insert a oder");
            return false;
        }
    }

    public boolean updateOder(oder Oder){
        try{
            String query= "UPDATE oder SET "
                    + "CustomerId = '" + Oder.CustomerId + "', "
                    + "OderDate = '" + Oder.OderDate + "', "
                    + "OderCategory = '" + Oder.OderCategory + "', "
                    + "Quantity = " + Oder.Quantity + ", "
                    + "TotalPrice = " + Oder.TotalPrice + ", "
                    + "OderDescription = '" + Oder.OderDescription + "', "
                    + "Status = '" + Oder.Status + "' "
                    + "WHERE OderId = '" + Oder.OderId + "'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex){
            System.out.println("cannot update a oder");
            return false;
        }
    }

    public boolean deleteOder(oder Oder){
        try{
            String query="DELETE FROM oder WHERE OderId = '" + Oder.OderId + "'";
            boolean result=singleConn.ExecuteQuery(query);
            return result;
        }catch (Exception ex){
            System.out.println("cannot delete a oder");
            return false;
        }
    }



    public oder getOderDetail(String orderId) {
        System.out.println(orderId);
        System.out.println("getOderDetail view");
        String query = "SELECT * FROM oder WHERE OderId ='"+orderId+"'";
        oder order = null;


        try (ResultSet resultSet = singleConn.executeQueryAndGetResultSet(query)) {
            if (resultSet != null && resultSet.next()) {
                order = new oder();
                order.setOderId(resultSet.getString("OderId"));
                order.setCustomerId(resultSet.getString("CustomerId"));
                order.setOderDate(resultSet.getString("OderDate"));
                order.setOderCategory(resultSet.getString("OderCategory"));
                order.setQuantity(resultSet.getInt("Quantity"));
                order.setTotalPrice(resultSet.getDouble("TotalPrice"));
                order.setOderDescription(resultSet.getString("OderDescription"));
                order.setStatus(resultSet.getString("Status"));
            }
        } catch (SQLException ex) {
            System.out.println("Cannot fetch order details: " + ex.getMessage());
        }

        return order; // Return the Order object, or null if no order with the given ID is found
    }


}
