/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fagun
 */
public class viewChat extends JPanel {

    private User origin, destiny;
    private JPanel panel, panel_status, panel_chat, panel_send;
    private JLabel destiny_name,destiny_image,destiny_status,destiny_icon_status;
   
    public viewChat(User origin, User destiny)
    {
        this.origin = origin;
        this.destiny = destiny;
        
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
    }
    
    private void initComponents()
    {
        panel = new JPanel();
        panel_status = new JPanel();
        panel_chat = new JPanel();
        panel_send = new JPanel();
        
        destiny_name = new JLabel();
        destiny_image = new JLabel();
        destiny_status = new JLabel();
        destiny_icon_status = new JLabel();
    }
    
    private void configComponents()
    {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridy = 0;
        c.weighty = 0.09;
        this.add(panel_status,c);
        
        c.gridy = 1;
        c.weighty = 0.835;
        this.add(panel_chat,c);
        
        c.gridy = 2;
        c.weighty = 0.075;
        this.add(panel_send,c);
        
        panel_status.setBackground(Color.white);
        panel_status.setBorder(BorderFactory.createMatteBorder(0,0,1,0,ChatApp.PRIMARY_DARK));
        panel_status.setLayout(null);
        
        panel_chat.setBackground(Color.yellow);
        panel_chat.setLayout(null);
        
        panel_send.setBackground(Color.pink);
        panel_chat.setLayout(null);
        
        destiny_name.setFont(new Font("Arial", Font.BOLD, 14));
        destiny_name.setBounds(440, 2, 400, 40);
        destiny_name.setForeground(Color.GRAY);
        destiny_name.setText("Dinho");
        panel_status.add(destiny_name);
        
        destiny_image.setIcon(new ImageIcon("icones/usuario_sem_foto_.png"));
        destiny_image.setBounds(400,7,40,40);
        panel_status.add(destiny_image);
        
        destiny_status.setFont(new Font("Arial", Font.PLAIN, 11));
        destiny_status.setBounds(455, 20, 400, 40);
        destiny_status.setText("Online");
        destiny_status.setForeground(Color.gray);
        panel_status.add(destiny_status);
        
        destiny_icon_status.setIcon(new ImageIcon("icones/online-icon_.png"));
        destiny_icon_status.setBounds(445,36,7,7);
        panel_status.add(destiny_icon_status);
    }
    
    private void insertComponents()
    {
        
    }
    
    private void insertActions()
    {
        
    }
  
}
