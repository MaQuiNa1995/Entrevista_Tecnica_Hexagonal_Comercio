package inditex.christian.infrastructure.product.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Currency;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Product implements Serializable {

	@EmbeddedId
	private ProductPk productPk;
	private Integer brandId;
	private Integer priority;
	private Timestamp startDate;
	private Timestamp endDate;
	private Double price;
	private Currency curr;

}
