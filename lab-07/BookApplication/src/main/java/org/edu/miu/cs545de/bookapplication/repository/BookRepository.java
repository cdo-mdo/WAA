package org.edu.miu.cs545de.bookapplication.repository;

import org.edu.miu.cs545de.bookapplication.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {

    private final Map<Long, Book> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public BookRepository() {
        // sample data
        save(new Book(null, "Clean Code", "Robert C. Martin", "9780132350884", 45.0));
        save(new Book(null, "Effective Java", "Joshua Bloch", "9780134685991", 55.0));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            long id = idGenerator.incrementAndGet();
            book.setId(id);
        }
        store.put(book.getId(), book);
        return book;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
