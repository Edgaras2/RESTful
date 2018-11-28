package lt.baltictalents.restin.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lt.baltictalents.restin.entities.Book;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitleStartingWith(String title);

	@Query("SELECT b FROM Book b where b.description LIKE %?1%")
	List<Book> findByDescription(String description);

	@Query("SELECT i FROM Book i WHERE i.isbn = :isbn_param")
	Set<Book> findByIsbn(@Param("isbn_param") String isbn);
	
	Set<Book> findByAuthorFirstname(String author);
	
	@Query("SELECT n FROM Book n WHERE n.author.firstname LIKE %?1% OR n.author.lastname LIKE %?1%")
	Set<Book> findByAuthor(String author);
}
