package com.example.springdataredis.boards;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardService(final BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public List<Board> findBoards(final int page, final int size) {
		final Pageable pageable = PageRequest.of(page - 1, size);

		final Page<Board> pageOfBoards = boardRepository.findAllByOrderByCreatedAtDesc(pageable);

		return pageOfBoards.getContent();
	}
}
