package com.cs.resolver;

import com.cs.entity.Author;
import com.cs.entity.Book;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: szh
 * @since:
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {
    public List<Book> findBooks() {
        Author author = Author.builder()
                .id(1)
                .name("Bill Venners")
                .age(40)
                .build();

        Book b = Book.builder()
                .id(1)
                .name("scala编程第三版")
                .author(author)
                .publisher("电子工业出版社")
                .build();

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(b);
        return bookList;
    }
}