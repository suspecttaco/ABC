package Interfaces;

import javax.swing.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ABCContactos implements ActionListener {
    Connection bdConnection;
    JPanel panel = new JPanel();
    JLabel labelNom = new JLabel();
    JLabel labelAp = new JLabel();
    JLabel labelNum = new JLabel();
    JLabel labelCorreo = new JLabel();
    JLabel labelLog = new JLabel();
    JTextField textNom = new JTextField();
    JTextField textAp = new JTextField();
    JTextField textNum = new JTextField();
    JTextField textCorreo = new JTextField();
    JTextArea textLog = new JTextArea();
    JButton btnAdd = new JButton();
    JButton btnQry = new JButton();
    JButton btnDel = new JButton();
    JButton btnUpdt = new JButton();

    public  void startUi(){
        panel.setLayout(null);

        setLabels();
        setText();
        setButtons();
    }

    public JPanel getPanel(){
        return panel;
    }

    private void setLabels(){
        labelNom.setText("Nombre:");
        labelNom.setBounds(50,50,120,30);
        labelNom.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelNom);

        labelAp.setText("Apellido(s)");
        labelAp.setBounds(50,100,120,30);
        labelAp.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelAp);

        labelNum.setText("Celular:");
        labelNum.setBounds(50,150,120,30);
        labelNum.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelNum);

        labelCorreo.setText("E-mail:");
        labelCorreo.setBounds(50,200,120,30);
        labelCorreo.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelCorreo);

        labelLog.setText("Salida");
        labelLog.setBounds(520,50,50,30);
        panel.add(labelLog);
    }

    public void setText(){
        textNom.setBounds(200,50,250,37);
        textNom.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textNom);

        textAp.setBounds(200,100,250,37);
        textAp.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textAp);

        textNum.setBounds(200,150,250,37);
        textNum.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textNum);

        textCorreo.setBounds(200,200,250,37);
        textCorreo.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textCorreo);

        textLog.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textLog.setEditable(false);
        textLog.setFocusable(false);
        JScrollPane scrollBar = new JScrollPane(textLog);
        scrollBar.setBounds(520, 80, 350, 250);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollBar);
    }

    private void setButtons() {
        btnAdd.setText("Añadir");
        btnAdd.setBounds(50, 250, 200, 40);
        btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnAdd.addActionListener(this);
        panel.add(btnAdd);

        btnQry.setText("Consultar");
        btnQry.setBounds(280, 250, 200, 40);
        btnQry.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnQry.addActionListener(this);
        panel.add(btnQry);

        btnDel.setText("Eliminar");
        btnDel.setBounds(50, 300, 200, 40);
        btnDel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnDel.addActionListener(this);
        panel.add(btnDel);

        btnUpdt.setText("Actualizar");
        btnUpdt.setBounds(280, 300, 200, 40);
        btnUpdt.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnUpdt.addActionListener(this);
        panel.add(btnUpdt);

    }

    public void clearAll(){
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setText("");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        if (x.getSource() == btnAdd){
            try {
                String query = "insert into contactos (nombre,apellidos,num,email) values ('" + textNom.getText() + "','" + textAp.getText() + "','" + textNum.getText() +"','" + textCorreo.getText() + "')";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnDel) {
            try {
                String query = "delete from contactos where nombre = '" + textNom.getText() + "' and apellidos = '" + textAp.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnQry) {
            try {
                String query = "select * from contactos where nombre = '" + textNom.getText() + "' and apellidos = '" + textAp.getText() + "'";
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
        } else if (x.getSource() == btnUpdt) {
            try {
                String query = "update contactos set num = '" + textNum.getText() + "', email = '"+ textCorreo.getText() +"' where nombre = '" + textNom.getText() + "' and apellidos = '" + textAp.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public void setBdConnection(Connection bdConnection){
        this.bdConnection = bdConnection;
    }
}
