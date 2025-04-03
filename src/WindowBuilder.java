import javax.swing.*;
import java.awt.Color;
import helper_classes.*;

public class WindowBuilder {
  public static void main(String[] args) {

     JFrame frame = new JFrame("My Awesome Window");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(939, 506);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#eeeeee"));

     JLabel labelNom = new JLabel("Nombre:");
     labelNom.setBounds(100, 120, 120, 30);
     labelNom.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 25));
     labelNom.setForeground(Color.decode("#1b1b1b"));
     panel.add(labelNom);

     JLabel labelCant = new JLabel("Cantidad:");
     labelCant.setBounds(100, 180, 120, 30);
     labelCant.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 25));
     labelCant.setForeground(Color.decode("#1b1b1b"));
     panel.add(labelCant);

     JTextField textNom = new JTextField("");
     textNom.setBounds(250, 120, 200, 37);
     textNom.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 25));
     textNom.setBackground(Color.decode("#ffffff"));
     textNom.setForeground(Color.decode("#737674"));
     textNom.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));
     OnFocusEventHelper.setOnFocusText(textNom, "Your Input!", Color.decode("#1b1b1b"),   Color.decode("#737674"));
     panel.add(textNom);

     JTextField textCant = new JTextField("");
     textCant.setBounds(250, 180, 200, 37);
     textCant.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 25));
     textCant.setBackground(Color.decode("#ffffff"));
     textCant.setForeground(Color.decode("#737674"));
     textCant.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));
     OnFocusEventHelper.setOnFocusText(textCant, "Your Input!", Color.decode("#1b1b1b"),   Color.decode("#737674"));
     panel.add(textCant);

     JTextArea textLog = new JTextArea("");
     textLog.setBounds(500, 100, 350, 250);
     textLog.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 18));
     textLog.setBackground(Color.decode("#ffffff"));
     textLog.setForeground(Color.decode("#737674"));
     textLog.setBorder(new RoundedBorder(2, Color.decode("#626262"), 1));
     OnFocusEventHelper.setOnFocusText(textLog, "Your long Input!", Color.decode("#1b1b1b"),   Color.decode("#737674"));
     panel.add(textLog);

     JButton bntAdd = new JButton("Añadir");
     bntAdd.setBounds(120, 250, 150, 40);
     bntAdd.setBackground(Color.decode("#ffffff"));
     bntAdd.setForeground(Color.decode("#1b1b1b"));
     bntAdd.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
     bntAdd.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
     bntAdd.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(bntAdd, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
     panel.add(bntAdd);

     JButton btnQry = new JButton("Consultar");
     btnQry.setBounds(280, 250, 150, 40);
     btnQry.setBackground(Color.decode("#ffffff"));
     btnQry.setForeground(Color.decode("#1b1b1b"));
     btnQry.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
     btnQry.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
     btnQry.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(btnQry, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
     panel.add(btnQry);

     JButton btnDel = new JButton("Borrar");
     btnDel.setBounds(120, 300, 150, 40);
     btnDel.setBackground(Color.decode("#ffffff"));
     btnDel.setForeground(Color.decode("#1b1b1b"));
     btnDel.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
     btnDel.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
     btnDel.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(btnDel, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
     panel.add(btnDel);

     JButton btnUpdt = new JButton("Actualizar");
     btnUpdt.setBounds(280, 300, 150, 40);
     btnUpdt.setBackground(Color.decode("#ffffff"));
     btnUpdt.setForeground(Color.decode("#1b1b1b"));
     btnUpdt.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
     btnUpdt.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
     btnUpdt.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(btnUpdt, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
     panel.add(btnUpdt);

     frame.add(panel);
     frame.setVisible(true);

  }
}