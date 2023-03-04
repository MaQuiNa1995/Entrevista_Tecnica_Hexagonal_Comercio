package inditex.christian.presentation.product.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto implements Serializable {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	protected LocalDateTime actualDate;
	protected Long productId;
	protected Integer brandId;

}