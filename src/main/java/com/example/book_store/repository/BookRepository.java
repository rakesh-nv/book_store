package com.example.book_store.repository;

import com.example.book_store.entity.Book;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface BookRepository extends MongoRepository<Book, String> {

    // bookId field maps to MongoDB's _id field automatically
    @Query("{ '_id' : ?0 }")
    Book findBookBookById(String bookId);

    @Query("{ '_id' : ?0 }")
    @Update("{ '$set' : { 'name' : ?1 } }")
    void updateBookNameByBookId(String bookId, String name);

    @DeleteQuery("{ '_id' : ?0 }")
    void deleteBookByBookId(String bookId);

}
