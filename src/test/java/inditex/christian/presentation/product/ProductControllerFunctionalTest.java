package inditex.christian.presentation.product;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import inditex.christian.Main;
import inditex.christian.presentation.product.dto.ProductRequestDto;
import inditex.christian.presentation.product.dto.ProductResponseDto;

@SpringBootTest(classes = Main.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductControllerFunctionalTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private Integer localPort;

	@Test
	void productFinalPrizeForDay14Hour10Test() {
		
		// given
		int day = 14;
		int hour = 10;
		ProductRequestDto productBaseDto = createProduct(day, hour);

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(productBaseDto);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay14Hour16Test() {

		// given
		int day = 14;
		int hour = 16;
		ProductRequestDto productBaseDto = createProduct(day, hour);

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(productBaseDto);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(25.45D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(2, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay14Hour21Test() {

		// given
		int day = 14;
		int hour = 21;
		ProductRequestDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(productBaseDto);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay15Hour10Test() {

		// given
		int day = 15;
		int hour = 10;
		ProductRequestDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(productBaseDto);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(30.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(3, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay16Hour21Test() {

		// given
		int day = 16;
		int hour = 21;
		ProductRequestDto productBaseDto = this.createProduct(day, hour);

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(productBaseDto);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(38.95D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(this.createDate(day, hour), productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(4, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	private ResponseEntity<ProductResponseDto> callToController(ProductRequestDto productBaseDto) {
		UriComponentsBuilder paramBuilder = this.queryParamBuilder(productBaseDto.getActualDate(),
				productBaseDto.getProductId(), productBaseDto.getBrandId());

		ResponseEntity<ProductResponseDto> response = this.doGet(paramBuilder);
		return response;
	}

	private UriComponentsBuilder queryParamBuilder(LocalDateTime actualDate, Long productId, Integer brandId) {
		return UriComponentsBuilder.fromHttpUrl("http://localhost:" + localPort + "/product")
				.queryParam("actualDate", actualDate)
				.queryParam("productId", productId)
				.queryParam("brandId", brandId);
	}

	private ResponseEntity<ProductResponseDto> doGet(UriComponentsBuilder builder) {
		URI uri = builder.build()
				.encode()
				.toUri();
		return testRestTemplate.exchange(uri, HttpMethod.GET, null, ProductResponseDto.class);
	}

	private ProductRequestDto createProduct(int day, int hour) {
		ProductRequestDto productBaseDto = new ProductRequestDto();
		productBaseDto.setActualDate(this.createDate(day, hour));
		productBaseDto.setBrandId(1);
		productBaseDto.setProductId(35455L);
		return productBaseDto;
	}

	private LocalDateTime createDate(int day, int hour) {
		return LocalDateTime.of(2020, 6, day, hour, 0, 0);
	}

}
