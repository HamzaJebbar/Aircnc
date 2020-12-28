package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Voyageur {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id_Voyageur;
	private String nom;
	private String prenom;
	private int age;
	private String sexe;

	@ManyToMany
	@JoinTable(name="Appart_fav",
			joinColumns= {@JoinColumn(name="id_Voyageur)")},inverseJoinColumns= {@JoinColumn(name="id_Appartement")} )
	private List<Appartement> appartement_fav= new ArrayList<Appartement>();

	@OneToMany(cascade= CascadeType.ALL, mappedBy="voyageur")
	private List<Appartement> appartement_loue= new ArrayList<Appartement>();

	//Constructeur par defaut
	public Voyageur() {
		this.nom="";
		this.prenom="";
		this.age=23;
		this.sexe="femme";
		
	}
	//Construction d'initialisation
	
	public Voyageur(String nom,String prenom,int age,String sexe) {
		this.nom=nom;
		this.prenom=prenom;
		this.age=age;
		this.sexe=sexe;
	}

	//ajouter un appartement favorit a la liste du voyageur
	
	public  List<Appartement>  addAptFav(Appartement appartement){
		appartement_fav.add(appartement);
		return appartement_fav;
	}
	
	
	//Les getters
	public String getId_voy() {
		return this.id_Voyageur;
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


	public List<Appartement> getAppartement_fav() {
		return appartement_fav;
	}

	// Les setters
	
	public void setId_voy(String id) {
		this.id_Voyageur=id;
	}
	
	public void setNom(String nom) {
		this.nom= nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setSexe(String sexe) {
		this.sexe=sexe;
	}

	public void setAppartement_fav(List<Appartement> appartement_fav) {
		this.appartement_fav = appartement_fav;
	}

	public List<Appartement> getAppartement_loue() {
		return appartement_loue;
	}

	public void setAppartement_loue(List<Appartement> appartement_loue) {
		this.appartement_loue = appartement_loue;
	}

	//Affichage
	
	@Override
    public String toString (){
        return "Voyageur[id:" + getId_voy() + ",nom:" + nom+ ",prenom:"+ prenom + ",age:" + age + ",sexe:" + sexe +"] " ;
    }
	
	
	
	
}
