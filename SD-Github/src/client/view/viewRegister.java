package client.view;

import common.ServerMessage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class viewRegister extends JPanel {

    private JLabel title;

    private JLabel label_email;
    private JTextField input_email;

    private JLabel label_nickname;
    private JTextField input_nickname;

    private JLabel label_confirm_email;
    private JTextField input_confirm_email;

    private JLabel label_password;
    private JPasswordField input_password;

    private JLabel label_cpf;
    private JTextField input_cpf;

    private JLabel label_matricula;
    private JTextField input_matricula;

    private JLabel label_dataNasscimento;
    private JTextField input_dataNascimento;

    private JLabel label_sexo;
    private JComboBox jc_sexo;

    private JButton jb_confirmar;
    private JButton jb_voltar;

    private ChatApp chat;

    public viewRegister(ChatApp chat) {
        initComponents();
        configComponents();
        insertActions();
        this.chat = chat;
    }

    private void initComponents() {
        title = new JLabel("CADASTRAR");

        label_email = new JLabel("Email", SwingConstants.CENTER);
        input_email = new JTextField();

        label_nickname = new JLabel("Nickname", SwingConstants.CENTER);
        input_nickname = new JTextField();

        label_confirm_email = new JLabel("Confirme o e-mail", SwingConstants.CENTER);
        input_confirm_email = new JTextField();

        label_password = new JLabel("Senha", SwingConstants.CENTER);
        input_password = new JPasswordField();

        label_cpf = new JLabel("CPF", SwingConstants.CENTER);
        input_cpf = new JTextField();

        label_matricula = new JLabel("Matrícula", SwingConstants.CENTER);
        input_matricula = new JTextField();

        label_dataNasscimento = new JLabel("Data de nascimento", SwingConstants.CENTER);
        input_dataNascimento = new JTextField();

        label_sexo = new JLabel("Sexo", SwingConstants.CENTER);
        jc_sexo = new JComboBox();

        jb_confirmar = new JButton("CONFIRMAR");
        jb_voltar = new JButton("<<");
    }

    private void configComponents() {
        int y = 120;
        int salto = 50;

        this.setLayout(null);
        this.setBackground(ChatApp.PRIMARY_DARK);

        title.setFont(ChatApp.FONT_IMPACT);
        title.setForeground(ChatApp.PRIMARY_GREEN);
        title.setBounds(531, 30, 234, 61);

        label_email.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_email.setOpaque(true);
        label_email.setForeground(ChatApp.PRIMARY_DARK);
        label_email.setBackground(ChatApp.PRIMARY_GREEN);
        label_email.setBounds(333, y, 110, 40);
        label_email.setToolTipText("Digite seu email.");

        input_email.setBounds(443, y, 450, 40);
        input_email.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_email.setToolTipText("Digite seu email.");

        y += salto;

        label_confirm_email.setFont(new Font("Microsoft Tai Le", Font.BOLD, 12));
        label_confirm_email.setOpaque(true);
        label_confirm_email.setForeground(ChatApp.PRIMARY_DARK);
        label_confirm_email.setBackground(ChatApp.PRIMARY_GREEN);
        label_confirm_email.setBounds(333, y, 110, 40);
        label_confirm_email.setToolTipText("Confirme seu e-mail.");

        input_confirm_email.setBounds(443, y, 450, 40);
        input_confirm_email.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_confirm_email.setToolTipText("Confirme seu e-mail.");

        y += salto;

        label_password.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_password.setOpaque(true);
        label_password.setForeground(ChatApp.PRIMARY_DARK);
        label_password.setBackground(ChatApp.PRIMARY_GREEN);
        label_password.setBounds(333, y, 110, 40);
        label_password.setToolTipText("Sua senha deve conter no mínimo 8 caracteres!");

        input_password.setBounds(443, y, 450, 40);
        input_password.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_password.setToolTipText("Sua senha deve conter no mínimo 8 caracteres!");

        y += salto;

        label_nickname.setToolTipText("As pessoas irão ver este nome quando conversar com você.");
        label_nickname.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_nickname.setOpaque(true);
        label_nickname.setForeground(ChatApp.PRIMARY_DARK);
        label_nickname.setBackground(ChatApp.PRIMARY_GREEN);
        label_nickname.setBounds(333, y, 110, 40);

        input_nickname.setBounds(443, y, 450, 40);
        input_nickname.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        y += salto;

        label_cpf.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_cpf.setOpaque(true);
        label_cpf.setForeground(ChatApp.PRIMARY_DARK);
        label_cpf.setBackground(ChatApp.PRIMARY_GREEN);
        label_cpf.setBounds(333, y, 110, 40);

        input_cpf.setBounds(443, y, 450, 40);
        input_cpf.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        y += salto;

        label_matricula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_matricula.setOpaque(true);
        label_matricula.setForeground(ChatApp.PRIMARY_DARK);
        label_matricula.setBackground(ChatApp.PRIMARY_GREEN);
        label_matricula.setBounds(333, y, 110, 40);

        input_matricula.setBounds(443, y, 450, 40);
        input_matricula.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        y += salto;

        label_dataNasscimento.setFont(new Font("Microsoft Tai Le", Font.BOLD, 11));
        label_dataNasscimento.setOpaque(true);
        label_dataNasscimento.setForeground(ChatApp.PRIMARY_DARK);
        label_dataNasscimento.setBackground(ChatApp.PRIMARY_GREEN);
        label_dataNasscimento.setBounds(333, y, 110, 40);
        label_dataNasscimento.setToolTipText("Digite a data no formato: dd//mm/aa");

        input_dataNascimento.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_dataNascimento.setBounds(443, y, 450, 40);
        input_dataNascimento.setToolTipText("Digite a data no formato: dd/mm/aa");

        y += salto;

        label_sexo.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        label_sexo.setOpaque(true);
        label_sexo.setForeground(ChatApp.PRIMARY_DARK);
        label_sexo.setBackground(ChatApp.PRIMARY_GREEN);
        label_sexo.setBounds(333, y, 110, 40);

        jc_sexo.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        jc_sexo.setOpaque(true);
        jc_sexo.setForeground(ChatApp.SECONDARY_DARK);
        jc_sexo.setBounds(443, y, 450, 40);
        jc_sexo.setFocusable(false);
        jc_sexo.addItem("Feminino");
        jc_sexo.addItem("Masculino");

        y += salto + 30;

        jb_confirmar.setFocusable(false);
        jb_confirmar.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
        jb_confirmar.setBounds(515, y, 250, 55);
        jb_confirmar.setForeground(ChatApp.PRIMARY_GREEN);
        jb_confirmar.setBackground(ChatApp.SECONDARY_DARK);
        jb_confirmar.setBorderPainted(false);

        jb_voltar.setBounds(15, 15, 75, 50);
        jb_voltar.setFont(new Font("Impact", Font.BOLD, 15));
        jb_voltar.setForeground(ChatApp.PRIMARY_GREEN);
        jb_voltar.setBackground(ChatApp.PRIMARY_DARK);
        jb_voltar.setFocusable(false);
        jb_voltar.setBorderPainted(false);

        this.add(title);
        this.add(label_email);
        this.add(input_email);
        this.add(label_confirm_email);
        this.add(input_confirm_email);
        this.add(label_password);
        this.add(input_password);
        this.add(label_nickname);
        this.add(input_nickname);
        this.add(label_cpf);
        this.add(input_cpf);
        this.add(label_matricula);
        this.add(input_matricula);
        this.add(label_dataNasscimento);
        this.add(input_dataNascimento);
        this.add(label_sexo);
        this.add(jc_sexo);
        this.add(jb_confirmar);
        this.add(jb_voltar);
    }

    private void insertActions() {
        jb_voltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg) {
                chat.setScreen(new viewLogin(chat));
            }
        });

        jb_confirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setNickname(input_nickname.getText().toString());
                user.setEmail(input_email.getText().toString());
                user.setPassword(String.valueOf(input_password.getPassword()));
                user.setCpf(input_cpf.getText().toString());
                user.setMatricula(input_matricula.getText().toString());
                user.setDataNascimento(input_dataNascimento.getText().toString());
                user.setSexo((jc_sexo.getSelectedItem().toString().toLowerCase().equals("feminino") ? User.FEMININO : User.MASCULINO));
                
                if (!user.validate() || !user.getEmail().equals(input_confirm_email.getText().toString()) || input_confirm_email.getText().toString().length() < 3) {
                    JOptionPane.showMessageDialog(null, "Erro! Verifique os campos e tente novamente!");
                } else {
                    try {
                        switch (chat.getServer().registerUser(user)) {
                            case 0:
                                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                                chat.setScreen(new viewLogin(chat));
                                break;
                            case ServerMessage.REGISTER_EXISTS_EMAIL:
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, já existe alguem com este e-mail!");
                                break;
                            case ServerMessage.REGISTER_EXISTS_CPF:
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, já existe alguem com este CPF!");
                                break;
                            case ServerMessage.REGISTER_EXISTS_MATRICULA:
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, já existe alguem com esta matrícula!");
                                break;
                            case ServerMessage.REGISTER_EXISTS_NICKNAME:
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar, já existe alguem com este nickname!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar!!!");
                                break;
                        }
                    } catch (RemoteException ex) {
                        System.out.println("RegisterUser - Exception: " + ex.getMessage());
                    }
                }
            }
        });
    }

}
