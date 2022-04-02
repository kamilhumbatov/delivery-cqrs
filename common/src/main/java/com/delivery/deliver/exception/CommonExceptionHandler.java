package com.delivery.deliver.exception;

import com.delivery.deliver.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class CommonExceptionHandler {

    public ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        return buildErrorResponse(
                exception,
                exception.getMessage(),
                httpStatus,
                request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
