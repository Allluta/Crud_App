package pl.crudapplication.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.crudapplication.library.model.Book;
import pl.crudapplication.library.repository.LibraryRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {
    @Autowired
    LibraryRepository libraryRepository;

    @GetMapping("")
    public List<Book> getAll() {
        return libraryRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return libraryRepository.getById(id);

    }

    @PostMapping("")
    public int add(@RequestBody List<Book> books) {
        return libraryRepository.save(books);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book updateBook) {
        Book book = libraryRepository.getById(id);

        if (book != null) {
            book.setName(updateBook.getName());
            book.setRating(updateBook.getRating());
            book.setAuthor(updateBook.getAuthor());

            libraryRepository.update(book);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updateBook) {
        Book book = libraryRepository.getById(id);

        if (book != null) {
            if (updateBook.getName() != null) book.setName(updateBook.getName());
            if (updateBook.getRating() > 0) book.setRating(updateBook.getRating());
            if (updateBook.getName() != null) book.setAuthor(updateBook.getAuthor());

            libraryRepository.update(book);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return libraryRepository.delete(id);
    }
}