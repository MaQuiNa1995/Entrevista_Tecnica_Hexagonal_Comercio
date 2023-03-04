package inditex.christian.infrastructure.productport.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.infrastructure.ProductRepositoryPort;
import inditex.christian.infrastructure.common.repository.ProductRepository;
import inditex.christian.infrastructure.productport.factory.ProductDomainFactory;
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
				.collect(Collectors.toList());
	}

}
