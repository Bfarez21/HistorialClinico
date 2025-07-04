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

import ista.sistemaClinica.model.entity.Paciente;
import ista.sistemaClinica.model.services.IPacienteService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class PacienteRestController {
	@Autowired
	private IPacienteService pacienteService;
	
	@GetMapping("/pacientes")
	public List<Paciente> index() {
		return pacienteService.findAll();
	}
	
	@GetMapping("/pacientes/id/{id}")
	public Paciente showId(@PathVariable Long id) {
		return pacienteService.findById(id);
	}
	
	@GetMapping("/pacientes/cedula/{cedula}")
	public Paciente showCedula(@PathVariable String cedula) {
		return pacienteService.findByCedulaPac(cedula);
	}
	
	@GetMapping("/pacientes/apellido/{apellido}")
	public List<Paciente> filterByApellido(@PathVariable String apellido) {
		return pacienteService.filterByApellidoPac(apellido);
	}
	
	@GetMapping("/pacientes/profesion/{profesion}")
	public List<Paciente> filterByProfesion(@PathVariable String profesion) {
		return pacienteService.filterByProfesionPac(profesion);
	}

	@GetMapping("/pacientes/carrera/{carrera}")
	public List<Paciente> filterByCarrera(@PathVariable String carrera) {
		return pacienteService.filterByCarreraPac(carrera);
	}


	@PostMapping("/pacientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente create(@RequestBody Paciente paciente) {
		return pacienteService.save(paciente);
	}
	
	@PutMapping("/pacientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente update(@RequestBody Paciente paciente, @PathVariable Long id) {
		Paciente pacienteActual = pacienteService.findById(id);
		
		
		pacienteActual.setCedulaPac(paciente.getCedulaPac());
		pacienteActual.setFechaNacimientoPac(paciente.getFechaNacimientoPac());
		pacienteActual.setNombrePac(paciente.getNombrePac());
		pacienteActual.setApellidoPac(paciente.getApellidoPac());
		pacienteActual.setLugarPac(paciente.getLugarPac());
		pacienteActual.setPaisPac(paciente.getPaisPac());
		pacienteActual.setDireccionPac(paciente.getDireccionPac());
		pacienteActual.setBarrioPac(paciente.getBarrioPac());
		pacienteActual.setParroquiaPac(paciente.getParroquiaPac());
		pacienteActual.setCantonPac(paciente.getCantonPac());
		pacienteActual.setProvinciaPac(paciente.getProvinciaPac());
		pacienteActual.setTelefonoPac(paciente.getTelefonoPac());
		pacienteActual.setProfesionPac(paciente.getProfesionPac());
		pacienteActual.setTipoSangrePac(paciente.getTipoSangrePac());
		pacienteActual.setGeneroPac(paciente.getGeneroPac());
		pacienteActual.setEstadoCivilPac(paciente.getEstadoCivilPac());
		pacienteActual.setCarreraPac(paciente.getCarreraPac());
		pacienteActual.setCicloPac(paciente.getCicloPac());
		
		return pacienteService.save(pacienteActual);
		
	}
	
	@DeleteMapping("/pacientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pacienteService.delete(id);
	}
	
	
	
}
