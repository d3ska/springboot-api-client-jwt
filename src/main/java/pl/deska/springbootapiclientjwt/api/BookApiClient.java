package pl.deska.springbootapiclientjwt.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.deska.springbootapiclientjwt.service.LibraryService;


@Controller
public class BookApiClient {

    private LibraryService libraryService;

    @Autowired
    public BookApiClient(LibraryService libraryService) {
        this.libraryService = libraryService;
        libraryService.getBooks();
        libraryService.addBook("Book title");
        libraryService.getBooks();
    }





}
