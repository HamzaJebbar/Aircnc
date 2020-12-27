package airBnb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LouerAppartement {
	
	private int id_voyageur;
	private int id_appartement;
	private Date dateEntree;
	private int duree;
	private List<Appartement> appartements_loues= new ArrayList<Appartement>();
	private List<Voyageur> voyageur= new ArrayList<Voyageur>();

	
	public LouerAppartement(int id_v ,int id_app, Date dateEntree,  Date dateSortie) {
		this.id_voyageur=id_v;
		this.id_appartement=id_app;
		this.dateEntree=dateEntree;
		this.duree=duree;
		
	}
	
	//getters
	public int getId_voyageur() {
		return id_voyageur;
	}
	
	public int getId_appartement() {
		return id_appartement;
	}
	
	public Date getDate() {
		return dateEntree;
	}
	
	public int getDuree() {
		return duree;
	}
	
	//setters
	
	public void setId_voy(int id_v) {
		this.id_voyageur=id_v;
	}  
	
	public void setId_app(int id_app) {
		this.id_appartement=id_app;
	}
	
	public void setDate(Date dateEntree) {
		this.dateEntree=dateEntree;
	}
	
	public void duree(int duree) {
		this.duree=duree;
	}
	
	public void louerApp(Appartement app,int nbr_max) {
		if (app.isReserve()==false && app.getnbrPersonne_max()<= nbr_max)
			{
				
				appartements_loues.add(app);
				app.setReserve(true);
				
			}else if (app.isReserve()) {
				
				System.out.println("l'appartement est déja loué, veuillez choisir une autre date");
				
			}else if(app.getnbrPersonne_max()<= nbr_max) {
				System.out.println("Le nombre de personnes dépasse le nombre maximmum de l'appartement");
			}
		}
			
	
	
	
	public double prixSejour(Appartement app, int duree) {
		return app.getPrix_nuit()*duree;
	}
	
	public void retournerApp(Appartement app) {
		if(app.isReserve()) {
			appartements_loues.remove(app);
			app.setReserve(false);
		}
	}
	
}
