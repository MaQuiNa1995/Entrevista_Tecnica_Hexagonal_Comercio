package inditex.christian.infrastructure.product.dao.entity;

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
