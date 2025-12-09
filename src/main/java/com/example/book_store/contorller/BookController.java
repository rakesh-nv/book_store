package com.example.book_store.contorller;

import com.example.book_store.dto.BookDto;
import com.example.book_store.response.ResponseHandler;
import com.example.book_store.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookController {

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    BookService bookService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage() {
        return new ResponseEntity<>("Welcome To the Book Store", HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getBook(@PathVariable String bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK, bookDto);
//        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllBooks() {
        List<BookDto> bookDtosList = bookService.getAllBooks();
        return ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK, bookDtosList);
//        return new ResponseEntity<>(bookDtosList, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> createBooks(@RequestBody BookDto bookDto) {
        BookDto book = bookService.createBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable String bookId) {
        BookDto bookDto1 = bookService.updateBookName(bookDto, bookId);
        return new ResponseEntity<>(bookDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        bookService.deleteBookByBookId(bookId);
        return new ResponseEntity<>("Book Deleted Successfully", HttpStatus.OK);
    }
}
