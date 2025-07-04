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

import ista.sistemaClinica.model.entity.SignoVital;
import ista.sistemaClinica.model.services.ISignoVitalService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class SignoVitalRestController {
	@Autowired
	private ISignoVitalService signoVitalService;
	
	@GetMapping("/signos_vitales")
	public List<SignoVital> index() {
		return signoVitalService.findAll();
	}
	
	@GetMapping("/signos_vitales/{id}")
	public SignoVital show(@PathVariable Long id) {
		return signoVitalService.findById(id);
	}
	
	@PostMapping("/signos_vitales")
	@ResponseStatus(HttpStatus.CREATED)
	public SignoVital create(@RequestBody SignoVital signoVital) {
		return signoVitalService.save(signoVital);
	}
	
	@PutMapping("/signos_vitales/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public SignoVital update(@RequestBody SignoVital signoVital, @PathVariable Long id) {
		SignoVital signoVitalActual = signoVitalService.findById(id);
		
		
		signoVitalActual.setUnidadSig(signoVital.getUnidadSig());
		signoVitalActual.setNombreSig(signoVital.getNombreSig());

		return signoVitalService.save(signoVitalActual);
		
	}
	
	@DeleteMapping("/signos_vitales/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		signoVitalService.delete(id);
	}
}
