package com.model;

import java.util.ArrayList;
import java.util.List;


public class Voyageur {

	private String id;
	private String nom;
	private String prenom;
	private int age;
	private int etage;
	private String sexe;
	
	private List<Appartement> appartement_fav= new ArrayList<Appartement>();
	
	//Constructeur par defaut
	public Voyageur() {
		this.id="1";
		this.nom="";
		this.prenom="";
		this.age=23;
		this.sexe="femme";
		
	}
	//Construction d'initialisation
	
	public Voyageur(String id,String nom,String prenom,int age,String sexe) {
		
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
		this.sexe=sexe;
	}
	
	//Afficher les chambre favorites des voyageurs
	
	public List<Appartement> chambre_fav(){
		return appartement_fav;
		
	}
	
	//ajouter une chambre favorite a la liste du voyageur
	
	public  List<Appartement>  addChambre(Appartement appartement){
		appartement_fav.add(appartement);
		return appartement_fav;
	}
	
	
	//Les getters
	
	public String getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		
		return this.prenom;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getSexe() {
		return this.sexe;
	}
	
	public int getEtage() {
		return this.etage;
	}
	
	// Les setters
	
	public void setId(String id) {
		this.id=id;
	}
	
	public void setNom(String nom) {
		this.nom= nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	
	public void setId(int age) {
		this.age=age;
	}
	
	public void setSexe(String sexe) {
		this.sexe=sexe;
	}
	
	public void setEtage(int etage) {
		this.etage=etage;
	}
	
	//Ajouter un appartement favorite
	
	public void AppartementFav(Appartement app) {
		appartement_fav.add(app);
	}
	
	
	//Affichage
	
	@Override
    public String toString (){
        return "Voyageur[id:" + getId() + ",nom:" + nom+ ",prenom:"+ prenom + ",age:" + age + ",sexe:" + sexe +"] " ;
    }
	
	
	
	
}
