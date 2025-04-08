package Interfaces;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;

public class ABCProductos extends JFrame implements ActionListener {
    Connection bdConnection;
    JPanel panel = new JPanel();
    JLabel labelNom = new JLabel();
    JLabel labelCant = new JLabel();
    JTextField textNom = new JTextField();
    JTextField textCant = new JTextField();
    JLabel labelLog = new JLabel();
    JTextArea textLog = new JTextArea();
    JButton btnAdd = new JButton();
    JButton btnQry = new JButton();
    JButton btnDel = new JButton();
    JButton btnUpdt = new JButton();

    public void startUi() {
        panel.setLayout(null);

        setLabels();
        setTexts();
        setButtons();
    }

    public JPanel getPanel(){
        return panel;
    }

    //Config de etiquetas
    private void setLabels() {
        labelNom.setText("Nombre:");
        labelNom.setBounds(50, 100, 120, 30);
        labelNom.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        panel.add(labelNom);

        labelCant.setText("Cantidad:");
        labelCant.setBounds(50, 150, 120, 30);
        labelCant.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        panel.add(labelCant);

        labelLog.setText("Salida");
        labelLog.setBounds(520, 50, 50, 50);
        panel.add(labelLog);
    }

    //config de textbox
    private void setTexts() {
        textNom.setBounds(200, 100, 250, 37);
        textNom.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        textNom.setToolTipText("En este campo ingrese el nombre del producto a añadir/actualizar/consultar/eliminar.");
        panel.add(textNom);

        textCant.setBounds(200, 150, 250, 37);
        textCant.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        textCant.setToolTipText("En este campo ingrese la cantidad del producto a añadir/actualizar.");
        panel.add(textCant);

        //Esto es un TextArea - se agregaron los scrolls verticales y horizontales
        //para el scroll el text area ya no se agrea solo el scroll se añade al JPanel
        textLog.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textLog.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(textLog);
        scrollBar.setBounds(520, 100, 350, 250);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollBar);
    }

    //config de botones
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

    //eventos de los botones
    @Override
    public void actionPerformed(ActionEvent x) {
        
        if (x.getSource() == btnAdd) { //boton añadir
            try {
                String query = "insert into producto (nombre,cantidad) values ('" + textNom.getText() + "','" + textCant.getText() + "')";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnDel) { //boton eliminar
            try {
                String query = "delete from producto where nombre = '" + textNom.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnUpdt) { //boton actualizar
            try {
                String query = "update producto set cantidad = '" + textCant.getText() + "' where nombre = '" + textNom.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e);
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnQry) { //boton consultar
            try {
                //"producto" es mi tabla dentro de mi bd, cambienla según sus necesidades, igual las columnas.
                String query = "select * from producto where nombre = '" + textNom.getText() + "'";
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

}
