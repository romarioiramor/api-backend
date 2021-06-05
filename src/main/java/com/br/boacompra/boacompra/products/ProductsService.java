package com.br.boacompra.boacompra.products;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.boacompra.boacompra.exceptions.DatabaseException;
import com.br.boacompra.boacompra.exceptions.ResourceNotFoundException;


@Service
public class ProductsService {

    Products products = new Products();

    @Autowired
    public ProductsRepository productsRepository;

    private static final Logger LOG = LogManager.getLogger();
    private static final String ERROR_SAVE_PRODUCT = "Ocorreu um erro ao tentar salvar o produto.";
    private static final String ERROR_INSERT_PRODUCT = "Deve ser inserido ao menos um produto no estoque";
    private static final String ERROR_MAX_STOCK = "Quantidade máxima excedida!!";
    private static final String ERROR_INSERT_MIN = "A quatidade minima deve ser menor que a quantidade cadastrada ";
    private static final String ERROR_GREATER_LESS = "Estoque minimo não pode ser maior ou igual ao estoque maximo!";
    private static final String ERROR_PRODUCTS_NOT_FOUND = "Nenhum produto encontrado!";

    public List<Products> findAll() {
        return productsRepository.findAll(Sort.by(Sort.Direction.DESC, "productReceiptDate"));
    }

    public Products findById(Long id) {
        Optional<Products> obj = productsRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Products insert(Products obj) throws ServiceException {
        Random code = new Random();
        try {
            if (obj.getId() == null) {
                throw new ServiceException(ERROR_INSERT_PRODUCT);
            }

            obj.setProductCode(code.nextInt(Integer.max(10000, 99999)));
            obj.setProductName(obj.getProductName().toUpperCase());
            obj.setProductCategory(obj.getProductCategory().toUpperCase());
            obj.setProductReceiptDate(obj.getProductReceiptDate());
            maxProductStock(obj);
            minProductStock(obj);
            minMaxProductStock(obj);

        } catch (Exception e) {
            throw new ServiceException(ERROR_SAVE_PRODUCT);
        }

        return productsRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            productsRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Products update(Long id, Products obj) {
        try {
            Products entity = productsRepository.getOne(id);
            updateData(entity, obj);
            return productsRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Products entity, Products obj) {
        entity.setProductName(obj.getProductName());
        entity.setProductQuantity(obj.getProductQuantity());
        entity.setProductPrice(obj.getProductPrice());
        entity.setProductDiscount(obj.getProductDiscount());
        entity.setStatus(obj.isStatus());
    }

    /**
     * @param maxstock end minStock
     * @return The storage capacity is the purchased product is checked before being
     * saved
     * @throws ServiceException
     */
    private Products maxProductStock(Products maxstock) throws ServiceException {

        if (maxstock.getProductQuantity() <= maxstock.getMaxStock()) {
            this.products.setProductQuantity(maxstock.getMaxStock());
        } else {
            throw new ServiceException(ERROR_MAX_STOCK);
        }
        return maxstock;

    }

    private Products minProductStock(Products minstock) throws ServiceException {
        if (minstock.getProductQuantity() < minstock.getMinStock()) {
            throw new ServiceException(
                    ERROR_INSERT_MIN + minstock.getProductQuantity() + " é menor que " + minstock.getMinStock());
        }
        return minstock;

    }

    private Products minMaxProductStock(Products minMaxStock) throws ServiceException {

        if (minMaxStock.getMinStock() >= minMaxStock.getMaxStock()) {
            throw new ServiceException(ERROR_GREATER_LESS);
        }

        return minMaxStock;
    }

    public List<Products> negativeBalance() {
        List<Products> productsNegativeBalance = productsRepository.findByNegativebalance();

        if (productsNegativeBalance.size() <= 0) {
            LOG.warn(ERROR_PRODUCTS_NOT_FOUND);
        }
        return productsNegativeBalance;

    }

    public List<Products> findByExpirationDate() {
        List<Products> productsExpirationDate = productsRepository.findByExpirationDate();

        if (productsExpirationDate.size() <= 0) {
            LOG.warn(ERROR_PRODUCTS_NOT_FOUND);
        }
        return productsExpirationDate;

    }

}
