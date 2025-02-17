package inditex.christian.infrastructure.product.dao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import inditex.christian.business.domain.ProductDomain;
import inditex.christian.infrastructure.product.dao.entity.Product;
import inditex.christian.infrastructure.product.dao.entity.ProductPk;
import inditex.christian.infrastructure.product.dao.repository.ProductRepository;
import inditex.christian.infrastructure.product.port.dao.ProductDao;
import inditex.christian.infrastructure.product.port.factory.ProductDomainFactory;

@SpringBootTest
public class ProductDaoTest {
	
	@Autowired
	private ProductDao productDao;
	
	@MockitoBean
	private ProductRepository productRepository;
	
	@MockitoBean
	private ProductDomainFactory productDomainFactory;
	
	@Test
    void findByProductIdAndBrandIdAndActualDate() {

		LocalDateTime actualDate = LocalDateTime.now();
		Timestamp actualDateTimeStamp = Timestamp.valueOf(actualDate);

		Product product = new Product();
        
        ProductPk productPk = new ProductPk();
        productPk.setPriceList(5);
        productPk.setProductId(9L);
        
        product.setProductPk(productPk);
        product.setBrandId(2);
        product.setCurr(Currency.getInstance("EUR"));
		product.setEndDate(actualDateTimeStamp);
        product.setPrice(78D);
        product.setPriority(1);

		ProductDomain productDomain = new ProductDomain();
		productDomain.setActualDate(actualDate);
		productDomain.setBrandId(20);
		productDomain.setCurrency("EUR");
		productDomain.setFinalPrice(5D);
		productDomain.setPriority(80);
		productDomain.setProductId(900L);
		productDomain.setRateApplied(5);

        when(productRepository.findByProductIdAndBrandIdAndActualDate(productDomain.getProductId(), productDomain.getBrandId(), actualDateTimeStamp))
                .thenReturn(List.of(product));

        when(productDomainFactory.create(product, actualDateTimeStamp)).thenReturn(productDomain);

        List<ProductDomain> result = productDao.findByProductIdAndBrandIdAndActualDate(productDomain.getProductId(), productDomain.getBrandId(), actualDateTimeStamp);

        assertEquals(1, result.size());
        assertEquals(productDomain, result.get(0));

        verify(productRepository).findByProductIdAndBrandIdAndActualDate(productDomain.getProductId(), productDomain.getBrandId(), actualDateTimeStamp);
        verify(productDomainFactory).create(product, actualDateTimeStamp);
    }

    @Test
    void findByProductIdAndBrandIdAndActualDateNoResult() {

        when(productRepository.findByProductIdAndBrandIdAndActualDate(Mockito.anyLong(), Mockito.anyInt(), Mockito.any(Timestamp.class)))
                .thenReturn(List.of());

        List<ProductDomain> result = productDao.findByProductIdAndBrandIdAndActualDate(7L, 9, Timestamp.valueOf(LocalDateTime.now()));

        Assertions.assertTrue(result.isEmpty());

        verify(productRepository).findByProductIdAndBrandIdAndActualDate(Mockito.anyLong(), Mockito.anyInt(), Mockito.any(Timestamp.class));
    }
}
