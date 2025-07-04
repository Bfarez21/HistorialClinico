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

import ista.sistemaClinica.model.entity.Discapacidad;
import ista.sistemaClinica.model.services.IDiscapacidadService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class DiscapacidadRestController {
	@Autowired
	private IDiscapacidadService discapacidadService;
	
	@GetMapping("/discapacidades")
	public List<Discapacidad> index() {
		return discapacidadService.findAll();
	}
	
	@GetMapping("/discapacidades/{id}")
	public Discapacidad show(@PathVariable Long id) {
		return discapacidadService.findById(id);
	}
	
	@PostMapping("/discapacidades")
	@ResponseStatus(HttpStatus.CREATED)
	public Discapacidad create(@RequestBody Discapacidad discapacidad) {
		return discapacidadService.save(discapacidad);
	}
	
	@PutMapping("/discapacidades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Discapacidad update(@RequestBody Discapacidad discapacidad, @PathVariable Long id) {
		Discapacidad atencioMedicaActual = discapacidadService.findById(id);
		
		
		//atencioMedicaActual.setFisicaDis(discapacidad.getFisicaDis());
		//atencioMedicaActual.setIntelectualDis(discapacidad.getIntelectualDis());
		atencioMedicaActual.setDiscapacidadG(discapacidad.getDiscapacidadG());
		atencioMedicaActual.setPorcentajeDis(discapacidad.getPorcentajeDis());
		atencioMedicaActual.setSubtipoDis(discapacidad.getSubtipoDis());
		atencioMedicaActual.setCarnetCon(discapacidad.getCarnetCon());
		atencioMedicaActual.setNumeroConadis(discapacidad.getNumeroConadis());
	
		return discapacidadService.save(atencioMedicaActual);
		
	}
	
	@DeleteMapping("/discapacidades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		discapacidadService.delete(id);
	}
	
	
}
