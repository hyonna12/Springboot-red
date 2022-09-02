package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor	// final이 붙은걸 생성자로 주입받아줌
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")	// users 테이블에서 id입력해서 한개의 id만 가져오기
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id));
	}
	
	@GetMapping("/users")	// users 테이블 전체 가져오기
	public RespDto<?> getUsersList() {
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")	// users 테이블에 insert하기
	public RespDto<?> insert(JoinDto joinDto) {	// 3개의 값만 insert해야되기 때문에 JoinDto 를 따로 만들어서 넣어줌
		usersDao.insert(joinDto);	// joinDto 넘겨줌
		return new RespDto<>(1, "회원가입완료", null);	// 201번 - insert됨
	}
	
//	@PutMapping("/users/{id}") 	// where절에 걸리는건 주소로 받기
//	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){	
//		Users users = new Users();
//		users.setId(id);
//		users.setUsername(updateDto.getUsername());
//		users.setPassword(updateDto.getPassword());
//		users.setEmail(updateDto.getEmail());
//		// update는 id, updateDto 두개를 받을수가 없어서 users로 받아줌
//		// users는 db와 통신하는 dto
//		// joinDto, updateDto는 클라이언트와 통신하기 위한 dto
//		usersDao.update(users);	
//		return new RespDto<>(1, "회원수정완료", null);
//	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		// 1번 : id로 select하자. (영속화)
		Users users = usersDao.findById(id);
		
		// 2번 : 변경
		users.전체수정(updateDto);
		
		// 3번 : 영속화된 오브젝트로 update하기
		usersDao.update(users);	
		return new RespDto<>(1, "회원수정완료", null);
	}
	
	@PutMapping("/users/{id}/password")
	public RespDto<?> update(@PathVariable Integer id, String password){
		// 1번 영속화
		Users usersPS = usersDao.findById(id);
		
		// 2번 변경
		usersPS.패스워드수정(password);
		
		// 3번 전체업데이트
		usersDao.update(usersPS);	
		return new RespDto<>(1, "패스워드수정완료", null);
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		usersDao.delete(id);
		return new RespDto<>(1, "삭제완료", null);
	}
}
