package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import library.Library;
import library.Book;

public class LibraryAppSwing extends JFrame {
    private Library library;
    private JTextArea textArea;
    private JTextField isbnField, nameField, authorField, borrowerField;

    public LibraryAppSwing() {
        library = new Library();
        initUI();
    }

    private void initUI() {
        setTitle("Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        inputPanel.add(isbnField);

        inputPanel.add(new JLabel("Book Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Author Name:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Borrower Name:"));
        borrowerField = new JTextField();
        inputPanel.add(borrowerField);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        JButton listButton = new JButton("List Books");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listBooks();
            }
        });

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        inputPanel.add(addButton);
        inputPanel.add(listButton);
        inputPanel.add(borrowButton);
        inputPanel.add(returnButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void addBook() {
        String isbn = isbnField.getText();
        String name = nameField.getText();
        String author = authorField.getText();

        library.addBook(new Book(isbn, name, author));
        textArea.append("Book added: " + name + "\n");
    }

    private void listBooks() {
        textArea.append("Books in the library:\n");
        for (Book book : library.getBooks()) {
            textArea.append("ISBN: " + book.getISBN() + ", Name: " + book.getBook_Name() + ", Author: " + book.getAuthor_Name() + "\n");
        }
    }

    private void borrowBook() {
        String isbn = isbnField.getText();
        String borrower = borrowerField.getText();
        library.borrowBook(isbn, borrower);
        textArea.append("Book borrowed: " + isbn + " by " + borrower + "\n");
    }

    private void returnBook() {
        String isbn = isbnField.getText();
        library.returnBook(isbn);
        textArea.append("Book returned: " + isbn + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryAppSwing app = new LibraryAppSwing();
            app.setVisible(true);
        });
    }
}
