package learning.springtransaction.propagation.application;

import learning.springtransaction.propagation.domain.Child;
import learning.springtransaction.propagation.domain.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChildService {

    private final ChildRepository childRepository;

    @Transactional
    public void testRequired() {
        childRepository.save(new Child("2"));
    }

    @Transactional
    public void testRequired_Exception() {
        childRepository.save(new Child("2"));
        throw new RuntimeException("자식 트랜잭션 실패");
    }





    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testRequiresNew() {
        childRepository.save(new Child("2"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testRequiresNew_Exception() {
        childRepository.save(new Child("2"));
        throw new RuntimeException("자식 트랜잭션 실패");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testRequiresNew_TryCatch() {
        try {
            childRepository.save(new Child("2"));
            throw new RuntimeException("자식 트랜잭션 실패");
        } catch (Exception e) {
            System.out.println("자식 try-catch 자식 트랜잭션 실패");
        }
    }
}
