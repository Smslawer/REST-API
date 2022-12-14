package org.example.service;

import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.repository.BookRepository;

@Service
public class BookServiceImpl {

    @Autowired
    BookRepository bookRepository;

    //CREATE
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    //READ
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    //SEARCH
    public Page<Book> searchProducts(String query, Pageable pageable) {
        return bookRepository.searchProducts(query, pageable);
    }

    //UPDATE
    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = bookRepository.findById(bookId).get();

        book.setTitle(bookDetails.getTitle());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(book.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrintYear(bookDetails.getPrintYear());
        book.setReadAlready(false);

        return bookRepository.save(book);
    }

    //PATCH
    public Book patchBook(Long bookId, Book bookDetails) {
        Book book = bookRepository.findById(bookId).get();

        if (!book.isReadAlready() && bookDetails.isReadAlready()) {
            book.setReadAlready(true);
        }

        return bookRepository.save(book);
    }

    //DELETE
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
