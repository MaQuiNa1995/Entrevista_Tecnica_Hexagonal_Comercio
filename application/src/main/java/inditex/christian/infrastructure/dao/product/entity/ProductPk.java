package inditex.christian.infrastructure.dao.product.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProductPk implements Serializable {

	private Long productId;
	private Integer priceList;

}
