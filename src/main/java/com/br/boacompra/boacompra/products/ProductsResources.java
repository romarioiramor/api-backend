package com.br.boacompra.boacompra.products;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

import com.br.boacompra.boacompra.DTO.ProductsDTO;
import com.br.boacompra.boacompra.exceptions.ServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/v1")
@Api(tags = { "Produtos" })
public class ProductsResources {

	@Autowired
	ProductsService productsService;

	private static final String SUCESSO = "sucesso";

	private static final String MENSAGEM = "mensagem";

	@GetMapping(value = "/products")
	@ApiOperation(value = "Retorna uma lista de Produtos")
	public ResponseEntity<List<Products>> findlAll() {
		List<Products> products = productsService.findAll();
		return ResponseEntity.ok().body(products);
	}

	@GetMapping(value = "/products/{id}")
	@ApiOperation(value = "Retorna o produto por id")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id){
		HashMap<String, Object> response = new HashMap<>();
		Products products;
		try {
			products = productsService.findById(id);
		} catch (EntityNotFoundException e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} catch (Exception e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok().body(products);
	}

	@PostMapping(value = "/products")
	@ApiOperation(value = "Cadastra um novo produto", notes = "Cadastra um novo produto")
	public ResponseEntity<Object> insert(
	      @ApiParam(name = "Produto", type = "String", required = true) @RequestBody Products products)
	      throws ServiceException {
		HashMap<String, Object> response = new HashMap<>();
		try {
			products = productsService.insert(products);
		} catch (EntityNotFoundException e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status((HttpStatus) HttpStatus.NOT_FOUND).body(response);
		} catch (Exception e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status((HttpStatus) HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

		response.put(SUCESSO, true);
		response.put(MENSAGEM, "Produto Cadastrado com sucesso!");
		response.put("Produto", products);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(value = "/products/{id}")
	@ApiOperation(value = "Deleta um produto por id")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		HashMap<String, Object> response = new HashMap<>();

		try {
			productsService.delete(id);
		} catch (EntityNotFoundException e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} catch (Exception e) {
			response.put(SUCESSO, false);
			response.put(MENSAGEM, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		response.put(SUCESSO, true);
		response.put(MENSAGEM, "Produto deletado com sucesso!");
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value = "/products/{id}")
	@ApiOperation(value = "Atualiza produto por id")
	public ResponseEntity<Products> update(@PathVariable Long id, @RequestBody Products products) {
		products = productsService.update(id, products);
		return ResponseEntity.ok().body(products);
	}

	@GetMapping(value = "/productsFindByNegativebalance")
	@ApiOperation(value = "Retorna uma lista de produtos com saldo negativo")
	public ResponseEntity<List<Products>> findByNegativebalance() {
		List<Products> products = productsService.negativeBalance();
		HashMap<String, Object> response = new HashMap<>();
		response.put(SUCESSO, true);
		response.put("Produto(s) econtrado(s)", products);
		return ResponseEntity.ok().body(products);
	}

	@GetMapping(value = "/productsFindByExpirationDate")
	@ApiOperation(value = "Retorna uma lista de produtos com validade proximo do vencimento")
	public ResponseEntity<List<ProductsDTO>> findByExpirationDate() {
		HashMap<String, Object> response = new HashMap<>();
		List<Products> products = productsService.findByExpirationDate();
		List<ProductsDTO> productsDTOs = products.stream().map(x -> new ProductsDTO(x)).collect(Collectors.toList());
		response.put(SUCESSO, true);
		response.put("Produto(s) econtrado(s)", productsDTOs);
		return ResponseEntity.ok().body(productsDTOs);
	}
}
