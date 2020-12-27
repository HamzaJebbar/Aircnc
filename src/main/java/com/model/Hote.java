package airBnb;

import java.util.ArrayList;
import java.util.List;

public class Hote extends Voyageur{
	
	private List<Appartement> appartement= new ArrayList <Appartement>();
	

	
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
		appartement.add(new Appartement("chiraton",24,2,1,70.50,1));
	
	}
	
	
	
	
	
	//Afficher la liste des appartements
		public List<Appartement> listDesChambres() {
		        return appartement;
		    }
		
	
	//Vérifier si la chambre est réservé ou non 
		
	public boolean estRéservée(Appartement app) {
		if(appartement.contains(app)) {
			return true;
		
	}
			return false;
	
	}
	
	public String toString (){
        return "Hote[id:" + getId()+ ",nom:" + getNom() + ",prenom:"+ getPrenom() + ",age:" + getAge() + ",sexe:" +getSexe() +"] " ;
    }
}

	
