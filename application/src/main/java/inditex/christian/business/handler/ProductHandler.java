package inditex.christian.business.handler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.infrastructure.ProductRepositoryPort;
import inditex.christian.business.port.presentation.ProductPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductHandler implements ProductPort {

	private final ProductRepositoryPort productRepositoryPort;

	@Override
	public Optional<ProductDomain> find(Long productId, Integer brandId, LocalDateTime actualDate) {
		
		return productRepositoryPort
				.findByProductIdAndBrandIdAndActualDate(productId, brandId, Timestamp.valueOf(actualDate))
				.stream()
				.max(Comparator.comparing(e -> e));
	}

}
