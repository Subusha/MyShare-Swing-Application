package servicelayer;

import dblayer.DatabaseConnection;
import models.oder;

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

}
