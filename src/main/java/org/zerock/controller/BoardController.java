package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/boards/*")
public class BoardController {

    @Setter(onMethod = @__(@Autowired))
    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model) {
//
//        log.info("list");
//        model.addAttribute("list", service.getList());
//
//    }

//    @GetMapping("/list")
//    public void list(Criteria cri, Model model) {
//
//        log.info("list: " + cri);
//        model.addAttribute("list", service.getList(cri));
//
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {

        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));

        //total 123
        //model.addAttribute("pageMaker", new PageDTO(cri, 123));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }


    @GetMapping("/register")
    public void register() {

    }


    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("register: " + board);

        service.register(board);

        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:/boards/list";
    }

//    @GetMapping({"/get","/modify"})
//    public void get(@RequestParam("bno") Long bno, Model model) {
//
//        log.info("/get or modify");
//        service.get(bno).ifPresent(board-> model.addAttribute("board", board));
//    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno,
                    @ModelAttribute("cri") Criteria cri,
                    Model model) {


        log.info("/get or modify");
        service.get(bno).ifPresent(board -> model.addAttribute("board", board));
    }


//    @PostMapping("/modify")
//    public String modify(BoardVO board, RedirectAttributes rttr ) {
//        log.info("modify:" + board);
//
//        if(service.modify(board)) {
//            rttr.addFlashAttribute("result", "success");
//        }
//        return "redirect:/boards/list";
//    }


//    @PostMapping("/remove")
//    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
//
//        log.info("remove..." + bno);
//        if( service.remove(bno) ) {
//            rttr.addFlashAttribute("result", "success");
//        }
//        return "redirect:/boards/list";
//    }

//    @PostMapping("/modify")
//    public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr ) {
//        log.info("modify:" + board);
//
//        if(service.modify(board)) {
//            rttr.addFlashAttribute("result", "success");
//        }
//
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//
//        return "redirect:/boards/list";
//    }
//
//    @PostMapping("/remove")
//    public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
//
//        log.info("remove..." + bno);
//        if( service.remove(bno) ) {
//            rttr.addFlashAttribute("result", "success");
//        }
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//
//        return "redirect:/boards/list";
//    }

    @PostMapping("/modify")
    public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
        log.info("modify:" + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/boards/list" + cri.getListLink();
    }


    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {

        log.info("remove..." + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/boards/list" + cri.getListLink();
    }


}
