package ru.Kodrik.MySpringBoot2Dbase.service;

import ru.Kodrik.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

public interface DisciplineService {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(Long id);
    void deleteDiscipline(Long id);
}