package com.example.tstrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import static com.example.tstrestaurant.utils.ValidationUtil.checkExisted;
import static com.example.tstrestaurant.utils.ValidationUtil.checkModification;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(Long id);

    default void deleteExisted(Long id) {
        checkModification(delete(id), id);
    }

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id")
    T get(Long id);

    default T getExisted(Long id) {
        return checkExisted(get(id), id);
    }
}
