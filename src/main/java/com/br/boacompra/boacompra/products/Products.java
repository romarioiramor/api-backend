package com.br.boacompra.boacompra.products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBL_PRODUCTS")
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Nome do produto deve ser preenchido!")
	@Column(name = "PRODUCTNAME")
	private String productName;

	@NotNull(message = "Quantidade do produto deve ser preenchido!")
	@Column(name = "PRODUCTQUANTITY")
	private Long productQuantity;

	@NotNull(message = "Alerta de saldo negativo deve ser preenchido!")
	@Column(name = "NEGATIVEBALANCE")
	private Long negativeBalance;

	@NotNull(message = "Preço do produto deve ser preenchido!")
	@Column(name = "PRODUCTPRICE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0,00")
	private BigDecimal productPrice;

	@Column(name = "PRODUCTCODE", nullable = false)
	private int productCode;

	@Column(name = "PRODUCTDISCOUNT")
	private Long productDiscount;

	@NotNull(message = "Quantidade mínima do produto em estoque deve ser informada!")
	@Column(name = "MINSTOCK", nullable = false)
	private Long minStock;

	@NotNull(message = "Quantidade maxima do produto em estoque deve ser informada!")
	@Column(name = "MAXSTOCK")
	private Long maxStock;

	@NotNull(message = "Preço de custo deverá ser informada!")
	@Column(name = "MANUFACTURINEXPENSES")
	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0,00", inclusive = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0,00")
	private BigDecimal manufacturingExpenses;

	@Column(name = "PRODUCTPROFITPERCENT")
	private BigDecimal productProfitPercent;

	@Column(name = "PRODUCTPROFITREAIS")
	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0,00", inclusive = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0,00")
	private BigDecimal productProfitReais;

	@NotNull(message = "Categoria do produto deverá ser informada!")
	@Column(name = "PRODUCTCATEGORY")
	private String productCategory;

	@NotNull(message = "Marca do produto deverá ser informada!")
	@Column(name = "PRODUCTCBRAND")
	private String productBrand;

	@NotNull(message = "Unidade de medida do produto deverá ser informada!")
	@Column(name = "UNITOFMEASUREMENT")
	private String unitOfMeasurement;

	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "BARCODE")
	private String BarCode;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "PRODUCTRECEPTDATE")
	private LocalDateTime productReceiptDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "EXPIRATIONDATE")
	private LocalDateTime expirationDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@CreationTimestamp
	@Column(name = "CREATEDATE")
	private LocalDateTime created;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@UpdateTimestamp
	@Column(name = "UPDATEDATE")
	private LocalDateTime updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Long getNegativeBalance() {
		return negativeBalance;
	}

	public void setNegativeBalance(Long negativeBalance) {
		this.negativeBalance = negativeBalance;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public Long getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(Long productDiscount) {
		this.productDiscount = productDiscount;
	}

	public Long getMinStock() {
		return minStock;
	}

	public void setMinStock(Long minStock) {
		this.minStock = minStock;
	}

	public Long getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Long maxStock) {
		this.maxStock = maxStock;
	}

	public BigDecimal getManufacturingExpenses() {
		return manufacturingExpenses;
	}

	public void setManufacturingExpenses(BigDecimal manufacturingExpenses) {
		this.manufacturingExpenses = manufacturingExpenses;
	}

	public BigDecimal getProductProfitPercent() {
		return productProfitPercent;
	}

	public void setProductProfitPercent(BigDecimal productProfitPercent) {
		this.productProfitPercent = productProfitPercent;
	}

	public BigDecimal getProductProfitReais() {
		return productProfitReais;
	}

	public void setProductProfitReais(BigDecimal productProfitReais) {
		this.productProfitReais = productProfitReais;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public LocalDateTime getProductReceiptDate() {
		return productReceiptDate;
	}

	public void setProductReceiptDate(LocalDateTime productReceiptDate) {
		this.productReceiptDate = productReceiptDate;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (id == null) {
			return other.id == null;
		} else
			return id.equals(other.id);
	}
}