package com.br.boacompra.boacompra.payment;

import java.util.List;
import java.util.Optional;

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
public class PaymentFormService {
	
	@Autowired
	private PaymentFormRepository paymantFormRepository;

	private static final Logger LOG = LogManager.getLogger();
	private static final String ERROR_INSERT_CLIENTS = "Não foi possivel cadastrar esta forma de pagamento";

	public List<PaymentForm> findAll() {
		return paymantFormRepository.findAll();
	}

	public PaymentForm findById(Long id) {
		Optional<PaymentForm> paymantForm = paymantFormRepository.findById(id);
		return paymantForm.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public PaymentForm insert(PaymentForm paymantForm) throws ServiceException {
		try {
			paymantFormRepository.save(paymantForm);
		} catch (Exception e) {
			LOG.warn(ERROR_INSERT_CLIENTS);
			LOG.warn(e.getMessage());
			throw new ServiceException(ERROR_INSERT_CLIENTS);
		}
		return paymantForm;
	}

	public void delete(Long id) {
		try {
			paymantFormRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public PaymentForm update(Long id, PaymentForm obj) {

			PaymentForm paymantForm = paymantFormRepository.getOne(id);
			updateData(paymantForm, obj);
			return paymantFormRepository.save(paymantForm);
	}

	private void updateData(PaymentForm paymantForm, PaymentForm obj) {

		paymantForm.setId(obj.getId());
		paymantForm.setCv(obj.getCv());
		paymantForm.setDate(obj.getDate());
		paymantForm.setName(obj.getName());
		paymantForm.setNumber(obj.getNumber());
		paymantForm.setTypePayment(obj.getTypePayment());
		
	}

}
