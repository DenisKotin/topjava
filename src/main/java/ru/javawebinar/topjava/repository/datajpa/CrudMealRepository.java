package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Optional<Meal> getMealByIdAndUserId(int id, int userId);

  //  @Transactional
    List<Meal> getAllByUserIdOrderByDateTimeDesc(int userId);


    void delete(Meal meal);
   // Meal getMealByIdAndUserId(int id, int userId);

   // @Transactional
    @Query("SELECT u FROM User u WHERE u.id =: id ")
    User getBy(@Param("id") int id);

  //  @Transactional
    List<Meal> getAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeBeforeOrderByDateTimeDesc(int userId,
        LocalDateTime startDateTime, LocalDateTime endDateTime);



}
