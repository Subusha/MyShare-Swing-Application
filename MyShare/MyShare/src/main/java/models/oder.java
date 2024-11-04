package models;

public class oder {
    public String OderId;
    public String CustomerId;
    public String OderDate;
    public String OderCategory;
    public int Quantity;
    public Double TotalPrice;
    public String OderDescription;
    public String Status;

    public oder(String oderId,String customerId,String oderDate,String oderCategory,int quantity,Double totalPrice,String oderDescription,String status){
        OderId=oderId;
        CustomerId=customerId;
        OderDate=oderDate;
        OderCategory=oderCategory;
        Quantity=quantity;
        TotalPrice=totalPrice;
        OderDescription=oderDescription;
        Status=status;
    }

    @Override
    public String toString(){
        return "oder{"+
                "OderId='"+OderId+'\''+
                "CustomerId='"+CustomerId+'\''+
                "OderDate='"+OderDate+
                "OderCategory='"+OderCategory+
                "Quantity='"+Quantity+
                "TotalPrice='"+TotalPrice+
                "OderDescription='"+OderDescription+
                "Status='"+Status+
                '}';
    }
}
