package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.repository.BookReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookReopsitory brepo;


    public void save(Book b){
        brepo.save(b);
    }

    public List<Book> getAllBooks(){
        return  brepo.findAll();
    }

    public Book getBookById(int id){
        return brepo.findById(id).get();
    }

    public  void deleteBook(int id){
       brepo.deleteById(id);
    }
}
