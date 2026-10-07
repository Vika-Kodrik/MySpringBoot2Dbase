package ru.Kodrik.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.Kodrik.MySpringBoot2Dbase.entity.Discipline;

import java.util.List;

@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        return entityManager.createQuery("FROM Discipline", Discipline.class).getResultList();
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public Discipline getDiscipline(Long id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public void deleteDiscipline(Long id) {
        Discipline discipline = entityManager.find(Discipline.class, id);
        if (discipline != null) {
            entityManager.remove(discipline);
        }
    }
}