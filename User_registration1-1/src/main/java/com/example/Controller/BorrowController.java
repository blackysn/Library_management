package com.example.Controller;

import com.example.librarymanagement.service.BorrowService;
import com.example.librarymanagement.entities.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Endpoint to borrow a book
    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(@RequestParam Long memberId, 
                                             @RequestParam Long bookId, 
                                             @RequestParam String borrowedDate, 
                                             @RequestParam String dueDate) {
        try {
            // Parse the dates
            LocalDate borrowedDateParsed = LocalDate.parse(borrowedDate);
            LocalDate dueDateParsed = LocalDate.parse(dueDate);

            // Call the service to borrow the book
            String result = borrowService.borrowBook(memberId, bookId, borrowedDateParsed, dueDateParsed);
            return ResponseEntity.status(201).body(result);  // return success message with 201 status
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error borrowing the book: " + e.getMessage());
        }
    }

    // Endpoint to get all borrow records with details
    @GetMapping("/all")
    public ResponseEntity<List<Borrow>> getAllBorrowRecordsWithDetails() {
        try {
            List<Borrow> borrowRecords = borrowService.getAllBorrowRecordsWithDetails();
            return ResponseEntity.ok(borrowRecords);  // return list of borrow records with 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);  // return error if something fails
        }
    }

    // Endpoint to return a book
    @PutMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam Long borrowId) {
        try {
            String result = borrowService.returnBook(borrowId);
            return ResponseEntity.ok(result);  // return success message with 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error returning the book: " + e.getMessage());
        }
    }
}
