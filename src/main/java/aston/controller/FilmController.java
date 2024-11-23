package aston.controller;

import aston.dto.film.FilmDto;
import aston.dto.film.NewFilmDto;
import aston.dto.film.UpdateFilmDto;
import aston.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/{id}")
    public FilmDto getFilmById(@PathVariable(value = "id") Long id) {
        log.info("Начинаем получение фильма с id = {}", id);
        final FilmDto film = filmService.getFilmById(id);
        log.info("Закончено получение фильма с id = {}", id);
        return film;
    }

    @PostMapping
    public FilmDto create(@Valid @RequestBody NewFilmDto film) {
        log.info("Начинаем добавление фильма");
        final FilmDto savedFilm = filmService.create(film);
        log.info("Закончено добавление фильма");
        return savedFilm;
    }

    @PutMapping
    public FilmDto update(@Valid @RequestBody UpdateFilmDto dto) {
        log.info("Начинаем обновление фильма");
        final FilmDto savedFilm = filmService.update(dto);
        log.info("Закончено обновление фильма");
        return savedFilm;
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable(value = "id") Long id,
                        @PathVariable(value = "userId") Long userId) {
        log.info("Начинаем добавление \"лайка\" фильму с id = {}", id);
        filmService.addLike(id, userId);
        log.info("Закончено добавление \"лайка\" фильму с id = {}", id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable(value = "id") Long id,
                           @PathVariable(value = "userId") Long userId) {
        log.info("Начинаем удаление \"лайка\" фильму с id = {}", id);
        filmService.deleteLike(id, userId);
        log.info("Закончено удаление \"лайка\" фильму с id = {}", id);
    }

    @GetMapping("/popular")
    public List<FilmDto> getPopularFilms() {
        log.info("Начинаем получение популярных фильмы");
        final List<FilmDto> popularFilms = filmService.getPopularFilms();
        log.info("Закончено получение популярных фильмов");
        return popularFilms;
    }
}
