package repository.sanphamrepository;

import java.util.List;

/**
 *
 * @author cuongwf
 */
public interface IThuocTinhRepository<T> {

    List<T> getAll();

    boolean add(T entity);

    boolean update(T entity);

}
