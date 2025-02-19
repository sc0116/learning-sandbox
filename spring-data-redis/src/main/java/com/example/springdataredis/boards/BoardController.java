package com.example.springdataredis.boards;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	public BoardController(final BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping
	public List<Board> findBoards(
		@RequestParam(defaultValue = "1") final int page,
		@RequestParam(defaultValue = "10") final int size
	) {
		return boardService.findBoards(page, size);
	}
}
