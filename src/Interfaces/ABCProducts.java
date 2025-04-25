package Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ABCProducts implements ActionListener {
    private JTextField textName;
    private JTextField textQty;
    private JButton addButton;
    private JButton queryButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea textLog;
    private JPanel mainPanel;
    private Connection bdConnection;

    public ABCProducts() {
        this.addButton.addActionListener(this);
        this.deleteButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.queryButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        if (x.getSource() == addButton) { //boton añadir
            try {
                String query = String.format("insert into producto (nombre,cantidad) values ('%s','%s')",textName.getText(),textQty.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == deleteButton) { //boton eliminar
            try {
                String query = String.format("delete from producto where nombre = '%s'",textName.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == updateButton) { //boton actualizar
            try {
                String query = String.format("update producto set cantidad = '%s' where nombre = '%s'",textQty.getText(),textName.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == queryButton) { //boton consultar
            try {
//                String query = "select * from producto where nombre = '" + textNom.getText() + "'";
                String query = String.format("select * from producto where nombre = '%s'",textName.getText());
                Statement st = bdConnection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                textLog.setText("");

                while (resultSet.next()) {
                    textLog.append("id: " + resultSet.getString("id_producto") +" nombre: " + resultSet.getString("nombre") + " cantidad: " + resultSet.getString("cantidad")+ "\n");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public void setBdConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
