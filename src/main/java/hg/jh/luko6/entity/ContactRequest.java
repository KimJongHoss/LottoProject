package hg.jh.luko6.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "이름은 필수항목입니다.")
    @Size(max = 20, message = "이름이 너무 깁니다..")
    private String name;

    @NotBlank(message = "이메일은 필수항목입니다.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;


    @NotBlank(message = "전화번호는 필수항목입니다.")
    private String phone;

    @NotBlank(message = "메시지는 필수항목입니다.")
    @Size(max = 500, message = "메시지는 500자 이하로 입력해주세요.")
    private String message;

}