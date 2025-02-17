package inditex.christian.infrastructure.product.port.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Currency;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.infrastructure.product.dao.entity.Product;

@Mapper
public interface ProductDomainFactory {

	@Mapping(source = "product.productPk.productId", target = "productId")
	@Mapping(source = "product.productPk.priceList", target = "rateApplied")
	@Mapping(source = "product.price", target = "finalPrice")
	@Mapping(source = "product.curr", target = "currency", qualifiedByName = "mapCurrency")
	@Mapping(source = "actualDate", target = "actualDate", qualifiedByName = "mapLocalDateTime")
	public ProductDomain create(Product product, Timestamp actualDate);

	@Named("mapCurrency")
	default String mapCurrency(Currency currency) {
		return currency.toString();
	}
	
	@Named("mapLocalDateTime")
	default LocalDateTime mapActualDate(Timestamp actualDate) {
		return actualDate.toLocalDateTime();
	}

}
