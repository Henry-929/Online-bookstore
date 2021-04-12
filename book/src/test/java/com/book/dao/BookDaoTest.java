package com.book.dao;

import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "一路向北a", "周杰伦", new BigDecimal(80), 999, 9, null));

    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(28);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(1, "辣妹子la", "周星星", new BigDecimal(80), 999, 9, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for(Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for(Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50)){
            System.out.println(book);
        }
    }
}