package com.model;

import java.util.ArrayList;
import java.util.List;


public class Voyageur {

	private String id;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	
	private List<Appartement> appartement_fav= new ArrayList<>();
	
	//Constructeur par defaut
	public Voyageur() {
	}

	//Construction d'initialisation
	public Voyageur(String id,String nom,String prenom,int age,String sexe) {
		
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
		this.sexe=sexe;
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public List<Appartement> getAppartement_fav() {
		return appartement_fav;
	}

	public void setAppartement_fav(List<Appartement> appartement_fav) {
		this.appartement_fav = appartement_fav;
	}

//ajouter une chambre favorite a la liste du voyageur
	
	public void addAptFav(Appartement appartement){
		appartement_fav.add(appartement);
	}
	
	

	

	//Ajouter un appartement favorit
	
	public void AppartementFav(Appartement app) {
		appartement_fav.add(app);
	}
	
	
	//Affichage
	
	@Override
    public String toString (){
        return "Voyageur[id:" + getId() + ",nom:" + nom+ ",prenom:"+ prenom + ",age:" + age + ",sexe:" + sexe +"] " ;
    }
	
	
	
	
}
