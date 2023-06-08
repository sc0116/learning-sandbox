package learning.springtransaction.propagation.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ParentRepository extends Repository<Parent, Long> {
    Parent save(Parent entity);
    List<Parent> findAll();
    void deleteAll();
}
