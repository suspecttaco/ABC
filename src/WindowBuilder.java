import javax.swing.*;

public class WindowBuilder {
  public static void startUi() {

    JFrame frame = new JFrame("ABC de productos");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(939, 506);
    JPanel panel = new JPanel();
    panel.setLayout(null);
    
    JLabel labelNom = new JLabel("Nombre:");
    labelNom.setBounds(100, 120, 120, 30);
    panel.add(labelNom);

    JLabel labelCant = new JLabel("Cantidad:");
    labelCant.setBounds(100, 180, 120, 30);
    panel.add(labelCant);

    JTextField textNom = new JTextField("");
    textNom.setBounds(250, 120, 200, 37);
    panel.add(textNom);

    JTextField textCant = new JTextField("");
    textCant.setBounds(250, 180, 200, 37);
    panel.add(textCant);

    JTextArea textLog = new JTextArea("");
    textLog.setBounds(520, 100, 350, 250);
    panel.add(textLog);

    JButton bntAdd = new JButton("Añadir");
    bntAdd.setBounds(100, 250, 150, 40);
    panel.add(bntAdd);

    JButton btnQry = new JButton("Consultar");
    btnQry.setBounds(280, 250, 150, 40);
    panel.add(btnQry);

    JButton btnDel = new JButton("Borrar");
    btnDel.setBounds(100, 300, 150, 40);
    panel.add(btnDel);

    JButton btnUpdt = new JButton("Actualizar");
    btnUpdt.setBounds(280, 300, 150, 40);
    panel.add(btnUpdt);

    frame.add(panel);
    frame.setVisible(true);

  }
}