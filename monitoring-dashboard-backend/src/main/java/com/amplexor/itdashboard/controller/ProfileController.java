package com.amplexor.itdashboard.controller;

import com.amplexor.itdashboard.model.User;
import com.amplexor.itdashboard.service.UserService;
import com.amplexor.itdashboard.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProfileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {

        String userId = principal.getName();

        Map<String, Object> profileMap = new HashMap<>();

        Optional<User> userOptional = null;

        if (StringUtils.isStringInt(userId))
            userOptional = userService.findByUserId(new BigInteger(userId));
        else
            userOptional = userService.findOneByLogin(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            profileMap.put("UserProfile", user);

            return ResponseEntity.ok(profileMap);
        } else {
            logger.error("Dashboard user not found");
            return ResponseEntity.notFound().build();
        }
    }
}
