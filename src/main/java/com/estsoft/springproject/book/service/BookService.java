package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책 전체 조회
    public List<Book> findAll() {
        return bookRepository.findAll(Sort.by("id")); // 오름차순
    }

    // 책 단건 조회
    public Book findBy(String id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    // 책 생성
    public Book saveOne(Book book) {
        return bookRepository.save(book);
    }
}
