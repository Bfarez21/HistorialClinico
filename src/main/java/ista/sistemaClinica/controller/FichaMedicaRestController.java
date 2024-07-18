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

import ista.sistemaClinica.model.entity.FichaMedica;
import ista.sistemaClinica.model.services.IFichaMedicaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class FichaMedicaRestController {
	@Autowired
	private IFichaMedicaService fichaMedicaService;
	
	@GetMapping("/fichas_medicas")
	public List<FichaMedica> index() {
		return fichaMedicaService.findAll();
	}
	
	@GetMapping("/fichas_medicas/{id}")
	public FichaMedica show(@PathVariable Long id) {
		return fichaMedicaService.findById(id);
	}
	
	@PostMapping("/fichas_medicas")
	@ResponseStatus(HttpStatus.CREATED)
	public FichaMedica create(@RequestBody FichaMedica fichaMedica) {
		return fichaMedicaService.save(fichaMedica);
	}
	
	@PutMapping("/fichas_medicas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public FichaMedica update(@RequestBody FichaMedica fichaMedica, @PathVariable Long id) {
		FichaMedica fichaMedicaActual = fichaMedicaService.findById(id);
		
		fichaMedicaActual.setPaciente(fichaMedica.getPaciente());
		fichaMedicaActual.setFecha_elaboracion_fic(fichaMedica.getFecha_elaboracion_fic());
	
		return fichaMedicaService.save(fichaMedicaActual);
		
	}
	
	@DeleteMapping("/fichas_medicas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		fichaMedicaService.delete(id);
	}
}