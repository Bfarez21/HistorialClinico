package ista.sistemaClinica.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import ista.sistemaClinica.model.entity.AtencionMedica;
import ista.sistemaClinica.model.entity.ReferenciaMedica;
import ista.sistemaClinica.model.services.IAtencionMedicaService;
import ista.sistemaClinica.model.services.IReferenciaMedicaService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class ReferenciaMedicaRestController {
	@Autowired
	private IReferenciaMedicaService referenciaMedicaService;
	@Autowired
    private IAtencionMedicaService atencionMedicaService;
	
	//@GetMapping("/referencias_medicas")
	//public List<ReferenciaMedica> index() {
		//return referenciaMedicaService.findAll();
	//}
	
	//@GetMapping(value = "/referencias_medicas", produces = MediaType.APPLICATION_JSON_VALUE)
	//public List<ReferenciaMedica> index() {
	  //  return referenciaMedicaService.findAll();
	//}
	@GetMapping("/referencias_medicas")
	public ResponseEntity<List<ReferenciaMedica>> getAllReferenciasMedicas() {
	    List<ReferenciaMedica> referencias = referenciaMedicaService.findAll();
	    // Imprime para depurar
	    System.out.println("Datos a devolver: " + referencias);
	    return ResponseEntity.ok(referencias);
	}




	@GetMapping("/referencias_medicas/{id}")
	public ReferenciaMedica show(@PathVariable Long id) {
		return referenciaMedicaService.findById(id);
	}
	
	@PostMapping("/referencias_medicas")
    @ResponseStatus(HttpStatus.CREATED)
    public ReferenciaMedica create(@RequestBody ReferenciaMedica referenciaMedica) {
        if (referenciaMedica.getAtencionMedica() != null && referenciaMedica.getAtencionMedica().getIdAte() != null) {
            AtencionMedica atencionMedica = atencionMedicaService.findById(referenciaMedica.getAtencionMedica().getIdAte());
            if (atencionMedica != null) {
                referenciaMedica.setAtencionMedica(atencionMedica);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Atención Médica no encontrada");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID de Atención Médica no proporcionado");
        }
        return referenciaMedicaService.save(referenciaMedica);
    }
	
	
	@PutMapping("/referencias_medicas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ReferenciaMedica update(@RequestBody ReferenciaMedica referenciaMedica, @PathVariable Long id) {
		ReferenciaMedica referenciaMedicaActual = referenciaMedicaService.findById(id);
		
		
		referenciaMedicaActual.setInstitucion_ref(referenciaMedica.getInstitucion_ref());
		referenciaMedicaActual.setCedula_doc_ref(referenciaMedica.getCedula_doc_ref());
		referenciaMedicaActual.setServicio_ref(referenciaMedica.getServicio_refe_ref());
		
		referenciaMedicaActual.setEntidad_sistema_ref(referenciaMedica.getEntidad_sistema_ref());
		referenciaMedicaActual.setEstablecimiento_ref(referenciaMedica.getEstablecimiento_ref());
		referenciaMedicaActual.setServicio_ref(referenciaMedica.getServicio_ref());
		referenciaMedicaActual.setEspecialidad_ref(referenciaMedica.getEspecialidad_ref());
		referenciaMedicaActual.setFecha_ref(referenciaMedica.getFecha_ref());
		referenciaMedicaActual.setMotivo_limitada_ref(referenciaMedica.isMotivo_limitada_ref());
		referenciaMedicaActual.setMotivo_falta_ref(referenciaMedica.isMotivo_falta_ref());
		referenciaMedicaActual.setMotivo_otros_ref(referenciaMedica.isMotivo_otros_ref());

		referenciaMedicaActual.setResumen_ref(referenciaMedica.getResumen_ref());
		referenciaMedicaActual.setHallazgos_ref(referenciaMedica.getHallazgos_ref());
		
		referenciaMedicaActual.setNombre_doc_ref(referenciaMedica.getNombre_doc_ref());
		referenciaMedicaActual.setCodigo_msp_ref(referenciaMedica.getCodigo_msp_ref());
		referenciaMedicaActual.setAtencionMedica(referenciaMedica.getAtencionMedica());
		referenciaMedicaActual.setDiagnosticos(referenciaMedica.getDiagnosticos());
	
		return referenciaMedicaService.save(referenciaMedicaActual);
		
	}
	
	@DeleteMapping("/referencias_medicas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		referenciaMedicaService.delete(id);
	}
}
