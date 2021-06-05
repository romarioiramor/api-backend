package com.br.boacompra.boacompra.clients;

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
public class ClientsService {

	@Autowired
	private ClientsRepository clientsRepository;

	private static final Logger LOG = LogManager.getLogger();
	private static final String ERROR_INSERT_CLIENTS = "Não foi possivel cadastrar este cliente";

	public List<Clients> findAll() {
		return clientsRepository.findAll();
	}

	public Clients findById(Long id) {
		Optional<Clients> clients = clientsRepository.findById(id);
		return clients.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Clients insert(Clients clients) throws ServiceException {
		try {
			clientsRepository.save(clients);
		} catch (Exception e) {
			LOG.warn(ERROR_INSERT_CLIENTS);
			LOG.warn(e.getMessage());
			throw new ServiceException(ERROR_INSERT_CLIENTS);
		}
		return clients;
	}

	public void delete(Long id) {
		try {
			clientsRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Clients update(Long id, Clients obj) {
		
			Clients clients = clientsRepository.getOne(id);
			updateData(clients, obj);
			return clientsRepository.save(clients);
		
	}

	private void updateData(Clients clients, Clients obj) {

		clients.setId(obj.getId());
		clients.setName(obj.getName());
		clients.setCell(obj.getCell());
		clients.setBirth(obj.getBirth());
		clients.setEmail(obj.getEmail());
		clients.setGender(obj.getGender());
		clients.setTelphone(obj.getTelphone());
	}
}
