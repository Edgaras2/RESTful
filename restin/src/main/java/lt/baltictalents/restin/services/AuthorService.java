package lt.baltictalents.restin.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lt.baltictalents.restin.entities.Author;
import lt.baltictalents.restin.repositories.AuthorJPA;

@Service
@Transactional
public class AuthorService {
	
	private AuthorJPA authorRep;
	
	public AuthorService(AuthorJPA authorRep) {
		this.authorRep = authorRep;
	}

	public List<Author> findALlAuthors() {
		return authorRep.findAll();
	}
	
	public Optional<Author> findAuthor(int id) {
		return authorRep.findById(id);
	}
	public int saveAuthor(Author author) {
		authorRep.save(author);
		return author.getId();
	}

	public void deleteAuthorById(int id) {
		authorRep.deleteById(id);
	}

	public Author updateAuthor(Author author, int id) {
		author.setId(id);
		return authorRep.save(author);

	}
}
