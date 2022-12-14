package org.example.controller;

import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.service.BookServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public Page<Book> getBooks(@PageableDefault(sort = {"title"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return bookService.getBooks(pageable);
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.GET)
    public Page<Book> findBook(@PageableDefault(sort = {"title"}) Pageable pageable,
                                   @RequestParam("query") String query) {
        return bookService.searchProducts(query, pageable);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public Book updateBooks(@PathVariable(value = "id") Long id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PATCH)
    public Book patchBook(@PathVariable(value = "id") Long id, @RequestBody Book bookDetails) {
        return bookService.patchBook(id, bookDetails);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public void deleteBooks(@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
    }
}
