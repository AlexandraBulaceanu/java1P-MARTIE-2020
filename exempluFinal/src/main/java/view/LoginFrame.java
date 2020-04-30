package view;

import model.User;
import service.MainService;

import javax.swing.*;
import java.util.Optional;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField textField1;
    private JLabel labelUsername;
    private JPasswordField passwordField1;
    private JLabel passLabel;

    public LoginFrame(){
        add(panel);
        loginButton.addActionListener(ev->login());
        registerButton.addActionListener(ev->register());
        setLocationRelativeTo(null);
        setSize(500,500);
        setVisible(true);
    }
    public void login(){
        String username = textField1.getText();
        String parola = new String(passwordField1.getPassword());
        Optional<User> user = MainService.getInstance().login(username,parola);
        if(user.isPresent()){
            new MainFrame();
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"User sau parola gresite!");
        }
    }
    public void register(){
        String username = textField1.getText();
        String parola = new String(passwordField1.getPassword());
        User user = new User(username,parola);
        if(MainService.getInstance().inregistrare(user)){
            JOptionPane.showMessageDialog(null,"Userul a fost inregidtrat!");
        }else{
            JOptionPane.showMessageDialog(null,"Userul nu a fost inregistrat!");
        }
    }

}
