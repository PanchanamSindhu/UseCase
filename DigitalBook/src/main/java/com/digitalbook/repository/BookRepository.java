package com.digitalbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitalbook.entity.Book;

/**
 * 
 * @author sindhu This is BookRepository which is used for saving book details
 *         and fetching book details from db
 *
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByTitle(String title);

	@Modifying
	@Query(value = "UPDATE book_data SET active=?3 WHERE id=?1 and book_author_id=?2", nativeQuery = true)
	Integer bookStatusUpdate(Integer bookid, Integer authorid, boolean status);
}
