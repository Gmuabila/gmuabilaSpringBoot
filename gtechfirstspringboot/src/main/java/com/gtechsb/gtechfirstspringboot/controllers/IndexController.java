package com.gtechsb.gtechfirstspringboot.controllers;

import com.gtechsb.gtechfirstspringboot.domain.Book;
import com.gtechsb.gtechfirstspringboot.domain.User;
import com.gtechsb.gtechfirstspringboot.services.AuthorBookPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/booksapi")
public class IndexController {


    private final AuthorBookPublisherService authorBookPublisherService;

    public IndexController(AuthorBookPublisherService authorBookPublisherService) {
        this.authorBookPublisherService = authorBookPublisherService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("firstName","Johnny");
        model.addAttribute("lastName","Karla");

        return "test/index";

    }

    @GetMapping("/indexbooks")
    public String getBooks(Model model){
        model.addAttribute("book", new Book());
        return "books/bookshome";
    }

    @GetMapping("/allbooks")
//    public String getBooksIndexPage(Model model, ServerWebExchange serverWebExchange){
    public String getAllBooksPage(@RequestParam Map<String, String> map, Model model){
//        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();
        model.addAttribute("returnedBooks", authorBookPublisherService.getAllBooks());

        return "books/allbooklist";
    }

    @PostMapping("/limitbookslist")
//    public String getBooksIndexPage(Model model, ServerWebExchange serverWebExchange){
    public String getLimitedBooksList(@RequestParam Map<String, String> map, Model model) {
//        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();
        Integer limitIn = Integer.parseInt(map.get("limit"));

        log.debug("Received Limit value" + limitIn);
        if (limitIn == 0 || limitIn == null) {
            log.debug("Setting value to default of 10");
            limitIn = 10;
        }
        model.addAttribute("limitedBooks", authorBookPublisherService.getLimitNumOfBooks(limitIn));

            return "books/limitedbooklist";
    }

//    @PostMapping("/addBookfrombrowser")
//    public String addingBookFromBrowser(@RequestParam Map<String, String> map, Model model){
//        Book book = new Book();
////        book.setTitle(bookIn.getTitle());
////        book.setIsbn(bookIn.getIsbn());
//        book.setTitle(map.get("title"));
//        book.setIsbn(map.get("isbn"));
//
//        model.addAttribute("addedbook", authorBookPublisherService.addNewBook(book));
//
//        return "books/bookadded";
//
//    }

    @PostMapping("/addBookfrombrowser")
    public String addingBookFromBrowser(@ModelAttribute Book book, Model model){
//        Code does not work, no error page was rendered when error occurred.
//        To try the code again add the following parameter "BindingResult result"
//        if(result.hasErrors()){
//            return "books/error";
//        }
        model.addAttribute("addedbook", authorBookPublisherService.addNewBook(book));

        return "books/bookadded";

    }

    @GetMapping("/register")
    public String showForm(Model model){
        User user = new User();
        model.addAttribute("user", user);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);
        return "books/register_form";
    }


}
