package com.example.DemoCallAPI.Controller;


import com.example.DemoCallAPI.dto.KhoaHocReponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class KhoaHocController {

    @GetMapping("/getAll")
    public List<KhoaHocReponse> callAPI() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8087/khoahoc/khoahocs";
        ResponseEntity<List<KhoaHocReponse> > response
                = restTemplate.exchange(fooResourceUrl , HttpMethod.GET, null,
                new ParameterizedTypeReference<List<KhoaHocReponse>>() {});

        List<KhoaHocReponse> courseResponses = response.getBody();
        return courseResponses;

    }
}
