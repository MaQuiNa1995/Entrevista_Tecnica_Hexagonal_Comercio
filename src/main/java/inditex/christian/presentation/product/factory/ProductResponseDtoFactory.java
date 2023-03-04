package inditex.christian.presentation.product.factory;

import org.mapstruct.Mapper;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.presentation.product.dto.ProductResponseDto;

@Mapper
public interface ProductResponseDtoFactory {

	public ProductResponseDto create(ProductDomain productDomain);

}
