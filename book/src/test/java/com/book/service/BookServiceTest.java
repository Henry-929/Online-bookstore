package com.book.service;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.impl.BookeServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookeServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "国哥在手a", "国哥", new BigDecimal(888), 88, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(29);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(1, "国哥在手", "国哥", new BigDecimal(888), 88, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }

}