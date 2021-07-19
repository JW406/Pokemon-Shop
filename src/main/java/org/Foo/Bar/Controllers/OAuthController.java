package org.Foo.Bar.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.Foo.Bar.SpringContextAccessor;
import org.Foo.Bar.Entities.User;
import org.Foo.Bar.RestObjects.GitHubAccessToken;
import org.Foo.Bar.RestObjects.GoogleAccessToken;
import org.Foo.Bar.Services.UserService;
import org.Foo.Bar.UtilityServices.HTTPUtils;
import org.Foo.Bar.UtilityServices.QueryStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import okhttp3.OkHttpClient;

@Controller
public class OAuthController {
  private QueryStringParser qsParser;
  private HTTPUtils http;
  private UserService userService;

  @Autowired
  public OAuthController(QueryStringParser parser, HTTPUtils http, UserService userService) {
    this.qsParser = parser;
    this.http = http;
    this.userService = userService;
  }

  @GetMapping("/auth/google_redirect")
  public String GoogleAuth(HttpServletRequest req, OkHttpClient client, Model model) throws IOException {
    String baseurl = req.getScheme() + "://"
        + (req.getRemoteHost().equals("127.0.0.1") ? "localhost" : req.getRemoteHost()) + ":" + req.getServerPort();
    Map<String, String> qsMap = qsParser.parse(req.getQueryString());
    String res = "";
    // TODO: use restTemplate insteat of okhttp3
    if (qsMap.containsKey("code")) {
      res = http.post("https://oauth2.googleapis.com/token", http.encodeQueryString(new HashMap<String, String>() {
        {
          put("code", qsMap.get("code"));
          put("client_id", SpringContextAccessor.googleClientID);
          put("client_secret", SpringContextAccessor.googleClientSecret);
          put("redirect_uri", baseurl + "/auth/google_redirect");
          put("grant_type", "authorization_code");
        }
      }), HTTPUtils.FORM_ENCODED);
    }
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      model.addAttribute("authType", "google");
      GoogleAccessToken googleAccessToken = objectMapper.readValue(res, GoogleAccessToken.class);
      model.addAttribute("accessToken", googleAccessToken.getAccess_token());
      model.addAttribute("idToken", googleAccessToken.getId_token());
    } catch (Exception e) {
    }
    return "access_token_helper";
  }

  @GetMapping("/auth/github_redirect")
  public String GitHubAuth(HttpServletRequest req, OkHttpClient client, Model model) throws IOException {
    String baseurl = req.getScheme() + "://"
        + (req.getRemoteHost().equals("127.0.0.1") ? "localhost" : req.getRemoteHost()) + ":" + req.getServerPort();
    Map<String, String> qsMap = qsParser.parse(req.getQueryString());
    String res = "";
    ObjectMapper objectMapper = new ObjectMapper();

    // TODO: use restTemplate insteat of okhttp3
    if (qsMap.containsKey("code")) {
      res = http.post("https://github.com/login/oauth/access_token", objectMapper.writeValueAsString(new HashMap<String, String>() {
        {
          put("code", qsMap.get("code"));
          put("client_id", SpringContextAccessor.githubClientID);
          put("client_secret", SpringContextAccessor.githubClientSecret);
          put("state", "foobar");
          put("redirect_uri", baseurl + "/auth/github_redirect");
        }
      }), HTTPUtils.JSON);
    }
    try {
      model.addAttribute("authType", "github");
      GitHubAccessToken githubAccessToken = objectMapper.readValue(res, GitHubAccessToken.class);
      model.addAttribute("accessToken", githubAccessToken.getAccess_token());
    } catch (Exception e) {
    }
    return "access_token_helper";
  }

  @ResponseBody
  @PostMapping("/auth/google_redirect_persist")
  public void GoogleUserPersist(@RequestBody User user) {
    userService.persistUser(user);
  }

  @ResponseBody
  @PostMapping("/auth/github_redirect_persist")
  public void GitHubUserPersist(@RequestBody User user) {
    userService.persistUser(user);
  }
}
