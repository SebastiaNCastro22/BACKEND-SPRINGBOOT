package com.example.apisimulacro202245678.controller;

import com.example.apisimulacro202245678.exception.ValidationException;
import com.example.apisimulacro202245678.model.Book;
import com.example.apisimulacro202245678.model.Employee;
import com.example.apisimulacro202245678.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class BookController {

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //Endopint (url): http://localhost:8080/api/library/v1/books
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
    }

    //Endopint (url): http://localhost:8080/api/library/v1/books/filterByEditorial
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/books/filterByEditorial")
    public ResponseEntity<List<Book>> getAllBooksByEditorial(@RequestParam(name = "editorial") String editorial){
        return new ResponseEntity<List<Book>>(bookRepository.findByEditorial(editorial), HttpStatus.OK);
    }

    //Endopint (url): http://localhost:8080/api/v1/library/books
    //Method: POST
    @Transactional
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        existsBookByTitleAndEditorial(book);
        validateBook(book);
        return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.CREATED);
    }

    private void validateBook(Book book){
        if(book.getTitle()==null || book.getTitle().trim().isEmpty()){
            throw new ValidationException("El título del libro debe ser obligatorio");
        }

        if(book.getTitle().length()>22){
            throw new ValidationException("El título del libro no debe exceder los 22 caracteres");
        }

        if(book.getEditorial()==null || book.getEditorial().trim().isEmpty()){
            throw new ValidationException("La editorial del libro debe ser obligatorio");
        }

        if(book.getEditorial().length()>14){
            throw new ValidationException("La editorial del libro no debe exceder los 14 caracteres");
        }
    }

    private void existsBookByTitleAndEditorial(Book book){
        if(bookRepository.existsByTitleAndEditorial(book.getTitle(),book.getEditorial())){
            throw new ValidationException("No se puede registrar el libro porque existe uno con el mismo título y editorial");
        }
    }

}
