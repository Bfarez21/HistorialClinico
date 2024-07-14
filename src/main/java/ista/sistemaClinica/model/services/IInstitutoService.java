package ista.sistemaClinica.model.services;

import java.util.List;

import ista.sistemaClinica.model.entity.Instituto;

public interface IInstitutoService {
	public List<Instituto> findAll();
	public Instituto save(Instituto instituto);
	public Instituto findById(Long id);
	public void delete(Long id);

}