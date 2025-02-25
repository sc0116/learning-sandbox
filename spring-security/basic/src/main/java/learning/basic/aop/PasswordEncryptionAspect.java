package learning.basic.aop;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import learning.basic.service.EncryptService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class PasswordEncryptionAspect {

	private final EncryptService encryptService;

	@Around("execution(* learning.basic.controller..*.*(..))")
	public Object passwordEncryptionAspect(final ProceedingJoinPoint pjp) throws Throwable {
		Arrays.stream(pjp.getArgs())
			.forEach(this::fieldEncryption);

		return pjp.proceed();
	}

	public void fieldEncryption(final Object object) {
		if (ObjectUtils.isEmpty(object)) {
			return;
		}

		FieldUtils.getAllFieldsList(object.getClass()).stream()
			.filter(field -> !(Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())))
			.forEach(field -> {
				try {
					final boolean hasCustomEncryption = field.isAnnotationPresent(CustomEncryption.class);
					if (!hasCustomEncryption) {
						return;
					}

					final Object encryptionTarget = FieldUtils.readField(field, object, true);
					if (!(encryptionTarget instanceof String)) {
						return;
					}

					final String encryptedValue = encryptService.encrypt((String) encryptionTarget);
					FieldUtils.writeField(field, object, encryptedValue);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
	}
}
