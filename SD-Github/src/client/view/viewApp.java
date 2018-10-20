package client.view;

import common.Relation;
import common.ServerMessage;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import common.User;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class viewApp extends JPanel {

    public static final Dimension DIMENSION_TALKS = new Dimension(315, 576);
    public static final Rectangle DIMENSION_PANEL_FRIEND_LIST = new Rectangle(0, 115, 315, 576);
    private static final int SELECTED_EXIT = 1;
    private static final int SELECTED_CHAT = 2;
    private static final int SELECTED_GROUP = 3;
    private static final int SELECTED_CONFIG = 4;
    private static final int SELECTED_FRIENDS = 5;

    private ChatApp chat;
    private User user;

    private JLabel line, line_add_friend;

    /* Components das configurações */
    private JPanel panel_configs;
    private JButton jb_cvsas;
    private JButton jb_contatos;
    private JButton jb_grupos;
    private JButton jb_configs;
    private JButton jb_sair;
    private JButton jb_user_config;
    private JButton jb_search_addfriend;

    /* Componentes das configurações do painel de amigos */
    private JPanel panel_friends;
    private JPanel panel_talks;
    private JScrollPane scroll_talks;
    private JTextField input_search;

    private JButton jb_addFriend;
    private JLabel lb_friends_title;

    /* Componentes das configurações do painel de adicionar amigos */
    private JPanel panel_addfriend;
    private JTextField input_addfriend;
    private JButton jb_close_addfriend;
    private JLabel lb_addfriend;

    private JLabel lb_friend_img_perf;
    private JLabel lb_friend_found;
    private JButton jb_add_friend_found;
    private JLabel lb_not_found;

    private JPanel panel_chat;

    public viewApp(ChatApp chat) {
        super();
        this.chat = chat;
        this.user = chat.getUser();
        initComponents();
        configComponents();
        insertActions();
    }

    private void initComponents() {
        panel_configs = new JPanel();
        panel_friends = new JPanel();
        //panel_chat = new JPanel();
        panel_chat = new viewChat(user, user);
        panel_addfriend = new JPanel();
        jb_configs = new JButton("");
        jb_contatos = new JButton("");
        jb_cvsas = new JButton();
        jb_grupos = new JButton("");
        jb_sair = new JButton("");
        jb_user_config = new JButton("");
        input_search = new JTextField();
        input_addfriend = new JTextField();
        jb_addFriend = new JButton("+");
        jb_close_addfriend = new JButton("x");
        jb_search_addfriend = new JButton("");
        line = new JLabel();
        line_add_friend = new JLabel();
        lb_friends_title = new JLabel("Conversas", SwingConstants.CENTER);
        lb_addfriend = new JLabel("Busque pelo e-mail", SwingConstants.LEFT);
        lb_friend_found = new JLabel();
        lb_friend_img_perf = new JLabel();
        jb_add_friend_found = new JButton("+");
        lb_not_found = new JLabel();
        panel_talks = new JPanel();
        scroll_talks = new JScrollPane(panel_talks);
    }

    private void configComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridx = 0;
        c.weightx = 0.0625;
        this.add(panel_configs, c);
        c.gridx = 1;
        c.weightx = 0.25;
        c.weighty = 1.0;
        this.add(panel_friends, c);
        c.gridx = 2;
        c.weightx = 0.6875;
        this.add(panel_chat, c);
        c.gridx = 1;
        c.weightx = 0.25;
        c.weighty = 1.0;
        this.add(panel_addfriend, c);

        /* CONFIGURAÇAO DO PAINEL DE CONFIGURAÇÃO */
        panel_configs.setBackground(ChatApp.PRIMARY_DARK);
        panel_configs.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ChatApp.SECONDARY_GRAY));
        panel_configs.setLayout(null);

        jb_cvsas.setBounds(0, 300, 75, 50);
        jb_cvsas.setBackground(ChatApp.PRIMARY_DARK);
        jb_cvsas.setFocusable(false);
        jb_cvsas.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_cvsas.setIcon(new ImageIcon("icones/cvsa_.png"));
        panel_configs.add(jb_cvsas);

        jb_grupos.setBounds(0, 350, 75, 50);
        jb_grupos.setBackground(ChatApp.PRIMARY_DARK);
        jb_grupos.setFocusable(false);
        jb_grupos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_grupos.setIcon(new ImageIcon("icones/group_.png"));
        panel_configs.add(jb_grupos);

        jb_contatos.setBounds(0, 400, 75, 50);
        jb_contatos.setBackground(ChatApp.PRIMARY_DARK);
        jb_contatos.setFocusable(false);
        jb_contatos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_contatos.setIcon(new ImageIcon("icones/user_.png"));
        panel_configs.add(jb_contatos);

        jb_configs.setBounds(0, 450, 75, 50);
        jb_configs.setBackground(ChatApp.PRIMARY_DARK);
        jb_configs.setFocusable(false);
        jb_configs.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_configs.setIcon(new ImageIcon("icones/config_.png"));
        panel_configs.add(jb_configs);

        jb_sair.setBounds(0, 620, 75, 50);
        jb_sair.setBackground(ChatApp.PRIMARY_DARK);
        jb_sair.setFocusable(false);
        jb_sair.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_sair.setIcon(new ImageIcon("icones/quit_.png"));
        panel_configs.add(jb_sair);

        /* CONFIGURAÇÃO DO PAINEL DE AMIGOS */
        panel_friends.setBackground(ChatApp.PRIMARY_DARK);
        panel_friends.setLayout(null);

        input_search.setBounds(10, 20, 240, 30);
        input_search.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_search.setBackground(ChatApp.SECONDARY_GRAY);
        input_search.setForeground(Color.white);
        input_search.setCaretColor(Color.white);

        line.setBounds(0, 70, 320, 1);
        line.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ChatApp.SECONDARY_GRAY));

        line_add_friend.setBounds(0, 70, 320, 1);
        line_add_friend.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ChatApp.SECONDARY_GRAY));

        jb_addFriend.setBackground(ChatApp.SECONDARY_GRAY);
        jb_addFriend.setForeground(Color.white);
        jb_addFriend.setBounds(265, 20, 35, 30);
        jb_addFriend.setFocusable(false);
        jb_addFriend.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        lb_friends_title.setFont(new Font("Impact", Font.PLAIN, 25));
        lb_friends_title.setBounds(10, 75, 300, 40);
        lb_friends_title.setForeground(ChatApp.SECONDARY_GREEN);

        panel_friends.add(input_search);
        panel_friends.add(line);
        panel_friends.add(jb_addFriend);
        panel_friends.add(lb_friends_title);

        panel_addfriend.setBackground(ChatApp.PRIMARY_DARK);
        panel_addfriend.setLayout(null);
        panel_addfriend.add(line_add_friend);

        input_addfriend.setBounds(10, 90, 240, 30);
        input_addfriend.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        input_addfriend.setBackground(ChatApp.SECONDARY_GRAY);
        input_addfriend.setForeground(Color.white);
        input_addfriend.setCaretColor(Color.white);

        jb_search_addfriend.setBounds(258, 90, 35, 30);
        jb_search_addfriend.setBackground(ChatApp.PRIMARY_DARK);
        jb_search_addfriend.setFocusable(false);
        jb_search_addfriend.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jb_search_addfriend.setIcon(new ImageIcon("icones/search_.png"));

        jb_close_addfriend.setBackground(ChatApp.SECONDARY_GRAY);
        jb_close_addfriend.setForeground(Color.white);
        jb_close_addfriend.setBounds(265, 20, 35, 30);
        jb_close_addfriend.setFocusable(false);
        jb_close_addfriend.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        lb_addfriend.setBounds(10, 20, 300, 40);
        lb_addfriend.setForeground(ChatApp.SECONDARY_GREEN);
        lb_addfriend.setFont(new Font("Impact", Font.PLAIN, 23));

        lb_friend_found.setBounds(60, 140, 240, 30);
        lb_friend_found.setForeground(Color.white);
        lb_friend_found.setFont(new Font("Arial", Font.PLAIN, 18));

        lb_friend_img_perf.setBounds(10, 140, 40, 40);

        jb_add_friend_found.setBounds(258, 150, 35, 30);
        jb_add_friend_found.setBackground(ChatApp.SECONDARY_GRAY);
        jb_add_friend_found.setForeground(Color.white);
        jb_add_friend_found.setFocusable(false);
        jb_add_friend_found.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        lb_not_found.setBounds(60, 140, 240, 30);
        lb_not_found.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 13));
        lb_not_found.setForeground(ChatApp.WARNING_COLOR);

        panel_addfriend.add(input_addfriend);
        panel_addfriend.add(jb_close_addfriend);
        panel_addfriend.add(lb_addfriend);
        panel_addfriend.add(jb_search_addfriend);
        panel_addfriend.add(lb_friend_found);
        panel_addfriend.add(lb_friend_img_perf);
        panel_addfriend.add(jb_add_friend_found);
        panel_addfriend.add(lb_not_found);

        jb_add_friend_found.setVisible(false);
        lb_friend_img_perf.setVisible(false);
        lb_friend_found.setVisible(false);
        lb_not_found.setVisible(false);
        panel_addfriend.setVisible(false);
        
        panel_talks.setLayout(new GridLayout(8,1));
        panel_talks.setBackground(ChatApp.PRIMARY_DARK);
        scroll_talks.setBorder(null);
        
        scroll_talks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_talks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_talks.setBounds(DIMENSION_PANEL_FRIEND_LIST);
        
        panel_friends.add(scroll_talks);
        
        panel_talks.add(new viewTalk(user),0,0);
        panel_talks.add(new viewTalk(user),1,0);
        panel_talks.add(new viewTalk(user),2,0);
        panel_talks.add(new viewTalk(user),3,0);
        panel_talks.add(new viewTalk(user),4,0);
        panel_talks.add(new viewTalk(user),5,0);
        panel_talks.add(new viewTalk(user),6,0);
        panel_talks.add(new viewTalk(user),7,0);
        

        /* CONFIGURAÇÃO DO PAINEL DE CHAT */
        panel_chat.setBackground(Color.GREEN);
        
    }

    private void setDefaultColors() {
        jb_configs.setBackground(ChatApp.PRIMARY_DARK);
        jb_contatos.setBackground(ChatApp.PRIMARY_DARK);
        jb_cvsas.setBackground(ChatApp.PRIMARY_DARK);
        jb_grupos.setBackground(ChatApp.PRIMARY_DARK);
        jb_sair.setBackground(ChatApp.PRIMARY_DARK);
        jb_user_config.setBackground(ChatApp.PRIMARY_DARK);
    }

    private void insertActions() {
        jb_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chat.getServer().logout(user.getEmail(), user.getPassword());
                } catch (RemoteException ex) {
                    System.out.println("quit - Exception: " + ex.getMessage());
                }
                chat.setScreen(new viewLogin(chat));
            }
        });

        jb_sair.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_sair.setBackground(ChatApp.SECONDARY_GRAY);
                jb_sair.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 0, ChatApp.SECONDARY_GREEN));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setDefaultColors();
                jb_sair.setBackground(ChatApp.PRIMARY_DARK);
                jb_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, ChatApp.PRIMARY_GREEN));
            }
        });

        jb_grupos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jb_grupos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_grupos.setBackground(ChatApp.SECONDARY_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb_grupos.setBackground(ChatApp.PRIMARY_DARK);
            }
        });

        jb_contatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jb_contatos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_contatos.setBackground(ChatApp.SECONDARY_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb_contatos.setBackground(ChatApp.PRIMARY_DARK);
            }
        });

        jb_configs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jb_configs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_configs.setBackground(ChatApp.SECONDARY_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb_configs.setBackground(ChatApp.PRIMARY_DARK);
            }
        });

        jb_user_config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jb_user_config.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_user_config.setBackground(ChatApp.SECONDARY_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb_user_config.setBackground(ChatApp.PRIMARY_DARK);
            }
        });

        jb_cvsas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jb_cvsas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb_cvsas.setBackground(ChatApp.SECONDARY_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb_cvsas.setBackground(ChatApp.PRIMARY_DARK);
            }
        });

        jb_addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panel_friends.setVisible(false);
                panel_addfriend.setVisible(true);
            }

        });

        jb_addFriend.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jb_close_addfriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                input_addfriend.setText("");
                panel_friends.setVisible(true);
                panel_addfriend.setVisible(false);
                jb_add_friend_found.setVisible(false);
                lb_friend_img_perf.setVisible(false);
                lb_friend_found.setVisible(false);
                lb_not_found.setVisible(false);
            }

        });

        jb_close_addfriend.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        input_addfriend.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jb_search_addfriend.doClick();
                }
            }
        });

        jb_search_addfriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = input_addfriend.getText().toString();
                LinkedList<Relation> friendships = null;
                boolean found = false;
                User newfriend = null;

                try {
                    friendships = chat.getServer().getAllFriendships(user.getEmail(), user.getPassword());
                    newfriend = chat.getServer().searchUsers(email);
                } catch (RemoteException ex) {
                    System.out.println("Excessão search add friend!");
                }

                if (!email.equals(user.getEmail())) {
                    if (newfriend != null) {
                        for (Relation r : friendships) {
                            if (r.getEmail_user().equals(user.getEmail()) && r.getEmail_friend().equals(newfriend.getEmail())) {
                                found = true;
                            }
                        }
                        if (found) {
                            lb_not_found.setVisible(false);
                            lb_friend_img_perf.setIcon(new ImageIcon("icones/galgadot_.jpg"));
                            lb_friend_found.setText(newfriend.getNickname());
                            jb_add_friend_found.setVisible(true);
                            lb_friend_img_perf.setVisible(true);
                            lb_friend_found.setVisible(true);
                            jb_add_friend_found.setText("");
                            jb_add_friend_found.setBackground(ChatApp.PRIMARY_DARK);
                            jb_add_friend_found.setIcon(new ImageIcon("icones/ok_.png"));
                            jb_add_friend_found.setEnabled(false);
                        } else if (newfriend != null) {
                            lb_not_found.setVisible(false);
                            lb_friend_img_perf.setIcon(new ImageIcon("icones/galgadot_.jpg"));
                            lb_friend_found.setText(newfriend.getNickname());
                            jb_add_friend_found.setVisible(true);
                            lb_friend_img_perf.setVisible(true);
                            lb_friend_found.setVisible(true);

                        }
                    } else {
                        lb_friend_img_perf.setVisible(false);
                        lb_friend_found.setVisible(false);
                        jb_add_friend_found.setVisible(false);
                        lb_not_found.setText("Usuário não encontrado!");
                        lb_not_found.setVisible(true);
                    }
                }
            }

        });

        jb_add_friend_found.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Relation relation = new Relation();

                relation.setEmail_user(user.getEmail());
                relation.setEmail_friend(input_addfriend.getText().toString());

                try {
                    switch (chat.getServer().registerRelation(relation)) {
                        case ServerMessage.REGISTER_FRIEND_ADD_SUCCESSFULLY:
                            jb_add_friend_found.setText("");
                            jb_add_friend_found.setBackground(ChatApp.PRIMARY_DARK);
                            jb_add_friend_found.setIcon(new ImageIcon("icones/ok_.png"));
                            break;
                        case ServerMessage.REGISTER_RELATION_EXISTS:
                            JOptionPane.showMessageDialog(null, "Erro ao adicionar, você já tem esse amigo adicionado!");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Erro ao adicionar!!!");
                            break;
                    }
                } catch (RemoteException ex) {
                    System.out.println("RegisterUser - Exception: " + ex.getMessage());
                }
            }
        });
    }
}
