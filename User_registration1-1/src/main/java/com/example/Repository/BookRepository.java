package com.example.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.librarymanagement.entities.Book;

@Repository
public class BookRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Transactional
	public List<Book>getAllBooks(){
		String sql="SELECT * FROM books";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}
	public int addBook(Book book) {
		
		String sql="insert into books(title,author,isbn,published_date,available_copies) VALUES(?,?,?,?,?)";
		return jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getIsbn(),book.getPublishedDate(),book.getAvailableCopies());
	}
    public int updateBook(Book book) {
    	String sql="update books SET title=?,author=?,isbn=?,published_date=?,available_copies=? WHERE id=?";
    	return jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getIsbn(),book.getPublishedDate(),book.getAvailableCopies(),book.getId());
	}
    public int deleteBook(Long bookId) {
    	String sql="delete from books where id=?";
    	try {
            return jdbcTemplate.update(sql, bookId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while deleting the book with ID: " + bookId, e);
        }
    	
    }
    public List<Book>searchBooks(String keyword){
    	String sql="SELECT*FROM books WHERE title ILIKE ?OR author ILIKE? OR isbn =?";
    	
    	String queryParam="%"+keyword+"%";
    	return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),queryParam,queryParam,keyword);
    }
	public int getAvailableCopies(Long bookId) {
		String sql = "SELECT available_copies FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
	}
	public int updateAvailableCopies(Long bookId, int newCopies) {
	    String sql = "UPDATE books SET available_copies = ? WHERE id = ?";
	    return jdbcTemplate.update(sql, newCopies, bookId);
	}

}

