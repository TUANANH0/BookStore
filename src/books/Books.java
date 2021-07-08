/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package books;

/**
 *
 * @author Tuan
 */
public class Books {

    private String bookID, title, authorID;
    private int price;

    public Books() {

    }

    public Books(String bookID, String title, String authorID, int price) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.price = price;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorID() {
        return authorID;
    }

    public int getPrice() {
        return price;
    }

    public void output() {
        System.out.print("BookID: " + bookID);
        System.out.print("\tTitle: " + title);
        System.out.print("\tAuthorID: " + authorID);
        System.out.println("\tPrice: " + price);
    }

    @Override
    public String toString() {
        return bookID + ";" + title + ";" + authorID + ";" + price;
    }
}
