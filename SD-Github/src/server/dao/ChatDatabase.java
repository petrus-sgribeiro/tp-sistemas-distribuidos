/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dao;

import common.Message;
import common.Relation;
import common.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fagun
 */
public class ChatDatabase {

    private static final String DB_USERS = "users.db";
    private static final String DB_URL = "jdbc:sqlite:C:/chat-app/sqlite/db/";
    private static final String TALKS_URL = "C:/chat-app/talks/";

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
                + "  description VARCHAR(64) NOT NULL,\n"
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
        String sql = "UPDATE users SET email = ? , pass = ? , cpf = ? , matricula = ? , data_nascimento = ? , description = ? , sexo = ? , nickname = ?, online = ?, updated_at = DATETIME('now') WHERE email = ?";
        Connection conn;
        PreparedStatement pstmt;
        String email, pass, matricula, cpf, data_nascimento, nickname, description;
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
            pstmt.setString(6, user.getDescription());
            pstmt.setInt(7, user.getSexo());
            pstmt.setString(8, user.getNickname());
            pstmt.setBoolean(9, user.isOnline());
            pstmt.setString(10, user.getEmail());
            pstmt.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public LinkedList<User> loadUsers() {
        LinkedList<User> list = new LinkedList<User>();
        String sql = "SELECT email,pass,cpf,matricula,data_nascimento,description,sexo,nickname,online,created_at,updated_at FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, pass, matricula, cpf, data_nascimento, nickname, update,description;
            int sexo;
            Date created_at, updated_at = null;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("pass");
                cpf = rs.getString("cpf");
                matricula = rs.getString("matricula");
                data_nascimento = rs.getString("data_nascimento");
                description = rs.getString("description");
                sexo = rs.getInt("sexo");
                nickname = rs.getString("nickname");
                online = rs.getBoolean("online");
                created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("created_at"));
                update = rs.getString("updated_at");
                if (update != null) {
                    updated_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(update);
                }
                list.add(new User(email, pass, cpf, matricula, data_nascimento,description, sexo, nickname, online, created_at, updated_at));
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
        String sql = "SELECT email,pass,cpf,matricula,data_nascimento,description,sexo,nickname,online,created_at,updated_at FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, pass, matricula, cpf, data_nascimento, nickname, update, description;
            int sexo;
            Date created_at, updated_at = null;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                pass = rs.getString("pass");
                cpf = rs.getString("cpf");
                matricula = rs.getString("matricula");
                data_nascimento = rs.getString("data_nascimento");
                description = rs.getString("description");
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
                    return new User(email, pass, cpf, matricula, data_nascimento,description, sexo, nickname, online, created_at, updated_at);
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
        String sql = "SELECT email,description,sexo,nickname,online FROM users";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(DB_URL + DB_USERS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            String email, nickname,description;
            int sexo;
            boolean online;

            while (rs.next()) {
                email = rs.getString("email");
                description = rs.getString("description");
                sexo = rs.getInt("sexo");
                nickname = rs.getString("nickname");
                online = rs.getBoolean("online");

                if (email.equals(user_email)) {
                    conn.close();
                    return new User(email, null, null, null, null,description, sexo, nickname, online, null, null);
                }
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(email,pass,cpf,matricula,data_nascimento,description,sexo,nickname,online,created_at) VALUES (?,?,?,?,?,?,?,?,?,DATETIME('now'))";

        try {
            Connection conn = DriverManager.getConnection(DB_URL + DB_USERS);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getCpf());
            pstmt.setString(4, user.getMatricula());
            pstmt.setString(5, user.getDataNascimento());
            pstmt.setString(6, user.getDescription());
            pstmt.setInt(7, user.getSexo());
            pstmt.setString(8, user.getNickname());
            pstmt.setBoolean(9, user.isOnline());
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
    
    public void saveMessage(Message message)
    {
        File file = null;
        
        if(message.getOrigin().getEmail().compareTo(message.getDestination().getEmail()) < 0)
        {
            file = new File(TALKS_URL + message.getOrigin().getEmail() + "-" + message.getDestination().getEmail() + ".txt");
        }else file = new File(TALKS_URL + message.getDestination().getEmail() + "-" + message.getOrigin().getEmail() + ".txt");
        
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Falha ao criar arquivo: " + ex.getMessage());
            }
        }
        
        FileWriter fw;
        BufferedWriter bw;
        String str = message.getOrigin().getEmail() + "\t" + message.getDestination().getEmail() + "\t" + message.getMsg() + "\t" + message.getMsg_type() + "\t" + message.getTimestamp().toString() + "\t" + message.getUTC() + "\n";
        
        try {
            fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Falha ao salvar no arquivo: " + ex.getMessage());
        }
    }
    
    public LinkedList<Message> loadMessages(User user)
    {
        LinkedList<Message> messages = new LinkedList<Message>();
        File file = new File(TALKS_URL);
        File current = null;
        File []listFiles = file.listFiles();
        FileReader fr;
        BufferedReader br;
        String line;
        String []splited;
        Message message;
        
        for(int i = 0; i < listFiles.length; i++)
        {
            if(listFiles[i].getName().contains(user.getEmail()))
            {
               current = new File(listFiles[i].getName());
                try {
                    fr = new FileReader(TALKS_URL + current);
                    br = new BufferedReader(fr);
                    line = br.readLine();
                    while(line != null)
                    {
                        System.out.println(line);
                        splited = line.split("\t");
                        message = new Message();
                        
                        message.setOrigin(new User());
                        message.getOrigin().setEmail(splited[0]);
                        
                        message.setDestination(new User());
                        message.getDestination().setEmail(splited[1]);
                        
                        message.setMsg(splited[2]);
                        message.setMsg_type(Integer.parseInt(splited[3]));
                        message.setTimestamp(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US).parse(splited[4]));
                        message.setUTC(Integer.parseInt(splited[5]));
                        
                        messages.add(message);
                        line = br.readLine();
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("FileNotFoundException: " + ex.getMessage());
                } catch (IOException ex) {
                   System.out.println("IOException: " + ex.getMessage());
                } catch (ParseException ex) {
                    System.out.println("ParseException: " + ex.getMessage());
                }
               
            }
        }
        
        return messages;
    }
}
