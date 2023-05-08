package learning.springquerydsl.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {

    private String username;
    private int age;

    public MemberDto(final String username, final int age) {
        this.username = username;
        this.age = age;
    }
}
