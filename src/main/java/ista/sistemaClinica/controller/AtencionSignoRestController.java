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

import ista.sistemaClinica.model.entity.AtencionSigno;
import ista.sistemaClinica.model.services.IAtencionSignoService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class AtencionSignoRestController {
	@Autowired
	private IAtencionSignoService atencionSignoService;
	
	@GetMapping("/atenciones_signos")
	public List<AtencionSigno> index() {
		return atencionSignoService.findAll();
	}
	
	@GetMapping("/atenciones_signos/{id}")
	public AtencionSigno show(@PathVariable Long id) {
		return atencionSignoService.findById(id);
	}
	
	@PostMapping("/atenciones_signos")
	@ResponseStatus(HttpStatus.CREATED)
	public AtencionSigno create(@RequestBody AtencionSigno atencionSigno) {
		return atencionSignoService.save(atencionSigno);
	}
	
	@PutMapping("/atenciones_signos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public AtencionSigno update(@RequestBody AtencionSigno atencionSigno, @PathVariable Long id) {
		AtencionSigno atencionSignoActual = atencionSignoService.findById(id);
		
		
		atencionSignoActual.setValorAts(atencionSigno.getValorAts());
		atencionSignoActual.setSignoVital(atencionSigno.getSignoVital());	
	
		return atencionSignoService.save(atencionSignoActual);
		
	}
	
	@DeleteMapping("/atenciones_signos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		atencionSignoService.delete(id);
	}
}
