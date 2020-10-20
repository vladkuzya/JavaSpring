package mate.academy.controllers;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.UserResponseDto;
import mate.academy.model.User;
import mate.academy.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    @ResponseBody
    public String injectData() {
        userService.add(new User("bob@gmail.com", "b0b"));
        userService.add(new User("alica@gmail.com", "fox"));
        userService.add(new User("jack@mail.ru", "sparrow"));
        userService.add(new User("admin@admin.com", "777"));
        return "Users added";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return convertUserToDto(userService.getById(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto convertUserToDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
