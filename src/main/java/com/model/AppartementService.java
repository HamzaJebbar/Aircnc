package com.model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;



@RestController
public class AppartementService {
	
	private List<Appartement> appartements= new ArrayList<Appartement> ();
	
	
	public AppartementService() {
		
		appartements.add(new Appartement());

	}
	
	//Afficher la liste des appartements
	
	@RequestMapping (value = "/appartements", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	@ResponseBody
	public List <Appartement> getListeAppartement(){
		return appartements;
		
	    	
	}
	
	
	// Recuperer un Appartement dont l'id est connu
	
	@RequestMapping (value = "/appartements", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public Appartement Appartement(@PathVariable ("id_Appartement") int id_Appartement) {
		
        for (Appartement app : appartements) {
            if (app.getId_Appartement()==id_Appartement) {
                return app;
            }
        }

        System.out.println("L'appartement n'existe pas");
        return null;
    }
	
	
	
	
	//Ajouter un apparetement dans la liste
	
	@PostMapping("/appartements")
	
	public void addAppartement(@RequestBody Appartement appartement) {
		
		System.out.println(appartement);
		appartements.add(appartement);
		
	}
	
	//Supprimer un appartement de la liste
	
	@RequestMapping(value = "/appartements/{id_Appartement}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void supprimerAppartement(@PathVariable("id_Appartement") int id_Appartement) throws Exception{
		
		for (Appartement app :appartements) {
			if(app.getId_Appartement()==id_Appartement) {
				
				appartements.remove(app);
			}
			
			System.out.println("l'appartement n'existe pas !");
			
		
		}
	}
	
	
	//Louer un appartement s'il est disponnible
	
	@RequestMapping (value = "/apparetement/{id_Appartement}", method = RequestMethod.PUT)
    @ResponseStatus (HttpStatus.OK)
	
	public void LouerAppartement(@PathVariable ("id_Appartement") int id_Appartement,
            @RequestParam (value = "reserve", required = true) boolean reserve) throws Exception {
		
		for(Appartement app : appartements) {
			if (app.getId_Appartement()==id_Appartement) {
				if(app.isReserve()==false) {
					app.setReserve(true);
				}else {
					System.out.println("L'appartement est deja reserve");
				}
			}else {
				System.out.println("L'appartement n'existe pas");
			}
		}
	}
	
	
	
	//Rendre l'appartement s'il est deja loue
	
    

	@RequestMapping (value = "/apparetement/{id_Appartement}", method = RequestMethod.PUT)
    @ResponseStatus (HttpStatus.OK)
	
	public void RendreAppartement(@PathVariable ("id_Appartement") int id_Appartement,
            @RequestParam (value = "reserve", required = true) boolean reserve) throws Exception {
		
		for(Appartement app : appartements) {
			if (app.getId_Appartement()==id_Appartement) {
				if(app.isReserve()==true) {
					app.setReserve(false);
				}else {
					System.out.println("L'appartement est libre");
				}
			}else {
				System.out.println("L'appartement n'existe pas");
			}
		}
	}
   
	
	
}

