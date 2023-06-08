package learning.springtransaction.propagation.application;

import learning.springtransaction.propagation.domain.Parent;
import learning.springtransaction.propagation.domain.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParentService {

    private final ParentRepository parentRepository;
    private final ChildService childService;

    @Transactional
    public void testRequired() {
        parentRepository.save(new Parent("1"));
        childService.testRequired();
        parentRepository.save(new Parent("3"));
    }

    @Transactional
    public void testRequired_ChildException() {
        parentRepository.save(new Parent("1"));
        childService.testRequired_Exception();
        parentRepository.save(new Parent("3"));
    }

    @Transactional
    public void testRequired_ParentException() {
        parentRepository.save(new Parent("1"));
        childService.testRequired();
        parentRepository.save(new Parent("3"));
        throw new RuntimeException("부모 트랜잭션 실패");
    }
}
