package com.example.tutorial.service;

import com.example.tutorial.entity.ZthmMenu;
import com.example.tutorial.repository.ZthmMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.utils.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZthmMenuServiceImpl implements ZthmMenuService{
    private final ZthmMenuRepository zthmMenuRepository;
    private String username;

    @Override
    public String getMenuString() {
        List<ZthmMenu> zthmMenuList = zthmMenuRepository.findAll();

        Collections.sort(zthmMenuList, new ZthmMenuComparator());

        Map<Long, List<ZthmMenu>> zthmMenuMap = new HashMap<>();
        for (ZthmMenu zthmMenu : zthmMenuList){
            //로그 아웃의 경우 사용자 이름이 들어가야 하므로 메뉴테이블에는 빈값으로 저장하고 여기서 값을 넣어 준다.
            if(StringUtils.isBlank(zthmMenu.getMenuName())){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                username = authentication.getName();
                zthmMenu.setMenuName(username);
            }
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

        String navbarHeader =
                "    <a class=\"navbar-brand\" href=\"/index.html\">Auto Coding</a>\n" +
                "    <form class=\"form-inline\" action=\"/signout\" method=\"get\">\n" +
                "        <button class=\"btn btn-sm btn-outline-secondary\" type=\"submit\">Logout</button>\n" +
                "    </form>\n" +
                "    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n" +
                "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                "        <ul class=\"navbar-nav ms-auto mb-2 mb-lg-0\">\n";


        String navbarFooter =
                "        </ul>\n" +
                "    </div>\n";

        stringBuilder.append(navbarHeader);
        ZthmMenu current;
        for (int i=0; i<zthmMenuMap.get(null).size(); i++){
            current = zthmMenuMap.get(null).get(i);
            retrieve(current, zthmMenuMap, stringBuilder);
        }
        stringBuilder.append(navbarFooter);

        log.info(stringBuilder.toString());

        return stringBuilder.toString();
    }

    public List<ZthmMenu> getMenuList(){
        return zthmMenuRepository.findAll();
    }

    @Override
    public ZthmMenu getMenu(Long id) {
        return zthmMenuRepository.getById(id);
    }

    @Override
    public ZthmMenu update(ZthmMenu zthmMenu) {
        ZthmMenu zthmMenuNew = null;
        Optional<ZthmMenu> optionalZthmMenu = zthmMenuRepository.findById(zthmMenu.getId());
        if(optionalZthmMenu.isPresent()){
            zthmMenuNew = zthmMenuRepository.save(zthmMenu);
        }else{
            //no data error 리턴
        }
        return zthmMenuNew;
    }

    @Override
    public void delete(Long id) {
        zthmMenuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no data id=" + id));

        zthmMenuRepository.deleteById(id);
    }

    @Override
    public ZthmMenu save(ZthmMenu zthmMenu) {
        return zthmMenuRepository.save(zthmMenu);
    }

    private void retrieve(ZthmMenu current, Map<Long, List<ZthmMenu>> zthmMenuMap, StringBuilder stringBuilder) {
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
        log.info(tab + current.getMenuName());
    }
}

