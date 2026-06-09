package com.aivle.bookapp.repository;

import com.aivle.bookapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
https://github.com/KT-MINI-Book/book-spring/pull/3/conflict?name=src%252Fmain%252Fjava%252Fcom%252Faivle%252Fbookapp%252Frepository%252FBookRepository.java&ancestor_oid=dbab0d12fc2c0d63104dea60c452268ffd3b0d03&base_oid=259cd13c4fb234f8e42da6d5e5382ffb60f29c04&head_oid=2566551bbc6ed25c1bca9f806217392d3f5320fa    List<Book> findByTitleContainingOrAuthorContainingOrContentsContaining(
            String title,
            String author,
            String contents
    );
}
