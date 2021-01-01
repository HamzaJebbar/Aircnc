package com.model;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


@RestController
public class VoyageurService {

	VoyageurRepository voyageurRep;
	AppartementRepository aptRep;

	public VoyageurService(VoyageurRepository voyageurRep, AppartementRepository aptRep) {
				this.voyageurRep = voyageurRep;
				this.aptRep = aptRep;
				voyageurRep.save(new Voyageur());
				voyageurRep.save(new Voyageur("nn","pp",15,"homme",null,null));
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


	@RequestMapping(value = "/addAptLoue/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Voyageur addAptLoue(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Appartement apt = null;
		for (Appartement a: aptRep.findAll()){
			if(a.getId_Appartement() == id_Appartement)
				apt = a;
		}
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				voy.getAppartement_loue().add(apt);
				//apt.getVoyageurs().add(voy);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.put("http://localhost:8080/rentApt/"+id_Appartement,void.class);
				voyageurRep.save(voy);
				return voy;
			}

		}
		return null;
	}
	@RequestMapping(value = "/rmAptLoue/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Voyageur rmAptLoue(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				for(Appartement apt: voy.getAppartement_fav()){
					if(apt.getId_Appartement()==id_Appartement){
						voy.getAppartement_loue().remove(apt);
						RestTemplate restTemplate = new RestTemplate();
						restTemplate.put("http://localhost:8080/rendreApt/"+id_Appartement,void.class);
						voyageurRep.save(voy);
					}
				}
				return voy;
			}

		}
		return null;
	}
	@RequestMapping(value = "/addAptFav/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Voyageur addAptFav(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Appartement apt = null;
		for (Appartement a: aptRep.findAll()){
			if(a.getId_Appartement() == id_Appartement)
				apt = a;
		}
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				voy.getAppartement_fav().add(apt);
				//apt.getVoyageurs().add(voy);
				voyageurRep.save(voy);
				return voy;
			}

		}
		return null;
	}

	@RequestMapping(value = "/rmAptFav/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Voyageur rmAptFav(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				for(Appartement apt: voy.getAppartement_fav()){
					if(apt.getId_Appartement()==id_Appartement){
						voy.getAppartement_fav().remove(apt);
						voyageurRep.save(voy);
					}
				}
				return voy;
			}

		}
		return null;
	}



}
