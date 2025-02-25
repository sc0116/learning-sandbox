package learning.basic.controller;

import learning.basic.aop.CustomEncryption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class HelloRequestBody {
	private String id;
	@CustomEncryption
	private String password;
}
