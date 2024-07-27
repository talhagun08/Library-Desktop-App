package library;

import java.time.LocalDate;

public class Book {
    private String ISBN;
    private String book_Name;
    private String author_Name;
    private boolean isBorrowed;
    private String borrowedBy;
    private LocalDate borrowDate;

    public Book(String ISBN, String book_Name, String author_Name) {
        this.ISBN = ISBN;
        this.book_Name = book_Name;
        this.author_Name = author_Name;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowDate = null;
    }

    public void displayBookInfo() {
        System.out.println("ISBN: " + ISBN);
        System.out.println("Book Name: " + book_Name);
        System.out.println("Author Name: " + author_Name);
        if (isBorrowed) {
            System.out.println("Borrowed By: " + borrowedBy);
            System.out.println("Borrow Date: " + borrowDate);
        } else {
            System.out.println("Status: Available");
        }
    }

    public boolean isAvailable() {
        return !isBorrowed;
    }

    public void borrowBook(String user, LocalDate date) {
        if (isAvailable()) {
            isBorrowed = true;
            borrowedBy = user;
            borrowDate = date;
        }
    }

    public void returnBook() {
        isBorrowed = false;
        borrowedBy = null;
        borrowDate = null;
    }

    // Getter and Setter Methods
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

    public String getAuthor_Name() {
        return author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        this.author_Name = author_Name;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
