package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.BookRepository;
import com.example.librarymanagement.entities.Book;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book>getAllbooks(){
		return bookRepository.getAllBooks();
	}
	public String addbook(Book book) {
		int result=bookRepository.addBook(book);
		return result>0?"Book Added SuccessFully":"Failed to add book";
		
	}
	public String updatebook(Book book) {
		int result=bookRepository.updateBook(book);
		return result>0?"Book Updated SuccessFully":"Failed to Update book";
	}
	public String deletebook(Long id) {
		int result=bookRepository.deleteBook(id);
		return result>0?"Book id SuccessFully Deleted":"Failed to Delete book id";
	}
	public List<Book> searchBooks(String keyword){
		return bookRepository.searchBooks(keyword);
		
	}
}

