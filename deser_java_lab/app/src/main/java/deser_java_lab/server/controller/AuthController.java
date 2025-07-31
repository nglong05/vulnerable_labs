package deser_java_lab.server.controller;

import deser_java_lab.actions.common.serializable.AccessTokenUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;
import java.io.*;
import java.util.Base64;
import java.util.UUID;

@RestController
public class AuthController {

    private final JdbcTemplate jdbc;

    public AuthController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostMapping("/login")
    public String login (@RequestParam String username, @RequestParam String password, HttpServletResponse resp) throws IOException {

        Integer matches = jdbc.queryForObject("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?", Integer.class, username, password);
        
        if (matches == null || matches == 0) {
            return "invalid";
        }
        // AccessTokenUser contains username and accesstoken
        AccessTokenUser user = new AccessTokenUser(username, UUID.randomUUID().toString());
        var baos = new ByteArrayOutputStream(512);
        new ObjectOutputStream(baos).writeObject(user);
        String cookieVal = Base64.getEncoder().encodeToString(baos.toByteArray());

        resp.addCookie(new Cookie("SESSION", cookieVal));
        return "Logged in as " + username;
    }
}
