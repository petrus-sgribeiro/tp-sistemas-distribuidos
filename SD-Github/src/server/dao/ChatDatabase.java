/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao;

import common.Relation;
import common.User;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 *
 * @author fagun
 */
public class ChatDatabase {

    private static final String DB_USERS = "users.db";
    private static final String DB_URL = "jdbc:sqlite:C:/sqlite/db/";

    public ChatDatabase() {
        createNewDatabase(DB_USERS);
        createTableUser();
        createTableRelation();
    }

    public static void createNewDatabase(String db_name) {
        String url = DB_URL + db_name;

        try {
            Connection conn = DriverManager.getConnection(url);
            conn.close();
        } catch (SQLException e) {
            System.out.println("createNewDatabase - SQLException: " + e.getMessage());
        }

    }

    private void createTableUser() {
        Connection conn;
        Statement stmt;
        String sql = "CREATE TABLE IF NOT EXISTS users(\n"
                + "  email VARCHAR(64) NOT NULL PRIMARY KEY,\n"
                + "  pass VARCHAR(64) NOT NULL,\n"
                + "  cpf VARCHAR(64) NOT NULL ,\n"
                + "  matricula VARCHAR(64) NOT NULL UNIQUE,\n"
                + "  data_nascimento VARCHAR(64) NOT NULL,\n"
                + "  sexo INTEGER NOT NULL,\n"
                + "  nickname VARCHAR(64) NOT NULL UNIQUE,\n"
                + "  online BOOLEAN NOT NULL,\n"
                + "  created_at text NOT NULL,\n"
                + "  updated_at text\n"
                + ");";

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET email = ? , pass = ? , cpf = ? , matricula = ? , data_nascimento = ? , sexo = ? , nickname = ?, online = ?, updated_at = DATETIME('now') WHERE email = ?";
        Connection conn;
        PreparedStatement pstmt;
        String email, pass, matricula, cpf, data_nascimento, nickname;
        int sexo;
        Date created_at;
        boolean online;

        try {

            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getCpf());
            pstmt.setString(4, user.getMatricula());
            pstmt.setString(5, user.getDataNascimento());
            pstmt.setInt(6, user.getSexo());
            pstmt.setString(7, user.getNickname());
            pstmt.setBoolean(8, user.isOnline());
            pstmt.setString(9, user.getEmail());
            pstmt.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public LinkedList<User> loadUsers() {
        LinkedList<User> list = new LinkedList<User>();
        String sql = "SELECT email,pass,cpf,matricula,data_nascimento,sexo,nickname,online,created_at,updated_at FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, pass, matricula, cpf, data_nascimento, nickname, update;
            int sexo;
            Date created_at, updated_at = null;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("pass");
                cpf = rs.getString("cpf");
                matricula = rs.getString("matricula");
                data_nascimento = rs.getString("data_nascimento");
                sexo = rs.getInt("sexo");
                nickname = rs.getString("nickname");
                online = rs.getBoolean("online");
                created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("created_at"));
                update = rs.getString("updated_at");
                if (update != null) {
                    updated_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(update);
                }
                list.add(new User(email, pass, cpf, matricula, data_nascimento, sexo, nickname, online, created_at, updated_at));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public User getUser(String user_email, String user_pass) {
        String sql = "SELECT email,pass,cpf,matricula,data_nascimento,sexo,nickname,online,created_at,updated_at FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, pass, matricula, cpf, data_nascimento, nickname, update;
            int sexo;
            Date created_at, updated_at = null;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("pass");
                cpf = rs.getString("cpf");
                matricula = rs.getString("matricula");
                data_nascimento = rs.getString("data_nascimento");
                sexo = rs.getInt("sexo");
                nickname = rs.getString("nickname");
                online = rs.getBoolean("online");
                created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("created_at"));
                update = rs.getString("updated_at");
                if (update != null) {
                    updated_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(update);
                }

                if (email.equals(user_email) && pass.equals(user_pass)) {
                    conn.close();
                    return new User(email, pass, cpf, matricula, data_nascimento, sexo, nickname, online, created_at, updated_at);
                }

            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public User getUser(String user_email) {
        String sql = "SELECT email,sexo,nickname,online FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, nickname;
            int sexo;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                sexo = rs.getInt("sexo");
                nickname = rs.getString("nickname");
                online = rs.getBoolean("online");

                if (email.equals(user_email)) {
                    conn.close();
                    return new User(email, null, null, null, null, sexo, nickname, online, null, null);
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(email,pass,cpf,matricula,data_nascimento,sexo,nickname,online,created_at) VALUES (?,?,?,?,?,?,?,?,DATETIME('now'))";

        try {
            Connection conn = DriverManager.getConnection(DB_URL + DB_USERS);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getCpf());
            pstmt.setString(4, user.getMatricula());
            pstmt.setString(5, user.getDataNascimento());
            pstmt.setInt(6, user.getSexo());
            pstmt.setString(7, user.getNickname());
            pstmt.setBoolean(8, user.isOnline());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void listUsers() {
        LinkedList<User> users = loadUsers();

        for (User u : users) {
            System.out.println(u.toString());
            System.out.println("----------------------");
        }
    }

    private void createTableRelation() {
        Connection conn;
        Statement stmt;
        String sql = "CREATE TABLE IF NOT EXISTS relations(\n"
                + "  email_user VARCHAR(64) NOT NULL, \n"
                + "  email_friend VARCHAR(64) NOT NULL\n"
                + ");";

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insertRelation(Relation relation) {
        String sql = "INSERT INTO relations(email_user,email_friend) VALUES (?,?)";

        try {
            Connection conn = DriverManager.getConnection(DB_URL + DB_USERS);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, relation.getEmail_user());
            pstmt.setString(2, relation.getEmail_friend());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public LinkedList<Relation> loadRelations() {
        LinkedList<Relation> listR = new LinkedList<Relation>();
        String sql = "SELECT email_user,email_friend FROM relations";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email_user, email_friend;

            while (rs.next()) {
                email_user = rs.getString("email_user");
                email_friend = rs.getString("email_friend");

                listR.add(new Relation(email_user, email_friend));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listR;
    }
}
