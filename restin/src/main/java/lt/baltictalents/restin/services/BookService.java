package lt.baltictalents.restin.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.baltictalents.restin.entities.Author;
import lt.baltictalents.restin.entities.Book;
import lt.baltictalents.restin.repositories.AuthorJPA;
import lt.baltictalents.restin.repositories.BookJPARepository;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookJPARepository repository;
    
    @Autowired
    private AuthorJPA authorRep;
    
    public List<Book> findAllBooks() {
        return repository.findAll();
    }
    
    public Optional<Book> findBookById(int id) {
        return repository.findById(id);
    }
    
    public int createBook(Book book , int authorId) {
    	book.setAuthor(authorRep.getOne(authorId));
        return repository.save(book).getId();
    }
    
    public void deleteBook(int bookId) {
        repository.deleteById(bookId);
    }
    
    public int updateBook(Book book, int id) {
        book.setId(id);
        return repository.save(book).getId();
    }

	public List<Book> getBooksByTitle(String title) {
		return repository.findByTitleStartingWith(title);
	}

	public List<Book> getBooksByDescription(String description) {
		return repository.findByDescription(description);
	}

	public Set<Book> getBooksByIsbn(String isbn) {
		return repository.findByIsbn(isbn);
	}

	public Set<Book> getBooksbyAuthor(String author) {
		return repository.findByAuthor(author);
	}
}