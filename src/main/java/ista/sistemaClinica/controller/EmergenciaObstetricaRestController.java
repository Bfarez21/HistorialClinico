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

import ista.sistemaClinica.model.entity.EmergenciaObstetrica;
import ista.sistemaClinica.model.entity.FichaMedica;
import ista.sistemaClinica.model.entity.Paciente;
import ista.sistemaClinica.model.services.IEmergenciaObstetricaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class EmergenciaObstetricaRestController {
	@Autowired
	private IEmergenciaObstetricaService emergenciaObstetricaService;
	
	@GetMapping("/emergencias_obstetricas")
	public List<EmergenciaObstetrica> index() {
		return emergenciaObstetricaService.findAll();
	}
	
	@GetMapping("/emergencias_obstetricas/{id}")
	public EmergenciaObstetrica show(@PathVariable Long id) {
		return emergenciaObstetricaService.findById(id);
	}
	
	@GetMapping("/emergencias_obstetricas/fichaMedica/{fichaMedica}")
	public EmergenciaObstetrica show(@PathVariable FichaMedica fichaMedica) {
		return emergenciaObstetricaService.findByFichaMedica(fichaMedica);
	}
	
	@PostMapping("/emergencias_obstetricas")
	@ResponseStatus(HttpStatus.CREATED)
	public EmergenciaObstetrica create(@RequestBody EmergenciaObstetrica emergenciaObstetrica) {
		return emergenciaObstetricaService.save(emergenciaObstetrica);
	}
	
	@PutMapping("/emergencias_obstetricas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EmergenciaObstetrica update(@RequestBody EmergenciaObstetrica emergenciaObstetrica, @PathVariable Long id) {
		EmergenciaObstetrica emergenciaObstetricaActual = emergenciaObstetricaService.findById(id);
		
		emergenciaObstetricaActual.setMenarca_eme(emergenciaObstetrica.getMenarca_eme());
		emergenciaObstetricaActual.setRitmo_menstrual_eme(emergenciaObstetrica.getRitmo_menstrual_eme());
		emergenciaObstetricaActual.setCiclos_eme(emergenciaObstetrica.getCiclos_eme());
		emergenciaObstetricaActual.setFum_eme(emergenciaObstetrica.getFum_eme());
		emergenciaObstetricaActual.setIvsa_eme(emergenciaObstetrica.getIvsa_eme());
		emergenciaObstetricaActual.setNumero_parejas_sexuales_eme(emergenciaObstetrica.getNumero_parejas_sexuales_eme());
		emergenciaObstetricaActual.setG_eme(emergenciaObstetrica.getG_eme());
		emergenciaObstetricaActual.setP_eme(emergenciaObstetrica.getP_eme());
		emergenciaObstetricaActual.setA_eme(emergenciaObstetrica.getA_eme());
		emergenciaObstetricaActual.setC_eme(emergenciaObstetrica.getC_eme());
		emergenciaObstetricaActual.setDismenorrea_eme(emergenciaObstetrica.getDismenorrea_eme());
		emergenciaObstetricaActual.setMastodinia_eme(emergenciaObstetrica.getMastodinia_eme());
		emergenciaObstetricaActual.setFpp_eme(emergenciaObstetrica.getFpp_eme());
		emergenciaObstetricaActual.setControles_eme(emergenciaObstetrica.getControles_eme());
		emergenciaObstetricaActual.setInmunizaciones_eme(emergenciaObstetrica.getInmunizaciones_eme());
		emergenciaObstetricaActual.setDescripcion_eme(emergenciaObstetrica.getDescripcion_eme());
		emergenciaObstetricaActual.setFichaMedica(emergenciaObstetrica.getFichaMedica());
	
		return emergenciaObstetricaService.save(emergenciaObstetricaActual);
		
	}
	
	@DeleteMapping("/emergencias_obstetricas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		emergenciaObstetricaService.delete(id);
	}
}
