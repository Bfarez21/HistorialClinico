package ista.sistemaClinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ista.sistemaClinica.model.entity.AntecedenteFamiliar;
import ista.sistemaClinica.model.services.IAntecedenteFamiliarService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class AntecedenteFamiliarRestController {
	@Autowired
	private IAntecedenteFamiliarService antecedenteFamiliarService;
	
	@GetMapping("/antecedentes_familiares")
	public List<AntecedenteFamiliar> index() {
		return antecedenteFamiliarService.findAll();
	}
	
	@GetMapping("/antecedentes_familiares/{id}")
	public AntecedenteFamiliar show(@PathVariable Long id) {
		return antecedenteFamiliarService.findById(id);
	}
	
	@PostMapping("/antecedentes_familiares")
	@ResponseStatus(HttpStatus.CREATED)
	public AntecedenteFamiliar create(@RequestBody AntecedenteFamiliar antecedenteFamiliar) {
		return antecedenteFamiliarService.save(antecedenteFamiliar);
	}
	
	@PutMapping("/antecedentes_familiares/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public AntecedenteFamiliar update(@RequestBody AntecedenteFamiliar antecedenteFamiliar, @PathVariable Long id) {
		AntecedenteFamiliar antecedenteFamiliarActual = antecedenteFamiliarService.findById(id);
		
		
		antecedenteFamiliarActual.setAlergiaAnt(antecedenteFamiliar.getAlergiaAnt());
		antecedenteFamiliarActual.setClinicoAnt(antecedenteFamiliar.getClinicoAnt());
		antecedenteFamiliarActual.setGinecologoAnt(antecedenteFamiliar.getGinecologoAnt());
		antecedenteFamiliarActual.setTraumatologicoAnt(antecedenteFamiliar.getTraumatologicoAnt());
		antecedenteFamiliarActual.setQuirurgicoAnt(antecedenteFamiliar.getQuirurgicoAnt());
		antecedenteFamiliarActual.setFarmacologicoAnt(antecedenteFamiliar.getFarmacologicoAnt());	
		
		antecedenteFamiliarActual.setDescripcionAlergiaAnt(antecedenteFamiliar.getDescripcionAlergiaAnt());
		antecedenteFamiliarActual.setDescripcionClinicoAnt(antecedenteFamiliar.getDescripcionClinicoAnt());
		antecedenteFamiliarActual.setDescripcionGinecologoAnt(antecedenteFamiliar.getDescripcionGinecologoAnt());
		antecedenteFamiliarActual.setDescripcionTraumatologicoAnt(antecedenteFamiliar.getDescripcionTraumatologicoAnt());
		antecedenteFamiliarActual.setDescripcionQuirurgicoAnt(antecedenteFamiliar.getDescripcionQuirurgicoAnt());
		antecedenteFamiliarActual.setDescripcionFarmacologicoAnt(antecedenteFamiliar.getDescripcionFarmacologicoAnt());
	
		return antecedenteFamiliarService.save(antecedenteFamiliarActual);
		
	}
	
	@DeleteMapping("/antecedentes_familiares/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		antecedenteFamiliarService.delete(id);
	}
}
