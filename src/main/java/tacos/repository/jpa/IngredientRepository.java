package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.ingredient.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
