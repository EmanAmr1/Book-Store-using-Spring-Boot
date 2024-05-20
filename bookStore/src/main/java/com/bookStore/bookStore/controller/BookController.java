package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.BookDto;
import com.bookStore.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
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

//**************getBookDetails****************************
    @GetMapping("/bookDetails/{id}")
    public String bookDetails(@PathVariable int id, Model model) {

        try {
            Book b = bookService.getBookById(id);
            model.addAttribute("book", b);
            return "bookDetails";
        } catch (Exception ex) {
            System.out.println("EXCEPTION: " + ex.getMessage());
            return "redirect/bookList";
        }

    }



    //**************update book****************************
    @GetMapping("/update/{id}")
    public String showCreatePage(Model model, @PathVariable int id){
        try{
            Book b =bookService.getBookById(id);
            model.addAttribute("book",b);

            BookDto pd = new BookDto();  //take obj from bookDto to save the  edited book on it
            pd.setName(b.getName());
            pd.setAuthor(b.getAuthor());
            pd.setStory(b.getStory());
            pd.setPrice(b.getPrice());


            model.addAttribute("bookDto" ,pd);


        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect/bookList";
        }
        return "updateBook";
    }



@PostMapping("/update/{id}")
    public String updateBook(@PathVariable int id ,Model model,@ModelAttribute BookDto bookDto){

    try {
        Book b = bookService.getBookById(id);
        model.addAttribute("book", b);

        b.setName(bookDto.getName());
        b.setStory(bookDto.getStory());
        b.setAuthor(bookDto.getAuthor());
        b.setPrice(bookDto.getPrice());


        bookService.save(b);

    }catch (Exception ex){
        System.out.println("Exception: "+ex.getMessage()); }
    return "redirect:/getAllBooks";
}


//**************delete book****************************

    @GetMapping("/deleteBook/{id}")
    public  String  deleteBook(@PathVariable int id){

        bookService.deleteBook(id);
        return "redirect:/getAllBooks";
    }

}











