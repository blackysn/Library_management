package com.example.librarymanagement.service;

import com.example.Repository.BorrowRepository;
import com.example.librarymanagement.entities.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    
    @Transactional
    public String borrowBook(Long memberId, Long bookId, LocalDate borrowedDate, LocalDate dueDate) {
        try {
            
            Borrow borrow = new Borrow();
            borrow.setMemberId(memberId);  
            borrow.setBookId(bookId);      
            borrow.setBorrowedDate(Date.valueOf(borrowedDate)); 
            borrow.setDueDate(Date.valueOf(dueDate));    

            borrowRepository.save(borrow); 

            return "Book borrowed successfully!";
        } catch (Exception e) {
            return "Error borrowing the book: " + e.getMessage();
        }
    }

   
    public List<Borrow> getAllBorrowRecordsWithDetails() {
        return borrowRepository.getAllBorrowRecordsWithDetails();
    }

   
    @Transactional
    public String returnBook(Long borrowId) {
        try {
            int rowsAffected = borrowRepository.returnBook(borrowId);
            if (rowsAffected > 0) {
                return "Book returned successfully!";
            } else {
                return "Error returning the book.";
            }
        } catch (Exception e) {
            return "Error returning the book: " + e.getMessage();
        }
    }
}
