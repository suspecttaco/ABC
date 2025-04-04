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
    
    //Config de etiquetas
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

    //config de textbox
    private void setTexts() {
        textNom.setBounds(200, 100, 250, 37);
        textNom.setFont(new java.awt.Font("Segoe UI", 0, 24));
        textNom.setToolTipText("En este campo ingrese el nombre del producto a añadir/actualizar/consultar/eliminar.");
        panel.add(textNom);

        textCant.setBounds(200, 150, 250, 37);
        textCant.setFont(new java.awt.Font("Segoe UI", 0, 24));
        textCant.setToolTipText("En este campo ingrese la cantidad del producto a añadir/actualizar.");
        panel.add(textCant);

        //Esto es un TextArea - se agregaron los scrolls verticales y horizontales
        //para el scroll el text area ya no se agrea solo el scroll se añade al JPanel
        textLog.setFont(new java.awt.Font("Segoe UI", 0, 18));
        textLog.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(textLog);
        scrollBar.setBounds(520, 100, 350, 250);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollBar);
    }

    //config de botones
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

    //eventos de los botones
    public void actionPerformed(ActionEvent x) {
        
        if (x.getSource() == bntAdd) { //boton añadir
            try {
                String query = "insert into producto (nombre,cantidad) values ('" + textNom.getText() + "','" + textCant.getText() + "')";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e.toString());
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnDel) { //boton eliminar
            try {
                String query = "delete from producto where nombre = '" + textNom.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e.toString());
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnUpdt) { //boton actualizar
            try {
                String query = "update producto set cantidad = '" + textCant.getText() + "' where nombre = '" + textNom.getText() + "'";
                Statement st = bdConnection.createStatement();
                int rowsAffected = st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,rowsAffected + " rows effected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: " + e.toString());
                throw new RuntimeException(e);
            }
        } else if (x.getSource() == btnQry) { //boton consultar
            try {
                //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades, igual las columnas.
                String query = "select * from producto where nombre = '" + textNom.getText() + "'";
                Statement st = bdConnection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                textLog.setText("");

                while (resultSet.next()) {
                    textLog.append("id: " + resultSet.getString("id_producto") +" nombre: " + resultSet.getString("nombre") + " cantidad: " + resultSet.getString("cantidad")+ "\n");
                }

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
