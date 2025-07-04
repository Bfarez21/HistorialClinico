package ista.sistemaClinica.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ista.sistemaClinica.model.entity.Instituto;
import ista.sistemaClinica.model.services.IInstitutoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class InstitutoRestController {
	@Autowired
	private IInstitutoService institutoService;
	
	@GetMapping("/institutos")
	public List<Instituto> index() {
		return institutoService.findAll();
	}
	
	@GetMapping("/institutos/{id}")
	public Instituto show(@PathVariable Long id) {
		return institutoService.findById(id);
	}
	
	@PostMapping("/institutos")
	@ResponseStatus(HttpStatus.CREATED)
	public Instituto create(@RequestParam("nombreIns") String nombreIns,
	                         @RequestParam("direccionIns") String direccionIns,
	                         @RequestParam("rectorIns") String rectorIns,
	                         @RequestParam("imageInstituto") MultipartFile imageInstituto) {
	    Instituto instituto = new Instituto();
	    instituto.setNombreIns(nombreIns);
	    instituto.setDireccionIns(direccionIns);
	    instituto.setRectorIns(rectorIns);

	    if (imageInstituto != null && !imageInstituto.isEmpty()) {
	        try {
	            instituto.setImageInstituto(imageInstituto.getBytes()); // Guarda la imagen en bytes
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al procesar la imagen", e);
	        }
	    }

	    return institutoService.save(instituto);
	}
    
	


	@GetMapping("/institutos/{id}/imagen")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    Instituto instituto = institutoService.findById(id);
	    if (instituto != null && instituto.getImageInstituto() != null) {
	        byte[] imageBytes = instituto.getImageInstituto();
	        return ResponseEntity.ok()
	                .contentType(MediaType.IMAGE_PNG) // Ajusta según el tipo de imagen
	                .body(imageBytes);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	

    
    @DeleteMapping("/institutos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        institutoService.delete(id);
    }

 // En InstitutoRestController.java

    @PutMapping("/institutos/{id}")
    public ResponseEntity<Instituto> update(
            @PathVariable Long id,
            @RequestParam(value = "nombreIns", required = false) String nombreIns,
            @RequestParam(value = "direccionIns", required = false) String direccionIns,
            @RequestParam(value = "rectorIns", required = false) String rectorIns,
            @RequestParam(value = "imageInstituto", required = false) MultipartFile imageInstituto) {

        Instituto instituto = institutoService.findById(id);
        if (instituto == null) {
            return ResponseEntity.notFound().build();
        }

        if (nombreIns != null) {
            instituto.setNombreIns(nombreIns);
        }
        if (direccionIns != null) {
            instituto.setDireccionIns(direccionIns);
        }
        if (rectorIns != null) {
            instituto.setRectorIns(rectorIns);
        }
        if (imageInstituto != null && !imageInstituto.isEmpty()) {
            try {
                instituto.setImageInstituto(imageInstituto.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al procesar la imagen", e);
            }
        }

        Instituto updatedInstituto = institutoService.save(instituto);
        return ResponseEntity.ok(updatedInstituto);
    }

}
