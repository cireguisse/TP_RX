 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author mamad
 */
public class AS_TCP_Thread extends Thread {
    private ListeAuth list;
    Socket sss;
    public AS_TCP_Thread(ListeAuth list,Socket sss){
        this.list=list;
        this.sss=sss;
    }
    public void run(){
         try {
             // Construction d'un BufferedReader pour lire du texte envoyé à travers la connexion socket
             BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(sss.getInputStream()));
             // Construction d'un PrintStream pour envoyer du texte à travers la connexion socket
             PrintStream sortieSocket = new PrintStream(sss.getOutputStream());
             String chaine = "";
             String resultat;
             while (chaine != null) {
                 // lecture d'une chaine envoyée à travers la connexion socket
                 chaine = entreeSocket.readLine();
                 //System.out.println("Chaine reçue : "+chaine);
                 if (chaine == null) {
                     break;
                 }
                Metier_AS as_reponse= new Metier_AS(chaine, list);
                resultat=as_reponse.traitment(chaine);
                sortieSocket.println(resultat);   
             }
             //fermeture de connexion 
             Thread_Serveur_TCP.nb_cli++;
             sss.close();

        } catch (Exception e) {
             System.out.println("Main.ThreadClient.run()"+e.getMessage());
             e.printStackTrace();
        }
    }
    
}
