package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 책 전체 조회 API
    @GetMapping
    public String showAll(Model model) {
        List<BookDTO> list = bookService.findAll()
                .stream()
                .map(BookDTO::new)
                .toList();

        model.addAttribute("bookList", list);
        return "bookManagement";
    }

    // 책 단건 조회 API
    @GetMapping("/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Book book = bookService.findBy(id);
        model.addAttribute("book", new BookDTO(book));
        return "bookDetail";
    }

    // 책 정보 생성 API
    // id, name, author 정보 받아서 DB에 저장
    // 저장된 책 정보가 바로 노출될 수 있도록 화면 구성 (bookManagement.html)
    @PostMapping
    public String addBook(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String author) {

        bookService.saveOne(new Book(id, name, author));

        return "redirect:/books"; // 재호출
    }

}
