package com.app.book.controller;

import com.app.book.domain.Book;
import com.app.book.dto.BookDto;
import com.app.book.exception.BookNotFoundException;
import com.app.book.mapper.BookMapper;
import com.app.book.service.BookService;
import com.app.library.borrow.domain.Borrow;
import com.app.library.borrow.service.BorrowService;
import com.app.user.domain.User;
import com.app.user.dto.UserDto;
import com.app.user.mapper.UserMapper;
import com.app.user.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BorrowService borrowService;

    @Before
    public void before() {
        LOGGER.info("Start testing BookController Class");
    }

    @After
    public void after() {
        LOGGER.info("Finished testing BookController Class");
    }

    @Test
    public void testShouldGetBooks() {
        //Given
        bookService.saveBook(new Book("Lśnienie", "Stephen King", "1977", false));
        //When
        List<BookDto> result = bookController.getBooks();
        //Then
        assertEquals(1, result.size());
        //CleanUp
        bookService.deleteBook(result.get(0).getId());
    }

    @Test
    public void testShouldGetBook() {
        //Given
        bookService.saveBook(new Book("Lśnienie", "Stephen King", "1977", false));
        Long bookId = bookController.getBooks().get(0).getId();
        System.out.println("Book ID: " + bookId);
        //When
        try {
            BookDto result = bookController.getBook(bookId);
            System.out.println("Result ID: " + result.getId());
            //Then
            assertEquals(bookId, result.getId());
            assertEquals("Lśnienie", result.getTitle());
            assertEquals("Stephen King", result.getAuthor());
            assertFalse(result.isRented());
            //CleanUp
            bookService.deleteBook(result.getId());
        }catch (BookNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldCreateBook() {
        //Given
        //When
        bookController.createBook(bookMapper.mapToBookDto(new Book("Lśnienie", "Stephen King", "1977", false)));
        Long bookId = bookController.getBooks().get(0).getId();
        try{
            //Then
            assertEquals("Lśnienie", bookController.getBook(bookId).getTitle());
            assertEquals("Stephen King", bookController.getBook(bookId).getAuthor());
            assertEquals("1977", bookController.getBook(bookId).getYearOfPublication());
            assertFalse(bookController.getBook(bookId).isRented());
            //CleanUp
            bookService.deleteBook(bookId);
        } catch (BookNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldUpdateBook() {
        bookService.saveBook(new Book("Ogniem i Mieczem", "Henryk Sienkiewicz", "1884", false));
        Long bookId = bookController.getBooks().get(0).getId();
        BookDto ogniemIMieczem = new BookDto();
        ogniemIMieczem.setId(bookId);
        ogniemIMieczem.setTitle("Mieczem i Ogniem");
        //When
        bookController.updateBook(ogniemIMieczem);
        //Then
        try{
            assertEquals("Mieczem i Ogniem", bookController.getBook(bookId).getTitle());
            //CleanUp
            bookService.deleteBook(bookId);
        } catch (BookNotFoundException ignored) {
        }
    }

    @Test
    public void testShouldDeleteBook() {
        //Given
        bookService.saveBook(new Book("Quo Vadis", "Henryk Sienkiewicz", "1895", false));
        Long bookId = bookService.getAllBooks().get(0).getId();
        int sizeBefore = bookController.getBooks().size();
        //When
        bookController.deleteBook(bookId);
        int sizeAfter = bookController.getBooks().size();
        //Then
        assertEquals(1, sizeBefore);
        assertEquals(0, sizeAfter);
    }

    @Test
    public void testShouldBorrowBook() {
        //Given
        User jamesBond = userService.saveUser(new User("James", "Bond"));
        Book quoVadis = bookService.saveBook(new Book("Quo Vadis", "Henryk Sienkiewicz", "1895", false));
        UserDto dtoJamesBond = userMapper.mapToUserDto(jamesBond);
        BookDto dtoQuoVadis = bookMapper.mapToBookDto(quoVadis);
        //When
        bookController.borrowBook(dtoJamesBond, dtoQuoVadis);
        //Then
        assertTrue(bookService.findBookById(quoVadis.getId()).get().isRented());
        //CleanUp
        userService.deleteUser(jamesBond.getId());
        bookService.deleteBook(quoVadis.getId());
        borrowService.deleteAll();
    }

    @Test
    public void testShouldDeniedBorrow() {
        //Given
        User jamesBond = userService.saveUser(new User("James", "Bond"));
        Book quoVadis = bookService.saveBook(new Book("Quo Vadis", "Henryk Sienkiewicz", "1895", true));
        UserDto dtoJamesBond = userMapper.mapToUserDto(jamesBond);
        BookDto dtoQuoVadis = bookMapper.mapToBookDto(quoVadis);
        //When
        boolean result = bookController.borrowBook(dtoJamesBond, dtoQuoVadis);
        //Then
        assertFalse(result);
        //CleanUp
        userService.deleteUser(jamesBond.getId());
        bookService.deleteBook(quoVadis.getId());
    }

    @Test
    public void testShouldReturnBook() {
        //Given
        User jamesBond = userService.saveUser(new User("James", "Bond"));
        Book quoVadis = bookService.saveBook(new Book("Quo Vadis", "Henryk Sienkiewicz", "1895", false));
        UserDto dtoJamesBond = userMapper.mapToUserDto(jamesBond);
        BookDto dtoQuoVadis = bookMapper.mapToBookDto(quoVadis);

        bookController.borrowBook(dtoJamesBond, dtoQuoVadis);
        //When
        bookController.returnBook(dtoQuoVadis);
        //Then
        assertFalse(bookService.findBookById(quoVadis.getId()).get().isRented());
        //CleanUp
        userService.deleteUser(jamesBond.getId());
        bookService.deleteBook(quoVadis.getId());
        borrowService.deleteAll();
    }


}