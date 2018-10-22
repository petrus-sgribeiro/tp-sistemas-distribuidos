/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author fagun
 */
public class Message implements Serializable{
    
    private static final int MSG_TEXT = 1;
   
    private User origin;
    private User destination;
    private String msg;
    private int msg_type;
    private Date timestamp;
    private int UTC;

    public Message(User origin, User destination, String msg, Date date, int UTC){
        this.origin = origin;
        this.destination = destination;
        this.msg = msg;
        this.msg_type = MSG_TEXT;
        this.timestamp = date;
        this.UTC = UTC;
    }

    public Message(){
        this.origin = null;
        this.destination = null;
        this.msg = null;
        this.msg_type = -1;
        this.timestamp = null;
        this.UTC = -1;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUTC() {
        return UTC;
    }

    public void setUTC(int UTC) {
        this.UTC = UTC;
    }
    
    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }   

    @Override
    public String toString() {
        return "Origin: " + this.origin.getEmail()+ "\n" +
                "Destination: " + this.destination.getEmail()+ "\n" +
                "Message: " + this.msg + "\n" + 
                "Date: " + this.timestamp.toString() + "\n" +
                "UTC: " + this.UTC;
    }
    
}
