package com.redhat.apps.app1;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.redhat.apps.app1.services.AppService;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AppController {

    @Autowired
    AppService appService;

    @GetMapping("/home.page")
    public String home(Principal principal, HttpServletRequest request){
        // simple routing
        log.info("action: {} with {}", "home", principal.getName());
        request.setAttribute("REQ_PRINCIPAL", principal.getName());
        HttpSession session = request.getSession();
        session.setAttribute("SESS_ACCESS_TOKEN_STRING", getAccessToken(principal,request));
        
        return "home";
    }

    private String getAccessToken(Principal principal, HttpServletRequest request) {
        
        KeycloakPrincipal kprincipal=(KeycloakPrincipal)principal;
        KeycloakSecurityContext session = kprincipal.getKeycloakSecurityContext();
        //AccessToken accessToken = session.getToken();        
        String token = session.getTokenString();
        
        log.info("token :{}",token);
        return token;
    }
    @GetMapping("/")
    public String index() {
        // simple routing
        log.info("action: {}", "index");
        return "index";
    }

    @GetMapping("/logout.page")
    public String logout(HttpServletRequest request) {
        // simple routing
        try {
            request.logout();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("action: {}","logout");
        return "index";
    }   

    @GetMapping("/api/service1")
    public ModelAndView invokeService(Principal principal, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        log.info("action: {} , {}","service1", principal);
        String token = (String) session.getAttribute("SESS_ACCESS_TOKEN_STRING");

        String msg = appService.echo("hello appservice", token);
        request.setAttribute("REQ_ACTION", msg);

        ModelAndView mv = new ModelAndView("home");
        return mv;

    }
}
