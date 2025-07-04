package ista.sistemaClinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ista.sistemaClinica.model.entity.Doctor;
import ista.sistemaClinica.model.entity.Paciente;

public interface IDoctorDao extends JpaRepository<Doctor, Long> {
	Doctor findByCedulaDoc(String cedulaDoc);
	Doctor findByCedulaDocAndPasswordDoc(String cedulaDoc, String passwordDoc);

}
