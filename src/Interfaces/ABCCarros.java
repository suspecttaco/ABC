package Interfaces;

import javax.swing.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ABCCarros implements ActionListener {
    Connection bdConnection;
    JPanel panel = new JPanel();
    JLabel labelModelo = new JLabel();
    JLabel labelMarca = new JLabel();
    JLabel labelYear = new JLabel();
    JLabel labelLog = new JLabel();
    JTextField textModelo = new JTextField();
    JTextField textMarca = new JTextField();
    JTextField textYear = new JTextField();
    JTextArea textLog = new JTextArea();
    JButton btnAdd = new JButton();
    JButton btnQry = new JButton();
    JButton btnDel = new JButton();
    JButton btnUpdt = new JButton();

    public void startUi(){
        panel.setLayout(null);

        setLabels();
        setButtons();
        setTexts();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setLabels(){
        labelMarca.setText("Marca:");
        labelMarca.setBounds(50,50,120,30);
        labelMarca.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelMarca);

        labelModelo.setText("Modelo:");
        labelModelo.setBounds(50,100,120,30);
        labelModelo.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelModelo);

        labelYear.setText("Año:");
        labelYear.setBounds(50,150,120,30);
        labelYear.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(labelYear);

        labelLog.setText("Salida");
        labelLog.setBounds(520,50,50,30);
        panel.add(labelLog);
    }

    public void setTexts(){
        textMarca.setBounds(200,50,250,37);
        textMarca.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textMarca);

        textModelo.setBounds(200,100,250,37);
        textModelo.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textModelo);

        textYear.setBounds(200,150,250,37);
        textYear.setFont(new Font("Segoe UI", Font.PLAIN,24));
        panel.add(textYear);

        textLog.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textLog.setEditable(false);
        textLog.setFocusable(false);
        JScrollPane scrollBar = new JScrollPane(textLog);
        scrollBar.setBounds(520, 80, 350, 250);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollBar);
    }

    public void setButtons(){
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
                String query = "insert into carros (marca,modelo,año) values ('" + textMarca.getText() + "','" + textModelo.getText() + "','" + textYear.getText() + "')";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnDel) {
            try {
                String query = "delete from carros where modelo = '" + textModelo.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnQry) {
            try {
                String query = "select * from carros where marca = '" + textMarca.getText() + "' or modelo = '" + textModelo.getText() + "'";
                Statement st = bdConnection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                textLog.setText("");

                while (resultSet.next()) {
                    textLog.append("id: " + resultSet.getString("id_carro") +" marca: " + resultSet.getString("marca") + " modelo: " + resultSet.getString("modelo")+" año: " + resultSet.getString("año") + "\n");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnUpdt) {
            try {
                String query = "update carros set marca = '" + textMarca.getText() + "', año = '"+ textYear.getText() +"' where modelo = '" + textModelo.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public void setBdConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }
}