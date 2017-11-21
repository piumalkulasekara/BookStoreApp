package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Controller;

import views.html.books.*;

import javax.inject.Inject;
import java.util.List;


public class BooksController extends Controller {
    @Inject
    FormFactory formFactory;

    //for all books
    public Result index() {
        List<Book> books = Book.finder.all();
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
        book.save();
        return redirect(routes.BooksController.index());
    }


    //to edit a book
    public Result edit(Integer id) {
        Book book = Book.finder.byId(id);
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
        Book oldBook = Book.finder.byId(book.id);

        if (oldBook == null) {
            return notFound("Book not found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;
        oldBook.update();
        return redirect(routes.BooksController.index());
    }

    //for display single book
    public Result show(Integer id) {
        Book book = Book.finder.byId(id);
        if (book == null) {
            return notFound("Book not found");

        }
        return ok(show.render(book));
    }

    //to destroy a book
    public Result destroy(Integer id) {
        Book book = Book.finder.byId(id);
        if (book == null){
            return notFound("Book not found");
        }
        book.delete();

        return redirect(routes.BooksController.index());
    }


}
