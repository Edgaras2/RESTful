package lt.baltictalents.restin.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.restin.entities.Author;
import lt.baltictalents.restin.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
	
	private AuthorService service;
	
	public AuthorsController(AuthorService service) {
		this.service = service;
	} 

	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
		Optional<Author> auth = service.findAuthor(id);
		if(auth.isPresent()) {
			return ResponseEntity.of(auth);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Author> getAuthors() {
		return service.findALlAuthors();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public int createAuthor(@RequestBody Author author) {
		return service.saveAuthor(author);
	}

	@PutMapping("/{id}")
	public Author updateAuthor(@RequestBody Author author, @PathVariable int id) {
		return service.updateAuthor(author, id);
	}

	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable int id) {
		service.deleteAuthorById(id);
	}
}
