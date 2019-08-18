package JLMS.dao;
import java.util.List;

public interface GenericDao<T> {
    List<T> getAll() throws Exception;
    void add(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
}
