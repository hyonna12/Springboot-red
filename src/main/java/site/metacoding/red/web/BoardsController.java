package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor	// final이 붙은것만 생성자 붙여줌
@RestController
public class BoardsController{
	
	private final BoardsDao boardsDao;
	// boardscontoller가 new해서 ioc 컨테이너에 담겨있음
	//private final BoardsDao boardsDao;	// boardsdao를 의존하고 있음/ ioc 컨테이너에서 가져온거/ final을 붙여줌
	
//	public BoardsController(Boards boards) {
//		System.out.println("BoardsController의 디폴트");
//	}
//	// Boards는 ioc 컨테이너에 안떠있어서 안뜸
//	// 생성자가 이것만 있어서 이게 실행될건데 boards
//	
//	public BoardsController() {
//		if(boardsDao == null) {
//			System.out.println("boardsDao가 null입니다.");
//		}
//	}
//	
//	public BoardsController(BoardsDao boardsDao) {
//		this.boardsDao = boardsDao;
//	}
	// 생성자
	// 1. 생성자 주입
	// 2. @RequiredArgsConstructor
	// 3. @Autowired
	// 2번으로 생성자 만들었음!
	
	@PostMapping("/boards")
	public RespDto<?> insert(WriteDto writeDto){
		boardsDao.insert(writeDto);
		return new RespDto<>(1, "글쓰기 성공", null);
	}
	
	@PutMapping("/boards/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		// 영속화
		Boards boards = boardsDao.findById(id);
		
		// 변경
		boards.글전체수정(updateDto);
		
		// 업데이트 실행
		boardsDao.update(boards);
		return new RespDto<>(1, "글수정 성공", null);
	}

	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1, "글삭제 성공", null);
	}
	
	@GetMapping("/boards")
	public RespDto<?> select(){
		return new RespDto<>(1, "글조회 성공", boardsDao.findAll());
	}
	
	@GetMapping("/boards/{id}")
	public RespDto<?> selectById(@PathVariable Integer id){
		return new RespDto<>(1, "한건조회 성공", boardsDao.findById(id));
	}
}
