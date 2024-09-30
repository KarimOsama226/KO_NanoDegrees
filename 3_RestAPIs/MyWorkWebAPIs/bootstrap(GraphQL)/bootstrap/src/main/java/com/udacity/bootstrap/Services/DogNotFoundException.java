package com.udacity.bootstrap.Services;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dog not found")
public class DogNotFoundException extends RuntimeException implements GraphQLError {
    private final Map<String,Object> extensions = new HashMap<>();
    public DogNotFoundException() {
        super("Dog not found");
    }
    @Override
    public List<SourceLocation> getLocations() {
        return null; // This is often used for providing the location in the query where the error occurred
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
    public DogNotFoundException(String message) {
        super("Dog not found");
    }

    public DogNotFoundException(String s, Long id) {
        super("Dog not found");
        extensions.put(s,id);
    }
}
