package com.example.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;



@RestController
public class AppartementService {

	AppartementRepository aptRep;

	public AppartementService(AppartementRepository aptRep) {
		this.aptRep=aptRep;
		//aptRep.save(new Appartement());
		//aptRep.save(new Appartement("testAdr",2,2,2.5,3,null,null,null));
	}
	
	//Afficher la liste des appartements
	
	@RequestMapping (value = "/getApts", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	public List <Appartement> getListeAppartement(){
		return aptRep.findAll();
	}
	
// Recuperer un Appartement dont l'id est connu
	
	@RequestMapping (value = "/getApt/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public Appartement Appartement(@PathVariable ("id_Appartement") int id_Appartement) {
		
        for (Appartement app : aptRep.findAll()) {
            if (app.getId_Appartement()==id_Appartement) {
                return app;
            }
        }

        System.out.println("L'appartement n'existe pas");
        return null;
    }
	
	
	//Ajouter un appartement dans la liste
	
		@PostMapping("/addApt")
		
		public void addAppartement(@RequestBody Appartement appartement) {
			
			aptRep.save(appartement);
		}
		
		//Supprimer un appartement de la liste

		@RequestMapping(value = "/delApt/{id_Appartement}", method = RequestMethod.DELETE)
		@ResponseStatus(HttpStatus.OK)
		public void supprimerAppartement(@PathVariable("id_Appartement") int id_Appartement) throws Exception{

			for (Appartement app :aptRep.findAll()) {
				if(app.getId_Appartement()==id_Appartement) {
					
					aptRep.deleteById(app.getId_Appartement());
				}
			}
		}
	
}

