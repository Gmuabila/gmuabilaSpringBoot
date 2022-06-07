package com.gtechsb.gtechfirstspringboot.controllers;

import com.gtechsb.gtechfirstspringboot.domain.Author;
import com.gtechsb.gtechfirstspringboot.domain.Book;
import com.gtechsb.gtechfirstspringboot.domain.Publisher;
import com.gtechsb.gtechfirstspringboot.dto.AddBookAuthorDto;
import com.gtechsb.gtechfirstspringboot.repositories.AuthorRepository;
import com.gtechsb.gtechfirstspringboot.repositories.BookRepository;
import com.gtechsb.gtechfirstspringboot.repositories.PublisherRepository;
import com.gtechsb.gtechfirstspringboot.services.AuthorBookPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authbookapi")
public class AuthorBookPublisherController {

    @Autowired
    AuthorBookPublisherService authorBookPublisherService;

   // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> retrieveAllBooks() {
        List<Book> bookList = authorBookPublisherService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/index")
    public ResponseEntity<String> getIndexPage(){
        return ResponseEntity.ok("index");
    }

    @GetMapping("/allauthor")
    public ResponseEntity<List<Author>> retrieveAllAuthor() {
        List<Author> authorList = authorBookPublisherService.getAllAuthors();
        return ResponseEntity.ok(authorList);
    }

    @GetMapping("/allpublisher")
    public ResponseEntity<List<Publisher>> retrieveAllPublisher(){
        List<Publisher> publisherList = authorBookPublisherService.getAllPublishers();

        return ResponseEntity.ok(publisherList);
    }

    @GetMapping("/bookbyid")  //Might want to pass this as Path variable.  see config below.
    public Book getBookbyId(@RequestBody Long idIn){
        Book returnedBook = authorBookPublisherService.getABookById(idIn);

        return returnedBook;
    }

    @GetMapping("/bookbyisdn/{isbnIn}")
    public Book getBookByIsdn(@PathVariable("isbnIn") String isbnIn){
        return authorBookPublisherService.getABookByIsbn(isbnIn);
    }

    @GetMapping("/authorbyname/{lastNameIn}")
    public Author getAAuthorByLastName(@PathVariable("lastNameIn") String lastNameIn){
        return authorBookPublisherService.getAuthorByLastName(lastNameIn);
    }

    @GetMapping("/publisherbyid/{idIn}")
    public Publisher getAPublisherById(@PathVariable("idIn") Long idIn){
        return authorBookPublisherService.getPublisherById(idIn);
    }

    @GetMapping("/authorbyid/{idIn}")
    public Author getAAuthorById(@PathVariable("idIn") Long idIn){
        return authorBookPublisherService.getAuthorById(idIn);
    }

    @PostMapping("/addbook")
    public Book addANewBook(@RequestBody Book bookIn){
        return authorBookPublisherService.addNewBook(bookIn);
    }

    @PostMapping("/addauthor")
    public Author addANewAuthor(@RequestBody Author authorIn){
        Author newAuthor = authorBookPublisherService.addNewAuthor(authorIn);

        return newAuthor;
    }

    @PostMapping("/addpublisher")
    public Publisher addANewPublisher(@RequestBody Publisher publisherIn){
        Publisher newPublisher = authorBookPublisherService.addNewPublisher(publisherIn);

        return newPublisher;
    }

    @PostMapping("/addbookauthor")
    public void addingBookAuthor(@RequestBody AddBookAuthorDto addBookAuthorDtoIn){
        authorBookPublisherService.addBookAuthor(addBookAuthorDtoIn);
    }

    @PostMapping("/addbookwithpublisherauthor")
    public Book addingBookWithPublisherAndAuthor(@RequestBody Book bookIn){
       Book newBook = authorBookPublisherService.addBookWithPublisherAndAuthor(bookIn);

       return newBook;
    }

    @DeleteMapping("/deletebook/{idIn}")
    public void deleteABook(@PathVariable("idIn") Long idIn){
         authorBookPublisherService.deleteBook(idIn);
    }

    @DeleteMapping("/deleteauthor/{idIn}")
    public void deleteAAuthor(@PathVariable("idIn") Long idIn){
        authorBookPublisherService.deleteAuthor(idIn);
    }

    @DeleteMapping("/deletepublisher/{idIn}")
    public void deleteAPublisher(@PathVariable("idIn") Long idIn){
        authorBookPublisherService.deletePublisher(idIn);
    }

    @PutMapping("/updatepublisher")
    public Publisher updateAPublisher(@RequestBody Publisher publisherIn){
        return authorBookPublisherService.updatePublisher(publisherIn);
    }
}
