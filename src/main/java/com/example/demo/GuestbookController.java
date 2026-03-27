package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor // 생성자 주입
public class GuestbookController {

    private final GuestbookRepository guestbookRepository;

    // 1. 목록 페이지 (최신순)
    @GetMapping
    public String list(Model model) {
        List<Guestbook> list = guestbookRepository.findAllByOrderByIdDesc();
        model.addAttribute("list", list);
        return "guestbook/list"; // templates/guestbook/list.mustache
    }

    // 2. 작성 폼 페이지
    @GetMapping("/write")
    public String writeForm() {
        return "guestbook/write";
    }

    // 3. 방명록 저장 처리
    @PostMapping("/write")
    public String write(Guestbook guestbook) {
        guestbookRepository.save(guestbook);
        return "redirect:/guestbook";
    }

    // 4. 삭제 처리
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        guestbookRepository.deleteById(id);
        return "redirect:/guestbook";
    }
}