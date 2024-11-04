package controller;

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

    public boolean addOderToDatabase(){
        return service.addOder(oderobj);
    }

}
