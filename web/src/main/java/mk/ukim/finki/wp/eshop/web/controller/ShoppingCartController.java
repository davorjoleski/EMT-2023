package mk.ukim.finki.wp.eshop.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.eshop.model.ShoppingCart;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.service.AuthService;
import mk.ukim.finki.wp.eshop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;


    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @GetMapping("/filter")
    public String addProductToShoppingCart(@RequestParam(required = false) LocalDateTime from,
                                           @RequestParam(required = false) LocalDateTime to,
                                           HttpServletRequest req,
                                           Model model) {
        List<ShoppingCart> carts = shoppingCartService.findAll();
        model.addAttribute("carts", carts);
        return "shopping-carts-filtered";
    }


    @PostMapping("/filter")
    public String filterShoppingCarts(@RequestParam LocalDateTime from,
                                      @RequestParam LocalDateTime to,
                                      HttpServletRequest req,
                                      Model model) {
        List<ShoppingCart> filteredCarts = shoppingCartService.filterByDateTimeBetween(from, to);
        model.addAttribute("carts", filteredCarts);
        return "shopping-carts-filtered";
    }

    @GetMapping("/count")
    public String countShoppingCarts(HttpServletRequest req, Model model) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        model.addAttribute("carts", shoppingCarts);

        List<User> users = authService.findAll();
        model.addAttribute("users", users);
        return "shopping-carts-count";
    }

    @PostMapping("/count")
    public String countShoppingCarts(@RequestParam String username,
                                     HttpServletRequest req,
                                     Model model) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        model.addAttribute("carts", shoppingCarts);

        List<User> users = authService.findAll();
        model.addAttribute("users", users);

        Long count = shoppingCartService.countSuccessfulOrders(username);
        model.addAttribute("count", count);
        model.addAttribute("username", username);

        return "shopping-carts-count";
    }
}
