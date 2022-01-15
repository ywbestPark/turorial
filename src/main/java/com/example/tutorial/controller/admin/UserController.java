package com.example.tutorial.controller.admin;

import com.example.tutorial.entity.ZthmError;
import com.example.tutorial.entity.ZthmMenu;
import com.example.tutorial.service.UserInfoService;
import com.example.tutorial.service.ZthmErrorService;
import com.example.tutorial.service.ZthmMenuService;
import com.example.tutorial.user.dto.UserInfoDTO;
import com.example.tutorial.user.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    public static final String REDIRECT_ADMIN_USER_LIST_PAGE = "redirect:/admin/user_list_page";
    private final ZthmMenuService zthmMenuService;
    private final UserInfoService userInfoService;
    private final ZthmErrorService zthmErrorService;

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
        return zthmMenuService.update(zthmMenu);
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

//    @PostMapping("/user")
//    public String userCreate(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO) throws JDBCException {
//        log.info(userInfoDTO.toString());
//        userInfoService.save(userInfoDTO);
//        return REDIRECT_ADMIN_USER_LIST_PAGE;
//    }

    @PostMapping({"/user/edit", "/user"})
    public String userUdate(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO) throws JDBCException{
        log.info(userInfoDTO.toString());
        userInfoService.saveOrUpdate(userInfoDTO);
        return REDIRECT_ADMIN_USER_LIST_PAGE;
    }

    @PostMapping("/user/delete")
    public String userDelete(@ModelAttribute("userInfo") UserInfoDTO userInfoDTO){
        log.info(userInfoDTO.toString());
        userInfoService.delete(userInfoDTO.getId());
        return REDIRECT_ADMIN_USER_LIST_PAGE;
    }

    @GetMapping("/error_list_page")
    public String getErrorListPage(){
        return "admin/errorList";
    }

    @GetMapping("/error_list")
    public ResponseEntity<List<ZthmError>> getErrorList(){
        return ResponseEntity.ok(zthmErrorService.getErrorList());
    }

}
