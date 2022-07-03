package com.gtechsb.gtechfirstspringboot.controllers;

import com.gtechsb.gtechfirstspringboot.repositories.BookRepository;
import com.gtechsb.gtechfirstspringboot.services.AuthorBookPublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorBookPublisherService authorBookPublisherService;

    public BookController(BookRepository bookRepository, AuthorBookPublisherService authorBookPublisherService) {
        this.bookRepository = bookRepository;
        this.authorBookPublisherService = authorBookPublisherService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("returnedBooks", authorBookPublisherService.getAllBooks());

        return "books/allbooklist";
    }
}
