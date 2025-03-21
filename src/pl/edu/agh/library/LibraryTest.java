package pl.edu.agh.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Book book;

    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("Test Book", "Author", "1234567890123");
        library.addBook(book);
    }

    @Test
    void shouldAddBookSuccessfully() {
        assertEquals(1, library.getTotalBooksCount());
    }

    @Test
    void shouldNotAllowDuplicateISBN() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(new Book("Another Book", "Author", "1234567890123")));
    }

    @Test
    void shouldBorrowBookSuccessfully() {
        library.borrowBook("1234567890123");
        assertEquals(0, library.getAvailableBooksCount());
    }

    @Test
    void shouldNotBorrowAlreadyBorrowedBook() {
        library.borrowBook("1234567890123");
        assertThrows(IllegalStateException.class, () -> library.borrowBook("1234567890123"));
    }

    @Test
    void shouldReturnBookSuccessfully() {
        library.borrowBook("1234567890123");
        library.returnBook("1234567890123");
        assertEquals(1, library.getAvailableBooksCount());
    }

    @Test
    void shouldNotReturnNonBorrowedBook() {
        assertThrows(IllegalArgumentException.class, () -> library.returnBook("9999999999999"));
    }

}
