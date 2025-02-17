package inditex.christian.infrastructure.product.port.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.infrastructure.ProductRepositoryPort;
import inditex.christian.infrastructure.product.dao.repository.ProductRepository;
import inditex.christian.infrastructure.product.port.factory.ProductDomainFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDao implements ProductRepositoryPort {

	private final ProductRepository productRepository;
	private final ProductDomainFactory productDomainFactory;

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findByProductIdAndBrandIdAndActualDate(Long productId, Integer brandId,
			Timestamp actualDate) {

		return productRepository.findByProductIdAndBrandIdAndActualDate(productId, brandId, actualDate)
				.stream()
				.map(product -> productDomainFactory.create(product, actualDate))
				.toList();
	}

}
