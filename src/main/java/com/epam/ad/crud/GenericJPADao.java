package com.epam.ad.crud;

import com.epam.ad.dao.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Askar on 06.08.2014.
 */
public interface GenericJPADao<T> {
    /** Создает новую запись и соответствующий ей объект */
    public T add(T entity);
    /** Создает новую запись, соответствующую объекту object */
    public T getByPK(int key);
    /** Сохраняет состояние объекта group в базе данных */
    public void update(T entity);
    /** Удаляет запись об объекте из базы данных */
    public void delete(int key);
    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll();
}

