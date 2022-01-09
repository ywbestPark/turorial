package com.example.tutorial.entity;

import com.example.tutorial.service.ZthmMenuComparator;

import java.util.*;

public class ZthmMenuTest {

    public static void main(String[] args) {
        ZthmMenu zthmMenu1 =ZthmMenu.builder()
                .id(1L)
                .parentId(null)
                .level(1)
                .menuOrder(1)
                .menuName("Home")
                .menuPath("/index.html")
                .build();
        ZthmMenu zthmMenu2 =ZthmMenu.builder()
                .id(2L)
                .parentId(null)
                .level(1)
                .menuOrder(2)
                .menuName("About")
                .menuPath("/about.html")
                .build();
        ZthmMenu zthmMenu3 =ZthmMenu.builder()
                .id(3L)
                .parentId(null)
                .level(1)
                .menuOrder(3)
                .menuName("Contact")
                .menuPath("/contact.html")
                .build();
        ZthmMenu zthmMenu4 =ZthmMenu.builder()
                .id(4L)
                .parentId(null)
                .level(1)
                .menuOrder(4)
                .menuName("Pricing")
                .menuPath("/pricing.html")
                .build();
        ZthmMenu zthmMenu5 =ZthmMenu.builder()
                .id(5L)
                .parentId(null)
                .level(1)
                .menuOrder(5)
                .menuName("FAQ")
                .menuPath("/faq.html")
                .build();
        ZthmMenu zthmMenu6 =ZthmMenu.builder()
                .id(6L)
                .parentId(null)
                .level(1)
                .menuOrder(6)
                .menuName("ToDo")
                .menuPath("/board/lists")
                .build();
        ZthmMenu zthmMenu7 =ZthmMenu.builder()
                .id(7L)
                .parentId(null)
                .level(1)
                .menuOrder(7)
                .menuName("Image Board")
                .menuPath("/image?page=0&size=9&sort=modifiedDate,desc")
                .build();
        ZthmMenu zthmMenu8 =ZthmMenu.builder()
                .id(8L)
                .parentId(null)
                .level(1)
                .menuOrder(8)
                .menuName("Blog")
                .menuPath("#")
                .build();
        ZthmMenu zthmMenu9 =ZthmMenu.builder()
                .id(9L)
                .parentId(8L)
                .level(2)
                .menuOrder(1)
                .menuName("Blog Home")
                .menuPath("/blog-home.html")
                .build();
        ZthmMenu zthmMenu10 =ZthmMenu.builder()
                .id(10L)
                .parentId(8L)
                .level(2)
                .menuOrder(2)
                .menuName("Blog Post")
                .menuPath("/blog-post.html")
                .build();
        ZthmMenu zthmMenu11 =ZthmMenu.builder()
                .id(11L)
                .parentId(null)
                .level(1)
                .menuOrder(9)
                .menuName("Project")
                .menuPath("#")
                .build();
        ZthmMenu zthmMenu12 =ZthmMenu.builder()
                .id(12L)
                .parentId(11L)
                .level(2)
                .menuOrder(1)
                .menuName("Json To JavaCode")
                .menuPath("/json")
                .build();
        ZthmMenu zthmMenu13 =ZthmMenu.builder()
                .id(13L)
                .parentId(11L)
                .level(2)
                .menuOrder(2)
                .menuName("Excel Upload")
                .menuPath("/excel/view")
                .build();

        List<ZthmMenu> zthmMenuList = new ArrayList<>();
        zthmMenuList.add(zthmMenu1);
        zthmMenuList.add(zthmMenu2);
        zthmMenuList.add(zthmMenu3);
        zthmMenuList.add(zthmMenu4);
        zthmMenuList.add(zthmMenu5);
        zthmMenuList.add(zthmMenu7);
        zthmMenuList.add(zthmMenu8);
        zthmMenuList.add(zthmMenu9);
        zthmMenuList.add(zthmMenu10);
        zthmMenuList.add(zthmMenu11);
        zthmMenuList.add(zthmMenu12);
        zthmMenuList.add(zthmMenu13);

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
                "        <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">\n";


        String navbarFooter = "        </ul>\n" +
                "    </div>\n" +
                "</div>";

        stringBuilder.append(navbarHeader);
        ZthmMenu current;
        for (int i=0; i<zthmMenuMap.get(null).size(); i++){
            current = zthmMenuMap.get(null).get(i);
            retrieve(current, zthmMenuMap, stringBuilder);
        }
        stringBuilder.append(navbarFooter);

        System.out.println(stringBuilder.toString());

        List<ZthmMenu> zthmMenuRootList = zthmMenuMap.get(null);



    }

    private static void retrieve(ZthmMenu current, Map<Long, List<ZthmMenu>> zthmMenuMap, StringBuilder stringBuilder) {
        String tab = "";
        List<ZthmMenu> childrenList = zthmMenuMap.get(current.getId());
        if(childrenList==null){
            print(current, tab);
            if(current.getLevel()==1){
                stringBuilder.append("<li class=\"nav-item\"><a class=\"nav-link\" href="+current.getMenuPath()+">"+current.getMenuName()+"</a></li>"+"\n");
            }else{
                stringBuilder.append("        <li><a class=\"dropdown-item\" href="+current.getMenuPath()+">"+current.getMenuName()+"</a></li>" + "\n");
            }
            return;
        }

        print(current, tab);
        boolean isFirstChild = true;
        boolean isLastChild = false;
        for (ZthmMenu zthmMenu : childrenList){

            if(isFirstChild){
                stringBuilder.append("<li class=\"nav-item dropdown\">"+"\n");
                stringBuilder.append("    <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdown"+current.getMenuName()+"\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"+current.getMenuName()+"</a>"+"\n");
                stringBuilder.append("    <ul class=\"dropdown-menu dropdown-menu-end\" aria-labelledby=\"navbarDropdown"+current.getMenuName()+"\">"+"\n");
            }

            retrieve(zthmMenu, zthmMenuMap, stringBuilder);

            isFirstChild = false;
            isLastChild = true;

        }

        if(isLastChild){
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

