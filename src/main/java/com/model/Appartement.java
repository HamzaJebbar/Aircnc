package com.model;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Appartement {
	
	private int id_Appartement;
	private String adresse;
	private int nbr_chambres;
	private int nbr_salle_bains;
	private double prix_nuit;
	private boolean reserve;
	private int nbrPersonne_max;
	
	
	
	
	private List<Voyageur> voyageurs= new ArrayList<Voyageur>();
	
	//Constructeur par defaut
	public Appartement() {
		
		this.adresse="";
		this.id_Appartement=1;
		this.nbr_chambres=1;
		this.nbr_salle_bains=1;
		this.prix_nuit=40.00;
		this.nbrPersonne_max=1;
		this.reserve=false;
		
	}
	
	public Appartement(int id, String adresse, int numero, int nbr_chambres, int nbr_salle_bains, double prix_nuit, int nbrPersonne_max) {
		
		this.adresse=adresse;
		this.nbr_chambres=nbr_chambres;
		this.nbr_salle_bains=nbr_salle_bains;
		this.prix_nuit=prix_nuit;
		this.nbrPersonne_max=nbrPersonne_max;
		this.reserve=false;
		this.id_Appartement=id;
		
	}

	//getters
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId_App() {
		return this.id_Appartement;
	}
	
	public String getAdresse() {
		return this.adresse;
	}
	
	public int getnbrPersonne_max() {
		return this.nbrPersonne_max;
	}
	
	
	public int nbr_chambres() {
		return this.nbr_chambres;
	}
	
	public int getNbr_salle_bains() {
		return this.nbr_salle_bains;
	}
	
	public double getPrix_nuit() {
		return this.prix_nuit;
	}
	
	public boolean isReserve() {
		return reserve;
	}
	
	
	//Etablir la relation ManyToMany
	@ManyToMany
	
	@JoinTable(name="LouerAppartement", 
	joinColumns= {@JoinColumn(name="id_Appartement)")},inverseJoinColumns= {@JoinColumn(name="id_voyageur")} )
	
	public List<Voyageur> getListVoyageur(){
		return voyageurs;
	}
	//Setters
	
	
	public void settAdresse(String adresse) {
		this.adresse=adresse;
	}
	
	public void setId_App(int id) {
		this.id_Appartement=id;
	}
	
	public void setnbrPersonne_max( int nbrPersonne_max) {
		this.nbrPersonne_max=nbrPersonne_max;
	}
	
	
	public void nbr_chambres(int nbr_chambres) {
		this.nbr_chambres=nbr_chambres;
	}
	
	public void getNbr_salle_bains(int nbr_salle_bains) {
		this.nbr_salle_bains=nbr_salle_bains;
	}
	
	public void getPrix_nuit(float prix_nuit) {
		this.prix_nuit=prix_nuit;
	}
	
	public void setReserve(boolean reserve) {
		this.reserve=reserve;
	}
    
	public void addVoyageur(Voyageur voy) {
		voyageurs.add(voy);
	}
	
	
	
}
