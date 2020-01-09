package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DriverManagerConnectionPool;
import modelBean.OrdineBean;

public class OrdineDao {
	 public OrdineDao() {}
	 /**
	  * Inserisce un ordine 
	  * @param ordine 
	  * @return int  
	  * context OrdineDao::insert(OrdineBean ordine)
	  * @pre ordine!=null
	  * **/
	 public int insert(OrdineBean ordine){
		 OrdineBean ord = null;
		 try {
			 	Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement stm = conn.prepareStatement("INSERT INTO ordine(nomeCliente, dataOdierna) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
				stm.setString(1,ordine.getCliente());
				stm.setDate(2,ordine.getData());				
				stm.executeUpdate();
				
				ResultSet res = stm.getGeneratedKeys();
				
				int auto_id = -1;
				
				if(res.next())
					auto_id = res.getInt(1);
				
				ord = new OrdineBean();
				ord.setNumOrdine(auto_id);
	
				conn.commit();
				
				return auto_id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
	 }
}