/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.User;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author fagun
 */
public class viewTalk extends JPanel{
    private User user;
    private JLabel user_name;
    private JLabel user_description;
    private JLabel icon_profile_picture,icon_status;
    private Date date;
    
    public viewTalk(User user)
    {
        this.user = user;
        this.user_name = null;
        this.user_description = null;
        this.icon_profile_picture = null;
        this.icon_status = null;
        this.date = null; 
        
        initComponents();
        configComponents();
        insertActions();
        
    }
    
    private void initComponents()
    {
        this.user_name = new JLabel(this.user.getNickname(),SwingConstants.LEFT);
        this.user_description = new JLabel();
        this.icon_profile_picture = new JLabel("",SwingConstants.CENTER);
        this.icon_status = new JLabel("",SwingConstants.CENTER);
    }
    
    private void configComponents()
    {
        this.setPreferredSize(new Dimension(315,50));
        this.setBackground(Color.red);
        this.setBorder(BorderFactory.createMatteBorder(5,5 ,5,5, Color.WHITE));
        //this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
        this.setVisible(true);
    }
    
    private void insertActions()
    {
        
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JLabel getUser_name() {
        return user_name;
    }

    public void setUser_name(JLabel user_name) {
        this.user_name = user_name;
    }

    public JLabel getUser_description() {
        return user_description;
    }

    public void setUser_description(JLabel user_description) {
        this.user_description = user_description;
    }

    public JLabel getIcon_profile_picture() {
        return icon_profile_picture;
    }

    public void setIcon_profile_picture(JLabel icon_profile_picture) {
        this.icon_profile_picture = icon_profile_picture;
    }

    public JLabel getIcon_status() {
        return icon_status;
    }

    public void setIcon_status(JLabel icon_status) {
        this.icon_status = icon_status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
}
