package com.example.user.webapi;

import com.example.user.core.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("user")
public class Controller {
  private final UserService userService;

  public Controller(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{handle}/")
  public ListUserDto getUser(@PathVariable String handle) {
    return ListUserDto.fromEntity(userService.getUserByHandle(handle));
  }

  @GetMapping("/list/")
  public List<ListUserDto> getAllUsers() {
    return userService.getActiveUsers()
                      .stream()
                      .map(ListUserDto::fromEntity)
                      .collect(Collectors.toList());
  }
}
