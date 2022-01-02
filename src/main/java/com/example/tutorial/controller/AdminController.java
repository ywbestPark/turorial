package com.example.tutorial.controller;

import com.example.tutorial.entity.ZthmMenu;
import com.example.tutorial.service.ZthmMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ZthmMenuService zthmMenuService;

    @GetMapping("/menu_page")
    public String menuPage(){
        return "admin/menu";
    }

    @GetMapping("/menu")
    public ResponseEntity<List<ZthmMenu>> menus(){
        return ResponseEntity.ok(zthmMenuService.getMenuList());
    }

    @GetMapping("/menu_new_page")
    public String menuNewPage(){
        return "admin/menu_new_page";
    }

    @GetMapping("/menu/{id}")
    public String menuUpdate(@PathVariable("id") Long id, Model model){
        model.addAttribute("menu", zthmMenuService.getMenu(id));
        return "admin/menu_update";
    }

    @PostMapping("/menu")
    @ResponseBody
    public ZthmMenu menuCreate(@RequestBody ZthmMenu zthmMenu){
        log.info(zthmMenu.toString());

        ZthmMenu zthmMenuNew = zthmMenuService.save(zthmMenu);
        log.info(zthmMenuNew.toString());

        return zthmMenuNew;
    }

    @PutMapping("/menu")
    @ResponseBody
    public ZthmMenu update(@RequestBody ZthmMenu zthmMenu){
        log.info(zthmMenu.toString());
        ZthmMenu zthmMenuNew = zthmMenuService.update(zthmMenu);
        return zthmMenuNew;
    }

    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public Long deleteMenu(@PathVariable(value = "id") Long id) {
        zthmMenuService.delete(id);
        return id;
    }

}
