package inditex.christian.presentation.product.handler;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import inditex.christian.presentation.product.ProductController;

@RestControllerAdvice(basePackageClasses = ProductController.class)
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		
		String errorMessage = "Parameter " + ex.getPropertyName() + " with value " + ex.getValue()
		+ " cannot be converted to required type " + ex.getRequiredType();

		Map<String, Object> body = Map.of(
			"timestamp", LocalDateTime.now(),
			"detail", errorMessage,
			"property", ex.getPropertyName(),
			"badValue", ex.getValue(),
			"requiredType", ex.getRequiredType()
		);

		return ResponseEntity.badRequest()
				.body(body);
	}
}