package learning.springtransaction.propagation.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ChildRepository extends Repository<Child, Long> {
    Child save(Child entity);
    List<Child> findAll();
    void deleteAll();
}
