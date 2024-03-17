package nl.minbzk.rig.demo.testshop.rest;

import jakarta.servlet.http.HttpServletRequest;
import nl.minbzk.rig.demo.testshop.TestshopException;
import nl.minbzk.rig.demo.testshop.rest.controller.OrderController;
import nl.minbzk.rig.demo.testshop.rest.model.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = {OrderController.class})
@EnableWebMvc
public class TestshopControllerAdvice extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(TestshopException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(statusCode);
    }
}
