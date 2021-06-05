package com.br.boacompra.boacompra.supplier;

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
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	private static final Logger LOG = LogManager.getLogger();
	private static final String ERROR_INSERT_SUPPLIER = "Não foi possivel cadastrar este fornecedor";

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public Supplier findById(Long id) {
		Optional<Supplier> obj = supplierRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Supplier insert(Supplier obj) throws ServiceException {

		try {
				obj.setSupplierName(obj.getSupplierName().toUpperCase());
				obj.setLegalNature(obj.getLegalNature().toUpperCase());
				supplierRepository.save(obj);
		} catch (Exception e) {
			LOG.warn(ERROR_INSERT_SUPPLIER);
			throw new EntityNotFoundException(ERROR_INSERT_SUPPLIER);
		}
		return obj;
	}

	public void delete(Long id) {
		try {
			supplierRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Supplier update(Long id, Supplier obj) {
		try {
			Supplier entity = supplierRepository.getOne(id);
			updateData(entity, obj);
			return supplierRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Supplier entity, Supplier obj) {
		entity.setStatus(obj.isStatus());
		entity.setCompanySize(obj.getCompanySize());
		entity.setProducts(obj.getProducts());
	}

}
