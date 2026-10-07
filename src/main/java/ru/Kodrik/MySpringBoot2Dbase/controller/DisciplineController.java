package ru.Kodrik.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Kodrik.MySpringBoot2Dbase.dao.OperationResult;
import ru.Kodrik.MySpringBoot2Dbase.entity.Discipline;
import ru.Kodrik.MySpringBoot2Dbase.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<OperationResult<List<Discipline>>> allDisciplines() {
        List<Discipline> list = disciplineService.getAllDisciplines();
        if (list.isEmpty()) {
            return ResponseEntity.ok(OperationResult.success("Список дисциплин пуст", list));
        }
        return ResponseEntity.ok(OperationResult.success(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResult<Discipline>> getDiscipline(@PathVariable("id") Long id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return ResponseEntity.status(404)
                    .body(OperationResult.failure("Дисциплина с id=" + id + " не найдена"));
        }
        return ResponseEntity.ok(OperationResult.success(discipline));
    }

    @PostMapping
    public ResponseEntity<OperationResult<Discipline>> saveDiscipline(@RequestBody Discipline discipline) {
        try {
            Discipline saved = disciplineService.saveDiscipline(discipline);
            return ResponseEntity.ok(OperationResult.success("Дисциплина добавлена", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при добавлении: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<OperationResult<Discipline>> updateDiscipline(@RequestBody Discipline discipline) {
        try {
            Discipline existing = disciplineService.getDiscipline(discipline.getId());
            if (existing == null) {
                return ResponseEntity.status(404)
                        .body(OperationResult.failure("Дисциплина с id=" + discipline.getId() + " не найдена"));
            }
            disciplineService.saveDiscipline(discipline);
            return ResponseEntity.ok(OperationResult.success("Дисциплина обновлена", discipline));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при обновлении: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Void>> deleteDiscipline(@PathVariable("id") Long id) {
        try {
            Discipline existing = disciplineService.getDiscipline(id);
            if (existing == null) {
                return ResponseEntity.status(404)
                        .body(OperationResult.failure("Дисциплина с id=" + id + " не найдена"));
            }
            disciplineService.deleteDiscipline(id);
            return ResponseEntity.ok(OperationResult.success("Дисциплина удалена", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при удалении: " + e.getMessage()));
        }
    }
}