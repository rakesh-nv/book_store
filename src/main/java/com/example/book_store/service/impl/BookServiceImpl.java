package com.example.book_store.service.impl;

import com.example.book_store.dto.BookDto;
import com.example.book_store.entity.Book;
import com.example.book_store.mapper.BookMapper;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getBook(String bookId) {
        Book book = bookRepository.findBookBookById(bookId);
        BookDto bookDto = BookMapper.toDto(book);
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : books) {
            bookDtoList.add(BookMapper.toDto(book));
        }
        return bookDtoList;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        // Create book without bookId - MongoDB will auto-generate _id
        Book toInsert = new Book(null, bookDto.name(), bookDto.price(), bookDto.author(), bookDto.description());
        Book book = bookRepository.insert(toInsert);
        return BookMapper.toDto(book);
    }

    @Override
    public BookDto updateBookName(BookDto bookDto,String bookId) {
        bookRepository.updateBookNameByBookId(bookId, bookDto.name());
        Book book = bookRepository.findBookBookById(bookId);
        return BookMapper.toDto(book);
    }

    @Override
    public void deleteBookByBookId(String bookId) {
        bookRepository.deleteBookByBookId(bookId);
    }
}
