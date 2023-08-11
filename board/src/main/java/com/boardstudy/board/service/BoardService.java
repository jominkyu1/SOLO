package com.boardstudy.board.service;

import com.boardstudy.board.entity.Board;
import com.boardstudy.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void write(Board board) {
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }
}
