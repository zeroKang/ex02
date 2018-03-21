package org.zerock.service;

import java.util.Optional;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

    public Long register(BoardVO board);

    public Optional<BoardVO> get(Long bno);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

    public Iterable<BoardVO> getList();

    public Iterable<BoardVO> getList(Criteria cri);

    public int getTotal(Criteria cri);
}
