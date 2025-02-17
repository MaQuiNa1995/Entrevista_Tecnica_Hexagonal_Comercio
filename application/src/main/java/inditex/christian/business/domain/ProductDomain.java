package inditex.christian.business.domain;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductDomain implements Comparable<ProductDomain> {

	private Integer rateApplied;
	private Double finalPrice;
	private String currency;
	private Integer priority;
	protected LocalDateTime actualDate;
	protected Long productId;
	protected Integer brandId;

	@Override
	public int compareTo(ProductDomain otherProduct) {
		return this.priority.compareTo(otherProduct.getPriority());
	}

}
