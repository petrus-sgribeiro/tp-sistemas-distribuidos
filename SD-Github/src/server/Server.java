package server;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.ServerMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fagun
 */
public class Server {
    
    public Server(){
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException
    {
        Registry r = LocateRegistry.createRegistry(ServerMessage.PORT);
        r.rebind("ServerMessage",new ServerMessage());
        System.out.println("Server is running...");
    }
}
