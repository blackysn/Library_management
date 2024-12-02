package com.example.Repository;

import com.example.librarymanagement.entities.Borrow;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    @Query("""
        SELECT b FROM Borrow b
        JOIN Member m ON b.memberId = m.id
        JOIN Book bk ON b.bookId = bk.id
        """)
    public List<Borrow> getAllBorrowRecordsWithDetails();

    @Transactional
    @Query("UPDATE Borrow b SET b.returnedDate = CURRENT_DATE WHERE b.id = :borrowId")
    int returnBook(Long borrowId);
}
