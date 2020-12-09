package pl.deska.springbootapiclientjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@Service
public class LibraryService {

    private  TokenService tokenService;

    @Autowired
    public LibraryService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void addBook(String bookTitle) {
        String jwt = tokenService.generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(bookTitle, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8080/api/books",
                                HttpMethod.POST,
                                httpEntity,
                                Void.class);
    }


    public void getBooks(){
        String jwt = tokenService.generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> exchange = restTemplate.exchange("http://localhost:8080/api/books",
                HttpMethod.GET,
                httpEntity,
                String[].class);

        Stream.of(exchange.getBody()).forEach(System.out::println);
    }

}
