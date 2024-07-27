package library;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getBook_Name());
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor_Name().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean removeBook(String ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted: " + book.getBook_Name());
            return true;
        } else {
            System.out.println("Book not found!");
            return false;
        }
    }

    public boolean updateBook(String ISBN, String newBookName, String newAuthorName) {
        Book book = findBookByISBN(ISBN);
        if (book != null) {
            book.setBook_Name(newBookName);
            book.setAuthor_Name(newAuthorName);
            return true;
        }
        return false;
    }

    public boolean borrowBook(String ISBN, String user) {
        Book book = findBookByISBN(ISBN);
        if (book != null && book.isAvailable()) {
            book.borrowBook(user, LocalDate.now());
            System.out.println("Book borrowed by " + user);
            return true;
        } else {
            System.out.println("Book is not available or not found.");
            return false;
        }
    }

    public boolean returnBook(String ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("Book returned.");
            return true;
        } else {
            System.out.println("Book is not borrowed or not found.");
            return false;
        }
    }
}
