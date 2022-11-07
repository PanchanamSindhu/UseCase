package com.digitalbook.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.digitalbook.entity.Book;
import com.digitalbook.repository.BookRepository;
import com.digitalbook.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author sindhu This is BookServiceImpl which is used for running methods from
 *         controller
 */
@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book saveBook(Book book, Integer authorId) {
		// TODO Auto-generated method stub
		log.info("###BookServiceImplementation - BookCreation###");
		book.setBookAuthorId(authorId);
		return bookRepository.save(book);
	}

	@Override
	public Book getBook(String title) {
		Book book = new Book();
		Optional<Book> bookOptional = bookRepository.findByTitle(title);
		if (bookOptional.isPresent()) {
			book = bookOptional.get();
		}
		return book;
	}

	@Override
	public List<Book> searchBooks(String title, String category, String author) {
		{
			List<Book> listOfBooks = new ArrayList<>();
			Boolean flag = false;

			List<Book> bookList = bookRepository.findAll();
			System.out.println("title is : " + title + " , " + "category is: " + category);
			System.out.println("boom list :" + bookList);
			if (!bookList.isEmpty()) {
				if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(category) && !StringUtils.isEmpty(author)) {
					listOfBooks = bookList.stream().filter(
							book -> ((book.getActive() == Boolean.TRUE) && (book.getTitle().equalsIgnoreCase(title)
									&& book.getCategory().toString().equalsIgnoreCase(category)
									&& book.getAuthorName().equalsIgnoreCase(author)

							))).collect(Collectors.toList());
					System.out.println("inside if loop :" + listOfBooks);
				} else {
					listOfBooks = new ArrayList<>(bookList);
					if (!title.isBlank()) {
						System.out.println("flag is in title: " + flag);
						flag = true;
						System.out.println("flag is in title after update: " + flag);
						listOfBooks = bookList.stream().filter(
								book -> (book.getActive() == Boolean.TRUE) && (book.getTitle().equalsIgnoreCase(title)))
								.collect(Collectors.toList());
					}
					if (!author.isBlank()) {
						flag = true;
						System.out.println("flag is in author after update: " + flag);
						listOfBooks = listOfBooks.stream()
								.filter(book -> (book.getActive() == Boolean.TRUE)
										&& (book.getAuthorName().equalsIgnoreCase(author)))
								.collect(Collectors.toList());
					}
					if (!category.isEmpty()) {
						flag = true;
						System.out.println("flag is in catefgory after update: " + flag);
						listOfBooks = listOfBooks.stream()
								.filter(book -> (book.getActive() == Boolean.TRUE)
										&& (book.getCategory().toString().equalsIgnoreCase(category)))
								.collect(Collectors.toList());
					}
					System.out.println("inside else loop :" + listOfBooks);
					System.out.println("flag value s " + flag);

					if (flag.equals(Boolean.FALSE)) {
						listOfBooks = new ArrayList<>();
					}
				}
			}
			return listOfBooks;
		}

	}

	@Override
	public Book bookUpdate(Book book, Integer authorId, Integer bookId) {
		log.info("###BookServiceImplementation - BookUpdate###");
//		Book b1=this.getBook(book.getTitle());
//		b1.setBookAuthorId(authorId);
		return this.bookRepository.save(book);
	}

}
