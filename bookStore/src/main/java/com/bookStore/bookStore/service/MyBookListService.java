package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    public MyBookRepository mybookrepo;

    public void saveMyBooks(MyBookList book){
        mybookrepo.save(book);
    }

    public List<MyBookList> getAllBooks(){
        return  mybookrepo.findAll();

    }


    public  void deleteBook(int id){
        mybookrepo.deleteById(id);
    }
}
