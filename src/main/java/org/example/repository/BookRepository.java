package org.example.repository;

import org.example.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT book FROM Book book WHERE " +
            "LOWER(book.title) LIKE LOWER(CONCAT('%',:query, '%'))" +
            "OR LOWER(book.description) LIKE LOWER(CONCAT('%', :query, '%'))" +
            "OR LOWER(book.author) LIKE LOWER(CONCAT('%', :query, '%'))" +
            "OR LOWER(book.isbn) LIKE LOWER(CONCAT('%', :query, '%'))" )
    Page<Book> searchProducts(String query, Pageable pageable);
}
