package learning.springtransaction.propagation.application;

import learning.springtransaction.propagation.domain.ChildRepository;
import learning.springtransaction.propagation.domain.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public abstract class ApplicationTest {

    @Autowired
    protected ParentService sut;

    @Autowired
    protected ParentRepository parentRepository;

    @Autowired
    protected ChildRepository childRepository;

    @BeforeEach
    void setUp() {
        parentRepository.deleteAll();
        childRepository.deleteAll();
    }

    protected void findAll() {
        System.out.println(parentRepository.findAll());
        System.out.println(childRepository.findAll());
    }
}
