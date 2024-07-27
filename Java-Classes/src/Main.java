import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Find Book by ISBN");
            System.out.println("4. Find Books by Author");
            System.out.println("5. Remove Book");
            System.out.println("6. Update Book");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("Enter Book Name: ");
                    String book_Name = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author_Name = scanner.nextLine();
                    library.addBook(new Book(ISBN, book_Name, author_Name));
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    System.out.print("Enter ISBN: ");
                    String searchIsbn = scanner.nextLine();
                    Book book = library.findBookByISBN(searchIsbn);
                    if (book != null) {
                        book.displayBookInfo();
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    List<Book> booksByAuthor = library.findBooksByAuthor(author);
                    for (Book b : booksByAuthor) {
                        b.displayBookInfo();
                        System.out.println("--------------");
                    }
                    break;
                case 5:
                    System.out.print("Enter ISBN: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 6:
                    System.out.print("Enter ISBN of the book to update: ");
                    String updateIsbn = scanner.nextLine();
                    System.out.print("Enter new Book Name: ");
                    String newBookName = scanner.nextLine();
                    System.out.print("Enter new Author Name: ");
                    String newAuthorName = scanner.nextLine();
                    if (library.updateBook(updateIsbn, newBookName, newAuthorName)) {
                        System.out.println("Book updated successfully!");
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;
                case 7:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String borrowerName = scanner.nextLine();
                    library.borrowBook(borrowIsbn, borrowerName);
                    break;
                case 8:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }




    }


}

class Book {
    String ISBN;
    String book_Name;
    String author_Name;
    boolean  isBorrowed;
    String borrowedBy;
    LocalDate borrowDate;

    public Book(String ISBN,String book_Name, String author_Name){
        this.ISBN = ISBN;
        this.book_Name = book_Name;
        this.author_Name = author_Name;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowDate = null;

    }
    public void displayBookInfo(){
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
    public boolean isAvailable(){
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
}
class Library {
    List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.book_Name);
    }

    public void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            book.displayBookInfo();
            System.out.println("--------------");
        }
    }
    public Book findBookByISBN(String ISBN) {
        for(Book book : books){
            if (book.ISBN.equals(ISBN)){
                return book;
            }
        }
        return null;
    }
    
    public List<Book> findBooksByAuthor(String authorName){
        List<Book> result = new ArrayList<>();
        for (Book book : books){
            if(book.author_Name.equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean removeBook(String ISBN){
        Book book = findBookByISBN(ISBN);
        if (book !=null) {
            books.remove(book);
            System.out.println("Book deleted: " + book.book_Name);
            System.out.println("--------------");
            return true;
        }else{
            System.out.println("Book not found!");
            System.out.println("--------------");
            return false;
        }
    }

    public boolean updateBook(String ISBN, String newBookName, String newAuthorName){
        Book book = findBookByISBN(ISBN);
        if(book !=null){
            book.book_Name = newBookName;
            book.author_Name = newAuthorName;
            return true;

        }
        return false;

    }
    public boolean borrowBook(String ISBN, String user) {
        Book book = findBookByISBN(ISBN);
        if (book != null && book.isAvailable()) {
            book.borrowBook(user, LocalDate.now());
            System.out.println("Book borrowed by " + user);
            System.out.println("--------------");
            return true;
        } else {
            System.out.println("Book is not available or not found.");
            System.out.println("--------------");
            return false;
        }
    }

    public boolean returnBook(String ISBN) {
        Book book = findBookByISBN(ISBN);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("Book returned.");
            System.out.println("--------------");
            return true;
        } else {
            System.out.println("Book is not borrowed or not found.");
            System.out.println("--------------");
            return false;
        }
    }


}
