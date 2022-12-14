package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
//	public static Users update(Integer id, UpdateDto updateDto) {	// 받아서 users 리턴
//		return "";
//	}
//	public static Users delete(Integer id) {
//		return "";
//	}
	
	public void 패스워드수정(String password) {
		this.password = password;
	}
	
	public void 전체수정(UpdateDto updateDto) {
		this.username = updateDto.getUsername();
		this.password = updateDto.getPassword();
		this.email = updateDto.getEmail();
	}



}
