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

import ista.sistemaClinica.model.entity.Diagnostico;
import ista.sistemaClinica.model.services.IDiagnosticoService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class DiagnosticoRestController {
	@Autowired
	private IDiagnosticoService diagnosticoService;
	
	@GetMapping("/diagnosticos")
	public List<Diagnostico> index() {
		return diagnosticoService.findAll();
	}
	
	@GetMapping("/diagnosticos/{id}")
	public Diagnostico show(@PathVariable Long id) {
		return diagnosticoService.findById(id);
	}
	
	@PostMapping("/diagnosticos")
	@ResponseStatus(HttpStatus.CREATED)
	public Diagnostico create(@RequestBody Diagnostico diagnostico) {
		return diagnosticoService.save(diagnostico);
	}
	
	@PutMapping("/diagnosticos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Diagnostico update(@RequestBody Diagnostico diagnostico, @PathVariable Long id) {
		Diagnostico diagnosticoActual = diagnosticoService.findById(id);
		
		diagnosticoActual.setInicialDia(diagnostico.getInicialDia());
		diagnosticoActual.setEstadoDia(diagnostico.getEstadoDia());
	
		return diagnosticoService.save(diagnosticoActual);
		
	}
	
	@DeleteMapping("/diagnosticos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		diagnosticoService.delete(id);
	}
}
