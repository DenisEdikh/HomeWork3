package aston.controller;

import aston.dto.user.NewUserDto;
import aston.dto.user.UpdateUserDto;
import aston.dto.user.UserDto;
import aston.model.User;
import aston.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        log.info("Начинаем получение всех пользователей");
        final Collection<User> users = userService.getAllUsers();
        log.info("Получены все пользователи");
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        log.info("Начинаем получение пользователя с id = {}", id);
        final User user = userService.getUserById(id);
        log.info("Получен пользователь с id = {}", id);
        return user;
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserDto user) {
        log.info("Начинаем добавление пользователя");
        final UserDto savedUser = userService.create(user);
        log.info("Закончено добавление пользователя");
        return savedUser;
    }

    @PutMapping
    public UserDto update(@Valid @RequestBody UpdateUserDto dto) {
        log.info("Начинаем обновление пользователя");
        final UserDto savedUser = userService.update(dto);
        log.info("Закончено Обновление пользователя");
        return savedUser;
    }
}
