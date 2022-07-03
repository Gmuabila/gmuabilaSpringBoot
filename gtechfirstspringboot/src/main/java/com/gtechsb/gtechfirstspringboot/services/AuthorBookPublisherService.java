package com.gtechsb.gtechfirstspringboot.services;


import com.gtechsb.gtechfirstspringboot.domain.Author;
import com.gtechsb.gtechfirstspringboot.domain.Book;
import com.gtechsb.gtechfirstspringboot.domain.Publisher;
import com.gtechsb.gtechfirstspringboot.dto.AddBookAuthorDto;
import com.gtechsb.gtechfirstspringboot.repositories.AuthorRepository;
import com.gtechsb.gtechfirstspringboot.repositories.BookRepository;
import com.gtechsb.gtechfirstspringboot.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AuthorBookPublisherService {

    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final PublisherRepository publisherRepository;

    public AuthorBookPublisherService(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<Book> getAllBooks() {
        Iterable<Book> returnedBooks = bookRepository.findAll();  //Not iterating through element of the list
        List<Book> bookList = (List<Book>) returnedBooks;         //A different approach compared to below findAll Authors
                                                                  //Note: findAll() method returns Iterable<T>.  TypeCast to change type to List<T> or
        return bookList;                                         //Set<T>.....
    }

    public List<Book> getLimitNumOfBooks(Integer limitIn) {
       // Book[] arrayBooks = new Book[limitIn];
        List<Book> returnBookList = new ArrayList<>();

        Iterable<Book> returnedBooks = bookRepository.findAll();
        List<Book> bookList = (List<Book>) returnedBooks;
        if(limitIn <= bookList.size()) {
            for (int x = 0; x < limitIn; x++) {
                // arrayBooks[x] = bookList.get(x);
                // returnBookList.add(arrayBooks[x]);
                returnBookList.add(bookList.get(x));
            }
        }
        else {
            System.out.println("The Size entered is greater than the total number of books "
                    + "\"" + bookList.size() + "\"" + " in stock\n" + "Please enter number between 1 to " + bookList.size() + ".");
        }

        return returnBookList;
    }

    public List<Author> getAllAuthors() {
    //  List<Author> authorList = new ArrayList<>();
    //  authorRepository.findAll().forEach(author -> authorList.add(author));
        Iterable<Author> returnedAuthor = authorRepository.findAll();

        return (List<Author>) returnedAuthor;

       // return authorList;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publisherList = new ArrayList<>();
        publisherRepository.findAll().forEach(publisher -> publisherList.add(publisher));

        return publisherList;
    }

    public Book getABookById(Long idIn) {
        Optional<Book> bookFromDb = bookRepository.findById(idIn);
        Book returnedBook = new Book();             //The returnedBook is of type Optional.  Getting syntax
        if(bookFromDb.isPresent()) {                //error when returning (return returnedBook;), even when typecast to Book (return (Book) returnedBook;).
            returnedBook = bookFromDb.get();        //Resolved: The findById method returns an Optional.  Use the .get() to return the Object.
        } else{                                     //Optional is a container object which may or may not contain a non-null value.
           System.out.println("Book not found");    // You must import java.util package to use this class. If a value is present,
        }                                           // isPresent() will return true and get() will return the value.
        return returnedBook;
}

    public Book getABookByIsbn(String isbnIn) {
        Book returnedBook = new Book();
//        Optional<Book> first = bookRepository.findAll().stream().filter(book -> book.getIsbn().equalsIgnoreCase(isbnIn)).findFirst();
//        if(first.isPresent()){
//            returnedBook = first.get();                     //get() will return the object,
//        } else {                                           //Book, in this case.
//            System.out.println("Book Not Found, please check ISBN and try again");
//        }
//        List<Book> result = new ArrayList<>();
//        bookRepository.findAll().forEach(book -> result.add(book));
//        Optional<Book> first = result.stream().filter(book -> book.getIsbn().equalsIgnoreCase(isbnIn)).findFirst();
//        return first.get();

       // Book returnedBook = new Book();
       bookRepository.findAll().forEach(book -> {
           if (book.getIsbn().equalsIgnoreCase(isbnIn)) {
                returnedBook.setId(book.getId());
                returnedBook.setTitle(book.getTitle());
                returnedBook.setIsbn(book.getIsbn());
            //  All 3 options below do not work why?  Instead of setting up a new book, can we not just return the returned book from the DB.
            //    return (Book) book;
            //    returnedBook = book;
            //    returnedBook = (Book) book;
            } else {
                return;
            }
        });
//
       return returnedBook;
    }

    public Author getAuthorByLastName(String lastNameIn) {

        Optional <Author> first = authorRepository.findAll().stream().filter(author -> author.getLastName().equalsIgnoreCase(lastNameIn)).findFirst();
        Author returnedAuthor = new Author();
        if(first.isPresent()){
            returnedAuthor = first.get();
        }else {
            System.out.println("Author with Lastname " + lastNameIn + " has not been found.  \nPlease check lastname and try again");
        }
//        Author returnedAuthor = new Author();
//        authorRepository.findAll().forEach(author -> {
//            if (author.getLastName().equalsIgnoreCase(lastNameIn)) {
//                returnedAuthor.setId(author.getId());
//                returnedAuthor.setLastName(author.getLastName());
//                returnedAuthor.setFirstName(author.getFirstName());
//            } else {
//                return;
//            }
//        });

       return returnedAuthor;
    }

    public Publisher getPublisherById(Long idIn) {
        Optional<Publisher> foundPublisher = publisherRepository.findById(idIn);
        Publisher returnedPublisher = new Publisher();
        if(foundPublisher.isPresent()){
            returnedPublisher = foundPublisher.get();
        }else {
            System.out.println("Publisher not found.  Please check id and try again");
        }
        return returnedPublisher;
    }

    public Author getAuthorById(Long idIn) {
        Optional<Author> returnedAuthor = authorRepository.findById(idIn);
        Author author = new Author();
        if (returnedAuthor.isPresent()) {
            author = returnedAuthor.get();
        } else {
            System.out.println("Author not found.  Please check id number and try again.");
        }
        return author;
    }

    public Book addNewBook(Book bookIn) {
        Book returnedSavedBook = new Book();
        Optional<Book> foundBook = bookRepository.findAll().stream().filter(x-> x.getIsbn().equalsIgnoreCase(bookIn.getIsbn())).findFirst();
        if (foundBook.isEmpty()){
            returnedSavedBook = bookRepository.save(bookIn);                              //No need to create an object as Spring recognises the object from the DTO
    }else {                                                                               //and will convert JSON to the object automatically.
             System.out.println("Unable to add Book.  Book already exist");
        }
        return returnedSavedBook;
    }

    public Author addNewAuthor(Author authorIn) {
        Author returnedAuthor = new Author();                         //No need to create an object as Spring recognises the object from the DTO
//        newAuthor.setFirstName(authorIn.getFirstName());            //and will convert JSON to the object automatically.
//        newAuthor.setLastName(authorIn.getLastName());
        Optional<Author> first = authorRepository.findAll().stream().filter(x -> x.getLastName().equalsIgnoreCase(authorIn.getLastName())
                        && x.getFirstName().equalsIgnoreCase(authorIn.getFirstName())).findFirst();
        if(!first.isPresent()){
            returnedAuthor = authorRepository.save(authorIn);
        }else {
            System.out.println("Unable to add this Author.  Author already exists.");
        }
//        authorRepository.findAll().forEach(author -> {
//            if (author.getLastName().equalsIgnoreCase(authorIn.getLastName()) && author.getFirstName().equalsIgnoreCase(authorIn.getFirstName())) {
//                System.out.println("Unable to add Author.  Author already exists.");
//            } else {
//                returnedAuthor = authorRepository.save(authorIn);  //Error on variable "returnedAuthor": "variable used in Lambda expression should be final or
//                // effectively final".
//            }
//        });

        return returnedAuthor;
    }

    public Publisher addNewPublisher(Publisher publisherIn) {
        Publisher returnedPublisher = new Publisher();
        Optional<Publisher> first = publisherRepository.findAll().stream().filter(publisher -> publisher.getName().equalsIgnoreCase(publisherIn.getName())
                && publisher.getPostcode().equalsIgnoreCase(publisherIn.getPostcode())).findFirst();
        if(!first.isPresent()){
            returnedPublisher = publisherRepository.save(publisherIn);
        }else {
            System.out.println("Unable to add this Publisher. Publisher already exist.");
        }
//        publisherRepository.findAll().forEach(publisher -> {
//           if(publisher.getName().equalsIgnoreCase(publisherIn.getName()) && publisher.getPostcode().equalsIgnoreCase(publisherIn.getPostcode())){
//               System.out.println("Unable to add Publisher.  Publisher already exist");
//           }else {
//              returnedPublisher = publisherRepository.save(publisherIn);   //Error on variable "returnedPublisher": "variable used in Lambda expression
//                                                                          //should be final or effectively final".
//           }
//        });
//        Publisher newPublisher = new Publisher();
//        newPublisher.setName(publisherIn.getName());
//        newPublisher.setAddressLine1(publisherIn.getAddressLine1());
//        newPublisher.setCity(publisherIn.getCity());
//        newPublisher.setPostcode(publisherIn.getPostcode());
//        newPublisher.setCountry(publisherIn.getCountry());
        //Publisher returnedSavedPublisher = publisherRepository.save(newPublisher);

        return returnedPublisher;
    }

    public Book addBookWithPublisherAndAuthor(Book bookIn){
        Publisher publisher = new Publisher();
        publisher.setName(bookIn.getPublisher().getName());
        publisher.setAddressLine1(bookIn.getPublisher().getAddressLine1());
        publisher.setCity(bookIn.getPublisher().getCity());
        publisher.setPostcode(bookIn.getPublisher().getPostcode());
        publisher.setCountry(bookIn.getPublisher().getCountry());
        Publisher returnedPublisher = publisherRepository.save(publisher);


        Set<Author> authorSet = new HashSet<>();
        bookIn.getAuthors().forEach(item -> {
            Author returnedAuthor = authorRepository.save(item);
           // bookIn.getAuthors().add(item);
            authorSet.add(returnedAuthor);
        });

//        newBook.setPublisher(bookIn.getPublisher());   //No need for these codes, create the publisher object and set with values from the DTO publisher.
//        newBook.setAuthors(bookIn.getAuthors());       //save publisher to the Database.  Create the author Set, and save all authors from the DTO Set to
//        newBook.setTitle(bookIn.getTitle());           //the Database, they will now all have IDs.  Add the returned publisher to the book object.
//        newBook.setIsbn(bookIn.getIsbn());             //For the author, add each returned author to the Database, and add it also to the Set,
                                                        //then add both the publisher object and the author Set to the book object and save the book to DB.
        bookIn.setPublisher(returnedPublisher);
        bookIn.setAuthors(authorSet);

        Book returnedSavedBook = bookRepository.save(bookIn);

        return returnedSavedBook;
    }

    //deleteBook method improved so that we do not iterate through elements in the database unnecessarily.
    //implement the same codes for all delete methods below.
    public void deleteBook(Long idIn) {
        Optional<Book> returnedBook = bookRepository.findById(idIn);
        if (returnedBook.isPresent()) {
            bookRepository.deleteById(idIn);
        } else {
            System.out.println("Unable to find book with id number: " + idIn + ".  Please verify and try again");
        }
    }

    public void deleteAuthor(Long idIn) {
        Optional<Author> returnedAuthor = authorRepository.findById(idIn);
        if (returnedAuthor.isPresent()) {
            authorRepository.deleteById(idIn);
        } else {
            System.out.println("Unable to find Author with id number: " + idIn + ".  Please verify and try again");
        }
        //Previous implementation.  No need to loop through all items.
//        authorRepository.findAll().forEach(author -> {
//            if (author.getId() == idIn) {
//                authorRepository.deleteById(idIn);
//            }
//        });

    }

    public void deletePublisher(Long idIn) {
        Optional<Publisher> returnedPublisher = publisherRepository.findById(idIn);
        if (returnedPublisher.isPresent()) {
            publisherRepository.deleteById(idIn);
        } else {  //is it best to throw an Exception here ??
            System.out.println("Unable to find a Publisher with id number: " + idIn + ".  Please verify and try again");
        }
        //Improved code above to avoid going through all element in the database.
//        publisherRepository.findAll().forEach(publisher -> {
//            if (publisher.getId() == idIn) {
//                publisherRepository.deleteById(idIn);
//            }
//        });
    }

    public Publisher updatePublisher(Publisher publisherIn) {
        Publisher publisher = new Publisher();
        Long publisherInId = publisherIn.getId();
        Optional<Publisher> returnedPublisher = publisherRepository.findById(publisherInId);
        if (returnedPublisher.isPresent()) {
            publisher = returnedPublisher.get();

            publisher.setName(publisherIn.getName());
            publisher.setAddressLine1(publisherIn.getAddressLine1());
            publisher.setCity(publisherIn.getCity());
            publisher.setPostcode(publisherIn.getPostcode());
            publisher.setCountry(publisherIn.getCountry());

            publisher = publisherRepository.save(publisher);
        }

//        **********Removing the below codes as no need to loop through the database as we can find item by id**********
//        publisherRepository.findAll().forEach(publisher -> {
//            if (publisher.getId() == publisherIn.getId()) {
//                publisher.setName(publisherIn.getName());
//                publisher.setAddressLine1(publisherIn.getAddressLine1());
//                publisher.setCity(publisherIn.getCity());
//                publisher.setPostcode(publisherIn.getPostcode());
//                publisher.setCountry(publisherIn.getCountry());
//
//                Publisher returnedSavedPublisher = publisherRepository.save(publisher);
//            }
        else {
            System.out.println("Publisher with id: " + "'" + publisherIn.getId() + "'" + " not found.  Please check id and try again.");
        }

        return publisher;
    }

    public void addBookAuthor(AddBookAuthorDto addBookAuthorDtoIn) {
        Optional<Book> returnedBook = bookRepository.findById(addBookAuthorDtoIn.getBookId());
        Optional<Author> returnedAuthor = authorRepository.findById(addBookAuthorDtoIn.getAuthorId());

        if (returnedBook.isPresent() && returnedAuthor.isPresent()) {
//            authorRepository.findAll().forEach(author -> {                    //This block of code does not work
//                if(author.getId() == addBookAuthorDtoIn.getAuthorId()){
//                    author.getBooks().add(returnedBook.get());
//                }
//            });
//            bookRepository.findAll().forEach(book -> {
//                if(book.getId() == addBookAuthorDtoIn.getBookId()){
//                    book.getAuthors().add(returnedAuthor.get());
//                }
//            });

            Book book = returnedBook.get();
            Author author = returnedAuthor.get();
            book.getAuthors().add(author);
            author.getBooks().add(book);

            bookRepository.save(book);
            authorRepository.save(author);
        }
        else {
            System.out.println("Book or Author not found.  Please check id number and try again.");
        }
    }
}