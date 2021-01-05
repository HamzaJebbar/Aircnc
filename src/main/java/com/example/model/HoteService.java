package com.example.model;


import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
public class HoteService {
    HoteRepository hoteRep;
    AppartementRepository aptRep;

    public HoteService(HoteRepository hoteRep, AppartementRepository aptRep) {

        this.hoteRep = hoteRep;
        this.aptRep = aptRep;
    }
    //Afficher la liste des Hotes

    @RequestMapping (value = "/getHotes", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<List <Hote>> getListHotes(){

        return new ResponseEntity<>(hoteRep.findAll(), HttpStatus.OK);
    }


    // Recuperer un Hote dont l'id est connu

    @RequestMapping (value = "/getHote/{id_Hote}", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<Hote> Hote(@PathVariable ("id_Hote") int id_Hote) {
        for (Hote hote : hoteRep.findAll()) {
            if (hote.getId_voy()==id_Hote ){

                return new ResponseEntity<>(hote, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    }

    //Ajouter un Hote dans la liste

    @PostMapping("/addHote")
    public ResponseEntity<Hote> addHote(@RequestBody Hote hote) {

        hoteRep.save(hote);
        return new ResponseEntity<>(hote, HttpStatus.OK);
    }

    //Supprimer un Hote de la liste

    @RequestMapping(value = "/delHote/{id_Hote}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Hote> supprimerHote(@PathVariable("id_Hote") int id_Hote) throws Exception{
        int id = -1;
        for (Hote Hote : hoteRep.findAll()) {
            if(Hote.getId_voy() == id_Hote) {

                id = id_Hote;
            }
        }
        if(id!=-1) {
            hoteRep.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/addHoteApt/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Hote> addHoteApt(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
        Hote h = null;
        Appartement a = null;
        for (Appartement apt: aptRep.findAll()){
            if(apt.getId_Appartement() == id_Appartement)
                for (Hote hote : hoteRep.findAll()) {
                    if(hote.getId_voy() == id_Voyageur) {

                        h = hote;
                        a = apt;
                    }

                }
        }
        if(h!=null && a!=null){
            h.getAppartements().add(a);
            a.setHote(h);
            hoteRep.save(h);
            aptRep.save(a);
            return new ResponseEntity<>(h, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/rmHoteApt/{id_Voyageur}/{id_Appartement}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Hote> rmHoteApt(@PathVariable("id_Appartement") int id_Appartement, @PathVariable("id_Voyageur") int id_Voyageur) {
        Hote h = null;
        Appartement a = null;
        for (Hote hote : hoteRep.findAll()) {
            if(hote.getId_voy() == id_Voyageur) {
                for(Appartement apt: hote.getAppartements()){
                    if(apt.getId_Appartement()==id_Appartement){

                        h = hote;
                        a = apt;
                    }
                }
            }
        }
        if(h!=null && a!=null){
            h.getAppartements().remove(a);
            a.setHote(null);
            hoteRep.save(h);
            return new ResponseEntity<>(h, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    //Mettre a jours un hote
    @PutMapping(path = "updateHot/{id_Hote}")
	public ResponseEntity<Hote> updateHote(@RequestBody Hote hote, @PathVariable("id_Hote") int id_Hote){
		int id=-1;
		for (Hote hot : hoteRep.findAll()) {
			if(hot.getId_voy() == id_Hote) {
				id = id_Hote;
			}

		}
		if(id==-1) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else{
			hoteRep.save(hote);
			return new ResponseEntity<>(hote, HttpStatus.OK);
		}
	}

}
