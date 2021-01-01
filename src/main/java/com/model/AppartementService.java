package com.model;

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

	@Autowired
	public AppartementService(AppartementRepository aptRep) {
		this.aptRep=aptRep;
		aptRep.save(new Appartement());
		aptRep.save(new Appartement("testAdr",2,2,2.5,3,null,null,null));
	}
	
	//Afficher la liste des appartements
	
	@RequestMapping (value = "/getApts", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	@ResponseBody
	public List <Appartement> getListeAppartement(){
		return aptRep.findAll();
	}
	
// Recuperer un Appartement dont l'id est connu
	
	@RequestMapping (value = "/getApt/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    @ResponseBody
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
			
			System.out.println(appartement);
			aptRep.save(appartement);
			
		}
		
		//Supprimer un appartement de la liste

		@RequestMapping(value = "/delApt/{id_Appartement}", method = RequestMethod.DELETE)
		@ResponseStatus(HttpStatus.OK)
		@ResponseBody
		public void supprimerAppartement(@PathVariable("id_Appartement") int id_Appartement) throws Exception{

			for (Appartement app :aptRep.findAll()) {
				if(app.getId_Appartement()==id_Appartement) {
					
					aptRep.deleteById(app.getId_Appartement());
				} else {

					System.out.println("l'appartement n'existe pas !");
				}
			
			}
		}

		//Louer un appartement s'il est disponnible
		
		@RequestMapping (value = "/rentApt/{id_Appartement}", method = RequestMethod.PUT)
	    @ResponseStatus (HttpStatus.OK)
		
		public void LouerAppartement(@PathVariable ("id_Appartement") int id_Appartement) throws Exception {
			
			for(Appartement app : aptRep.findAll()) {
				if (app.getId_Appartement()==id_Appartement) {
					if(app.isReserve()==false) {
						app.setReserve(true);
						aptRep.save(app);
					}else {
						System.out.println("L'appartement est deja reserve");
					}
				}else {
					System.out.println("L'appartement n'existe pas");
				}
			}
		}
		
		
		//Rendre l'appartement s'il est deja loue
		
	    

		@RequestMapping (value = "/rendreApt/{id_Appartement}", method = RequestMethod.PUT)
	    @ResponseStatus (HttpStatus.OK)

		public void RendreAppartement(@PathVariable ("id_Appartement") int id_Appartement)
	           throws Exception {
			
			for(Appartement app : aptRep.findAll()) {
				if (app.getId_Appartement()==id_Appartement) {
					if(app.isReserve()==true) {
						app.setReserve(false);
						aptRep.save(app);
					}else {
						System.out.println("L'appartement est libre");
					}
				}else {
					System.out.println("L'appartement n'existe pas");
				}
			}
		}
		
		
		
	
	
}

