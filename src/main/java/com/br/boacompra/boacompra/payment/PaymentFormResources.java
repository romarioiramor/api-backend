package com.br.boacompra.boacompra.payment;

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
@Api(tags = { "PaymentForm" })
public class PaymentFormResources {
	@Autowired
	PaymentFormService paymentFormService;

	private static final String SUCESSO = "sucesso";

	private static final String MENSAGEM = "mensagem";

	@GetMapping("/paymentForm")
	@ApiOperation(value = "Retorna uma lista de forma de pagamento")
	public ResponseEntity<List<PaymentForm>> findlAll() {
		List<PaymentForm> paymentForm = paymentFormService.findAll();
		return ResponseEntity.ok().body(paymentForm);
	}

	@GetMapping("/paymentForm/{id}")
	@ApiOperation(value = "Retorna a forma de pagamento por id")
	public ResponseEntity<PaymentForm> findById(@PathVariable("id") Long id){
		PaymentForm paymentForm = paymentFormService.findById(id);
		return ResponseEntity.ok().body(paymentForm);
	}

	@PostMapping("/paymentForm")
	@ApiOperation(value = "Realiza o cadastro de forma de pagamento")
	public ResponseEntity<Object> insert(@ApiParam(value = "Cadastro de forma de pagamento", required = true) @Valid @RequestBody PaymentForm paymentForm) 
			throws ServiceException {
		HashMap<String, Object> response = new HashMap<>();

		try {
			paymentForm = paymentFormService.insert(paymentForm);
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
		response.put(MENSAGEM, "Forma de pagamento cadastrado com sucesso!");
		response.put("Forma de pagamento", paymentForm);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(value = "/paymentForm/{id}")
	@ApiOperation(value = "Deleta uma forma de pagamento por id")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		paymentFormService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/paymentForm/{id}")
	@ApiOperation(value = "Atualiza forma de pagamento por id")
	public ResponseEntity<PaymentForm> update(@PathVariable Long id, @RequestBody PaymentForm paymentForm) {
		paymentForm = paymentFormService.update(id, paymentForm);
		return ResponseEntity.ok().body(paymentForm);
	}
}
