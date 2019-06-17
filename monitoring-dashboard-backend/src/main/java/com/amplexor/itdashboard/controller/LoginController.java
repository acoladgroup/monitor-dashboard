package com.amplexor.itdashboard.controller;

import com.amplexor.itdashboard.model.User;
import com.amplexor.itdashboard.service.UserService;
import com.amplexor.itdashboard.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * Created by marquesh on 13/08/2018.
 */
@RestController
public class LoginController {

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private UserService userService;

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(OAuth2Authentication auth) throws IOException {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        tokenServices.revokeToken(details.getTokenValue());
    }

    @GetMapping("/connecteduser")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        String userId = principal.getName();
        Optional<User> optionalUser = null;

        if (StringUtils.isStringInt(userId))
            optionalUser = userService.findByUserId(new BigInteger(userId));
        else
            optionalUser = userService.findOneByLogin(userId);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
