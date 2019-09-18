package todoList.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {

	public T create(T newObject);
	public T read(PK primeKey);
	public boolean update(T updateObject);
	public boolean delete(PK primeKey);
	public List<T> getAll();
}
