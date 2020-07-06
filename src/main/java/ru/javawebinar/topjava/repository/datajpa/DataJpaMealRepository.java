package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

  //  @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(crudRepository.getBy(userId));
        if (meal.isNew()){
            crudRepository.save(meal);
        }else if (get(meal.id(), userId)==null){
            return null;
        }
        return crudRepository.save(meal);

   }


 //   @Transactional
    @Override
    public boolean delete(int id, int userId) {
        Optional<Meal> meal= crudRepository.getMealByIdAndUserId(id, userId);
        try {
            if (meal.get().getUser().getId() == userId){
                crudRepository.delete(meal.get());
            }

        }catch (Exception e){
            throw  new NotFoundException("Not Found");
        }
        return  true;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getMealByIdAndUserId(id, userId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.getAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeBeforeOrderByDateTimeDesc(userId,
                startDateTime, endDateTime);
    }
}
