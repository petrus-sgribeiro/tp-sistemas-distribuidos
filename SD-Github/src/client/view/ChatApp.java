package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.ServerMessageInterface;
import common.User;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatApp extends JFrame {

    /* CORES DO SISTEMA */
    public static final Color PRIMARY_DARK = new Color(20, 33, 48, 255);
    public static final Color SECONDARY_DARK = new Color(23, 21, 21,255);
        
    public static final Color PRIMARY_GREEN = new Color(0, 255, 153,255);
    public static final Color SECONDARY_GREEN = new Color(27,161,103,255);

    public static final Color PRIMARY_GRAY = new Color(167,183,195,255);
    public static final Color SECONDARY_GRAY = new Color(54,62,71,204);
    
    public static final Color PRIMARY_WHITE = new Color(198,226,226,255);
    public static final Color SECONDARY_WHITE = new Color(242,247,247,255);
    public static final Color HOVER_GRAY = new Color(65,65,65,255);
    
    public static final Color BORDER_BLUE = new Color(198,226,226,255);
    public static final Color WARNING_COLOR = new Color(255,120,120,255);
    private static final int port = 1099;

    /* FONTES DO SISTEMA */
    public static final Font FONT_IMPACT = new Font("Impact", Font.BOLD, 40);

    public static final Dimension APP_WINDOW_SIZE = new Dimension(1280, 720);
    
    private JPanel screen;
    private JPanel currentWindow;
    private ServerMessageInterface server = null;
    private User user;

    public ChatApp() {
        super();
        user = null;
        currentWindow = null;
        initComponents();
        configComponents();
        insertActions();
        start();
        connect();
    }

    public ServerMessageInterface getServer() {
        return server;
    }
    
    private boolean connect() {
        try {
            server = (ServerMessageInterface) Naming.lookup("rmi://localhost:" + String.valueOf(port) + "/ServerMessage");
            return true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Connect - Exception: " + e.getMessage());
            return false;
        }

    }

    private void initComponents() {
        screen = new JPanel();
    }

    private void configComponents() {
        /* FRAME CONFIGS */
        this.setMinimumSize(APP_WINDOW_SIZE);
        this.setPreferredSize(APP_WINDOW_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(screen);

        /* SCREEN CONFIGS */
        screen.setLayout(new GridBagLayout());
        screen.setBackground(ChatApp.PRIMARY_DARK);
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    private void start() {
        this.pack();
        this.setVisible(true);
    }

    public void setScreen(JPanel currentWindow) {
        if (this.currentWindow != null) {
            screen.remove(this.currentWindow);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        this.currentWindow = currentWindow;
        screen.add(this.currentWindow, c);

        refresh();
    }
    
    private void insertActions()
    {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if(user != null)
                    try {
                        server.logout(user.getEmail(),user.getPassword());
                } catch (RemoteException ex) {
                    System.out.println("windowClosing - RemoteException: " + ex.getMessage());
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
               
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
               
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 
}
