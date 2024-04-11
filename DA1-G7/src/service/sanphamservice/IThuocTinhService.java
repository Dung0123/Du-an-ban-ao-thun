package service.sanphamservice;

import java.util.List;

/**
 *
 * @author cuongwf
 */
public interface IThuocTinhService<T> {

    List<T> getAll();

    String add(T entity);

    String update(T entity);
}
