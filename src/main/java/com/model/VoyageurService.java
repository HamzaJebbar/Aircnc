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

	VoyageurRepository voyageurRep;
	
	public VoyageurService(VoyageurRepository voyageurRep) {
				this.voyageurRep = voyageurRep;
	}
	
    //Afficher la liste des voyageurs
	
	@RequestMapping (value = "/getVoys", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	@ResponseBody

	public List <Voyageur> getListVoyageurs(){
		return voyageurRep.findAll();
	}
	
	
	// Recuperer un Voyageur dont l'id est connu
	
	@RequestMapping (value = "/getVoy/{id_Voyageur}", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	@ResponseBody
	
	public Voyageur Voyageur(@PathVariable ("id_Voyageur") int id_Voyageur) {
		for (Voyageur voy : voyageurRep.findAll()) {
			if (voy.getId_voy()==id_Voyageur ){
				return voy;
			}
		}

		System.out.println("Le voyageur n'existe pas");
		return null;
	}
		
	//Ajouter un voyageur dans la liste
		
	@PostMapping("/addVoy")
			
	public void addVoyageur(@RequestBody Voyageur voyageur) {

		voyageurRep.save(voyageur);

		}
			
	//Supprimer un voyageur de la liste
			
	@RequestMapping(value = "/delVoy/{id_Voyageur}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)

	public void supprimerVoyageur(@PathVariable("id_Voyageur") int id_Voyageur) throws Exception{

		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {

				voyageurRep.deleteById(voy.getId_voy());
			}

			System.out.println("le voyageur n'existe pas !");

		}
	}
			
	
		
		
	
}
