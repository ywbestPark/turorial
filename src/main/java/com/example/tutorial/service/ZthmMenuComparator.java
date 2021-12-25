package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmMenu;

import java.util.Comparator;

public class ZthmMenuComparator implements Comparator<ZthmMenu> {
    @Override
    public int compare(ZthmMenu m1, ZthmMenu m2) {
        if (m1.getMenuOrder() > m2.getMenuOrder()) {
            return 1;
        } else if (m1.getMenuOrder() < m2.getMenuOrder()) {
            return -1;
        }
        return 0;
    }
}
