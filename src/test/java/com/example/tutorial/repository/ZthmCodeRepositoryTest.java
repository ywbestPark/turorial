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

    @Test
    public void savePrice1(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("000660")
                .codeName("SK하이닉스")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice2(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("051910")
                .codeName("LG화학")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice3(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("035420")
                .codeName("NAVER")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice4(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("207940")
                .codeName("삼성바이오로직스")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice5(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("006400")
                .codeName("삼성SDI")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("\t주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice6(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("068270")
                .codeName("셀트리온")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("\t주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice7(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("091990")
                .codeName("셀트리온헬스케어")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("\t주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice8(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("035720")
                .codeName("카카오")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("\t주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

    @Test
    public void savePrice9(){
        ZthmCommonCode zthmCommonCode = ZthmCommonCode.builder()
                .codeId("373220")
                .codeName("LG에너지솔루션")
                .codeDescription("주식 조회 대상")
                .codeGroupId("price")
                .codeGroupName("price")
                .codeGroupDescription("\t주식 가격 조회")
                .build();

        ZthmCommonCode zthmCommonCodeNew = repository.save(zthmCommonCode);

        assertEquals(true, zthmCommonCodeNew.isEnable());
    }

//    @Test
//    public void findByCodeGroupIdAndIsEnable(){
//        repository.findByCodeGroupIdAndIsEnable("ROLE", true);
//    }

}