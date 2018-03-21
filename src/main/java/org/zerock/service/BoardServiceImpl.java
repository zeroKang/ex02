package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.persistence.BoardMapper;

import java.util.Optional;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod = @__(@Autowired))
    private BoardMapper mapper;

    @Override
    public Long register(BoardVO board) {

        log.info("register......" + board);

        mapper.insertSelectKey(board);

        return board.getBno();
    }

    public Optional<BoardVO> get(Long bno) {

        log.info("get......" + bno);

        return Optional.of(mapper.read(bno));

    }

    @Override
    public boolean modify(BoardVO board) {

        log.info("modify......" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {

        log.info("remove...." + bno);

        return mapper.delete(bno) == 1;
    }


    @Override
    public Iterable<BoardVO> getList() {
        return mapper.getList();
    }

    @Override
    public Iterable<BoardVO> getList(Criteria cri) {

        log.info("getListWithPage.........." + cri);

        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {

        log.info("getTotal.........." + cri);

        return mapper.getTotalCount(cri);

    }


}
