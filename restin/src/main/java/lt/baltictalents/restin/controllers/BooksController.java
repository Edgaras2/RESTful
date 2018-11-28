package lt.baltictalents.restin.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.restin.entities.Book;
import lt.baltictalents.restin.services.BookService;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService service;
    
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
        Optional<Book> auth = service.findBookById(bookId);
        if (auth.isPresent()) {
            return ResponseEntity.of(auth);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public List<Book> getBooks() {
        return service.findAllBooks();
    }
    
    @PostMapping("/{authorId}")
    @ResponseStatus(value=HttpStatus.CREATED)
    public int createBook(@RequestBody Book book, @PathVariable int authorId) {
        return service.createBook(book , authorId);
    }
    
    @PutMapping("/{bookId}")
    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    public int updateBook(@RequestBody Book book, @PathVariable int bookId) {
        return service.updateBook(book, bookId);
    }
    
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        service.deleteBook(bookId);
    }
    
    @GetMapping("/title")
    public Set<Book> getBooksByParams(@RequestParam(required=false) String title,
    								  @RequestParam(required=false)  String description,
    								  @RequestParam(required=false) String isbn,
    								  @RequestParam(required=false) String author) {
//    	List<Book> books = new ArrayList<>();
    	Set<Book> books = new HashSet<>();
    	if(title !=null && !title.isEmpty()) {
    		books.addAll(service.getBooksByTitle(title));
    	}
    	if(!StringUtils.isEmpty(description)) {
    		books.addAll(service.getBooksByDescription(description));
    	}
    	if(!StringUtils.isEmpty(isbn) ) {
    		books.addAll(service.getBooksByIsbn(isbn));
    	}
    	if(author != null && !author.isEmpty()) {
    		books.addAll(service.getBooksbyAuthor(author));
    	}
		return books;
    	
    }
}
