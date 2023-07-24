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

    public oder() {

    }

    public String getOderId() {
        return OderId;
    }

    public void setOderId(String oderId) {
        OderId = oderId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getOderDate() {
        return OderDate;
    }

    public void setOderDate(String oderDate) {
        OderDate = oderDate;
    }

    public String getOderCategory() {
        return OderCategory;
    }

    public void setOderCategory(String oderCategory) {
        OderCategory = oderCategory;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getOderDescription() {
        return OderDescription;
    }

    public void setOderDescription(String oderDescription) {
        OderDescription = oderDescription;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public oder(String oderId, String customerId, String oderDate, String oderCategory, int quantity, Double totalPrice, String oderDescription, String status){
        OderId=oderId;
        CustomerId=customerId;
        OderDate=oderDate;
        OderCategory=oderCategory;
        Quantity=quantity;
        TotalPrice=totalPrice;
        OderDescription=oderDescription;
        Status=status;
    }

    public oder(String oderId) {
        OderId=oderId;
    }

    public oder(String oderId, String customerId) {
        OderId=oderId;
        CustomerId=customerId;
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
