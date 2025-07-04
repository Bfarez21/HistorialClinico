package ista.sistemaClinica.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ista.sistemaClinica.model.entity.Paciente;


public interface IPacienteDao extends JpaRepository<Paciente, Long> {
	
	Paciente findByCedulaPac(String cedulaPac);
	List<Paciente> findByApellidoPac(String apellidoPac);
	List<Paciente> findByProfesionPac(String profesionPac);
	List<Paciente> findByCarreraPac(String carreraPac);


}
