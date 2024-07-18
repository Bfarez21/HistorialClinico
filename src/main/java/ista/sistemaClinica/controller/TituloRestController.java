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

import ista.sistemaClinica.model.entity.Titulo;
import ista.sistemaClinica.model.services.ITituloService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class TituloRestController {
	@Autowired
	private ITituloService tituloService;
	
	@GetMapping("/titulos")
	public List<Titulo> index() {
		return tituloService.findAll();
	}
	
	@GetMapping("/titulos/{id}")
	public Titulo show(@PathVariable Long id) {
		return tituloService.findById(id);
	}
	
	@PostMapping("/titulos")
	@ResponseStatus(HttpStatus.CREATED)
	public Titulo create(@RequestBody Titulo titulo) {
		return tituloService.save(titulo);
	}
	
	@PutMapping("/titulos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Titulo update(@RequestBody Titulo titulo, @PathVariable Long id) {
		Titulo tituloActual = tituloService.findById(id);
		
		
		tituloActual.setContenido_tit(titulo.getContenido_tit());
		tituloActual.setFecha_creacion_tit(titulo.getFecha_creacion_tit());
		tituloActual.setLeyenda_tit(titulo.getLeyenda_tit());
		tituloActual.setInstituto(titulo.getInstituto());
	
		return tituloService.save(tituloActual);
		
	}
	
	@DeleteMapping("/titulos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tituloService.delete(id);
	}
}