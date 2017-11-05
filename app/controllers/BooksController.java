package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Controller;

import views.html.books.*;

import javax.inject.Inject;
import java.util.Set;

public class BooksController extends Controller {
    @Inject
    FormFactory formFactory;

    //for all books
    public Result index() {
        Set<Book> books = Book.allBooks();
        return ok(index.render(books));
    }

    //to create a book
    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(create.render(bookForm));

    }


    //to save a book
    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
        Book.add(book);
        return redirect(routes.BooksController.index());
    }


    //to edit a book
    public Result edit(Integer id) {
        Book book = Book.findById(id);
        if (book == null) {
            return notFound("Book " + id + " not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm));

    }


    //to update a book
    public Result update() {
        Book book = formFactory.form(Book.class).bindFromRequest().get();

        //the old book
        Book oldBook = Book.findById(book.id);

        if (oldBook == null) {
            return notFound("Book not found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;

        return redirect(routes.BooksController.index());
    }

    //for display single book
    public Result show(Integer id) {
        Book book = Book.findById(id);
        if (book == null) {
            return notFound("Book not found");

        }
        return ok(show.render(book));
    }

    //to destroy a book
    public Result destroy(Integer id) {
        Book book = Book.findById(id);
        if (book == null){
            return notFound("Book not found");
        }
        Book.removeBook(book);

        return redirect(routes.BooksController.index());
    }


}
