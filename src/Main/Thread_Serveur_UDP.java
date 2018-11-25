/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author mamad
 */
public class Thread_Serveur_UDP  extends Thread{
    private  ListeAuth list;
    
     public Thread_Serveur_UDP(ListeAuth list_tmp) {
        this.list = list_tmp;
    }
    public void run(){
        try {
            DatagramSocket socket = new DatagramSocket(28414);
		
		// tampon pour recevoir les données des datagrammes UDP
		final byte[] tampon = new byte[1024];
		
		// objet Java permettant de recevoir un datagramme UDP
		DatagramPacket dgram = new DatagramPacket(tampon, tampon.length);	
                
               // ListeAuth list= new ListeAuth();
                //ServerSocket socketserver = new ServerSocket(28414);

		while(true) {
			// attente et réception d'un datagramme UDP
			socket.receive(dgram);			
			// extraction des données
			String chaine = new String(dgram.getData(), 0, dgram.getLength());
			
			//System.out.println("Chaine reçue : "+chaine);
                        String[] splitArray = null; //tableau de chaînes
                        splitArray = chaine.split(" ");
                        System.out.println("Type de requetes : "+splitArray[0]+" login :"+splitArray[1]+" password : "+splitArray[2] );
                        if(splitArray[0].equals("CHK")){
                            if(list.tester(splitArray[1], splitArray[2])==true){
                                System.out.println(" GOOD !!!");
                                dgram.setData("GOOD !!".getBytes());

                            }
                            else{
                                System.out.println(" BAD !!!");
                                dgram.setData("BAD !!!".getBytes());

                                
                            }
                        }
                        else if (splitArray[0].equals("ADD")){
                            if(list.creer(splitArray[1], splitArray[2])==true){
                                System.out.println(" DONE !!!");
                                dgram.setData("DONE !!!".getBytes());

                            }
                            else{
                                System.out.println("ERROR ");
                                dgram.setData("ERROR !!!".getBytes());

                            }
                            
                        }
                        else if (splitArray[0].equals("MOD")){
                            if(list.mettreAJour(splitArray[1], splitArray[2])==true){
                                 dgram.setData("DONE !!!".getBytes());
                                System.out.println(" DONE !!!");
                            }
                            else{
                                dgram.setData(" ERROR ".getBytes());
                                System.out.println("ERROR ");
                            }
                            
                        }
                        else if (splitArray[0].equals("DEL")){
                            if(list.supprimer(splitArray[1], splitArray[2])==true){
                                //System.out.println(" DONE !!!");
                                dgram.setData("DONE".getBytes());
                            }
                            else{
                                dgram.setData("DONE".getBytes());

                                System.out.println("ERROR ");
                            }
                            
                        }
                        
                         
                                
			// on renvoie le message au client
			socket.send(dgram);
			
			// on replace la taille du tampon au max
			// elle a été modifiée lors de la réception
                        dgram.setData(tampon);
			dgram.setLength(tampon.length);
		}

            
        } catch (Exception e) {
            System.out.println(" Error Exeption UPD Serveur"+e.getMessage());
            e.printStackTrace();
            
        }
        
    }
    public static void main(String []args){       
    
    Thread_Serveur_UDP thread_udp= new Thread_Serveur_UDP(new ListeAuth());
    //MonThreadTCP thread_tcp= new MonThreadTCP(new ListeAuth());
    thread_udp.start();    
    
    }
}
