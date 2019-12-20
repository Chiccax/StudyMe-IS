package modelDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DriverManagerConnectionPool;
import modelBean.SottocategoriaBean;

public class SottocategoriaDao implements Model_interface<SottocategoriaBean> {

	public SottocategoriaDao() {}

	@Override
	public void insert(SottocategoriaBean entity) throws SQLException {}

	@Override
	public void update(SottocategoriaBean entity) throws SQLException {}

	@Override
	public boolean remove(Object codiceP) throws SQLException {
		return false;
	}

	@Override
	public SottocategoriaBean findByKey(Object codiceP) throws SQLException {
		if(!(codiceP instanceof String))
			throw new IllegalArgumentException("La chiave primaria deve essere di tipo stringa");
		
		String key = (String)codiceP;
		String sql = "SELECT * FROM sottocategoria WHERE idSottocat = ?";
		
		Connection conn = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, key);
		
		ResultSet ris = stm.executeQuery();
		
		SottocategoriaBean bean = null;
		
		if(ris.next()) {
			bean = new SottocategoriaBean();
			bean.setIdSottoCat(ris.getString(1));
			bean.setNomeSott(ris.getString(2));
		}
		
		return bean;
	}

	@Override
	public Collection<SottocategoriaBean> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}