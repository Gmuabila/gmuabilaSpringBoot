package com.gtechsb.gtechfirstspringboot;

import com.gtechsb.gtechfirstspringboot.domain.Book;
import com.gtechsb.gtechfirstspringboot.repositories.AuthorRepository;
import com.gtechsb.gtechfirstspringboot.repositories.BookRepository;
import com.gtechsb.gtechfirstspringboot.repositories.PublisherRepository;
import com.gtechsb.gtechfirstspringboot.services.AuthorBookPublisherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class testCreateBookWithPublisherAndAuthorSet {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private final AuthorBookPublisherService authorBookPublisherService = new AuthorBookPublisherService(
            authorRepository, bookRepository, publisherRepository);

    @Test
    public void testGetAllBooks(){
//       List<Book> allBooksList = authorBookPublisherService.getAllBooks();
//
//       System.out.println(allBooksList);

        Iterable<Book> returnedBooks = bookRepository.findAll();
        List<Book> allBooks =(List<Book> ) returnedBooks;
        System.out.println(allBooks);
//       Optional<Book> first = al;
//       System.out.println(first);

    }
}
