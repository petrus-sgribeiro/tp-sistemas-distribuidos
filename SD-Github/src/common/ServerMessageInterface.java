/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import common.Message;
import common.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author fagun
 */
public interface ServerMessageInterface extends Remote{
    public abstract int registerUser(User user) throws RemoteException;
    public abstract int sendMessage(Message msg) throws RemoteException;
    public abstract Message receiveMessage(User origin) throws RemoteException;
    public abstract int login(String email, String password) throws RemoteException;
    public abstract int logout(String email, String password) throws RemoteException;
    public abstract int isConnected(String email) throws RemoteException;
    public abstract int disconnectAll() throws RemoteException;
    public abstract User searchUsers(String email) throws RemoteException;
    public abstract int registerRelation (Relation relation) throws RemoteException;
}
