package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Order;
import tacos.entity.ingredient.Ingredient;
import tacos.entity.Taco;
import tacos.entity.ingredient.IngredientType;
import tacos.repository.jpa.IngredientRepository;
import tacos.repository.jpa.TacoRepository;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacos")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public Order order() {
        return new Order();
    }

    @ModelAttribute
    public void ingredients(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        for (IngredientType type : IngredientType.values()) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients
                    .stream()
                    .filter(ingredient -> ingredient.getType()== type)
                    .toList());
        }
    }

    @ModelAttribute("tacos")
    public List<Taco> tacos() {
        return new LinkedList<>();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute("tacos") List<Taco> tacos) {
        if (errors.hasErrors()) {
            return "design";
        }

        Taco savedTaco = tacoRepository.save(taco);
        tacos.add(savedTaco);

        return "redirect:/orders/current";
    }

}
