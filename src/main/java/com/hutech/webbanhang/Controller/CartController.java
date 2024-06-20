package com.hutech.webbanhang.Controller;

import com.hutech.webbanhang.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model, @RequestParam(value = "error", required = false) String errorMessage) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.calculateTotal());
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "/cart/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
        String result = cartService.addToCart(productId, quantity);
        if ("Số lượng sản phẩm trong kho không đủ.".equals(result)) {
            redirectAttributes.addFlashAttribute("errorMessage", result);
            return "redirect:/products/detail/" + productId;
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
        String result = cartService.updateCart(productId, quantity);
        if ("Số lượng sản phẩm trong kho không đủ.".equals(result)) {
            redirectAttributes.addFlashAttribute("errorMessage", result);
            return "redirect:/cart";
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
