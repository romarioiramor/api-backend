package com.br.boacompra.boacompra.support;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.boacompra.boacompra.exceptions.DatabaseException;
import com.br.boacompra.boacompra.exceptions.ResourceNotFoundException;
import com.br.boacompra.boacompra.exceptions.ServiceException;

@Service
public class SupportService {
	
	@Autowired
	private SupportRepository supportRepository;
	
	private static final Logger LOG = LogManager.getLogger();
	private static final String ERROR_INSERT_SUPPLIER = "Não foi possivel cadastrar este Support";

	public List<Support> findAll() {
		return supportRepository.findAll();
	}

	public Support findById(Long id) {
		Optional<Support> obj = supportRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Support insert(Support obj) throws ServiceException {

		try {
				supportRepository.save(obj);
		} catch (Exception e) {
			LOG.warn(ERROR_INSERT_SUPPLIER);
			throw new EntityNotFoundException(ERROR_INSERT_SUPPLIER);
		}
		return obj;
	}

	public void delete(Long id) {
		try {
			supportRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Support update(Long id, Support obj) {

		Support support = supportRepository.getOne(id);
		updateData(support, obj);
		return supportRepository.save(support);
}

	private void updateData(Support support, Support obj) {
	
		support.setId(obj.getId());
		support.setDescription(obj.getDescription());
		support.setService(obj.getService());
			
	}
}
