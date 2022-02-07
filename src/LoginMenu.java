import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class LoginMenu implements ActionListener {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label;
    JLabel user;
    JLabel pass;
    JTextField userText;
    JPasswordField passText;
    JButton enter;
    JLabel error;

    public void run() {
        frame.setTitle("Login Menu");
        frame.setSize(300,250);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(null);

        label = new JLabel("Login Menu");
        label.setFont(new Font("Ink Free", Font.BOLD, 20));
        label.setBounds(90,JLabel.CENTER,200,25);
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
        error.setFont(new Font("Ink Free", Font.BOLD, 12));
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
            FileManager manager = new FileManager();
            String username = userText.getText();
            String password = passText.getText();
            if (!manager.isAvailable(username)) {
                if (manager.isInfoInDatabase(username,password)) {
                    manager.streamIn(username, password);
                    error.setText("You are approved!");
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
                    error.setText("Invalid Information");
                    TimerTask invalidInformationTask = new TimerTask() {
                        @Override
                        public void run() {
                            error.setText("");
                            userText.setText("");
                            passText.setText("");
                        }
                    };
                    new Timer().schedule(invalidInformationTask, 3000);
                }
            }
            else {
                error.setText("Invalid Information");
                TimerTask invalidInformationTask = new TimerTask() {
                    @Override
                    public void run() {
                        error.setText("");
                        userText.setText("");
                        passText.setText("");
                    }
                };
                new Timer().schedule(invalidInformationTask, 3000);
            }



        }

    }



}
