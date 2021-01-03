package com.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
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
	public ResponseEntity<List<Voyageur>> getListVoyageurs(){
		return new ResponseEntity<>(voyageurRep.findAll(),HttpStatus.OK);
	}
	
	
	// Recuperer un Voyageur dont l'id est connu
	
	@RequestMapping (value = "/getVoy/{id_Voyageur}", method = RequestMethod.GET)
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Voyageur> getVoy(@PathVariable ("id_Voyageur") int id_Voyageur) {
		Voyageur v = null;
		for (Voyageur voy : voyageurRep.findAll()) {
			if (voy.getId_voy()==id_Voyageur ){
				v = voy;
			}
		}
		if(v!=null){
			return new ResponseEntity<>(v, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}
		
	//Ajouter un voyageur dans la liste
		
	@PostMapping("/addVoy")
			
	public ResponseEntity<Voyageur> addVoyageur(@RequestBody Voyageur voyageur) {

		voyageurRep.save(voyageur);
		return new ResponseEntity<>(voyageur, HttpStatus.CREATED);

		}
			
	//Supprimer un voyageur de la liste
			
	@RequestMapping(value = "/delVoy/{id_Voyageur}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Voyageur> supprimerVoyageur(@PathVariable("id_Voyageur") int id_Voyageur) throws Exception{
		int id=-1;
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				id = id_Voyageur;
			}

		}
		if(id==-1) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else{
			voyageurRep.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}

	@PutMapping(path = "updateVoy/{id_Voyageur}")
	public ResponseEntity<Voyageur> updateVoy(@RequestBody Voyageur voyageur, @PathVariable("id_Voyageur") int id_Voyageur){
		int id=-1;
		for (Voyageur voy : voyageurRep.findAll()) {
			if(voy.getId_voy() == id_Voyageur) {
				id = id_Voyageur;
			}

		}
		if(id==-1) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else{
			voyageurRep.save(voyageur);
			return new ResponseEntity<>(voyageur, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/addAptLoue/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Voyageur addAptLoue(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Appartement a = null;
		Voyageur v = null;
		for (Appartement apt: aptRep.findAll()){
			if(apt.getId_Appartement() == id_Appartement && !apt.isReserve())

				for (Voyageur voy : voyageurRep.findAll()) {

					if(voy.getId_voy() == id_Voyageur) {
						a = apt;
						v = voy;
					}

				}
		}
		if(a!=null && v!=null){
			v.getAppartement_loue().add(a);
			a.setVoyageur(v);
			a.setReserve(true);
			voyageurRep.save(v);
			aptRep.save(a);
			return v;
		}
		return null;
	}
	@RequestMapping(value = "/rmAptLoue/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Voyageur rmAptLoue(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Voyageur v = null;
		Appartement a = null;
		for (Voyageur voy : voyageurRep.findAll()) {
			if(voy.getId_voy() == id_Voyageur) {
				for(Appartement apt: voy.getAppartement_fav()){
					if(apt.getId_Appartement()==id_Appartement){
						a = apt;
						v = voy;

					}
				}
			}

		}
		if(a!=null && v!=null){
			v.getAppartement_loue().remove(a);
			a.setVoyageur(null);
			a.setReserve(false);
			voyageurRep.save(v);
			aptRep.save(a);
			return v;
		}
		return null;
	}
	@RequestMapping(value = "/addAptFav/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Voyageur addAptFav(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Appartement apt = null;
		for (Appartement a: aptRep.findAll()){
			if(a.getId_Appartement() == id_Appartement)
				apt = a;
		}
		for (Voyageur voy : voyageurRep.findAll()) {

			if(voy.getId_voy() == id_Voyageur) {
				voy.getAppartement_fav().add(apt);
				apt.getVoyageurs().add(voy);
				voyageurRep.save(voy);
				aptRep.save(apt);
				return voy;
			}

		}
		return null;
	}

	@RequestMapping(value = "/rmAptFav/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Voyageur rmAptFav(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
		Voyageur v = null;
		Appartement a = null;
		for (Voyageur voy : voyageurRep.findAll()) {
			if(voy.getId_voy() == id_Voyageur) {
				for(Appartement apt: voy.getAppartement_fav()){
					if(apt.getId_Appartement()==id_Appartement){

						v = voy;
						a = apt;
					}
				}
			}

		}
		if(v!=null && a!=null){
			v.getAppartement_fav().remove(a);
			a.getVoyageurs().remove(v);
			voyageurRep.save(v);
			aptRep.save(a);
			return v;
		}
		return null;
	}



}
