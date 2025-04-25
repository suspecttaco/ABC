package Interfaces;

import Database.DBFunc;

import javax.swing.*;
import java.sql.*;

public class BetterMenu {
    JFrame frame;
    private JPanel panel;
    private JTabbedPane tabsPane;

    ABCProducts products;
    ABCContacts contacts;
    ABCCars cars;

    public void startUi(){
        this.frame = new JFrame("Practica - Conexion con bases de datos");
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        Connection mysqlConn = DBFunc.ConectarBD("bd_inventario", "testSQL", "Salsa1508");

        this.products = new ABCProducts();
        this.contacts = new ABCContacts();
        this.cars = new ABCCars();

        this.products.setBdConnection(mysqlConn);
        this.contacts.setBdConnection(mysqlConn);
        this.cars.setBdConnection(mysqlConn);

        this.tabsPane.addTab("ABC de Productos", products.getPanel());
        this.tabsPane.addTab("ABC de Contactos", contacts.getPanel());
        this.tabsPane.addTab("ABC de Coches", cars.getPanel());
        frame.setVisible(true);

    }
}
