package inditex.christian.presentation.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto extends ProductRequestDto {

	private Integer rateApplied;
	private Double finalPrice;
	private String currency;

}
