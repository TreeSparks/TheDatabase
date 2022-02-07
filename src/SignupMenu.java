import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class SignupMenu implements ActionListener {

    JFrame frame = new JFrame();
    JPanel panel;
    JLabel label;
    JLabel user;
    JLabel pass;
    JTextField userText;
    JPasswordField passText;
    JLabel error;
    JButton enter;

    public void run() {
        panel = new JPanel();
        frame.setTitle("Login Menu");
        frame.setSize(300,250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);

        label = new JLabel("Sign-Up Menu");
        label.setFont(new Font("Ink Free", Font.BOLD, 20));
        label.setBounds(75,JLabel.CENTER,200,25);
        panel.add(label);

        user = new JLabel("Username: ");
        user.setFont(new Font("Ink Free",Font.BOLD,15));
        user.setBounds(40,50,100,25);
        panel.add(user);

        userText = new JTextField();
        userText.setBounds(130,50,100,25);
        panel.add(userText);

        pass = new JLabel("Password: ");
        pass.setFont(new Font("Ink Free", Font.BOLD, 15));
        pass.setBounds(40,100,100,25);
        panel.add(pass);

        passText = new JPasswordField();
        passText.setBounds(130,100,100,25);
        panel.add(passText);

        error = new JLabel("");
        error.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        error.setBounds(20,130,200,25);
        panel.add(error);

        enter = new JButton();
        enter.setText("Enter");
        enter.setBounds(190,160,80,35);
        enter.addActionListener(this);
        panel.add(enter);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            String username = userText.getText();
            String password = passText.getText();
            FileManager manager = new FileManager();
            if (username.length()<7 || username.length()>15) {
                error.setText("Username must be 7-15 characters");
                TimerTask usernameLengthTask = new TimerTask() {
                    @Override
                    public void run() {
                        error.setText("");
                        userText.setText("");
                        passText.setText("");
                    }
                };
                new Timer().schedule(usernameLengthTask, 3000);
            }
            else if (password.length()<10 || password.length()>20) {
                error.setText("Password must be 10-20 characters");
                TimerTask passwordLengthTask = new TimerTask() {
                    @Override
                    public void run() {
                        error.setText("");
                        userText.setText("");
                        passText.setText("");
                    }
                };
                new Timer().schedule(passwordLengthTask, 3000);
            }
            else if (!password.contains("?") && !password.contains("@") && !password.contains("#") && !password.contains("$") && !password.contains("&")) {
                error.setText("Pass must include one of: ? @ # $ &");
                TimerTask passwordInvalidTask = new TimerTask() {
                    @Override
                    public void run() {
                        error.setText("");
                        userText.setText("");
                        passText.setText("");
                    }
                };
                new Timer().schedule(passwordInvalidTask, 3000);
            }
            else if (manager.isAvailable(username)) {
                manager.streamOut(username, password);
                error.setText("Information Added!");
                TimerTask disposeFrameTask = new TimerTask() {
                    @Override
                    public void run() {
                        frame.dispose();
                        System.exit(0);
                    }
                };
                new Timer().schedule(disposeFrameTask, 3000);
            }
            else {
                error.setText("Username Taken!");
                TimerTask userTakenTask = new TimerTask() {
                    @Override
                    public void run() {
                        error.setText("");
                        userText.setText("");
                        passText.setText("");
                    }
                };
                new Timer().schedule(userTakenTask, 3000);
            }
        }
    }

}
