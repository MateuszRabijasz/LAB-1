package pl.edu.agh.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private Library library;
    private LibraryService libraryService;
    private Book book;

    @BeforeEach
    void setUp() {
        library = new Library();
        libraryService = new LibraryService(library);
        book = new Book("Test Book", "Author", "1234567890123");
        library.addBook(book);
    }

    @Test
    void shouldBorrowBookWithConfirmationSuccessfully() {
        String message = libraryService.borrowBookWithConfirmation("1234567890123");
        assertEquals("Wypożyczono: Test Book | Autor: Author", message);
    }

    @Test
    void shouldReturnErrorMessageIfBookNotAvailable() {
        library.borrowBook("1234567890123"); // Najpierw wypożycz
        String message = libraryService.borrowBookWithConfirmation("1234567890123");
        assertEquals("Błąd podczas wypożyczania: Książka już wypożyczona", message);
    }

    @Test
    void shouldReturnErrorMessageIfBookDoesNotExist() {
        String message = libraryService.borrowBookWithConfirmation("9999999999999");
        assertEquals("Błąd podczas wypożyczania: Książka nie istnieje", message);
    }
}
