import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JButton login;
    JLabel or;
    JButton signup;
    String choice = " ";

    public void run() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setTitle("Menu");
        frame.setSize(300,250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);

        label = new JLabel("THE DATABASE");
        label.setFont(new Font("Ink Free", Font.BOLD, 20));
        label.setBounds(60,JLabel.CENTER,200,25);
        panel.add(label);

        //Add image to right of 'THE DATABASE'

        login = new JButton();
        login.setText("Login");
        login.setBounds(100, 50, 80, 35);
        login.addActionListener(this);
        panel.add(login);

        or = new JLabel("OR");
        or.setFont(new Font("Ink Free", Font.BOLD, 20));
        or.setBounds(120, 90, 75, 35);
        panel.add(or);

        signup = new JButton();
        signup.setText("Sign-Up");
        signup.setBounds(100,130,80,35);
        signup.addActionListener(this);
        panel.add(signup);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
            frame.dispose();
            LoginMenu menu = new LoginMenu();
            menu.run();
        }
        if(e.getSource() == signup) {
            frame.dispose();
            SignupMenu menu = new SignupMenu();
            menu.run();
        }
    }


}
