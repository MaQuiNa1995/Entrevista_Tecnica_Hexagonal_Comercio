package inditex.christian.presentation.product;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import inditex.christian.api.ProductApi;
import inditex.christian.business.port.presentation.ProductPort;
import inditex.christian.model.ProductResponseDto;
import inditex.christian.presentation.product.mapper.ProductResponseDtoMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final ProductPort productPort;
	private final ProductResponseDtoMapper productResponseDtoFactory;
	
	@Override
	public ResponseEntity<ProductResponseDto> productGet(Long productId, Integer brandId, LocalDateTime actualDate) {

		Optional<ProductResponseDto> product = productPort.find(productId, brandId, actualDate)
				.map(productResponseDtoFactory::create);

		return product.map(ResponseEntity::ok)
				.orElse(ResponseEntity.noContent()
						.build());
	}
}
