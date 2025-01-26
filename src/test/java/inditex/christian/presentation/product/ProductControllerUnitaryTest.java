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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.business.port.presentation.ProductPort;
import inditex.christian.presentation.product.dto.ProductRequestDto;
import inditex.christian.presentation.product.dto.ProductResponseDto;
import inditex.christian.presentation.product.factory.ProductResponseDtoFactory;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
public class ProductControllerUnitaryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<ProductResponseDto> productResponseDtoJackson;

	@MockBean
	private ProductPort productPort;

	@MockBean
	private ProductResponseDtoFactory productResponseDtoFactory;

	@Test
	void getTest() throws Exception {

		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setActualDate(LocalDateTime.now());
		productResponseDto.setBrandId(2);
		productResponseDto.setCurrency("EUR");
		productResponseDto.setFinalPrice(2D);
		productResponseDto.setProductId(1L);
		productResponseDto.setRateApplied(99);
		
		ProductRequestDto productRequestDto = new ProductRequestDto();
		productRequestDto.setActualDate(LocalDateTime.now());
		productRequestDto.setBrandId(2);
		productRequestDto.setProductId(1L);
		
		ProductDomain productDomain = new ProductDomain();
		productDomain.setActualDate(LocalDateTime.now());
		productDomain.setBrandId(20);
		productDomain.setCurrency("EUR");
		productDomain.setFinalPrice(5D);
		productDomain.setPriority(80);
		productDomain.setProductId(900L);
		productDomain.setRateApplied(5);

		Mockito.when(productPort.find(productRequestDto.getProductId(), productRequestDto.getBrandId(),
				productRequestDto.getActualDate())).thenReturn(Optional.of(productDomain));
		
		Mockito.when(productResponseDtoFactory.create(Mockito.any(ProductDomain.class)))
				.thenReturn(productResponseDto);

		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product")
				.queryParam("actualDate", productRequestDto.getActualDate().toString())
				.queryParam("brandId", productRequestDto.getBrandId().toString())
				.queryParam("productId", productRequestDto.getProductId().toString()))
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
		
		ProductRequestDto productRequestDto = new ProductRequestDto();
		productRequestDto.setActualDate(LocalDateTime.now());
		productRequestDto.setBrandId(2);
		productRequestDto.setProductId(1L);
		
		Mockito.when(productPort.find(productRequestDto.getProductId(), productRequestDto.getBrandId(),
				productRequestDto.getActualDate())).thenReturn(Optional.empty());
		
		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product")
				.queryParam("actualDate", productRequestDto.getActualDate().toString())
				.queryParam("brandId", productRequestDto.getBrandId().toString())
				.queryParam("productId", productRequestDto.getProductId().toString()))
				.andReturn()
				.getResponse();
		
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}

}
