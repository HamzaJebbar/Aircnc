package com.model;

import java.util.ArrayList;
import java.util.List;

public class Hote extends Voyageur{

	private List<Appartement> appartements= new ArrayList<>();

	public Hote() {
		super();
	
	}

	public Hote(String id, String nom, String prenom, int age, String sexe, List<Appartement> appartement) {
		super(id, nom, prenom, age, sexe);
		this.appartements = appartement;
	}

	public List<Appartement> getAppartement() {
		return appartements;
	}

	public void setAppartement(List<Appartement> appartement) {
		this.appartements = appartement;
	}

	public void addApt(Appartement appartement){
		appartements.add(appartement);
	}

	public String toString (){
        return "Hote[id:" + getId()+ ",nom:" + getNom() + ",prenom:"+ getPrenom() + ",age:" + getAge() + ",sexe:" +getSexe() +"] " ;
    }
}

	
