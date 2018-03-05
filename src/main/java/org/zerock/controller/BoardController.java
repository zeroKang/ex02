package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/boards/*")
public class BoardController {

    @Setter(onMethod = @__(@Autowired))
    private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {

        log.info("list");
        model.addAttribute("list", service.getList());

    }
    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("register: " + board);

        service.register(board);

        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:/boards/list";
    }

    @GetMapping("/get")
    public void get(@RequestParam("bno") Long bno, Model model) {

        log.info("/get");

        service.get(bno).ifPresent(board-> model.addAttribute("board", board));

    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr ) {
        log.info("modify:" + board);

        if(service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/boards/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {

        log.info("remove..." + bno);
        if( service.remove(bno) ) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/boards/list";
    }

}
