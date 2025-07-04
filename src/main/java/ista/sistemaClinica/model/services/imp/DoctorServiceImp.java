package ista.sistemaClinica.model.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ista.sistemaClinica.model.dao.IDoctorDao;
import ista.sistemaClinica.model.entity.Doctor;
import ista.sistemaClinica.model.entity.Paciente;
import ista.sistemaClinica.model.services.IDoctorService;


@Service
public class DoctorServiceImp implements IDoctorService{
	
	@Autowired
	private IDoctorDao doctorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAll() {
		
		return (List<Doctor>) doctorDao.findAll();
	}

	@Override
	@Transactional
	public Doctor save(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorDao.save(doctor);
	}

	@Override
	@Transactional(readOnly = true)
	public Doctor findById(Long id) {
		// TODO Auto-generated method stub
		return doctorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		doctorDao.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Doctor findByCedDoctor(String cedulaDoc) {
		return doctorDao.findByCedulaDoc(cedulaDoc);
	}

	@Override
	public Doctor findByCedulaAndPassword(String cedulaDoc, String passwordDoc) {
		return doctorDao.findByCedulaDocAndPasswordDoc(cedulaDoc, passwordDoc);
	}

}
