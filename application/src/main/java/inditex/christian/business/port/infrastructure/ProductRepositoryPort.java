package inditex.christian.business.port.infrastructure;

import java.sql.Timestamp;
import java.util.List;

import inditex.christian.business.domain.ProductDomain;

public interface ProductRepositoryPort {

	List<ProductDomain> findByProductIdAndBrandIdAndActualDate(Long productId, Integer brandId, Timestamp actualDate);

}