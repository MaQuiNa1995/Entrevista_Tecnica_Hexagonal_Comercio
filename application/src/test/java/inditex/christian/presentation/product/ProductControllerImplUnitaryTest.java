package inditex.christian.presentation.product;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.presentation.ProductPort;
import inditex.christian.model.ProductResponseDto;
import inditex.christian.presentation.product.mapper.ProductResponseDtoMapper;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
public class ProductControllerImplUnitaryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<ProductResponseDto> productResponseDtoJackson;

	@MockitoBean
	private ProductPort productPort;

	@MockitoBean
	private ProductResponseDtoMapper productResponseDtoFactory;

	@Test
	void getTest() throws Exception {

		LocalDateTime actualDate = LocalDateTime.now();
		long productId = 1L;
		int brandId = 2;

		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setActualDate(actualDate);
		productResponseDto.setBrandId(brandId);
		productResponseDto.setCurrency("EUR");
		productResponseDto.setFinalPrice(2D);
		productResponseDto.setProductId(productId);
		productResponseDto.setRateApplied(99);

		ProductDomain productDomain = new ProductDomain();
		productDomain.setActualDate(actualDate);
		productDomain.setBrandId(brandId);
		productDomain.setCurrency("EUR");
		productDomain.setFinalPrice(5D);
		productDomain.setPriority(80);
		productDomain.setProductId(productId);
		productDomain.setRateApplied(5);

		Mockito.when(productPort.find(productId, brandId, actualDate))
				.thenReturn(Optional.of(productDomain));

		Mockito.when(productResponseDtoFactory.create(Mockito.any(ProductDomain.class)))
				.thenReturn(productResponseDto);

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product")
				.queryParam("actualDate", actualDate
						.toString())
				.queryParam("brandId", String.valueOf(brandId))
				.queryParam("productId", String.valueOf(productId)))
				.andReturn()
				.getResponse();

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

		ProductResponseDto responseObject = productResponseDtoJackson.parseObject(response.getContentAsString());

		Assertions.assertEquals(productResponseDto.getBrandId(), responseObject.getBrandId());
		Assertions.assertEquals(productResponseDto.getFinalPrice(), responseObject.getFinalPrice());
		Assertions.assertEquals(productResponseDto.getProductId(), responseObject.getProductId());
		Assertions.assertEquals(productResponseDto.getRateApplied(), responseObject.getRateApplied());
		Assertions.assertEquals(productResponseDto.getCurrency(), responseObject.getCurrency());
		Assertions.assertEquals(productResponseDto.getActualDate(), responseObject.getActualDate());
	}

	@Test
	void getTestNoContent() throws Exception {

		LocalDateTime actualDate = LocalDateTime.now();
		long productId = 1L;
		int brandId = 2;

		Mockito.when(productPort.find(productId, brandId, actualDate))
				.thenReturn(Optional.empty());

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product")
				.queryParam("actualDate", actualDate.toString())
				.queryParam("brandId", String.valueOf(brandId))
				.queryParam("productId", String.valueOf(productId)))
				.andReturn()
				.getResponse();

		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}

}
