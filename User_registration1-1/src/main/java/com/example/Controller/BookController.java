package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
     @Autowired
     private BookService bookservice;
     
     @GetMapping
     public List<Book>getAllBooks(){
    	 return bookservice.getAllbooks();
     }
     @PostMapping
     public ResponseEntity<String>addBook(@RequestBody Book book){
    	 return ResponseEntity.ok(bookservice.addbook(book));
     }
     @PutMapping
     public ResponseEntity<String>updatebook(@RequestBody Book book){
    	 return ResponseEntity.ok(bookservice.updatebook(book));
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<String>deletebook(@PathVariable("id") Long id){
    	 return ResponseEntity.ok(bookservice.deletebook(id));
     }
     @GetMapping("/search")
     public List<Book>searchBooks(@RequestParam String Keyword){
    	 return bookservice.searchBooks(Keyword);
     }
}
