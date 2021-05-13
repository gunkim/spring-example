package dev.gunlog.app.web;

import dev.gunlog.app.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping("/registerForm")
    public void registerForm(Model model){
        model.addAttribute("member", new Member());
    }
    @PostMapping("/register")
    public String register(@Validated Member member, BindingResult result){
        if(result.hasErrors()){
            return "registerForm";
        }
        return "success";
    }
}