package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        org.zerock.config.RootConfig.class, org.zerock.config.ServiceConfig.class  } )
@Log4j
public class BoardServiceTests {

    @Setter(onMethod = @__(@Autowired))
    private BoardService service;

    @Test
    public void testRegister() {

        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        Long newBno = service.register(board);

        log.info("생성된 게시물의 번호: "+ newBno );
    }

    @Test
    public void testGetList() {

        //service.getList().forEach(board -> log.info(board));
        service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
    }


    @Test
    public void testGet() {

        service.get(1L).ifPresent(board -> log.info(board));
    }

    @Test
    public void testDelete() {

        //게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("REMOVE RESULT: "+service.remove(2L));

    }

    @Test
    public void testUpdate() {

        service.get(1L).ifPresent(board ->{

            board.setTitle("제목 수정합니다.");
            log.info("MODIFY RESULT: "+service.modify(board));

        });
    }

}
