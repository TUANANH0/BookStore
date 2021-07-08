/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tuan
 */
public class BookStore {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String fbName = "books.dat";
        String faName = "authors.dat";
        BookList bl = new BookList();
        bl.loadBooksFromFile(fbName);
        bl.loadAuthorsFromFile(faName);
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        String conti;
        do {
            try {
                System.out.println("---WElCOME BOOKSTORE---");
                System.out.println("1. Show the book list\n"
                        + "2. Add new book\n"
                        + "3. Update book\n"
                        + "4. Delete Author\n"
                        + "5. Search book\n"
                        + "6. Store data to file\n"
                        + "7. Delete Book\n"
                        + "8. Quit");

                System.out.print("Select the function you want: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.println("\t---Books---");
                        bl.showBook();
                        System.out.println("\t---Authors---");
                        bl.showAuthor();
                        break;
                    }

                    case 2: {
                        do {
//                            sc.nextLine();
                            bl.addBook();
                            System.out.print("Do you want to continue adding books? ");
                            conti = sc.next().trim().toUpperCase();
                        } while (conti.equals("Y"));
                        break;
                    }

                    case 3: {
                        do {
                            System.out.print("Book you want to Update. BookID: ");
                            String bookID = sc.nextLine().trim().toUpperCase();
                            //bl.updateBook(bookID);
                            boolean result = bl.updateBook(bookID);
                            if (result == false) {
                                System.out.println("Book does not exist.");
                            }
                            System.out.print("Do you want to continue updating other books? ");
                            conti = sc.nextLine().trim().toUpperCase();
                        } while (conti.contains("Y"));
                        break;
                    }

                    case 4: {
                        do {
//                            sc.nextLine();
                            System.out.print("Enter the AuthorName you want to delete: ");
                            String authorName = sc.nextLine().trim().toUpperCase();
                            boolean no = bl.deleteAuthor(authorName);
                            if (!no) {
                                System.out.println("Author does not exist.");
                            }
                            bl.writeToFileAuthor(faName);
                            System.out.print("Do you want to continue deleting other author? ");
                            conti = sc.nextLine().trim().toUpperCase();
                        } while (conti.contains("Y"));
                        break;
                    }

                    case 5: {
                        do {
                            System.out.println("1 Search by book name\n"
                                    + "2 Search by author name");
                            System.out.print("Which way do you want to choose? ");
                            int choose = Integer.parseInt(sc.nextLine());
                            boolean result = bl.SearchBook(choose);
                            if (result == false) {
                                System.out.println("Book not found");
                            }
                            System.out.print("Do you want to keep looking for other books? ");
                            conti = sc.nextLine().trim().toUpperCase();
                        } while (conti.contains("Y"));
                        break;
                    }

                    case 6: {
                        bl.writeToFileBook(fbName);
                        System.out.println("Writing file: DONE.");
                        break;
                    }

                    case 7: {
                        do {
//                            sc.nextLine();
                            System.out.print("Enter the BookID you want to delete: ");
                            String bookID = sc.nextLine().trim().toUpperCase();
                            boolean no = bl.deleteBook(bookID);
                            if (!no) {
                                System.out.println("Book does not exist.");
                            }
                            bl.writeToFileBook(fbName); 
                            System.out.print("Do you want to continue deleting other books? ");
                            conti = sc.nextLine().trim().toUpperCase();
                        } while (conti.contains("Y"));
                        break;
                    }

                    case 8: {
                        System.out.println("END GAME!!");
                        System.exit(0);
                        break;
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Enter 1 to 8.");
            }
        } while (choice >= 1 && choice <= 8);
    }
}
