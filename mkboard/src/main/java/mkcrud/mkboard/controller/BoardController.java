package mkcrud.mkboard.controller;

import lombok.extern.slf4j.Slf4j;
import mkcrud.mkboard.domain.Item;
import mkcrud.mkboard.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    ItemRepository itemRepository;

    @Autowired
    public BoardController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String boardList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {
        int totalItems = itemRepository.itemCnt();
        int pageSize = 5; //한 페이지에 보여줄 게시글 수
        int totalPages = (int) Math.ceil((double) totalItems/pageSize); //총 페이지 수
        List<Item> items = itemRepository.showList();
        // page-1 * 5 = 0, 5, 10, 15
        // page * 5 = 5, 10, 15, 20
        //        if(page==1){
        //            List<Item> subList = items.subList(0, 5);
        //            model.addAttribute("items", subList);
        //        } else if(page==2){
        //            List<Item> subList = items.subList(5, 10);
        //            model.addAttribute("items", subList);
        //        } else if(page==totalPages){
        //            List<Item> subList = items.subList(10, totalItems);
        //            model.addAttribute("items", subList);
        //        } else {
        //            model.addAttribute("items", items);
        //        }

        if ( page == totalPages) {
            List<Item> sList = items.subList(((page-1) * pageSize), totalItems);
            model.addAttribute("items", sList);
        } else {
            List<Item> sList = items.subList(((page-1) * pageSize), 5 * page);
            model.addAttribute("items", sList);
        }

        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",totalPages);
        return "boardList";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "boardWrite";
    }

    @PostMapping("/write")
    public String writeProcess(@ModelAttribute Item item){
        itemRepository.saveItem(item);
        log.info("저장된 PID: {}", item.getId());
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        Item foundItem = itemRepository.findItemById(id);
        log.info("업데이트됨. 게시글번호: {}", foundItem.getId());
        model.addAttribute("item", foundItem);
        return "boardUpdate";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Item item){
        itemRepository.itemUpdate(id, item);
        return "redirect:/board";
    }
}
