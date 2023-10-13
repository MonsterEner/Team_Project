package com.example.team_project.controller;

import com.example.team_project.dto.auth.SignupDto;
import com.example.team_project.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        return "main/loginPage";
    }

    @GetMapping("/findpw")
    public String findPw(){
        return "main/findpw";
    }
    @GetMapping("/findemail")
    public String findEmail(){
        return "main/findemailid";
    }

    @GetMapping("/signup")
    public String signup(){
        return "main/signup";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/home";
    }

    @PostMapping("/signup")
    public String signup1(@ModelAttribute SignupDto signupDto) {
        log.info(String.valueOf(signupDto));
        if(signupDto.getFile().isEmpty()){
            memberService.joinWithoutFile(signupDto);
        }
        else {
            memberService.join(signupDto);
        }

        return "redirect:/welcome";

    }


    @GetMapping("/mypage")
    public String mypage1(){
        return "main/mypage";
    }

    @GetMapping("/mySchedule")
    public String mypage2(){
        return "main/mySchedule";
    }


}
