package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import model.DriverManagerConnectionPool;
import modelBean.CategoriaBean;

public class CategoriaDao implements Model_interface<CategoriaBean> {

	public CategoriaDao() {}
	
		@Override
		public void insert(CategoriaBean entity) throws SQLException {
		}

		@Override
		public void update(CategoriaBean entity) throws SQLException {
		}

		@Override
		public boolean remove(Object codiceP) throws SQLException {
			return false;
		}

		@Override
		public CategoriaBean findByKey(Object codiceP) throws SQLException {
			if(!(codiceP instanceof String))
				throw new IllegalArgumentException("La chiave primaria deve essere di tipo String");

			String key = (String)codiceP;
			String sql = "SELECT * FROM categoria WHERE nomeCategoria = ?";
			
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, key);
			
			ResultSet ris = stm.executeQuery();
			
			CategoriaBean bean = null;
			
			if(ris.next()) {
				bean = new CategoriaBean();
				bean.setNomeCategoria(ris.getString(1));
				bean.setFotoCategoria(ris.getString(2));
			}
			
			return bean;
		}

		@Override
		public Collection<CategoriaBean> findAll() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
}