package com.digitalbook.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author sindhu
 * Book entity is used for declaring the details of book and validation of book details
 *
 */

@Component
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String logo;
	private String title;
	@Enumerated(EnumType.STRING)
	private BookCategory category;
	private BigDecimal price;
	private String authorUserName;
	private String authorName;
	private String publisher;
	private String content;
	private Boolean active;

	@DateTimeFormat(style = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;

	public Book(int id, String logo, String title, BookCategory category, BigDecimal price, String authorUserName,
			String authorName, String publisher, String content, Boolean active, LocalDate publishedDate) {
		super();
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.category = category;
		this.price = price;
		this.authorUserName = authorUserName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.content = content;
		this.active = active;
		this.publishedDate = publishedDate;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAuthorUserName() {
		return authorUserName;
	}

	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", logo=" + logo + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", authorUserName=" + authorUserName + ", authorName=" + authorName + ", publisher=" + publisher
				+ ", content=" + content + ", active=" + active + ", publishedDate=" + publishedDate + "]";
	}

}
