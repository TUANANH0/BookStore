/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authors;

/**
 *
 * @author Tuan
 */
public class Author {
    private String authorID, authorName;

    public Author() {
    }

    public Author(String authorID, String authorName) {
        this.authorID = authorID;
        this.authorName = authorName;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getAuthorName() {
        return authorName;
    }
    
     public void output() {
        System.out.print("AuthorID: " + authorID);
        System.out.println("\tAuthorName: " + authorName);
    }

    @Override
    public String toString() {
        return authorID + ";" + authorName;
    }
    
    
}
