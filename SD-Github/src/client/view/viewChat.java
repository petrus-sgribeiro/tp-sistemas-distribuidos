/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.Message;
import common.ServerMessageInterface;
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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author fagun
 */
public class viewChat extends JPanel {

    private static final Dimension CHAT_SIZE = new Dimension(855, 500);

    private User origin, destiny;
    private JPanel panel_chat, panel_chat_container, panel_chat_messages;
    private JPanel panel, panel_status, panel_send, panel_send_area;
    private JLabel destiny_name, destiny_image, destiny_status, destiny_icon_status;
    private JTextField input_message;
    private JButton jb_send, jb_index;
    private JScrollPane scrollpane;
    private viewApp app;
    private ServerMessageInterface cliente;
    private Message msg;
    private Thread thread = null;

    public viewChat(viewApp app, User destiny) {
        this.app = app;
        this.destiny = destiny;
        this.origin = app.getUser();
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
        connect();
    }

    private boolean connect() {

        try {
            cliente = (ServerMessageInterface) Naming.lookup("rmi://localhost:" + String.valueOf(ChatApp.PORT) + "/ServerMessage");
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            //System.out.println("Thread rodando..." + destiny.getNickname());
                            Thread.sleep(1000);
                            msg = cliente.receiveMessage(destiny,origin);
                            if (msg != null) {
                                receiveMessage(msg);
                            }
                        } catch (Exception ex) {

                        }
                    }
                }

            });
            thread.start();
            
            return true;
        } catch (Exception e) {
            cliente = null;
            return false;
        } finally {

        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
        
    private void sendMessage() throws RemoteException {
        String message = input_message.getText().toString();
        if (message.length() > 0) {
            panel_chat_messages.add(new viewMessage(viewMessage.USER_ORIGIN, message, new Date()));
            System.out.println("Mensagem enviada.\nOrigem: " + origin.getEmail() + "\nDestino: " + destiny.getEmail()+ "\nConteudo: " + message);
            app.getServer().sendMessage(new Message(origin, destiny, message, new Date(), 3));
            input_message.setText("");
            app.refresh();
        }
    }

    private void receiveMessage(Message msg) {
        panel_chat_messages.add(new viewMessage(viewMessage.USER_DESTINATION, msg.getMsg(), msg.getTimestamp()));
        app.refresh();
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
        scrollpane = new JScrollPane();
    }

    private void configComponents() {
        this.setBackground(ChatApp.SECONDARY_WHITE);
        this.setLayout(new GridBagLayout());

        panel_status.setBackground(ChatApp.SECONDARY_WHITE);
        panel_status.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        panel_status.setLayout(null);

        destiny_name.setFont(new Font("Arial", Font.BOLD, 14));
        destiny_name.setBounds(436, 10, 400, 40);
        destiny_name.setForeground(Color.GRAY);
        destiny_name.setText(destiny.getNickname());

        destiny_image.setIcon(new ImageIcon("icones/usuario_sem_foto_.png"));
        destiny_image.setBounds(380, 15, 40, 40);

        destiny_status.setFont(new Font("Arial", Font.PLAIN, 11));
        destiny_status.setBounds(451, 28, 400, 40);
        destiny_status.setText("Online");
        destiny_status.setForeground(Color.gray);

        destiny_icon_status.setIcon(new ImageIcon("icones/online-icon_.png"));
        destiny_icon_status.setBounds(441, 44, 7, 7);

        panel_send.setBackground(ChatApp.SECONDARY_WHITE);
        panel_send.setLayout(null);

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

        /* panel chat messages config's */
        panel_chat.setLayout(new GridLayout(1, 1));

        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        panel_chat_messages.setLayout(new GridLayout(20, 1));
        panel_chat_messages.setBackground(ChatApp.SECONDARY_WHITE);

    }

    private void insertComponents() {
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

        panel_status.add(destiny_name);
        panel_status.add(destiny_image);
        panel_status.add(destiny_icon_status);
        panel_status.add(destiny_status);

        panel_send.add(panel_send_area);
        panel_send_area.add(input_message);
        panel_send_area.add(jb_index);
        panel_send_area.add(jb_send);

        panel_chat.add(scrollpane, 0, 0);
        scrollpane.setViewportView(panel_chat_messages);
        
        if(app.getMessages() != null)
        for(Message m: app.getMessages())
        {
            if(m.getDestination().getEmail().equals(origin.getEmail()) && m.getOrigin().getEmail().equals(destiny.getEmail()))
            {
                panel_chat_messages.add(new viewMessage(viewMessage.USER_DESTINATION, m.getMsg(), m.getTimestamp()));
            }else if(m.getDestination().getEmail().equals(destiny.getEmail()) && m.getOrigin().getEmail().equals(origin.getEmail()))
            {
                panel_chat_messages.add(new viewMessage(viewMessage.USER_ORIGIN, m.getMsg(), m.getTimestamp()));
            }
        }
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

        scrollpane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                app.refresh();
            }
        });

    }

}
