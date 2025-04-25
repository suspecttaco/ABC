package Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ABCContacts implements ActionListener {
    Connection bdConnection;
    private JPanel panel;
    private JTextField textName;
    private JTextField textLastName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JButton addButton;
    private JButton queryButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea textLog;

    public ABCContacts() {
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);
        queryButton.addActionListener(this);
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
                String query = String.format("insert into contactos (nombre,apellidos,num,email) values ('%s','%s','%s','%s')",textName.getText(),textLastName.getText(),textPhone.getText(),textEmail.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == deleteButton) {
            try {
                String query = String.format("delete from contactos where nombre = '%s' and apellidos = '%s'",textName.getText(),textLastName.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == queryButton) {
            try {
                String query = String.format("select * from contactos where nombre = '%s' and apellidos = '%s'",textName.getText(),textLastName.getText());
                Statement st = bdConnection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                textLog.setText("");

                while (resultSet.next()) {
                    textLog.append("id: " + resultSet.getString("id_contacto") +" nombre: " + resultSet.getString("nombre") + " apellidos: " + resultSet.getString("apellidos")+" celular: " + resultSet.getString("num")+" E-mail: " + resultSet.getString("email")+ "\n");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == updateButton) {
            try {
                String query = String.format("update contactos set num = '%s', email = '"+ textEmail.getText() +"' where nombre = '%s' and apellidos = '%s'",textPhone.getText(),textName.getText(),textLastName.getText());
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        }
    }
}
