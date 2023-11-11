package com.upc.ejerc.Ejercicio.repository;

import com.upc.ejerc.Ejercicio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByEditorial(String editorial);
    Boolean existsByTitleAndEditorial(String title, String editorial);

}
