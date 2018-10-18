/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author Larissa
 */
public class Relation implements Serializable {

    private String email_user;
    private String email_friend;

    public Relation(String email_user, String email_friend) {
        this.email_user = email_user;
        this.email_friend = email_friend;
    }

    public Relation() {
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getEmail_friend() {
        return email_friend;
    }

    public void setEmail_friend(String email_friend) {
        this.email_friend = email_friend;
    }
}
