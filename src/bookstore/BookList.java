/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import authors.Author;
import books.Books;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Tuan
 */
public class BookList {

    ArrayList<Books> bl = new ArrayList();
    ArrayList<Author> aal = new ArrayList();
    Scanner sc = new Scanner(System.in);

    public void addBook() {
        String bookID, title, authorID;
        int price = 0;
        boolean duplicated;
        do {
            System.out.print("BookID: ");
            bookID = sc.nextLine().trim().toUpperCase();
            duplicated = (this.findBook(bookID) == true);
            if (duplicated) {
                System.out.println("BookID duplicated.");
            }
        } while (duplicated);

        System.out.print("Title: ");
        title = sc.nextLine().trim().toUpperCase();

        do {
            System.out.print("AuthorID: ");
            authorID = sc.nextLine().trim().toUpperCase();
            duplicated = (this.findAuthor(authorID) != null);
            if (!duplicated) {
                System.out.println("Author doesn't exist!!");
            }
        } while (!duplicated);

        do {
            try {
                System.out.print("Price: ");
                price = Integer.parseInt(sc.nextLine());
                if (price >= 0) {
                    duplicated = true;
                } else {
                    System.out.println("Price cannot be negative");
                    duplicated = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be a number!");
                duplicated = false;
            }
        } while (!duplicated);

        Books newBook = new Books(bookID, title, authorID, price);
        bl.add(newBook);
        System.out.println("Add successed");
    }

    public Author findAuthor(String authorID) {
        authorID = authorID.trim().toUpperCase();
        for (Author author : aal) {
            if (author.getAuthorID().equals(authorID)) {
                return author;
            }
        }
        return null;
    }

    public boolean findBook(String bookID) {
        bookID = bookID.trim().toUpperCase();
        boolean b = false;
        for (Books book : bl) {
            if (book.getBookID().equals(bookID)) {
                b = true;
            }
        }
        return b;
    }

    public boolean SearchBook(int choice) {

        boolean have = false;
        switch (choice) {
            case 1: {
                System.out.print("The title of the book you want to find: ");
                String text = sc.nextLine().trim().toUpperCase();
                for (Books book : bl) {
                    if (book.getTitle().equals(text)) {
                        book.output();
                        have = true;
                        break;
                    }
                }
                break;
            }

            case 2: {
                System.out.print("The name of the Author you want to find: ");
                String text = sc.nextLine().trim().toUpperCase();
                for (Author author : aal) {
                    if (author.getAuthorName().equals(text)) {
                        for (Books book : bl) {
                            if (book.getAuthorID().equals(author.getAuthorID())) {
                                book.output();
                                have = true;

                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return have;
    }

    public boolean deleteAuthor(String authorName) {
        String result;
        boolean no = false;
        boolean noA = false;
        try {
            authorName = authorName.trim().toUpperCase();
            for (Author author : aal) {
                if (author.getAuthorName().equals(authorName)) {
                    for (Books book : bl) {
                        if (book.getAuthorID().equals(author.getAuthorID())) {
                            System.out.println("This author has a book in the store, you cannot delete this author");
                            noA = false;
                            break;
                        } else {
                            noA = true;
                        }
                    }
                    no = true;
                    if (noA) {
                        System.out.print("Are you sure you want to delete? ");
                        result = sc.nextLine().trim().toUpperCase();
                        if (result.contains("Y")) {
                            aal.remove(author);
                            System.out.println("Author be deleted");
                        } else {
                            System.out.println("Author don't deleted");
                        }
                    }
                }
            }

        } catch (Exception e) {
        }
        return no;
    }

    public boolean updateBook(String bookID) {
        boolean result = false, duplicate = true;
        bookID = bookID.trim().toUpperCase();
        String newTitle;
        int newPrice = 0;
        try {
            for (Books book : bl) {
                if (book.getBookID().equals(bookID)) {
                    System.out.print("New Title: ");
                    newTitle = sc.nextLine().trim().toUpperCase();
                    if (newTitle.equals("")) {
                        newTitle = book.getTitle();
                    } else {
                        newTitle = newTitle;
                    }
                    boolean duplicated;

                    do {
                        try {
                            System.out.print("Price: ");
                            newPrice = Integer.parseInt(sc.nextLine());
                            if (newPrice >= 0) {
                                duplicate = true;
                            } else {
                                System.out.println("Price cannot be negative");
                                duplicate = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Price must be a number!");
                            duplicate = false;
                        }
                    } while (!duplicate);

                    Books newBook = new Books(bookID, newTitle, book.getAuthorID(), newPrice);
                    bl.add(newBook);
                    System.out.println("Update successed");
                    bl.remove(book);
                    result = true;
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public boolean writeToFileBook(String fName) throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fName);
            pw = new PrintWriter(fw);
            for (int i = 0; i < bl.size(); i++) {
                pw.println(bl.get(i));
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
        return true;
    }

    public boolean writeToFileAuthor(String faName) throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(faName);
            pw = new PrintWriter(fw);
            for (int i = 0; i < aal.size(); i++) {
                pw.println(aal.get(i));
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
        return true;
    }

    public void loadBooksFromFile(String fName) throws IOException {
        boolean check = true;
        FileReader fr = null;
        BufferedReader bf = null;
        String line = "";
        StringTokenizer stk = null;
        try {
            fr = new FileReader(fName);
            bf = new BufferedReader(fr);
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("//")) {
                    stk = new StringTokenizer(line, ";");
                    String bookID = stk.nextToken().toUpperCase();
                    String title = stk.nextToken().toUpperCase();
                    String authorID = stk.nextToken().toUpperCase();
                    int price = Integer.parseInt(stk.nextToken());
                    if (check) {
                        Books addBook = new Books(bookID, title, authorID, price);
                        bl.add(addBook);
                    }
                }
            }
        } catch (Exception e) {
//            System.out.println(e);
        } finally {
            if (bf != null) {
                bf.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public void showBook() {
        if (bl.isEmpty()) {
            System.out.println("List is empty");
        } else {
            bl.forEach((book) -> {
                book.output();
            });
        }
    }

    public void showAuthor() {
        if (aal.isEmpty()) {
            System.out.println("List is empty");
        } else {
            aal.forEach((author) -> {
                author.output();
            });
        }
    }

    public void loadAuthorsFromFile(String fName) throws IOException {
        FileReader fr = null;
        BufferedReader bf = null;
        String line = "";
        StringTokenizer stk = null;
        try {
            fr = new FileReader(fName);
            bf = new BufferedReader(fr);
            while ((line = bf.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("//")) {
                    stk = new StringTokenizer(line, ";");
                    String authorID = stk.nextToken();
                    String authorName = stk.nextToken();
                    Author addAuthor = new Author(authorID.trim().toUpperCase(), authorName.trim().toUpperCase());
                    aal.add(addAuthor);
                }
            }
        } catch (IOException e) {
//            System.out.println(e);
        } finally {
            if (bf != null) {
                bf.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public boolean deleteBook(String bookID) {
        boolean result = false;
        String action;
        bookID = bookID.trim().toUpperCase();
        for (Books book : bl) {
            if (bookID.equals(book.getBookID())) {
                book.output();
                System.out.print("Are you sure delete book[Y/N]? ");
                action = sc.nextLine().trim().toUpperCase();
                if ("Y".equals(action)) {
                    bl.remove(book);
                    System.out.println("Deleted successfully.");
                } else if ("N".equals(action)) {
                    System.out.println("The book has not been deleted.");
                }
                result = true;
                break;
            }

        }
        return result;
    }

}
