package com.example.BackendApp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired //Creates object
    BookRepository repository;
    BookValidator validator = new BookValidator();

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    @GetMapping("/bookssorted")
    public List<Book> getAllBooksinSortedOrder(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "Author"));
    }

    @GetMapping("/books/{id}")
    public Book getABook(@PathVariable int id){
        Optional<Book> object = repository.findById(id);
        if(object.isPresent()){
            return object.get();
        }
        System.out.println("Couldn't find book with this id");
        return null;
    }

    @GetMapping("/booksA/{author}")
    public List<Book> getAllBooksOfGivenAuthor(@PathVariable String author){
        List<Book> bookList1 = repository.findByAuthor(author);
        List<Book> bookList2 = repository.getBooksByAuthorName(author);
        if(bookList1.equals(bookList2)){
            System.out.println("They are same");
        }
        return bookList1;
    }

    @PostMapping("/books")
    public boolean createABook(@RequestBody Book book){
        if(validator.isValid(book)) {
            repository.save(book);
        }
        else return false;
        return true;
    }

    @DeleteMapping("books/{id}")
    public boolean deleteABook(@PathVariable int id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @PutMapping("/books")
    public boolean updateABook(@RequestBody Book book){
        repository.save(book);
        return true;
    }

    @GetMapping("books/{author}/{name}")
    public Book getBooks2(@PathVariable String author, @PathVariable String name){
        return repository.findBookByAuthorsAndName(author,name);
    }
}

