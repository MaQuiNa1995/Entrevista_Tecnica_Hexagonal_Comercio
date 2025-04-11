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
import inditex.christian.model.ProductResponseDto;

@SpringBootTest(classes = Main.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductControllerFunctionalTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private Integer localPort;

	@Test
	void productFinalPrizeForDay14Hour10Test() {
		
		// given
		LocalDateTime date = this.createDate(14, 10);
		int brandId = 1;
		long productId = 35455L;

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(date, productId, brandId);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(date, productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay14Hour16Test() {

		// given
		LocalDateTime date = this.createDate(14, 16);
		int brandId = 1;
		long productId = 35455L;

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(date, productId, brandId);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(
				() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(25.45D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(date, productDto.getActualDate()),
				() -> Assertions.assertEquals(brandId, productDto.getBrandId()),
				() -> Assertions.assertEquals(2, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency())
		);
	}

	@Test
	void productFinalPrizeForDay14Hour21Test() {

		// given
		LocalDateTime date = this.createDate(14, 21);
		int brandId = 1;
		long productId = 35455L;

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(date, productId, brandId);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(35.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(date, productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(1, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay15Hour10Test() {

		// given
		LocalDateTime localDateTime = this.createDate(15, 10);
		int brandId = 1;
		long productId = 35455L;

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(localDateTime, productId, brandId);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(30.5F, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(localDateTime, productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(3, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	@Test
	void productFinalPrizeForDay16Hour21Test() {

		// given
		LocalDateTime date = this.createDate(16, 21);
		int brandId = 1;
		long productId = 35455L;

		// when
		ResponseEntity<ProductResponseDto> response = this.callToController(date, productId, brandId);

		// then
		ProductResponseDto productDto = response.getBody();
		Assertions.assertAll(() -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()),
				() -> Assertions.assertEquals(38.95D, productDto.getFinalPrice()),
				() -> Assertions.assertEquals(date, productDto.getActualDate()),
				() -> Assertions.assertEquals(1, productDto.getBrandId()),
				() -> Assertions.assertEquals(4, productDto.getRateApplied()),
				() -> Assertions.assertEquals("EUR", productDto.getCurrency()));
	}

	private ResponseEntity<ProductResponseDto> callToController(LocalDateTime actualDate, Long productId, Integer brandId ) {
		UriComponentsBuilder paramBuilder = this.queryParamBuilder(actualDate, productId, brandId);

		return this.doGet(paramBuilder);
	}

	private UriComponentsBuilder queryParamBuilder(LocalDateTime actualDate, Long productId, Integer brandId) {
		return UriComponentsBuilder.fromUriString("http://localhost:" + localPort + "/product")
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

	private LocalDateTime createDate(int day, int hour) {
		return LocalDateTime.of(2020, 6, day, hour, 0, 0);
	}

}
