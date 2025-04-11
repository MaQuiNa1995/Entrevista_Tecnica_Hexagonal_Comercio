package inditex.christian.infrastructure.product.dao.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import inditex.christian.infrastructure.product.dao.entity.Product;
import inditex.christian.infrastructure.product.dao.entity.ProductPk;

public interface ProductRepository extends JpaRepository<Product, ProductPk> {

	@Cacheable("findByProductIdAndBrandIdAndActualDate")
	@Query(value = "SELECT pro FROM Product pro WHERE pro.productPk.productId=:productId AND "
	        + "pro.brandId=:brandId AND :actualDate BETWEEN pro.startDate AND pro.endDate")
	List<Product> findByProductIdAndBrandIdAndActualDate(Long productId, Integer brandId, Timestamp actualDate);

}
