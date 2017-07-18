package com.veera.web;

import com.veera.web.security.VeeraUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class IndexController {
    @RequestMapping(value = "/", method = GET)
    public String showIndex(final Model model) throws Exception {
        return "Veera Success"+ VeeraUser.getAuthenticatedUser().getUser().getFirstName();
    }
}
