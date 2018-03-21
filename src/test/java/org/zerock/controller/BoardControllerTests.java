package org.zerock.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        org.zerock.config.RootConfig.class,
        org.zerock.config.ServiceConfig.class,
        org.zerock.config.ServletConfig.class })
@Log4j

public class BoardControllerTests {
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList()throws Exception {

        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/boards/list"))
                        .andReturn().getModelAndView().getModelMap()
        );
    }
    @Test
    public void testRegister()throws Exception{

        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/boards/register")
                .param("title", "테스트 새글 제목")
                .param("content", "테스트 새글 내용")
                .param("writer", "user00")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);

    }

    @Test
    public void tetGet()throws Exception{

        //게시물의 번호가 존재하는지 먼저 확인할 것
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/boards/get")
                        .param("bno", "25"))
                        .andReturn().getModelAndView().getModelMap()
        );
    }

    @Test
    public void testModify()throws Exception{

        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/boards/modify")
                .param("bno", "25")
                .param("title", "수정된 테스트 새글 제목")
                .param("content", "수정된 테스트 새글 내용")
                .param("writer", "user00")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);

    }

    @Test
    public void testRemove()throws Exception{
        //삭제전 데이터베이스에 게시물 번호 확인할 것
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/boards/remove")
                .param("bno", "41")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testListPaging()throws Exception {

        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/boards/list")
                        .param("pageNum", "2")
                        .param("amount", "10"))
                        .andReturn().getModelAndView().getModelMap()
        );
    }



}
