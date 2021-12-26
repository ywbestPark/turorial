package com.example.tutorial.repository;

import com.example.tutorial.entity.ZthmCommonCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class ZthmCodeRepositoryTest {

    @Resource
    public ZthmCommonCodeRepository repository;

    @Test
    public void save(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                                        .codeId("ADMIN")
                                        .codeName("Admin")
                                        .codeDescription("Admin 권한")
                                        .codeGroupId("ROLE")
                                        .codeGroupName("권한")
                                        .codeGroupDescription("권한 관리를 위한 코드")
                                        .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals("ADMIN", zthmCommonCodeNew.getCodeId());
        assertEquals(true, zthmCommonCodeNew.isEnable());

    }

//    @Test
//    public void findByCodeGroupIdAndIsEnable(){
//        repository.findByCodeGroupIdAndIsEnable("ROLE", true);
//    }

}