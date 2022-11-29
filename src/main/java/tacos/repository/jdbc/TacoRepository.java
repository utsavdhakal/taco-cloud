package tacos.repository.jdbc;

import tacos.entity.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
