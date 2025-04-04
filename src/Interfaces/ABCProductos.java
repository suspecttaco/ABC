package Interfaces;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ABCProductos extends JFrame implements ActionListener {
    private Connection bdConnection;
    JFrame frame = new JFrame("ABC de productos");
    JPanel panel = new JPanel();
    JLabel labelNom = new JLabel("Nombre:");
    JLabel labelCant = new JLabel("Cantidad:");
    JTextField textNom = new JTextField("");
    JTextField textCant = new JTextField("");
    JLabel labelLog = new JLabel("Salida:");
    JTextArea textLog = new JTextArea("");
    JButton bntAdd = new JButton("Añadir");
    JButton btnQry = new JButton("Consultar");
    JButton btnDel = new JButton("Borrar");
    JButton btnUpdt = new JButton("Actualizar");

    public void startUi() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 500);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);

        setLabels();
        setTexts();
        setButtons();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void setLabels() {
        labelNom.setBounds(50, 100, 120, 30);
        labelNom.setFont(new java.awt.Font("Segoe UI", 0, 24));
        panel.add(labelNom);

        labelCant.setBounds(50, 150, 120, 30);
        labelCant.setFont(new java.awt.Font("Segoe UI", 0, 24));
        panel.add(labelCant);

        labelLog.setBounds(520, 50, 50, 50);
        panel.add(labelLog);
    }

    private void setTexts() {
        textNom.setBounds(200, 100, 250, 37);
        textNom.setFont(new java.awt.Font("Segoe UI", 0, 24));
        textNom.setToolTipText("En este campo ingrese el nombre del producto.");
        panel.add(textNom);

        textCant.setBounds(200, 150, 250, 37);
        textCant.setFont(new java.awt.Font("Segoe UI", 0, 24));
        textCant.setToolTipText("En este campo ingrese la cantidad del producto.");
        panel.add(textCant);

        textLog.setBounds(520, 100, 350, 250);
        textLog.setFont(new java.awt.Font("Segoe UI", 0, 24));
        panel.add(textLog);
    }

    private void setButtons() {
        bntAdd.setBounds(50, 250, 200, 40);
        bntAdd.setFont(new java.awt.Font("Segoe UI", 0, 24));
        bntAdd.addActionListener(this);
        panel.add(bntAdd);

        btnQry.setBounds(280, 250, 200, 40);
        btnQry.setFont(new java.awt.Font("Segoe UI", 0, 24));
        btnQry.addActionListener(this);
        panel.add(btnQry);

        btnDel.setBounds(50, 300, 200, 40);
        btnDel.setFont(new java.awt.Font("Segoe UI", 0, 24));
        btnDel.addActionListener(this);
        panel.add(btnDel);

        btnUpdt.setBounds(280, 300, 200, 40);
        btnUpdt.setFont(new java.awt.Font("Segoe UI", 0, 24));
        btnUpdt.addActionListener(this);
        panel.add(btnUpdt);
    }

    public void actionPerformed(ActionEvent x) {

        if (x.getSource() == bntAdd) {
            try {
                // "producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades
                String query = "insert into producto (nombre,cantidad) values ('" + textNom.getText() + "','" + textCant.getText() + "')";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e.toString());
                throw new RuntimeException(e);
            }
        }

    }

    public void setBdConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }

}
