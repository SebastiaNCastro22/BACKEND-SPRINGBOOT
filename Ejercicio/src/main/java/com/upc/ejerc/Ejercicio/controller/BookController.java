package com.upc.ejerc.Ejercicio.controller;

import com.upc.ejerc.Ejercicio.exception.ValidationException;
import com.upc.ejerc.Ejercicio.model.Book;
import com.upc.ejerc.Ejercicio.repository.BookRepository;
import com.upc.ejerc.Ejercicio.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book Controller", description = "Controller for books administration")
@RestController
@RequestMapping("/api/library/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    //URL: http://localhost:8080/api/library/v1/books

    //Method GET
    @Operation(summary = "Obtener una lista de los Libros")
    @ApiResponse(responseCode = "200", description = "Operacion Exitosa",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Book.class)))
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/library/v1/books
    //POST
    @Operation(summary = "Crea un Nuevo Libro")
    @ApiResponse(responseCode = "201", description = "Libro Creado Exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))

    @Transactional
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book){

        return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/library/v1/books/filterByEditorial
    //Method: GET

    @Operation(summary = "Obtiene la Lista de Libros por Editorial")
    @ApiResponse(responseCode = "200", description = "Operacion Exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)))
    @Transactional(readOnly = true)
    @GetMapping("/books/filterByEditorial")
    public ResponseEntity<List<Book>> getAllBooksByEditorial(@RequestParam(name = "editorial")String editorial){
        return new ResponseEntity<List<Book>>(bookRepository.findByEditorial(editorial), HttpStatus.OK);
    }

    private void validateBook(Book book){

        if (book.getTitle() == null || book.getTitle().isEmpty()){
            throw new ValidationException("The Title is obligatory");
        }

        if (book.getTitle().length()>22){
            throw new ValidationException("The title is too long");
        }

        if (book.getEditorial() == null || book.getEditorial().isEmpty()){
            throw new ValidationException("The Editorial is obligatory");
        }

        if (book.getEditorial().length()>14){
            throw new ValidationException("The Editorial is too long");
        }

    }
    private void existBookTitleAndEditorial(Book book){
        if (bookRepository.existsByTitleAndEditorial(book.getTitle(), book.getEditorial())){
            throw new ValidationException("No se puede registrar porque el titulo y editorial ya existen");
        }
    }


}
