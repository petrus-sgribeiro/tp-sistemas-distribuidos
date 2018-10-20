/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author fagun
 */
public class viewMessage extends JPanel {

    public static final int USER_ORIGIN = 1;
    public static final int USER_DESTINATION = 2;

    private static final Color BORDER_MESSAGE_COLOR = new Color(112, 112, 112);
    private JLabel label_message;
    private JLabel label_hour;
    private JPanel panel_message;
    private int MESSAGE_Y = 30;

    private String formatMessage(String message)
    {
        int i = 0;
        String str = "<html>";
        while(i < message.length())
        {
            if(i >= 60 && i % 60 == 0)
            {
                str += "<br>";
                MESSAGE_Y += 15;
                this.setPreferredSize(new Dimension(800, MESSAGE_Y));
            }
            str += message.charAt(i);
            i++;
        }
        str += "</html>";
        return str;
    }
    
    public viewMessage(int USER_TYPE, String message, Date date) {
        super();

        this.setBackground(new Color(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(800, MESSAGE_Y));

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");

        label_message = new JLabel(formatMessage(message));
        label_message.setBackground(Color.white);
        label_message.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 12));
        label_message.setBounds(10, 10, 100, 25);

        label_hour = new JLabel("               " + format.format(date));
        label_hour.setFont(new Font("Arial", Font.BOLD, 10));

        panel_message = new JPanel();
        panel_message.add(label_message);
        panel_message.add(label_hour);

        if (USER_DESTINATION == USER_TYPE) {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel_message.setBackground(Color.WHITE);
            label_message.setForeground(new Color(125, 138, 150));
            label_hour.setForeground(new Color(125, 138, 150));
        } else {
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            panel_message.setBackground(Color.BLACK);
            panel_message.setBorder(new EmptyBorder(0,0,0,20));
            label_message.setForeground(Color.WHITE);
            label_hour.setForeground(Color.WHITE);
        }

        this.add(panel_message);
    }
}
