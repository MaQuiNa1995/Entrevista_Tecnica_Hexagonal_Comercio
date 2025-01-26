package inditex.christian.business.handler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.infrastructure.ProductRepositoryPort;

@SpringBootTest
public class ProductHandlerTest {

	@Autowired
	private ProductHandler cut;

	@MockBean
	private ProductRepositoryPort productRepositoryPort;

	@Test
	void findByProductIdAndBrandIdAndActualDateTest() {

		ProductDomain productDomain = new ProductDomain();
		productDomain.setActualDate(LocalDateTime.now());
		productDomain.setBrandId(20);
		productDomain.setCurrency("EUR");
		productDomain.setFinalPrice(5D);
		productDomain.setPriority(80);
		productDomain.setProductId(900L);
		productDomain.setRateApplied(5);

		Mockito.doReturn(List.of(productDomain))
				.when(productRepositoryPort)
				.findByProductIdAndBrandIdAndActualDate(productDomain.getProductId(), productDomain.getBrandId(),
						Timestamp.valueOf(productDomain.getActualDate()));

		Optional<ProductDomain> result = cut.find(productDomain.getProductId(), productDomain.getBrandId(),
				productDomain.getActualDate());

		Assertions.assertAll(
				() -> Assertions.assertTrue(result.isPresent()),
				() -> Assertions.assertEquals(productDomain.getActualDate() ,result.get().getActualDate()),
				() -> Assertions.assertEquals(productDomain.getBrandId(),result.get().getBrandId()),
				() -> Assertions.assertEquals(productDomain.getCurrency(),result.get().getCurrency()),
				() -> Assertions.assertEquals(productDomain.getFinalPrice(),result.get().getFinalPrice()),
				() -> Assertions.assertEquals(productDomain.getPriority(),result.get().getPriority()),
				() -> Assertions.assertEquals(productDomain.getProductId(),result.get().getProductId()),
				() -> Assertions.assertEquals(productDomain.getRateApplied(),result.get().getRateApplied())
		);

	}

}
