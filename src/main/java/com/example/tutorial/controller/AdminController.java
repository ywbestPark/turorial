package com.example.tutorial.controller;

import com.example.tutorial.entity.ZthmMenu;
import com.example.tutorial.service.UserInfoService;
import com.example.tutorial.service.ZthmMenuService;
import com.example.tutorial.user.entity.UserInfo;
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
    private final UserInfoService userInfoService;

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

    @GetMapping("/user_list_page")
    public String getUserListPage(){
        return "admin/userList";
    }

    @GetMapping("/user_list")
    public ResponseEntity<List<UserInfo>> getUserList(){
        return ResponseEntity.ok(userInfoService.getUserList());
    }

    @GetMapping("/user_page")
    public String getUserCreatePage(Model model){
        UserInfo userInfo = new UserInfo();
        model.addAttribute("new", true);
        model.addAttribute("userInfo", userInfo);
        return "admin/user";
    }

    @GetMapping("/user/{id}")
    public String getUserEditPage(@PathVariable(value = "id") Long id, Model model){
        UserInfo userInfo = userInfoService.getUserById(id);
        model.addAttribute("new", false);
        model.addAttribute("userInfo", userInfo);
        return "admin/user";
    }

    @PostMapping("/user")
    public String userCreate(@ModelAttribute("userInfo") UserInfo userInfo){
        log.info(userInfo.toString());
        userInfoService.save(userInfo);
        return "redirect:/admin/user_list_page";
    }

    @PostMapping("/user/edit")
    public String userUdate(@ModelAttribute("userInfo") UserInfo userInfo){
        log.info(userInfo.toString());
        userInfoService.update(userInfo);
        return "redirect:/admin/user_list_page";
    }

    @PostMapping("/user/delete")
    public String userDelete(@ModelAttribute("userInfo") UserInfo userInfo){
        log.info(userInfo.toString());
        userInfoService.delete(userInfo.getId());
        return "redirect:/admin/user_list_page";
    }

}
