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

import ista.sistemaClinica.model.entity.ExamenFisico;
import ista.sistemaClinica.model.services.IExamenFisicoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class ExamenFisicoRestController {
	@Autowired
	private IExamenFisicoService examenFisicoService;
	
	@GetMapping("/examenes_fisicos")
	public List<ExamenFisico> index() {
		return examenFisicoService.findAll();
	}
	
	@GetMapping("/examenes_fisicos/{id}")
	public ExamenFisico show(@PathVariable Long id) {
		return examenFisicoService.findById(id);
	}
	
	@PostMapping("/examenes_fisicos")
	@ResponseStatus(HttpStatus.CREATED)
	public ExamenFisico create(@RequestBody ExamenFisico examenFisico) {
		return examenFisicoService.save(examenFisico);
	}
	
	@PutMapping("/examenes_fisicos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ExamenFisico update(@RequestBody ExamenFisico examenFisico, @PathVariable Long id) {
		ExamenFisico examenFisicoActual = examenFisicoService.findById(id);
			
		examenFisicoActual.setNombreExa(examenFisico.getNombreExa());
		examenFisicoActual.setNumeroExa(examenFisico.getNumeroExa());
		examenFisicoActual.setDescripcionExa(examenFisico.getDescripcionExa());	
		return examenFisicoService.save(examenFisicoActual);
		
	}
	
	@DeleteMapping("/examenes_fisicos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		examenFisicoService.delete(id);
	}
}
