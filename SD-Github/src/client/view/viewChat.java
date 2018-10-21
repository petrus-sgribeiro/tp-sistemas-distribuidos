/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.Message;
import common.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author fagun
 */
public class viewChat extends JPanel {

    private static final Dimension CHAT_SIZE = new Dimension(855, 500);

    private User origin, destiny;
    private JPanel panel, panel_status, panel_chat, panel_send, panel_send_area, panel_chat_messages;
    private JLabel destiny_name, destiny_image, destiny_status, destiny_icon_status;
    private JTextField input_message;
    private JButton jb_send, jb_index;
    private int scroll_height = 0;
    private ChatApp chat;

    public viewChat(ChatApp chat, User destiny) {
        this.chat = chat;
        this.destiny = destiny;
        this.origin = chat.getUser();
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
    }

    private void sendMessage() throws RemoteException {
        String message = input_message.getText().toString();
        if (message.length() > 0) {
            panel_chat_messages.add(new viewMessage(viewMessage.USER_ORIGIN, message, new Date()));
            chat.getServer().sendMessage(new Message(origin, destiny, message, new Date(), 3));
            input_message.setText("");
            chat.refresh();
        }
    }

    private void receiveMessage(Message msg) {
        panel_chat_messages.add(new viewMessage(viewMessage.USER_DESTINATION, msg.getMsg(), msg.getTimestamp()));
        chat.refresh();
    }

    private void initComponents() {
        panel = new JPanel();
        panel_status = new JPanel();
        panel_chat = new JPanel();
        panel_send = new JPanel();
        panel_send_area = new JPanel();

        destiny_name = new JLabel();
        destiny_image = new JLabel();
        destiny_status = new JLabel();
        destiny_icon_status = new JLabel();
        input_message = new JTextField();

        jb_index = new JButton();
        jb_send = new JButton();

        panel_chat_messages = new JPanel();
    }

    private void configComponents() {
        this.setBackground(ChatApp.SECONDARY_WHITE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;

        c.gridy = 0;
        c.weighty = 0.102;
        this.add(panel_status, c);

        c.gridy = 1;
        c.weighty = 0.748;
        this.add(panel_chat, c);

        c.gridy = 2;
        c.weighty = 0.15;
        this.add(panel_send, c);

        panel_status.setBackground(ChatApp.SECONDARY_WHITE);
        panel_status.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        panel_status.setLayout(null);

        destiny_name.setFont(new Font("Arial", Font.BOLD, 14));
        destiny_name.setBounds(436, 10, 400, 40);
        destiny_name.setForeground(Color.GRAY);
        destiny_name.setText(destiny.getNickname());
        panel_status.add(destiny_name);

        destiny_image.setIcon(new ImageIcon("icones/usuario_sem_foto_.png"));
        destiny_image.setBounds(380, 15, 40, 40);
        panel_status.add(destiny_image);

        destiny_status.setFont(new Font("Arial", Font.PLAIN, 11));
        destiny_status.setBounds(451, 28, 400, 40);
        destiny_status.setText("Online");
        destiny_status.setForeground(Color.gray);
        panel_status.add(destiny_status);

        destiny_icon_status.setIcon(new ImageIcon("icones/online-icon_.png"));
        destiny_icon_status.setBounds(441, 44, 7, 7);
        panel_status.add(destiny_icon_status);

        panel_chat.setBackground(ChatApp.SECONDARY_WHITE);
        panel_chat.setLayout(null);
        panel_chat.add(panel_chat_messages);
        panel_chat.setBackground(Color.blue);

        panel_send.setBackground(ChatApp.SECONDARY_WHITE);
        panel_send.setLayout(null);
        panel_send.add(panel_send_area);

        panel_send_area.setLayout(null);
        panel_send_area.setBounds(15, 10, 840, 80);
        panel_send_area.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ChatApp.BORDER_BLUE));
        panel_send_area.setBackground(Color.white);

        input_message.setBounds(75, 20, 690, 40);
        input_message.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ChatApp.BORDER_BLUE));

        jb_index.setBounds(20, 25, 30, 30);
        jb_index.setIcon(new ImageIcon("icones/anexar_.png"));
        jb_index.setFocusable(false);
        jb_index.setBackground(Color.white);
        jb_index.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jb_send.setBounds(780, 18, 40, 40);
        jb_send.setIcon(new ImageIcon("icones/send_.png"));
        jb_send.setFocusable(false);
        jb_send.setBackground(Color.white);
        jb_send.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panel_send_area.add(input_message);
        panel_send_area.add(jb_index);
        panel_send_area.add(jb_send);

        /* panel chat messages config's */
        panel_chat_messages.setMinimumSize(CHAT_SIZE);
        panel_chat_messages.setBackground(new Color(0, 0, 0, 0));
        panel_chat_messages.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void insertComponents() {

    }

    private void insertActions() {
        jb_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendMessage();
                } catch (RemoteException ex) {
                    System.out.println("Exception Send message: " + ex.getMessage());
                }
            }
        });
        
        input_message.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        sendMessage();
                    } catch (RemoteException ex) {
                        System.err.println("KeyListener exception: " + ex.getMessage());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
    }

}
