package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceImpl {
    private final RestTemplate restTemplate;

    public CommentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CommentResponse> getAllComments() {
        ResponseEntity<CommentResponse[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments", CommentResponse[].class);
        assert response.getBody() != null;
        return Arrays.asList(response.getBody());
    }
}
