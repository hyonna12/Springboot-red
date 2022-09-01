package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;

public interface BoardsDao {
	public void insert(WriteDto writeDto);	// boards에 insert는 글쓰기
	public Users findById(Integer id);
	public List<Boards> findAll();
	public void update(Boards boards);
	public void delete(Integer id);
}

// 하나씩 매개변수 수정하고 dto만들고 차례대로 작성
//글 작성할 때 id는 sequence로, title, content 입력받고 userid는 세션에서 입력받고 날짜는 sysdate