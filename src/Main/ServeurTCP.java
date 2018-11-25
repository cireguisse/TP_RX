/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author mamad
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {

    public static void main(String args[]) throws Exception {

        // Création d'un socket serveur générique sur le port 40000
        ServerSocket ssg = new ServerSocket(28415);

        while (true) {
            // On attend une connexion puis on l'accepte
            Socket sss = ssg.accept();

            // Construction d'un BufferedReader pour lire du texte envoyé à travers la connexion socket
            BufferedReader entreeSocket = new BufferedReader(new InputStreamReader(sss.getInputStream()));
            // Construction d'un PrintStream pour envoyer du texte à travers la connexion socket
            PrintStream sortieSocket = new PrintStream(sss.getOutputStream());

            String chaine = "";
            ListeAuth list = new ListeAuth();
            String  resultat;
            while (chaine != null) {
                // lecture d'une chaine envoyée à travers la connexion socket
                chaine = entreeSocket.readLine();
                //System.out.println("Chaine reçue : "+chaine);
                if(chaine==null){
                    break;
                }
                Metier_AS as_reponse= new Metier_AS(chaine, list);
                resultat=as_reponse.traitment(chaine);
                sortieSocket.println(resultat);
                
            }

            // on ferme nous aussi la connexion
            sss.close();
        }
    }
}
