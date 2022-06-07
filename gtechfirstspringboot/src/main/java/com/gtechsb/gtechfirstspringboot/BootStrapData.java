//package com.gtechsb.gtechfirstspringboot;
//
//import com.gtechsb.gtechfirstspringboot.domain.Author;
//import com.gtechsb.gtechfirstspringboot.domain.Book;
//import com.gtechsb.gtechfirstspringboot.domain.Publisher;
//import com.gtechsb.gtechfirstspringboot.repositories.AuthorRepository;
//import com.gtechsb.gtechfirstspringboot.repositories.BookRepository;
//import com.gtechsb.gtechfirstspringboot.repositories.PublisherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//@Component
//public class BootStrapData implements CommandLineRunner {
//
//    private final BookRepository bookRepository;
//    private final AuthorRepository authorRepository;
//    private final PublisherRepository publisherRepository;
//
//    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
//        this.bookRepository = bookRepository;
//        this.authorRepository = authorRepository;
//        this.publisherRepository = publisherRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Started in Bootstrap....");
//
//        Publisher publisher1 = new Publisher();
//        publisher1.setName("Gill Monter");
//        publisher1.setAddressLine1("11 Carlos Street");
//        publisher1.setCity("London");
//        publisher1.setPostcode("W2 9GL");
//        publisher1.setCountry("UK");
//
//        publisherRepository.save(publisher1);
//
//        System.out.println("Number of Publisher: " + publisherRepository.count());
//
//        Book book1 = new Book("The rise of the sun", "10008878592");
//        Author writer = new Author("Angel", "Clark");
//        book1.getAuthors().add(writer);
//        writer.getBooks().add(book1);
//
//        book1.setPublisher(publisher1);
//        publisher1.getBooks().add(book1);
//
//        authorRepository.save(writer);
//        bookRepository.save(book1);
//        publisherRepository.save(publisher1);
//
////        Set<Author> authorSet = new HashSet<>();
////        authorSet.add(writer);
////        book1.setAuthors(authorSet);
////
////        Set<Book> booksSet = new HashSet<>();
////        booksSet.add(book1);
////        writer.setBooks(booksSet);
//        Book book2 = new Book("All good", "777700119876");
//        Author writer2 = new Author("James", "Charles");
//        writer2.getBooks().add(book2);
//        book2.getAuthors().add(writer2);
//
//        book2.setPublisher(publisher1);
//        publisher1.getBooks().add(book2);
//
//        authorRepository.save(writer2);
//        bookRepository.save(book2);
//        publisherRepository.save(publisher1);
//
//        System.out.println("Number of Books: " + bookRepository.count());
//        System.out.println("Number of items in Authors repository: " + authorRepository.count());
//        System.out.println("Number of items in Books repository: " + bookRepository.count());
//        System.out.println("Number of items in Publisher repository: " + publisherRepository.count());
//        System.out.println("Publisher1 number of books: " + publisher1.getBooks().size());
//        System.out.println("Writer2 number of books: " + writer2.getBooks().size());
//        System.out.println("Writer1 number of books: " + writer.getBooks().size());
//        System.out.println("book1 number of authors: " + book1.getAuthors().size());
//    }
//
//}
