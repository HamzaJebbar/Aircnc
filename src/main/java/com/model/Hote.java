package com.model;

import java.util.ArrayList;
import java.util.List;

public class Hote extends Voyageur{
	
	private List<Appartement> appartement= new ArrayList <Appartement>();
	

	// Constructeur par defaut 
	public Hote() {
		super();
	
	}
	
	
	public Hote(String id,String nom,String prenom,int age,String sexe) {
		
		super(id,nom,prenom,age,sexe);
		
		if(appartement.size()!=0 ) {
			System.out.println("Afficher les appartements de cet hote");
			//System.out.println(appartement);
		}
		appartement.add(new Appartement());
		appartement.add(new Appartement(1,"chiraton",24,2,1,70.50,1));
	
	}
	
	
	
	
	
	//Afficher la liste des appartements
		public List<Appartement> listDesChambres() {
		        return appartement;
		    }
		
	
	//Verifier si la chambre est reserve ou non
		
	public boolean estReservee(Appartement app) {
		if(appartement.contains(app)) {
			return true;
		
	}
			return false;
	
	}
	
	public String toString (){
        return "Hote[id:" + getId_voy()+ ",nom:" + getNom() + ",prenom:"+ getPrenom() + ",age:" + getAge() + ",sexe:" +getSexe() +"] " ;
    }
}

	
