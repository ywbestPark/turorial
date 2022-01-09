package com.example.tutorial.entity;

import com.example.tutorial.service.ZthmMenuComparator;

import java.util.*;

public class ZthmMenuTest_back {

    public static void main(String[] args) {
        ZthmMenu zthmMenu1 =ZthmMenu.builder()
                .id(1L)
                .parentId(null)
                .level(1)
                .menuOrder(1)
                .menuName("Admin")
                .menuPath("/admin")
                .build();
        ZthmMenu zthmMenu2 =ZthmMenu.builder()
                .id(2L)
                .parentId(null)
                .level(1)
                .menuOrder(2)
                .menuName("Image")
                .menuPath("/image")
                .build();
        ZthmMenu zthmMenu6 =ZthmMenu.builder()
                .id(6L)
                .parentId(null)
                .level(1)
                .menuOrder(3)
                .menuName("Home")
                .menuPath("/home")
                .build();
        ZthmMenu zthmMenu4 =ZthmMenu.builder()
                .id(4L)
                .parentId(6L)
                .level(2)
                .menuOrder(2)
                .menuName("Menu")
                .menuPath("/menu")
                .build();
        ZthmMenu zthmMenu3 =ZthmMenu.builder()
                .id(3L)
                .parentId(6L)
                .level(2)
                .menuOrder(1)
                .menuName("User")
                .menuPath("/user")
                .build();
        ZthmMenu zthmMenu5 =ZthmMenu.builder()
                .id(5L)
                .parentId(2L)
                .level(2)
                .menuOrder(1)
                .menuName("List")
                .menuPath("/list")
                .build();
        ZthmMenu zthmMenu7 =ZthmMenu.builder()
                .id(7L)
                .parentId(3L)
                .level(3)
                .menuOrder(1)
                .menuName("Signout")
                .menuPath("/signout")
                .build();

        List<ZthmMenu> zthmMenuList = new ArrayList<>();
        zthmMenuList.add(zthmMenu1);
        zthmMenuList.add(zthmMenu6);
        zthmMenuList.add(zthmMenu2);
        zthmMenuList.add(zthmMenu4);
        zthmMenuList.add(zthmMenu3);
        zthmMenuList.add(zthmMenu5);
        zthmMenuList.add(zthmMenu7);

        Collections.sort(zthmMenuList, new ZthmMenuComparator());

        Map<Long, List<ZthmMenu>> zthmMenuMap = new HashMap<>();
        for (ZthmMenu zthmMenu : zthmMenuList){
            Long parentId = zthmMenu.getParentId();
            if(zthmMenuMap.containsKey(parentId)){
                zthmMenuMap.get(parentId).add(zthmMenu);
            }else{
                List<ZthmMenu> zthmMenuList1 = new ArrayList<>();
                zthmMenuList1.add(zthmMenu);
                zthmMenuMap.put(parentId, zthmMenuList1);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        String navbarHeader = "<div class=\"container px-5\">\n" +
                "    <a class=\"navbar-brand\" href=\"/index.html\">Auto Coding</a>\n" +
                "    <form class=\"form-inline\" action=\"/signout\" method=\"get\">\n" +
                "        <button class=\"btn btn-sm btn-outline-secondary\" type=\"submit\">Logout</button>\n" +
                "    </form>\n" +
                "    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n" +
                "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                "        <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">";


        String navbarFooter = "        </ul>\n" +
                "    </div>\n" +
                "</div>";

        ZthmMenu current;
        for (int i=0; i<zthmMenuMap.get(null).size(); i++){
            current = zthmMenuMap.get(null).get(i);
            stringBuilder.append("<li class=\"nav-item\"><a class=\"nav-link\" href=\"/index.html\">"+current.getMenuName()+"</a></li>"+"\n");
            retrieve(current, zthmMenuMap, stringBuilder);
        }

        System.out.println(stringBuilder.toString());

        List<ZthmMenu> zthmMenuRootList = zthmMenuMap.get(null);



    }

    private static void retrieve(ZthmMenu current, Map<Long, List<ZthmMenu>> zthmMenuMap, StringBuilder stringBuilder) {
        String tab = "";
        List<ZthmMenu> childrenList = zthmMenuMap.get(current.getId());
        if(childrenList==null){
            print(current, tab);
            stringBuilder.append("        <li><a class=\"dropdown-item\" href=\"blog-home.html\">" + current.getMenuName() + "</a></li>" + "\n");
            return;
        }

        print(current, tab);
        for (ZthmMenu zthmMenu : childrenList){
            stringBuilder.append("<li class=\"nav-item dropdown\">"+"\n");
            stringBuilder.append("    <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdownBlog\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"+current.getMenuName()+"</a>"+"\n");
            stringBuilder.append("    <ul class=\"dropdown-menu dropdown-menu-end\" aria-labelledby=\"navbarDropdownBlog\">"+"\n");
            retrieve(zthmMenu, zthmMenuMap, stringBuilder);
            stringBuilder.append("    </ul>"+"\n");
            stringBuilder.append("</li>"+"\n");
        }
    }

    private static void print(ZthmMenu current, String tab) {
        for (int i = 0; i < current.getLevel(); i++) {
            tab += "--";
        }
        System.out.println(tab + current.getMenuName());
    }
}

