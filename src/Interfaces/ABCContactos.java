package Interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ABCContactos extends JFrame implements ActionListener {
    Connection bdConnection;
    JFrame frame = new JFrame();
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

    public static void startUi(){

    }

    private static void setLabels(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
