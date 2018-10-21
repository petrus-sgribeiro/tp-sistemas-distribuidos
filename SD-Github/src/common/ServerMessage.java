/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import common.Message;
import common.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;
import server.LogInfo;
import server.dao.ChatDatabase;

/**
 *
 * @author fagun
 */
public class ServerMessage extends UnicastRemoteObject implements ServerMessageInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_ERROR_USER_ONLINE = 2;
    public static final int LOGIN_ERROR_WRONG = 3;

    public static final int REGISTER_EXISTS_EMAIL = 1;
    public static final int REGISTER_EXISTS_CPF = 2;
    public static final int REGISTER_EXISTS_NICKNAME = 3;
    public static final int REGISTER_EXISTS_MATRICULA = 4;

    public static final int REGISTER_RELATION_EXISTS = 5;
    public static final int REGISTER_FRIEND_ADD_SUCCESSFULLY = 6;

    public static final int PORT = 1099;
    private LinkedList<Message> messages;
    private ChatDatabase chatDatabase;

    public ServerMessage() throws RemoteException {
        super();
        chatDatabase = new ChatDatabase();
        this.messages = new LinkedList<Message>();
    }

    @Override
    public synchronized int registerUser(User user) throws RemoteException {

        LinkedList<User> users = chatDatabase.loadUsers();

        for (User u : users) {
            if (u.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())) {
                return REGISTER_EXISTS_EMAIL;
            } else if (u.getCpf().toLowerCase().equals(user.getCpf().toLowerCase())) {
                return REGISTER_EXISTS_CPF;
            } else if (u.getMatricula().toLowerCase().equals(user.getMatricula().toLowerCase())) {
                return REGISTER_EXISTS_MATRICULA;
            } else if (u.getNickname().toLowerCase().equals(user.getNickname().toLowerCase())) {
                return REGISTER_EXISTS_NICKNAME;
            }
        }

        Date date = new Date();

        if (chatDatabase.insertUser(user)) {
            System.out.println("Usuario '" + user.getEmail() + "' cadastrado com sucesso!");
            LogInfo.add("Usuario '" + user.getEmail() + "' cadastrado com sucesso!");
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public synchronized int sendMessage(Message msg) throws RemoteException {
        System.out.println(msg.toString());
        return (messages.add(msg)) ? 1 : 0;
    }

    @Override
    public synchronized Message receiveMessage(User origin) throws RemoteException {

        for (Message m : messages) {

            if (m.getDestination().getEmail().equals(origin.getEmail())) {
                messages.remove(m);
                return m;
            }
        }

        return null;
    }

    @Override
    public synchronized int login(String email, String password) throws RemoteException {
        User tmp = chatDatabase.getUser(email, password);

        if (tmp != null) {
            if (tmp.isOnline() && tmp.getPassword().equals(password)) {
                LogInfo.add("ERRO - Tentativa de login em: '" + email + "'. MOTIVO: Usuario já está ONLINE!");
                System.out.println("ERRO - Tentativa de login em: '" + email + "'. MOTIVO: Usuario já está ONLINE!");
                return LOGIN_ERROR_USER_ONLINE;
            } else if (tmp.getPassword().equals(password)) {
                tmp.setOnline(true);
                chatDatabase.updateUser(tmp);
                System.out.println("O usuario '" + email + "' está ONLINE!");
                LogInfo.add("O usuario '" + email + "' está ONLINE!");
                return LOGIN_SUCCESS;
            }
        }
        LogInfo.add("ERRO - Tentativa de login em: '" + email + "'. MOTIVO: Usuário ou Senha incorretos!");
        System.out.println("ERRO - Tentativa de login em: '" + email + "'. MOTIVO: Usuário ou Senha incorretos!");
        return LOGIN_ERROR_WRONG;
    }

    @Override
    public synchronized int logout(String email, String password) throws RemoteException {
        User tmp = chatDatabase.getUser(email, password);

        if (tmp != null) {
            if (tmp.isOnline()) {
                tmp.setOnline(false);
                chatDatabase.updateUser(tmp);
                LogInfo.add("O usuario '" + email + "' está OFFLINE!");
                System.out.println("O usuario '" + email + "' está OFFLINE!");
                return 1;
            }
        }

        return 0;
    }

    @Override
    public synchronized int isConnected(String email) throws RemoteException {
        User tmp = chatDatabase.getUser(email);

        if (tmp != null) {
            if (tmp.isOnline()) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public synchronized int disconnectAll() throws RemoteException {
        LogInfo.add("Todos usuários foram desconectados do servidor!");
        System.out.println("Todos usuários foram desconectados do servidor!");

        LinkedList<User> users = chatDatabase.loadUsers();
        for (User user : users) {
            user.setOnline(false);
            chatDatabase.updateUser(user);
        }
        return 1;
    }

    @Override
    public synchronized User searchUser(String email) throws RemoteException{

        User tmp = chatDatabase.getUser(email);

        if (tmp != null) {
            return tmp;
        }

        return null;
    }

    @Override
    public synchronized int registerRelation(Relation relation) {
        LinkedList<Relation> relations = chatDatabase.loadRelations();

        for (Relation r : relations) {
            if (r.getEmail_user().equals(relation.getEmail_user()) && r.getEmail_friend().equals(relation.getEmail_friend())) {
                return REGISTER_RELATION_EXISTS;
            }
        }

        if (chatDatabase.insertRelation(relation)) {
            System.out.println("Foi criada uma amizade entre '"+relation.getEmail_user()+"' e '" + relation.getEmail_friend() + "'.");
            LogInfo.add("Foi criada uma amizade entre '"+relation.getEmail_user()+"' e '" + relation.getEmail_friend() + "'.");
            return REGISTER_FRIEND_ADD_SUCCESSFULLY;
        } else {
            return 0;
        }
    }

    @Override
    public synchronized LinkedList<Relation> getAllFriendships(String email, String pass) throws RemoteException
    {
       User user =  chatDatabase.getUser(email, pass);
       if(user == null) return null;
       else
       {
           LinkedList<Relation> relations = chatDatabase.loadRelations();
           LinkedList<Relation> friendships = new LinkedList<Relation>();
           
           for(Relation r : relations)
           {
               if(r.getEmail_user().equals(email))
               {
                   friendships.add(new Relation(email,r.getEmail_friend()));
               }
           }
           
           return friendships;
       }
    }

    @Override
    public User getUser(String login, String password) throws RemoteException {
        return chatDatabase.getUser(login, password);
    }

    @Override
    public User getUser(String login) throws RemoteException {
        return chatDatabase.getUser(login);
    }
    
    

}
