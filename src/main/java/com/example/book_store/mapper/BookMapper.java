package com.example.book_store.mapper;

import com.example.book_store.dto.BookDto;
import com.example.book_store.entity.Book;

public class BookMapper {

    public static BookDto toDto(Book book){
        BookDto bookDto = new BookDto(book.bookId(),book.name(),book.price(),book.author(),book.description());
        return bookDto;
    }

    public static Book toEntity(BookDto bookDto){
        // bookId is null for new books - MongoDB will auto-generate _id
        Book book = new Book(bookDto.bookId(),bookDto.name(),bookDto.price(),bookDto.author(),bookDto.description());
        return book;
    }
}
