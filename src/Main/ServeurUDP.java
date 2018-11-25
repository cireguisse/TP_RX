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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ServeurUDP {   
    

	public static void main(String[] args) throws Exception {
		// Création d'un socket UDP sur le port 28414
		DatagramSocket socket = new DatagramSocket(28414);
		
		// tampon pour recevoir les données des datagrammes UDP
		final byte[] tampon = new byte[1024];
		
		// objet Java permettant de recevoir un datagramme UDP
		DatagramPacket dgram = new DatagramPacket(tampon, tampon.length);	
                
                ListeAuth list= new ListeAuth();
                //ServerSocket socketserver = new ServerSocket(28414);
                String resultat;
		while(true) {
			// attente et réception d'un datagramme UDP
			socket.receive(dgram);			
			// extraction des données
			String chaine = new String(dgram.getData(), 0, dgram.getLength());
			/**
                         * traitement de l'authentification Serveur
                         */
                        Metier_AS as_reponse= new Metier_AS(chaine, list);
                        resultat=as_reponse.traitment(chaine);
                        // reponse de l'authentification 
                        dgram.setData(resultat.getBytes());                 
                                                         
			// on renvoie le message au client
			socket.send(dgram);
			
			// on replace la taille du tampon au max
			// elle a été modifiée lors de la réception
                        dgram.setData(tampon);
			dgram.setLength(tampon.length);
		}

	}

}