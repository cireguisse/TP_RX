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
public class Metier_AS {

    private String chaine;
    private ListeAuth list;

    public Metier_AS(String str, ListeAuth list) {
        this.chaine = str;
        this.list = list;
    }

    public String traitment(String chaine) {
        String reponse = " ";
        String[] splitArray = null; //tableau de chaînes
        if (!chaine.isEmpty()) {

            splitArray = chaine.split(" ");
            System.out.println("Type de requetes : " + splitArray[0] + " login :" + splitArray[1] + " password : " + splitArray[2]);
            if (splitArray[0].equals("CHK")) {
                if (list.tester(splitArray[1], splitArray[2]) == true) {
                    reponse = " GOOD !!!";
                } else {
                    reponse = " BAD !!!";

                }
            } else if (splitArray[0].equals("ADD")) {
                if (list.creer(splitArray[1], splitArray[2]) == true) {
                    reponse = " DONE !!!";
                } else {
                    reponse = "ERROR ";
                }

            } else if (splitArray[0].equals("MOD")) {
                if (list.mettreAJour(splitArray[1], splitArray[2]) == true) {
                    reponse = " DONE !!!";
                } else {
                    reponse = "ERROR, MOD  ";
                }

            } else if (splitArray[0].equals("DEL")) {
                if (list.supprimer(splitArray[1], splitArray[2]) == true) {
                    reponse = " DONE !!!";
                } else {

                    reponse = "ERROR, DEL  ";
                }

            }
        }
        else{
            reponse=" ERROR ";
        }

        return reponse;

    }

}
