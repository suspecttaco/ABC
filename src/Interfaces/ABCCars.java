package Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ABCCars implements ActionListener {
    private Connection bdConnection;
    private JPanel panel;
    private JTextField textBrand;
    private JTextField textModel;
    private JTextField textYear;
    private JButton addButton;
    private JButton queryButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea textLog;

    public ABCCars() {
        this.addButton.addActionListener(this);
        this.deleteButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.queryButton.addActionListener(this);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setBdConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        if (x.getSource() == addButton){
            try {
                String query = String.format("insert into carros (marca,modelo,año) values ('%s','%s','%s')",textBrand.getText(),textModel.getText(),textYear.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == deleteButton) {
            try {
                String query = String.format("delete from carros where modelo = '%s' and marca = '%s'",textModel.getText(),textBrand.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == queryButton) {
            try {
                String query = String.format("select * from carros where marca = '%s' and modelo = '%s'",textBrand.getText(),textModel.getText());
                Statement st = bdConnection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                textLog.setText("");

                while (resultSet.next()) {
                    textLog.append(String.format("id: %s%n marca: %s%n modelo: %s%n año: %s%n",resultSet.getString("id_carro"),resultSet.getString("marca"),resultSet.getString("modelo"),resultSet.getString("año")));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == updateButton) {
            try {
                String query = String.format("update carros set año = '%s' where modelo = '%s' and marca = '%s'",textYear.getText(),textModel.getText(),textBrand.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows affected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        }
    }
}
