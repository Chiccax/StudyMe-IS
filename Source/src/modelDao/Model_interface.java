package modelDao;

import java.sql.SQLException;
import java.util.Collection;

public interface Model_interface<A> {
	public void  insert(A entity) throws SQLException;
	public void update(A entity) throws SQLException;
	public boolean remove(Object codiceP) throws SQLException;
	public A findByKey(Object codiceP) throws SQLException;
	public Collection<A> findAll() throws SQLException;

}