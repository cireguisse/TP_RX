/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mamad
 */
public class Thread_Serveur_TCP extends Thread {
    
    private ListeAuth list;
    static int nb_cli=100;
    public Thread_Serveur_TCP(ListeAuth list_temptcp){
        this.list=list_temptcp;
    }
    public void run(){
        try {
            ServerSocket ssg = new ServerSocket(28415);

            while (true) {
                // On attend une connexion puis on l'accepte
                Socket sss = ssg.accept();
                AS_TCP_Thread client_thread = new AS_TCP_Thread(list, sss);
                client_thread.start();
                
            }
        } catch (Exception e) {
            System.out.println(" Erreur Serveur TCP "+e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String []args){       
    
    Thread_Serveur_TCP thread_tcp= new Thread_Serveur_TCP(new ListeAuth());
    thread_tcp.start();
    
    }
}
