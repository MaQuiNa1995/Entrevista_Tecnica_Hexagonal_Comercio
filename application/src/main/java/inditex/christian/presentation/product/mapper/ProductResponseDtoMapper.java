package inditex.christian.presentation.product.mapper;

import org.mapstruct.Mapper;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.model.ProductResponseDto;

@Mapper
public interface ProductResponseDtoMapper {

	public ProductResponseDto create(ProductDomain productDomain);

}
