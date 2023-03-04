package inditex.christian.business.domain;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductCommand {

	protected LocalDateTime actualDate;
	protected Long productId;
	protected Integer brandId;

}