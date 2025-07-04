package ista.sistemaClinica.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import ista.sistemaClinica.model.entity.ExamenComplementario;
import ista.sistemaClinica.model.services.IExamenComplementarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class ExamenComplementarioRestController {
    
    @Autowired
    private IExamenComplementarioService examenComplementarioService;
    
    @GetMapping("/examenes_complementarios")
    public List<ExamenComplementario> index() {
        return examenComplementarioService.findAll();
    }
    
    @GetMapping("/examenes_complementarios/{id}")
    public ExamenComplementario show(@PathVariable Long id) {
        return examenComplementarioService.findById(id);
    }
    
    @PostMapping("/examenes_complementarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ExamenComplementario create(@RequestBody ExamenComplementario examenComplementario) {
        return examenComplementarioService.save(examenComplementario);
    }
    
    @PutMapping("/examenes_complementarios/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ExamenComplementario update(@RequestBody ExamenComplementario examenComplementario, @PathVariable Long id) {
        ExamenComplementario examenComplementarioActual = examenComplementarioService.findById(id);
        
        if (examenComplementarioActual == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Examen complementario no encontrado");
        }

        examenComplementarioActual.setTituloExa(examenComplementario.getTituloExa());
        examenComplementarioActual.setAplicaExa(examenComplementario.getAplicaExa());

        return examenComplementarioService.save(examenComplementarioActual);
    }
    
    @DeleteMapping("/examenes_complementarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        examenComplementarioService.delete(id);
    }

    @PostMapping("/examenes_complementarios/{id}/uploadPdf")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void uploadPdf(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        examenComplementarioService.savePdf(id, file.getBytes());
    }

    @GetMapping("/examenes_complementarios/{id}/downloadPdf")
    public ResponseEntity<ByteArrayResource> downloadPdf(@PathVariable Long id) {
        byte[] archivoPdf = examenComplementarioService.getPdf(id);
        if (archivoPdf != null) {
            ByteArrayResource resource = new ByteArrayResource(archivoPdf);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documento.pdf")
                    .contentLength(archivoPdf.length)
                    .body(resource);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
