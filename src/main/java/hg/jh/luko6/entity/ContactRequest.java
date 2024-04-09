package hg.jh.luko6.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "이름은 필수항목입니다.")
    @Size(min = 1, max = 20, message = "정확한 이름을 입력해주세요")
    private String name;

    @NotBlank(message = "이메일은 필수항목입니다.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @Size(min = 4, message = "올바른 이메일 주소를 입력해주세요.")
    private String email;


    @NotBlank(message = "전화번호는 필수항목입니다.")
    @Size(min = 8, message = "올바른 전화번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "메시지는 필수항목입니다.")
    @Size(min = 10, max = 500, message = "메시지는 10자 이상 500자 이하로 입력해주세요.")
    private String message;

}