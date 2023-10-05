package pl.crudapplication.library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.crudapplication.library.model.Book;

import java.util.List;

@Repository
public class LibraryRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAll() {
        return jdbcTemplate.query("Select id, name, rating, author FROM book",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, rating, author FROM book WHERE " +
                "id =? ", BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    public int save(List<Book> books) {
        books.forEach(book ->jdbcTemplate
                .update("INSERT INTO book(name, rating, author) VALUES(?,?,?)",
                book.getName(),book.getRating(),book.getAuthor()
                ));

        return 1;

    }

    public int update(Book book) {
      return  jdbcTemplate.update("UPDATE book SET name=?, rating=?, author=? WHERE id=?",
                book.getName(), book.getRating(), book.getAuthor(), book.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);

    }


































}

