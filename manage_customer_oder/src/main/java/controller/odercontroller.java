package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import models.oder;
import servicelayer.oderservice;
public class odercontroller {
    oder oderobj;
    oderservice service;
    public odercontroller(){
        service=new oderservice();
    }

    public oder addOder(String oderId,String customerId,String oderDate,String oderCategory,int quantity,Double totalPrice,String oderDescription,String status){
        oderobj=new oder(oderId,customerId,oderDate,oderCategory,quantity,totalPrice,oderDescription,status);
        return oderobj;
    }

    public oder updateOder(String oderId,String customerId,String oderDate,String oderCategory,int quantity,Double totalPrice,String oderDescription,String status){
        oderobj=new oder(oderId,customerId,oderDate,oderCategory,quantity,totalPrice,oderDescription,status);
        return oderobj;
    }

    public oder deleteOder(String oderId){
        oderobj=new oder(oderId);
        return oderobj;
    }


    public oder getOderDetail(String oderId){
        System.out.println("getOderDetail");
             oderservice ob = new oderservice();
                return ob.getOderDetail(oderId);

    }




    public boolean addOderToDatabase(){
        return service.addOder(oderobj);
    }

    public boolean updateOderToDatabase(){
        return service.updateOder(oderobj);
    }

    public boolean deleteOderToDatabase(){
        return service.deleteOder(oderobj);
    }




}
