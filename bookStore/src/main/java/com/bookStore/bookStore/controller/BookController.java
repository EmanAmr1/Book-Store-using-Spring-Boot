package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //*****************home********************
    @GetMapping("/")
    public String home(){
        return "home";
    }



    //**********************book register**************************
    @GetMapping("/book_register")
    public String ShowBookRegister(Model model){
        model.addAttribute("book",new Book());
        return "bookRegister";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/getAllBooks";
    }




    //**************getAllBooks****************************
    @GetMapping("/getAllBooks")
    public String AllBooks(Model model){
        List<Book> B=bookService.getAllBooks();
        model.addAttribute("books",B);
       return "bookList";
    }



}
