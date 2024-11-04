package servicelayer;

import dblayer.DatabaseConnection;
import models.SalesReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesReportDAO {

    public static List<SalesReport> getSalesReportData() throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<SalesReport> salesReportList = new ArrayList<>();

        String query_getSalesData =
                "SELECT o.orderId, o.quantity, o.totalPrice, o.status, o.category, o.socialmediaCategory, " +
                        "c.firstName as customerFirstName, c.lastName as customerLastName, s.firstName as supplierFirstName, s.lastName as supplierLastName " +
                "FROM orders o " +
                "LEFT JOIN customer c ON o.customerId = c.id " +
                "LEFT JOIN supplier s ON o.supplierId = s.id";

        try {
            connection = Objects.requireNonNull(DatabaseConnection.getSingleInstance()).getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query_getSalesData);

            while (resultSet.next()) {
                SalesReport report = new SalesReport();
                report.setOrderId(resultSet.getString("orderId"));
                report.setAdCategory(resultSet.getString("category"));
                report.setSocialMediaCategory(resultSet.getString("socialmediaCategory"));
                report.setStatus(resultSet.getString("status"));
                report.setQuantity(resultSet.getInt("quantity"));
                report.setCost(resultSet.getDouble("totalPrice"));
                report.setCustomerName(resultSet.getString("customerFirstName") + " " + resultSet.getString("customerLastName"));
                report.setSupplierName(resultSet.getString("supplierFirstName") + " " + resultSet.getString("supplierLastName"));
                salesReportList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return salesReportList;
    }
}
