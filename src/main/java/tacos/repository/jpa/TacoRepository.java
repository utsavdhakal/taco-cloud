package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
