package inditex.christian.api;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import inditex.christian.model.ProductResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class ProductApi extends DefaultProductApi {

	@Override
	@Retry(name = "productGetRetry", fallbackMethod = "getProductFallback")
	@CircuitBreaker(name = "productGetCircuitBreaker", fallbackMethod = "getProductFallback")
	public ProductResponseDto productGet(Long productId, Integer brandId, Date actualDate) throws RestClientException {
		return super.productGetWithHttpInfo(productId, brandId, actualDate).getBody();
	}

	public ProductResponseDto getProductFallback(Long productId, Integer brandId, Date actualDate, Throwable t)
			throws Exception {
		throw new Exception("La api de productos no está disponible.", t);
	}

}
