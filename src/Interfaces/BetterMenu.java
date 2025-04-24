package Interfaces;

import Database.DBFunc;

import javax.swing.*;
import java.sql.*;

public class BetterMenu {
    JFrame frame;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField textPNombre;
    private JTextField textPCantidad;
    private JButton consultarPButton;
    private JButton añadirPButton;
    private JButton eliminarPButton;
    private JButton actualizarPButton;
    private JTextArea textPLog;
    private JTextField textCNombre;
    private JTextField textCAp;
    private JTextField textCTel;
    private JTextField textCEmail;
    private JTextArea textCLog;
    private JButton añadirCButton;
    private JButton consultarCButton;
    private JButton eliminarCButton;
    private JButton actualizarCButton;
    private JTextField textAMarca;
    private JTextField textAModelo;
    private JTextField textAYear;
    private JTextArea textALog;
    private JButton añadirAButton;
    private JButton consultaArButton;
    private JButton eliminarAButton;
    private JButton actualizarAButton;

    ABCProductos productos;
    ABCContactos contactos;
    ABCCarros carros;

    public BetterMenu() {
        this.frame = new JFrame("Practica - Conexion con bases de datos");
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

        Connection mysqlConn = DBFunc.ConectarBD("bd_inventario", "root", "Salsa1508");

        this.productos = new ABCProductos();
        this.contactos = new ABCContactos();
        this.carros = new ABCCarros();

        this.productos.setBdConnection(mysqlConn);
        this.contactos.setBdConnection(mysqlConn);
        this.carros.setBdConnection(mysqlConn);
    }

    public void startUi(){
        frame.setVisible(true);

    }
}
