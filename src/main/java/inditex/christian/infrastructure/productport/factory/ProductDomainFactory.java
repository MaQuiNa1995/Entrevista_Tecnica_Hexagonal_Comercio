package inditex.christian.infrastructure.productport.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.infrastructure.common.entity.Product;

@Mapper
public interface ProductDomainFactory {

	@Mapping(source = "product.productPk.productId", target = "productId")
	@Mapping(source = "product.productPk.priceList", target = "rateApplied")
	@Mapping(source = "product.price", target = "finalPrice")
	@Mapping(source = "product.curr", target = "currency")
	public ProductDomain create(Product product, Timestamp actualDate);

	default String mapCurrency(Currency curr) {
		return curr.toString();
	}

	default LocalDateTime mapTimestamp(Timestamp timestamp) {
		return timestamp.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}

}
