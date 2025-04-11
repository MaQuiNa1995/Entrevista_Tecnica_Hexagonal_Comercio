package inditex.christian.business.port.presentation;

import java.time.LocalDateTime;
import java.util.Optional;

import inditex.christian.business.domain.ProductDomain;

public interface ProductPort {

	Optional<ProductDomain> find(Long productId, Integer brandId, LocalDateTime actualDate);

}