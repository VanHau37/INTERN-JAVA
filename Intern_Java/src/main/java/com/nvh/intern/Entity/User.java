	package com.nvh.intern.Entity;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Không được để trống")
	@Size(min = 8, message="Mã, bạn phải nhập ít nhất 8 kí tự")
	@Column(nullable = false, length = 64)
	 @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",message="Vui lòng nhập mã của bạn chứa đầy đủ các thành phần: chữ cái viết thường,chữ hoa và chữ số")  
	private String id_users;
	

	@NotEmpty(message="Không được để trống")
	@Size(min = 8, message="Mật khẩu phải có ít nhất 8 kí tự")
	@Column(nullable = false, length = 64)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message="Vui lòng nhập mật khẩu có đầy đủ các thành phần: chữ cái viết thường,chữ hoa, chữ số và kí tự đặt biệt")  
	private String password;
	
	@NotEmpty(message="Không được để trống")
	@Size(min = 2, message="Tên phải có ít nhất 2 kí tự")
	@Column(nullable = false, length = 30)
	private String username;
	
	
	@NotEmpty(message="Không được để trống")
	@Email
    @Pattern(regexp="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$",message="Vui lòng nhập email hợp lệ")  
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@NotEmpty(message="Không được để trống")
	@Size(min = 10, message="Phone phải có ít nhất 10 chữ số")
	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ](\\d{4})[- ](\\d{3})$",message="Không đúng cú pháp, Ví dụ: 034-3456-079") 
	private String phone;
	
	
	@NotEmpty(message="Không được để trống")
	@Pattern(regexp="\\d+", message="Mã zip là 1 chuỗi số!")
	@Column(nullable = false, length = 6)
	private String id_zip;
	
	
	@NotEmpty(message="Không được để trống")
	@Pattern(regexp="^\\d+.?\\d+[,]\\s[a-zA-Z]+.[a-z0-9]+\\s?\\d?.?\\w+[,]\\s[a-zA-Z]+.[a-zA-Z]+.?\\w+.?\\w[,]\\s\\w+.?\\d?[,]?\\s?\\w+$",message="Vui lòng điền đầy đủ thông tin. Ví dụ : 49, Duong-102, Tang-nhon-phu-A, Quan-9, HCM") 
	@Column(nullable = false, length = 64)
	private String address;
	
	
	@Column(nullable = false, length = 40)
	private String emoji;
	
	//Get image
	@Transient
	public String getImagesPath() {
		if(emoji == null|| id==null) return null;
		return "./images/"+ id + "/"+ emoji;
	}
}
