package views.report;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.Model_ReportCard;
import models.SalesReport;
import models.StatusType;
import servicelayer.SalesReportDAO;
import views.common.swing.PanelBorder;
import views.common.swing.ScrollBar;
import views.common.swing.Table;

import java.awt.*;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class SalesReportDashboard extends JPanel {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static Font font_title = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font font_red = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font font_subtitle = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font font_smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public SalesReportDashboard() {
        initComponents();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18));
        jLabel1.setForeground(new Color(127, 127, 127));
        jLabel1.setText("Monthly Sales Record");

        scrollpane1.setVerticalScrollBar(new ScrollBar());
        scrollpane1.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollpane1.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scrollpane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        try {
            List<SalesReport> data = SalesReportDAO.getSalesReportData();
            setReportTableData(data);

            reportCard1.setData(new Model_ReportCard("Total Completion", getTotalCompletedSales(data), ""));
            reportCard2.setData(new Model_ReportCard("Total Profit", getTotalSalesProfit(data), ""));
            reportCard3.setData(new Model_ReportCard("Visitors", getTotalVisitors(data), ""));

        } catch (SQLException e) {
            System.out.println("failed to load sales report data..");
            e.printStackTrace();
        }
    }

    private String getTotalVisitors(List<SalesReport> data) {
        return String.valueOf(data.size());
    }

    private String getTotalSalesProfit(List<SalesReport> data) {
        return String.valueOf(data.stream().mapToDouble(SalesReport::getCost).sum());
    }

    private String getTotalCompletedSales(List<SalesReport> data) {
        return String.valueOf(data.stream().filter(order -> order.getStatus().equals(StatusType.COMPLETED)).count());
    }

    public void setReportTableData(List<SalesReport> reportTableData) throws SQLException {
        System.out.println(">>>>> reports: " + reportTableData);

        for (SalesReport report : reportTableData)
        {
            table.addRow(new Object[]{
                    report.getOrderId(),
                    report.getCustomerName(),
                    report.getSupplierName(),
                    report.getQuantity(),
                    report.getCost(),
                    report.getSocialMediaCategory(),
                    report.getAdCategory(),
                    report.getStatus()});
        }
    }

    private void exportToPDF(){
        JFileChooser fileChooser = new JFileChooser();
        int userChoice = fileChooser.showSaveDialog(this);

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            try {
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(fileChooser.getSelectedFile() + ".pdf"));
                document.open();

                Paragraph p = new Paragraph();

                p.add(new Paragraph(" "));
                p.add(new Paragraph("MyShare", font_title));
                p.add(new Paragraph(" "));
                p.add(new Paragraph("Digital Advertising Company"));
                p.add(new Paragraph("Report generated at: " + new Date(), font_smallBold));
                p.add(new Paragraph(" "));
                p.add(new Paragraph(" "));
                p.add(new Paragraph("Monthly Sales Report", font_subtitle));
                p.add(new Paragraph(" "));
                p.add(new Paragraph(" "));

                document.add(p);
                document.addCreationDate();

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(table.getColumnName(i));
                }
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        pdfTable.addCell(table.getValueAt(row, col).toString());
                    }
                }
                document.add(pdfTable);

                document.close();

                JOptionPane.showMessageDialog(this, "Sales data exported successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting data to PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        reportCard1 = new ReportCard();
        reportCard2 = new ReportCard();
        reportCard3 = new ReportCard();
        panelBorder1 = new PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        scrollpane1 = new JScrollPane();
        table = new Table();
        jButton1 = new javax.swing.JButton();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        reportCard1.setColor1(new Color(142, 142, 250));
        reportCard1.setColor2(new Color(123, 123, 245));
        reportCard1.setName(""); // NOI18N
        panel.add(reportCard1);

        reportCard2.setColor1(new Color(186, 123, 247));
        reportCard2.setColor2(new Color(167, 94, 236));
        panel.add(reportCard2);

        reportCard3.setColor1(new Color(241, 208, 62));
        reportCard3.setColor2(new Color(211, 184, 61));
        panel.add(reportCard3);

        panelBorder1.setBackground(new Color(255, 255, 255));

        scrollpane1.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Customer", "Supplier", "Qty", "Cost", " Aired on", "Category", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollpane1.setViewportView(table);

        jButton1.setText("Export report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(scrollpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton1)))
                                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollpane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        exportToPDF();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private PanelBorder panelBorder1;
    private ReportCard reportCard1;
    private ReportCard reportCard2;
    private ReportCard reportCard3;
    private JScrollPane scrollpane1;
    private Table table;
    // End of variables declaration//GEN-END:variables
}
