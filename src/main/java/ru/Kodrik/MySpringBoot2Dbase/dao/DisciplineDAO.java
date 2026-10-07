package ru.Kodrik.MySpringBoot2Dbase.dao;

import ru.Kodrik.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(Long id);
    void deleteDiscipline(Long id);
}