package models;

public class SalesReport {

    private String orderId;
    private String customerName;
    private String supplierName;
    private Integer quantity;
    private Double cost;
    private String socialMediaCategory;
    private String AdCategory;
    private StatusType status;

    public SalesReport(String orderId, String customerName, String supplierName, Integer quantity, Double cost, String socialMediaCategory, String adCategory, StatusType status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.cost = cost;
        this.socialMediaCategory = socialMediaCategory;
        AdCategory = adCategory;
        this.status = status;
    }

    public SalesReport() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSocialMediaCategory() {
        return socialMediaCategory;
    }

    public void setSocialMediaCategory(String socialMediaCategory) {
        this.socialMediaCategory = socialMediaCategory;
    }

    public String getAdCategory() {
        return AdCategory;
    }

    public void setAdCategory(String adCategory) {
        AdCategory = adCategory;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase(String.valueOf(StatusType.PENDING))){
            this.status = StatusType.PENDING;
        }
        if (status.equalsIgnoreCase(String.valueOf(StatusType.APPROVED))){
            this.status = StatusType.APPROVED;
        }
        if (status.equalsIgnoreCase(String.valueOf(StatusType.COMPLETED))){
            this.status = StatusType.COMPLETED;
        }
        if (status.equalsIgnoreCase(String.valueOf(StatusType.REJECT))){
            this.status = StatusType.REJECT;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SalesReport{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", supplierName='").append(supplierName).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", cost=").append(cost);
        sb.append(", socialMediaCategory='").append(socialMediaCategory).append('\'');
        sb.append(", AdCategory='").append(AdCategory).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
