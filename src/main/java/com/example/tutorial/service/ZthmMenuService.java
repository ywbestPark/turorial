package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmMenu;

import java.util.List;

public interface ZthmMenuService {
    String getMenuString();
    List<ZthmMenu> getMenuList();
    ZthmMenu getMenu(Long id);
    ZthmMenu update(ZthmMenu zthmMenu);
    void delete(Long id);
    ZthmMenu save(ZthmMenu zthmMenu);
}
