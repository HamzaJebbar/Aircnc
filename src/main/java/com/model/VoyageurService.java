package com.model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;


@RestController
public class VoyageurService {

	private List<Voyageur> voyageurs= new ArrayList<Voyageur> ();
	
	
	public VoyageurService() {
		voyageurs.add(new Voyageur("LAMRI","Celia",23,"femme",null,null));
		
	}
	
    //Afficher la liste des voyageurs
	
@RequestMapping (value = "/voyageurs", method = RequestMethod.GET)
@ResponseStatus (HttpStatus.OK)
@ResponseBody

	public List <Voyageur> getListVoyageurs(){
		return voyageurs;
		
	    	
	}
	
	
	// Recuperer un Voyageur dont l'id est connu
	
@RequestMapping (value = "/voyageurss", method = RequestMethod.GET)
@ResponseStatus (HttpStatus.OK)
@ResponseBody
	
	   public Voyageur Voyageur(@PathVariable ("id_Voyageur") int id_Voyageur) {
				
	        for (Voyageur voy : voyageurs) {
	            if (voy.getId_voy()==id_Voyageur ){
	                return voy;
	            }
	        }

	        System.out.println("Le voyageur n'existe pas");
	        return null;
	    }
		
	//Ajouter un voyageur dans la liste
		
@PostMapping("/voyageursss")
			
		public void addVoyageur(@RequestBody Voyageur voyageur) {
				
		    System.out.println(voyageur);
			voyageurs.add(voyageur);
				
			}  
			
//Supprimer un voyageur de la liste
			
		@RequestMapping(value = "/voyageurs/{id_Voyageur}", method = RequestMethod.DELETE)
		@ResponseStatus(HttpStatus.OK)
		   
		public void supprimerVoyageur(@PathVariable("id_Voyageur") int id_Voyageur) throws Exception{
				
				for (Voyageur voy:voyageurs) {	
					
					if(voy.getId_voy() == id_Voyageur) {
						
						voyageurs.remove(voy);
					}
					
					System.out.println("le voyageur n'existe pas !");
					
				}
			}
			
	
		
		
	
}
