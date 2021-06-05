package com.br.boacompra.boacompra.addres;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1")
@Api(tags = { "Address" })
public class AddressResources {
	@Autowired
	AddressService addressService;

	private static final String SUCESSO = "sucesso";

	private static final String MENSAGEM = "mensagem";

	@GetMapping("/address")
	@ApiOperation(value = "Retorna uma lista de Endereco")
	public ResponseEntity<List<Address>> findlAll() {
		List<Address> address = addressService.findAll();
		return ResponseEntity.ok().body(address);
	}

	@GetMapping("/address/{id}")
	@ApiOperation(value = "Retorna o endereco por id")
	public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
		Address address = addressService.findById(id);
		return ResponseEntity.ok().body(address);
	}

	@PostMapping("/address")
	@ApiOperation(value = "Realiza o cadastro de endereco")
	public ResponseEntity<Object> insert(@ApiParam(value = "Cadastro de endereco", required = true) @Valid @RequestBody Address address) 
			throws ServiceException {
		HashMap<String, Object> response = new HashMap<>();

		try {
			address = addressService.insert(address);
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
		response.put(MENSAGEM, "Endereco cadastrado com sucesso!");
		response.put("Endereco", address);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(value = "/address/{id}")
	@ApiOperation(value = "Deleta um endereco por id")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
				addressService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/address/{id}")
	@ApiOperation(value = "Atualiza endereco por id")
	public ResponseEntity<Object> update(@ApiParam(value = "Atualizar de endereco", required = true) @PathVariable Long id, @Valid @RequestBody Address address) {
		address = addressService.update(id, address);
		return ResponseEntity.ok().body(address);
	}
}
