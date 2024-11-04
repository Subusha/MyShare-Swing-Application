package views.report;

import models.Model_ReportCard;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.*;

public class ReportCard extends JPanel {

    private Color color1;
    private Color color2;

    private  Font titleFont = new Font("san-serif", Font.BOLD, 20);
    private  Font valueFont = new Font("san-serif", Font.BOLD, 40);

    public ReportCard() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;

        lbTitle.setFont(titleFont);
        lbValue.setFont(valueFont);
    }

    public void setData(Model_ReportCard data) {
        lbTitle.setText(data.getTitle());
        lbValue.setText(data.getValue());
        lbDescription.setText(data.getDescription());
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lbTitle = new JLabel();
        lbValue = new JLabel();
        lbDescription = new JLabel();

        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax.swing.border.TitledBorder.CENTER
                , javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialo\u0067", java.awt.Font
                .BOLD, 12), Color.red), getBorder()));
        addPropertyChangeListener(
                new java.beans.PropertyChangeListener() {
                    @Override
                    public void propertyChange(java.beans.PropertyChangeEvent e) {
                        if ("borde\u0072"
                                .equals(e.getPropertyName())) throw new RuntimeException();
                    }
                });

        lbTitle.setText("Title");

        lbValue.setText("Values");

        lbDescription.setText("Description");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(lbDescription)
                        .addComponent(lbValue)
                        .addComponent(lbTitle))
                    .addContainerGap(283, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addGap(18, 18, 18)
                    .addComponent(lbTitle)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbValue)
                    .addGap(18, 18, 18)
                    .addComponent(lbDescription)
                    .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel lbTitle;
    private JLabel lbValue;
    private JLabel lbDescription;
    // End of variables declaration//GEN-END:variables
}
