package inditex.christian.presentation.product.handler;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
public class BadRequestExceptionHandlerTest {

	private BadRequestExceptionHandler handler = new BadRequestExceptionHandler();

	@Test
    void handleTypeMismatchTest(@Mock TypeMismatchException ex, @Mock WebRequest request) {

        String propertyName = "actualDate";
        String value = "badValue";
        Class<Integer> requiredType = Integer.class;

        Mockito.when(ex.getPropertyName()).thenReturn(propertyName);
        Mockito.when(ex.getValue()).thenReturn(value);
		Mockito.doReturn(requiredType).when(ex).getRequiredType();

        ResponseEntity<Object> response = handler.handleTypeMismatch(ex, null, HttpStatus.BAD_REQUEST, request);

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        
        Assertions.assertAll(
        		() -> Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
        		() -> Assertions.assertEquals("Parameter " + propertyName + " with value " + value +
                            " cannot be converted to required type " + requiredType, body.get("detail")),
        		() -> Assertions.assertEquals(propertyName, body.get("property")),
        		() -> Assertions.assertEquals(value, body.get("badValue")),
        		() -> Assertions.assertEquals(requiredType, body.get("requiredType")),
        		() -> Assertions.assertNotNull(body.get("timestamp"))
        );
	}
    
}
