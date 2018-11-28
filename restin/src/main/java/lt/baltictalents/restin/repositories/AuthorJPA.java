package lt.baltictalents.restin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.restin.entities.Author;

public interface AuthorJPA extends JpaRepository<Author, Integer> {

	
}
