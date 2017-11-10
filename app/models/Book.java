package models;

import java.util.HashSet;
import java.util.Set;

import io.ebean.Model;

public class Book extends Model {

    public Integer id;
    public String title;
    public Integer price;
    public String author;

    public Book(){}
    public Book(Integer id, String title, Integer price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }


    private static Set<Book> books;

    static {
        books = new HashSet<>();
        books.add(new Book(1,"C++",20,"ABC"));
        books.add(new Book(2,"Java",30,"XYZ"));

    }


    public static Set<Book> allBooks(){
        return books;
    }

    public static Book findById(Integer id){
        for (Book book: books){
            if (id.equals(book.id)){
                return book;
            }
        }
        return null;
    }

    public static void add(Book book){
        books.add(book);
    }


    public static boolean removeBook(Book book){
        return books.remove(book);
    }


}
