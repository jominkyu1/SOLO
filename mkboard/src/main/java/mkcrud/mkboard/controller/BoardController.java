package mkcrud.mkboard.controller;

import lombok.extern.slf4j.Slf4j;
import mkcrud.mkboard.domain.Item;
import mkcrud.mkboard.domain.ItemRepository;
import mkcrud.mkboard.domain.MemoryItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    ItemRepository memoryItemRepository = new MemoryItemRepository();

    @GetMapping
    public String boardList(Model model) {
        model.addAttribute("items", memoryItemRepository.showList());
        return "boardList";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "boardWrite";
    }

    @PostMapping("/write")
    public String writeProcess(@ModelAttribute Item item){
        memoryItemRepository.saveItem(item);
        log.info("저장된 PID: {}", item.getId());
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        Item foundItem = memoryItemRepository.findItemById(id);
        System.out.println(foundItem.getId());
        model.addAttribute("item", foundItem);
        return "boardUpdate";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Item item){
        memoryItemRepository.itemUpdate(id, item);
        return "redirect:/board";
    }

    @PostConstruct
    public void forTest(){
        memoryItemRepository.saveItem(new Item("테스트용 제목1", "테스트용 내용1"));
        memoryItemRepository.saveItem(new Item("테스트용 제목2", "테스트용 내용2"));

    }
}
