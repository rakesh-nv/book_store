package com.example.book_store.service;

import com.example.book_store.dto.BookDto;
import com.example.book_store.entity.Book;

import java.util.List;

public interface BookService {
    public BookDto getBook(String bookId);
    public List<BookDto> getAllBooks();
    public BookDto createBook(BookDto bookDto);
    public BookDto updateBookName(BookDto bookDto, String bookId);
    public void deleteBookByBookId(String BookId);
}
