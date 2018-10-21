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
public class User implements Serializable {

    public static final int MASCULINO = 1;
    public static final int FEMININO = 2;

    private String email;
    private String password;
    private String cpf;
    private String matricula;
    private String dataNascimento;
    private String description;
    private int sexo;
    private String nickname;
    private boolean online;
    private Date created_at;
    private Date updated_at;

    public User(String email, String password, String cpf, String matricula, String dataNascimento, String description, int sexo,
            String nickname, boolean online, Date created_at, Date updated_at) {
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nickname = nickname;
        this.online = online;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.description = description;
    }

    public User() {
        this.email = null;
        this.password = null;
        this.cpf = null;
        this.matricula = null;
        this.dataNascimento = null;
        this.sexo = FEMININO;
        this.nickname = null;
        this.online = false;
        this.created_at = null;
        this.updated_at = null;
        this.description = "Olá! Vamos conversar? ;)";
    }

    @Override
    public String toString() {
        String sexo = (this.sexo == MASCULINO) ? "Masculino" : "Feminino";
        String status = (online)? "Online" : "Offline";
        String updated = (updated_at == null)? "Never" : updated_at.toString();
        return "Email: " + this.email + "\n"
                + //"Password: " + this.password + "\n" +
                "Cpf: " + this.cpf + "\n"
                + "Matrícula: " + this.matricula + "\n"
                + "Data Nascimento: " + this.dataNascimento + "\n"
                + "Descrição: " + this.getDescription() + "\n"
                + "Sexo: " + sexo + "\n"
                + "Status: " +  status + "\n"
                + "Created at: " + this.created_at.toString() + "\n"
                + "Updated at: " + updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public static int getMasculino() {
        return MASCULINO;
    }

    public static int getFeminino() {
        return FEMININO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean validate() {
        if (!this.email.contains("@") || this.email.contains(" ") || this.email.equals("")) {
            return false;
        } else if (this.password.length() < 8) {
            return false;
        } else if (this.cpf.length() < 11 || this.cpf.contains(" ") || !this.cpf.matches("[0-9]+")) {
            return false;
        } else if (this.matricula.length() < 7 || this.matricula.contains(" ") || !this.matricula.matches("[0-9]+")) {
            return false;
        } else {
            if (this.dataNascimento.length() < 8) {
                return false;
            }

            String[] splited = this.dataNascimento.split("/");
            if (splited.length != 3) {
                return false;
            }
            for (int i = 0; i < 3; i++) {
                if (!splited[i].matches("[0-9]+")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.getEmail().toLowerCase().equals(this.getEmail().toLowerCase()) || user.getCpf().toLowerCase().equals(this.getCpf().toLowerCase())
                || user.getMatricula().toLowerCase().equals(this.getMatricula().toLowerCase()) || user.getNickname().toLowerCase().equals(this.getNickname().toLowerCase());
    }

}
