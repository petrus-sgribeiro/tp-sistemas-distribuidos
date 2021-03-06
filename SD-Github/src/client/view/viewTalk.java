/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import common.User;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author fagun
 */
public class viewTalk extends JPanel{

    private JPanel panel;

    private User user;
    private JLabel user_name;
    private JLabel user_description;
    private JLabel icon_profile_picture, icon_status;
    private JLabel lb_date;
    private Date date;
    private viewApp app;

    public viewTalk(viewApp app, User user) {
        super();
        this.app = app;
        this.panel = null;
        this.user = user;
        this.user_name = null;
        this.user_description = null;
        this.icon_profile_picture = null;
        this.icon_status = null;
        this.date = null;
        this.lb_date = null;

        initComponents();
        configComponents();
        addComponents();
        insertActions();
    }

    private void initComponents() {
        this.panel = new JPanel();
        this.user_name = new JLabel(this.user.getNickname(), SwingConstants.LEFT);
        this.user_description = new JLabel();
        this.icon_profile_picture = new JLabel("", SwingConstants.CENTER);
        this.icon_status = new JLabel("", SwingConstants.CENTER);
        this.lb_date = new JLabel("", SwingConstants.CENTER);
    }

    private void configComponents() {
        this.setPreferredSize(new Dimension(300, 50));
        this.setMinimumSize(new Dimension(300, 50));
        this.setBackground(ChatApp.PRIMARY_DARK);
        //this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
        this.setVisible(true);
        this.add(panel);

        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(315, 50));
        panel.setPreferredSize(new Dimension(315, 50));
        panel.setBackground(ChatApp.PRIMARY_DARK);
        
        icon_profile_picture.setIcon(new ImageIcon("icones/usuario_sem_foto_.png"));
        icon_profile_picture.setBounds(5,5,40,40);
        
        user_name.setBounds(60,5, 200,20);
        user_name.setFont(new Font("Microsoft Tai Le",Font.PLAIN,16));
        user_name.setForeground(Color.white);
        user_name.setText(user.getNickname());
        
        user_description.setBounds(60,30,200,20);
        user_description.setFont(new Font("Microsoft Tai Le",Font.PLAIN,12));
        user_description.setForeground(ChatApp.PRIMARY_GRAY);
        user_description.setText(user.getDescription());

    }

    private void insertActions() {
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                app.setPanel_chat(new viewChat(app,user));
                System.out.println(user.getEmail() + " " + user.getNickname());
                app.refresh();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {

            }

            @Override
            public void mouseEntered(MouseEvent me) {
                panel.setBackground(ChatApp.HOVER_GRAY);
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                panel.setBackground(ChatApp.PRIMARY_DARK);
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void addComponents() {
        this.add(panel);
        panel.add(user_name);
        panel.add(user_description);
        panel.add(icon_profile_picture);
        panel.add(icon_status);
        panel.add(lb_date);
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
