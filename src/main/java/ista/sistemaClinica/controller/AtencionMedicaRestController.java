package ista.sistemaClinica.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ista.sistemaClinica.model.entity.ExamenComplementario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ista.sistemaClinica.model.entity.AtencionMedica;
import ista.sistemaClinica.model.services.IAtencionMedicaService;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})   // uso localmnte
//@CrossOrigin(origins= {"http://192.168.18.158:8281"})
public class AtencionMedicaRestController {
	@Autowired
	private IAtencionMedicaService atencionMedicaService;
	
	@GetMapping("/atenciones_medicas")
	public List<AtencionMedica> index() {
		return atencionMedicaService.findAll();
	}
	
	@GetMapping("/atenciones_medicas/{id}")
	public AtencionMedica show(@PathVariable Long id) {
		return atencionMedicaService.findById(id);
	}
	
	@PostMapping("/atenciones_medicas")
	@ResponseStatus(HttpStatus.CREATED)
	public AtencionMedica create(@RequestBody AtencionMedica atencionMedica) {
		return atencionMedicaService.save(atencionMedica);
	}
	
	@PutMapping("/atenciones_medicas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public AtencionMedica update(@RequestBody AtencionMedica atencionMedica, @PathVariable Long id) {
		AtencionMedica atencioMedicaActual = atencionMedicaService.findById(id);
		
		
		atencioMedicaActual.setMotivoAte(atencionMedica.getMotivoAte());
		atencioMedicaActual.setEnfermedadActualAte(atencionMedica.getEnfermedadActualAte());
		atencioMedicaActual.setTratamientoAte(atencionMedica.getTratamientoAte());
		atencioMedicaActual.setFichaMedica(atencionMedica.getFichaMedica());
		atencioMedicaActual.setDoctor(atencionMedica.getDoctor());
		atencioMedicaActual.setDiagnosticos(atencionMedica.getDiagnosticos());
	
		return atencionMedicaService.save(atencioMedicaActual);
		
	}
	
	@DeleteMapping("/atenciones_medicas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		atencionMedicaService.delete(id);
	}
	
	@GetMapping("/enfermedades_actuales")
    public ResponseEntity<Map<String, Integer>> getEnfermedadesActuales() {
        List<AtencionMedica> atenciones = atencionMedicaService.findAll();
        Map<String, Integer> enfermedadesCount = new HashMap<>();

        for (AtencionMedica atencion : atenciones) {
            String enfermedad = atencion.getEnfermedadActualAte();
            enfermedadesCount.put(enfermedad, enfermedadesCount.getOrDefault(enfermedad, 0) + 1);
        }

        return new ResponseEntity<>(enfermedadesCount, HttpStatus.OK);
    }
	
	@GetMapping("/atenciones_por_ano")
	public ResponseEntity<Map<Integer, Integer>> getAtencionesPorAno() {
	    List<AtencionMedica> atenciones = atencionMedicaService.findAll();
	    Map<Integer, Integer> atencionesPorAno = new HashMap<>();

	    for (AtencionMedica atencion : atenciones) {
	        int ano = atencion.getFechaAtencionAte().toLocalDate().getYear();
	        atencionesPorAno.put(ano, atencionesPorAno.getOrDefault(ano, 0) + 1);
	    }

	    return new ResponseEntity<>(atencionesPorAno, HttpStatus.OK);
	}

	/**
	 * SUBIR ARCHIVO PDF A UN EXAMEN ESPECÍFICO
	 */
	@PostMapping("/atenciones_medicas/{atencionId}/examenes/{examenIndex}/pdf")
	public ResponseEntity<String> subirPdfExamen(
			@PathVariable Long atencionId,
			@PathVariable int examenIndex,
			@RequestParam("archivo") MultipartFile archivo
	) {
		System.out.println("=== SUBIR PDF EXAMEN ===");
		System.out.println("Atención ID: " + atencionId);
		System.out.println("Examen Index: " + examenIndex);
		System.out.println("Archivo: " + (archivo != null ? archivo.getOriginalFilename() : "null"));

		// Validaciones
		if (archivo == null || archivo.isEmpty()) {
			return ResponseEntity.badRequest().body("El archivo está vacío");
		}

		if (!archivo.getContentType().equals("application/pdf")) {
			return ResponseEntity.badRequest().body("Solo se permiten archivos PDF");
		}

		try {
			AtencionMedica atencion = atencionMedicaService.findById(atencionId);
			if (atencion == null) {
				return ResponseEntity.notFound().build();
			}

			List<ExamenComplementario> examenes = atencion.getExamenesComplementarios();
			if (examenes == null || examenIndex >= examenes.size() || examenIndex < 0) {
				return ResponseEntity.badRequest().body("Índice de examen inválido");
			}

			// Obtener el examen específico
			ExamenComplementario examen = examenes.get(examenIndex);

			// Guardar el archivo PDF
			byte[] pdfBytes = archivo.getBytes();
			examen.setArchivoPdf(pdfBytes);
			examen.setNombreArchivo(archivo.getOriginalFilename());
			examen.setTipoContenido(archivo.getContentType());
			examen.setTamañoArchivo(archivo.getSize());

			// Guardar la atención médica actualizada
			atencionMedicaService.save(atencion);

			return ResponseEntity.ok("PDF guardado correctamente para el examen: " + examen.getTituloExa());

		} catch (IOException e) {
			System.err.println("Error al procesar archivo: " + e.getMessage());
			return ResponseEntity.status(500).body("Error al guardar el archivo: " + e.getMessage());
		}
	}

}
