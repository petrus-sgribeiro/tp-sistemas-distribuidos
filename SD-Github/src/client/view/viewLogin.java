package client.view;

import common.ServerMessage;
import common.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class viewLogin extends JPanel {

    private ChatApp chat;

    private JLabel title;

    private JLabel label_login;
    private JTextField input_login;

    private JLabel label_password;
    private JPasswordField input_password;

    private JButton button_login;
    private JButton button_register;

    private JPanel panel1, panel2, panel3;
    
    private JLabel label_error_msg;

    public viewLogin(ChatApp chat) {
        super();
        this.chat = chat;
        initComponents();
        configComponents();
        insertActions();
    }

    private void initComponents() {
        label_login = new JLabel("Email", SwingConstants.CENTER);
        label_password = new JLabel("Senha", SwingConstants.CENTER);
        input_login = new JTextField();
        input_password = new JPasswordField();
        button_register = new JButton("Não possui conta? Crie uma!");
        button_login = new JButton("Entrar");
        title = new JLabel("LOGIN");
        label_error_msg = new JLabel();

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
    }

    private void configComponents() {
        /* CONFIGURAÇÃO DO LAYOUT DO GRID */
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        panel1.setBackground(new Color(0, 0, 0, 0));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 0.25;
        this.add(panel1, c);

        panel2.setBackground(new Color(0, 0, 0, 0));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.55;
        this.add(panel2, c);

        panel3.setBackground(new Color(0, 0, 0, 0));
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.2;
        this.add(panel3, c);

        this.setBackground(ChatApp.PRIMARY_DARK);

        /* CONFIGURAÇÃO DOS COMPONENTS */
        panel2.setLayout(null);
        title.setBounds(595, 10, 115, 61);
        title.setFont(ChatApp.FONT_IMPACT);
        title.setForeground(ChatApp.PRIMARY_GREEN);
        panel2.add(title);

        /* Configuração do formul�rio */
        label_login.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_login.setBounds(370, 100, 100, 40);
        label_login.setOpaque(true);
        label_login.setForeground(ChatApp.PRIMARY_DARK);
        label_login.setBackground(ChatApp.PRIMARY_GREEN);

        input_login.setBounds(470, 100, 450, 40);
        
        label_error_msg.setText("Testando");
        label_error_msg.setOpaque(true);
        label_error_msg.setFont(new Font("Arial",Font.ITALIC + Font.BOLD,13));
        label_error_msg.setBackground(ChatApp.PRIMARY_DARK);
        label_error_msg.setForeground(ChatApp.WARNING_COLOR);
        label_error_msg.setBounds(380,193,300,25);
        label_error_msg.setVisible(false);
        
        label_password.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_password.setBounds(370, 150, 100, 40);
        label_password.setOpaque(true);
        label_password.setForeground(ChatApp.PRIMARY_DARK);
        label_password.setBackground(ChatApp.PRIMARY_GREEN);

        input_password.setBounds(470, 150, 450, 40);

        button_login.setBounds(550, 225, 200, 40);
        button_login.setBackground(ChatApp.SECONDARY_DARK);
        button_login.setForeground(ChatApp.PRIMARY_GREEN);
        button_login.setFocusable(false);

        button_register.setBounds(545, 280, 200, 40);
        button_register.setForeground(ChatApp.PRIMARY_GREEN);
        button_register.setBackground(ChatApp.PRIMARY_DARK);
        button_register.setBorderPainted(false);
        button_register.setFocusable(false);

        panel2.add(label_error_msg);
        panel2.add(label_login);
        panel2.add(input_login);
        panel2.add(label_password);
        panel2.add(input_password);
        panel2.add(button_login);
        panel2.add(button_register);
    }

    private void insertActions() {
        input_login.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });

        input_password.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });

        button_login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        button_register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chat.setScreen(new viewRegister(chat));
            }
        });

    }

    private void login() {
        String email = input_login.getText().toString();
        String password = String.valueOf(input_password.getPassword());
        String msg = "";
        try {
            switch (chat.getServer().login(email, password)) {
                case ServerMessage.LOGIN_ERROR_USER_ONLINE:
                    msg = "Este usuário já está conectado!";
                    label_error_msg.setText(msg);
                    label_error_msg.setVisible(true);
                    break;
                case ServerMessage.LOGIN_ERROR_WRONG:
                    msg = "Email ou senha incorreto(s) !";
                    label_error_msg.setText(msg);
                    label_error_msg.setVisible(true);
                    break;
                case ServerMessage.LOGIN_SUCCESS:
                    msg = "Logado com sucesso!";
                    
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    chat.setUser(user);
                    
                    label_error_msg.setVisible(false);
                    chat.setScreen(new viewApp(chat));
                    break;
            }
            
            chat.refresh();
        } catch (RemoteException ex) {
            System.out.println("Login - RemoteException: " + ex.getMessage());
        }
    }

}
