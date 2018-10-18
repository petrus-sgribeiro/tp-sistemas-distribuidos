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
 * @author Larissa
 */
public class Relation implements Serializable {

    private String email_primary;
    private String email_secondary;
    private Date created_at;
    private Date updated_at;

    public Relation(String email_primary, String email_secondary, Date created_at, Date updated_at) {
        this.email_primary = email_primary;
        this.email_secondary = email_secondary;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Relation() {
        this.email_primary = null;
        this.email_secondary = null;
        this.created_at = null;
        this.updated_at = null;
    }


    /**
     * @return the email_primary
     */
    public String getEmail_primary() {
        return email_primary;
    }

    /**
     * @param email_primary the email_primary to set
     */
    public void setEmail_primary(String email_primary) {
        this.email_primary = email_primary;
    }

    /**
     * @return the email_secondary
     */
    public String getEmail_secondary() {
        return email_secondary;
    }

    /**
     * @param email_secondary the email_secondary to set
     */
    public void setEmail_secondary(String email_secondary) {
        this.email_secondary = email_secondary;
    }

    /**
     * @return the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the updated_at
     */
    public Date getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }



  


}
