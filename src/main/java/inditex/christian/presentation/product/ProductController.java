package inditex.christian.presentation.product;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inditex.christian.business.port.presentation.ProductPort;
import inditex.christian.presentation.product.dto.ProductRequestDto;
import inditex.christian.presentation.product.dto.ProductResponseDto;
import inditex.christian.presentation.product.factory.ProductResponseDtoFactory;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

	private final ProductPort productPort;
	private final ProductResponseDtoFactory productResponseDtoFactory;

	/**
	 * http://localhost:port/product/?actualDate=XXXXXX&productId=YYYYY&brandId=ZZZ
	 */
	@GetMapping
	public ResponseEntity<ProductResponseDto> get(ProductRequestDto productBaseDto) {

		Optional<ProductResponseDto> product = productPort
				.find(productBaseDto.getProductId(), productBaseDto.getBrandId(), productBaseDto.getActualDate())
				.map(productResponseDtoFactory::create);

		return product.isEmpty() ? ResponseEntity.noContent()
				.build() : ResponseEntity.ok(product.get());
	}

}
