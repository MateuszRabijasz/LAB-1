package pl.edu.agh.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void shouldCreateBookWithValidData() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        assertAll(
                () -> assertEquals("Clean Code", book.getTitle()),
                () -> assertEquals("Robert C. Martin", book.getAuthor()),
                () -> assertEquals("9780132350884", book.getIsbn()),
                () -> assertTrue(book.isAvailable())
        );
    }

    @Test
    void shouldThrowExceptionWhenCreatingWithInvalidISBN() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Test", "Author", "123")
        );
    }

    @Test
    void shouldChangeAvailabilityStatus() {
        Book book = new Book("Effective Java", "Joshua Bloch", "9780321356680");

        book.borrowBook();
        assertFalse(book.isAvailable());

        book.returnBook();
        assertTrue(book.isAvailable());
    }

    @Test
    void shouldThrowExceptionWhenBorrowingAlreadyBorrowedBook() {
        Book book = new Book("Effective Java", "Joshua Bloch", "9780321356680");
        book.borrowBook();
        assertThrows(IllegalStateException.class, book::borrowBook);
    }

    @Test
    void shouldThrowExceptionWhenReturningNonBorrowedBook() {
        Book book = new Book("Effective Java", "Joshua Bloch", "9780321356680");
        assertThrows(IllegalStateException.class, book::returnBook);
    }
}
