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
import org.springframework.web.server.ResponseStatusException;

import ista.sistemaClinica.model.entity.Enfermedad;
import ista.sistemaClinica.model.entity.Titulo;
import ista.sistemaClinica.model.entity.Instituto;

import ista.sistemaClinica.model.services.ITituloService;
import ista.sistemaClinica.model.services.IInstitutoService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class TituloRestController {
	@Autowired
	private ITituloService tituloService;
	 @Autowired
	    private IInstitutoService institutoService;
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
	    try {
	        // Verifica que el instituto exista usando el ID
	        if (titulo.getInstituto() == null || institutoService.findById(titulo.getInstituto()) == null) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instituto no encontrado");
	        }

	        // Guarda el título
	        return tituloService.save(titulo);
	    } catch (Exception e) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear título", e);
	    }
	}

	@PutMapping("/titulos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Titulo update(@RequestBody Titulo titulo, @PathVariable Long id) {
		Titulo tituloActual = tituloService.findById(id);
		
		
		tituloActual.setContenidoTit(titulo.getContenidoTit());
		tituloActual.setFechaCreacion(titulo.getFechaCreacion());
		tituloActual.setLeyendaTit(titulo.getLeyendaTit());
		tituloActual.setInstituto(titulo.getInstituto());
	
		return tituloService.save(tituloActual);
		
	}
	
	@DeleteMapping("/titulos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tituloService.delete(id);
	}
	// metodos para filtrar por codigo
	@GetMapping("/titulos/codigoTit/{codigoTit}")
	public Titulo showTitulo(@PathVariable Long codigoTit) {
		return tituloService.findById(codigoTit);
	}
	
}
