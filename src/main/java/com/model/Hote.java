package com.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hote extends Voyageur{

	@OneToMany(cascade= CascadeType.ALL, mappedBy="hote")
	private List<Appartement> appartement= new ArrayList <Appartement>();

	// Constructeur par defaut 
	public Hote() {
		super();
	}

	public Hote(String nom, String prenom, int age, String sexe, List<Appartement> appartement_fav,
				List<Appartement> appartement_loue, List<Appartement> appartement) {
		super(nom, prenom, age, sexe, appartement_fav, appartement_loue);
		this.appartement = appartement;
	}

	public List<Appartement> getAppartement() {
		return appartement;
	}

	public void setAppartement(List<Appartement> appartement) {
		this.appartement = appartement;
	}

	public  List<Appartement>  addApt(Appartement appartement){
		this.appartement.add(appartement);
		return this.appartement;
	}
	public String toString (){
        return "Hote[id:" + getId_voy()+ ",nom:" + getNom() + ",prenom:"+ getPrenom() + ",age:" + getAge() + ",sexe:" +getSexe() +"] " ;
    }
}

	
