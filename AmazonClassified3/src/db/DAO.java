package db;

import java.util.List;

public interface DAO<T>{

    int insert(T object);
    int update(T object);
    int update(String sql);
    int delete(T object);

    int adminUpdate(T object);
    List<T> retrieve();

    // To Retrieve Custom Data by passing select * query...
    List<T> retrieve(String sql);
}
