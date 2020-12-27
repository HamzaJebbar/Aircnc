package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


public class Voyageur {

	private String id_voyageur;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private List<Appartement> appartement_fav= new ArrayList<Appartement>();
	
	//Constructeur par defaut
	public Voyageur() {
		this.id_voyageur="1";
		this.nom="";
		this.prenom="";
		this.age=23;
		this.sexe="femme";
		
	}
	//Construction d'initialisation
	
	public Voyageur(String id,String nom,String prenom,int age,String sexe) {
		
		this.id_voyageur=id;
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
		this.sexe=sexe;
	}
	
	//Afficher les chambre favorites des voyageurs
	
	@ManyToMany
	@JoinTable(name="LouerAppartement", 
	joinColumns= {@JoinColumn(name="id_Voyageur)")},inverseJoinColumns= {@JoinColumn(name="id_Appartement")} )
	public List<Appartement> chambre_fav(){
		return appartement_fav;
		
	}
	
	//ajouter une chambre favorite a la liste du voyageur
	
	public  List<Appartement>  addChambre(Appartement appartement){
		appartement_fav.add(appartement);
		return appartement_fav;
	}
	
	
	//Les getters
	
	
	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	public String getId_voy() {
		return this.id_voyageur;
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
	
	
	// Les setters
	
	public void setId_voy(String id) {
		this.id_voyageur=id;
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
	

	//Ajouter un appartement favorite
	
	public void AppartementFav(Appartement app) {
		appartement_fav.add(app);
	}
	
	
	//Affichage
	
	@Override
    public String toString (){
        return "Voyageur[id:" + getId_voy() + ",nom:" + nom+ ",prenom:"+ prenom + ",age:" + age + ",sexe:" + sexe +"] " ;
    }
	
	
	
	
}
