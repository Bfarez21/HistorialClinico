package ista.sistemaClinica.model.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ista.sistemaClinica.model.dao.IPacienteDao;
import ista.sistemaClinica.model.entity.Paciente;
import ista.sistemaClinica.model.services.IPacienteService;

@Service
public class PacienteServiceImp implements IPacienteService{
	
	@Autowired
	private IPacienteDao pacienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		
		return (List<Paciente>) pacienteDao.findAll();
	}

	@Override
	@Transactional
	public Paciente save(Paciente paciente) {
		// TODO Auto-generated method stub
		return pacienteDao.save(paciente);
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findById(Long id) {
		// TODO Auto-generated method stub
		return pacienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pacienteDao.deleteById(id);
		
	}

	
	@Override
	@Transactional(readOnly = true)
	public Paciente findByCedulaPac(String cedulaPac) {
		
		return pacienteDao.findByCedulaPac(cedulaPac);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> filterByApellidoPac(String apellidoPac) {
		
		return pacienteDao.findByApellidoPac(apellidoPac);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> filterByProfesionPac(String profesionPac) {
		
		return pacienteDao.findByProfesionPac(profesionPac);
	}

	@Override
	public List<Paciente> filterByCarreraPac(String carreraPac) {
		return pacienteDao.findByCarreraPac(carreraPac);
	}


}
