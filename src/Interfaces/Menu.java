package Interfaces;

import Database.DBFunc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Menu extends JFrame implements ActionListener {
    Connection mysql = DBFunc.ConectarBD("bd_inventario", "root", "Salsa1508");
    JFrame menu = new JFrame();
    JPanel panel = new JPanel(new CardLayout());
    JButton btnAbc1 = new JButton();
    JButton btnAbc2 = new JButton();
    JButton btnAbc3 = new JButton();
    CardLayout cartasUi = (CardLayout) panel.getLayout();

    ABCProductos abcProductos;
    ABCContactos abcContactos;
    //ABCCarros abcCarros;

    public void startUi(){
        menu.setTitle("Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(950,500);
        menu.setLocationRelativeTo(null);

        setButtons();
        setCartas();

        menu.add(panel);
        menu.setVisible(true);
    }

    private void setCartas(){
        abcProductos = new ABCProductos();
        abcProductos.startUi();
        abcProductos.setBdConnection(mysql); //esto ignoralo es mi desmadre con mysql xd
        panel.add(abcProductos.getPanel(), "ABCProductos"); //este es el que importa

        abcContactos = new ABCContactos();
        abcContactos.startUi();
        abcContactos.setBdConnection(mysql);
        panel.add(abcContactos.getPanel(), "ABCContactos");

    }

    private void setButtons(){
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        btnAbc1.setText("ABC de Productos");
        btnAbc1.setSize(120,60);
        btnAbc1.addActionListener(this);
        btnAbc1.setFont(new Font("Segoe UI", Font.PLAIN,16));
        btnPanel.add(btnAbc1);

        btnAbc2.setText("ABC de Contactos");
        btnAbc2.setSize(120,60);
        btnAbc2.addActionListener(this);
        btnAbc2.setFont(new Font("Segoe UI", Font.PLAIN,16));
        btnPanel.add(btnAbc2);

        btnAbc3.setText("ABC de Carros");
        btnAbc3.setSize(120,60);
        btnAbc3.addActionListener(this);
        btnAbc3.setFont(new Font("Segoe UI", Font.PLAIN,16));
        btnPanel.add(btnAbc3);

        menu.add(btnPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAbc1){
            abcProductos.clearAll();
            cartasUi.show(panel, "ABCProductos");
        } else if (e.getSource() == btnAbc2) {
            abcContactos.clearAll();
            cartasUi.show(panel, "ABCContactos");
        } else if (e.getSource() == btnAbc3) {

        }
    }
}
