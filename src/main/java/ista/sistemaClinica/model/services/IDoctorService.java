package ista.sistemaClinica.model.services;

import java.util.List;

import ista.sistemaClinica.model.entity.Doctor;
import ista.sistemaClinica.model.entity.Paciente;

public interface IDoctorService {
	public List<Doctor> findAll();
	public Doctor save(Doctor doctor);
	public Doctor findById(Long id);
	public void delete(Long id);
	
	// Filtros y busquedas
		public Doctor findByCedDoctor(String cedulaDoc);
		Doctor findByCedulaAndPassword(String cedulaDoc, String passwordDoc);


}
