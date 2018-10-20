/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.User;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author fagun
 */
public class viewChat extends JPanel {

    private User origin, destiny;
    private JPanel panel, panel_status, panel_chat, panel_send;
    
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
        
        panel_chat.setBackground(Color.red);
        panel_chat.setLayout(null);
        
        panel_send.setBackground(Color.blue);
        panel_chat.setLayout(null);
    }
    
    private void insertComponents()
    {
        
    }
    
    private void insertActions()
    {
        
    }
  
}
