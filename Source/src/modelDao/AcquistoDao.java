package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DriverManagerConnectionPool;
import modelBean.AcquistoBean;

public class AcquistoDao {
	
	public AcquistoDao() {
		
	}
	
	public void insertAcquisto(AcquistoBean bean) {
		 try {
			 	Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement stm = conn.prepareStatement("INSERT INTO acquisto(numOrdine, codiceP,titoloPacchetto,prezzo) values(?,?,?,?)");
				stm.setInt(1, bean.getNumOrdine());
				stm.setString(2, bean.getCodiceP());
				stm.setString(3, bean.getTitoloPacchetto());
				stm.setDouble(4, bean.getImporto());
				
				stm.executeUpdate();
				conn.commit();		
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
	}
}

