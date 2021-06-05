package com.br.boacompra.boacompra.support;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.boacompra.boacompra.exceptions.ServiceException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = { "Support" })
public class SupportResources {
	
	private static final String SUCESSO = "sucesso";

	private static final String MENSAGEM = "mensagem";
	
	@Autowired
	private SupportService supportService;
	
	@GetMapping("/support")
	public ResponseEntity<List<Support>> findlAll() {
		List<Support> list = supportService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/support/{id}")
	public ResponseEntity<Support> findById(@PathVariable("id") Long id) {
		Support obj = supportService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping("/support")
	public ResponseEntity<Object> insert(@RequestBody Support support) throws ServiceException {
		HashMap<String, Object> response = new HashMap<>();
		try {
			support = supportService.insert(support);
		} catch (EntityNotFoundException e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status((HttpStatus) HttpStatus.NOT_FOUND).body(response);
		} catch (ServiceException e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status((HttpStatus) HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	
		response.put(SUCESSO, true);
		response.put(MENSAGEM, "Support Cadastrado com sucesso!");
		response.put("support", support);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(value = "/support/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		supportService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/support/{id}")
	public ResponseEntity<Support> update(@PathVariable Long id, @RequestBody Support obj) {
		obj = supportService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
