package tacos.repository.jdbc.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tacos.entity.ingredient.Ingredient;
import tacos.entity.ingredient.IngredientType;
import tacos.repository.jdbc.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int i) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                IngredientType.valueOf(rs.getString("type"))
        );
    }
}
