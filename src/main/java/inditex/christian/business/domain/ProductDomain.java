package inditex.christian.business.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductDomain extends ProductCommand implements Comparable<ProductDomain> {

	private Integer rateApplied;
	private Double finalPrice;
	private String currency;
	private Integer priority;

	/**
	 * Used by
	 * {@link inditex.christian.business.handler.ProductHandler#find(Long, Integer, String)}
	 */
	@Override
	public int compareTo(ProductDomain otherProduct) {
		return this.priority.compareTo(otherProduct.getPriority());
	}

}
