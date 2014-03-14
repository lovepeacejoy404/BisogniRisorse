

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class DBadapter {
	
	private static String dbURL = "jdbc:derby:DB;create=true";
	
	
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn=DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
            
        }
    }
    
    public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
    
    //YES
    public static Vector<String> getBisogni(boolean is_ricerca)
	{
		
		Vector<String> resultat = new Vector<String>();
		if (is_ricerca) resultat.add("Tutti");
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery("SELECT APP.BISOGNI.ID,APP.BISOGNI.BISOGNO FROM APP.BISOGNI ORDER BY APP.BISOGNI.BISOGNO");
			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
			StringBuffer sBuf;
			while(rsRecord.next())
			{
				sBuf =new StringBuffer();
			
				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
				{
					sBuf.append(rsRecord.getString(cptCols)+"-");
										
				}
				resultat.add(sBuf.toString());
				
			}
			
			
			stmt.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return resultat;
	}

    public static Vector<String> getBisogniPersona(String id_anagrafica)
	{
		
		Vector<String> resultat = new Vector<String>();
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery("SELECT APP.BISOGNI.BISOGNO FROM "+
				  "APP.BISOGNI JOIN APP.ANAGRAFICA JOIN APP.ANAG_BISOGNI ON APP.ANAGRAFICA.ID = APP.ANAG_BISOGNI.ID_ANAGRAFICA ON APP.BISOGNI.ID = APP.ANAG_BISOGNI.ID_BISOGNO "+
				  "WHERE APP.ANAG_BISOGNI.ID_ANAGRAFICA = "+id_anagrafica+
				  " ORDER BY APP.BISOGNI.BISOGNO"
);
			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
			StringBuffer sBuf;
			while(rsRecord.next())
			{
				sBuf =new StringBuffer();
			
				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
				{
					sBuf.append(rsRecord.getString(cptCols)+"-");
										
				}
				resultat.add(sBuf.toString());
				
			}
			
			
			stmt.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return resultat;
	}
    
    public static Vector<String> getAnagBisogni(String id_anagrafica)
   	{
   		
   		Vector<String> resultat = new Vector<String>();
   		//resultat.add("Altro...");
   		try
   		{
   			stmt = conn.createStatement();
   			ResultSet rsRecord = stmt.executeQuery(
   					"SELECT APP.ANAG_BISOGNI.ID_BISOGNO " +
   					"FROM APP.ANAG_BISOGNI " +
   					"WHERE APP.ANAG_BISOGNI.ID_ANAGRAFICA="+id_anagrafica);
   			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
   			StringBuffer sBuf;
   			while(rsRecord.next())
   			{
   				sBuf =new StringBuffer();
   			
   				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
   				{
   					sBuf.append(rsRecord.getString(cptCols)+"-");
   										
   				}
   				resultat.add(sBuf.toString());
   				
   			}
   			
   			
   			stmt.close();
   		}
   		catch (SQLException exception)
   		{
   			exception.printStackTrace();
   		}
   		
   		return resultat;
   	}

    
    
  //YES
    public static Vector<String> getRisorse(boolean is_ricerca)
	{
		
		Vector<String> resultat = new Vector<String>();
		if (is_ricerca) resultat.add("Tutte");
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery("SELECT APP.RISORSE.ID,APP.RISORSE.RISORSA FROM APP.RISORSE ORDER BY APP.RISORSE.RISORSA");
			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
			StringBuffer sBuf;
			while(rsRecord.next())
			{
				sBuf =new StringBuffer();
			
				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
				{
					sBuf.append(rsRecord.getString(cptCols)+"-");
										
				}
				resultat.add(sBuf.toString());
				
			}
			
			
			stmt.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return resultat;
	}

    public static Vector<String> getRisorsePersona(String id_anagrafica)
   	{
   		
   		Vector<String> resultat = new Vector<String>();
   		
   		try
   		{
   			stmt = conn.createStatement();
   			ResultSet rsRecord = stmt.executeQuery(
   					"SELECT APP.RISORSE.RISORSA "+
   					"FROM "+
   					"APP.ANAGRAFICA JOIN APP.RISORSE JOIN APP.ANAG_RISORSE ON APP.RISORSE.ID = APP.ANAG_RISORSE.ID_RISORSA ON APP.ANAGRAFICA.ID = APP.ANAG_RISORSE.ID_ANAGRAFICA "+
   					"WHERE APP.ANAG_RISORSE.ID_ANAGRAFICA ="+ id_anagrafica+
   					" ORDER BY APP.ANAGRAFICA.COGNOME ");
   			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
   			StringBuffer sBuf;
   			while(rsRecord.next())
   			{
   				sBuf =new StringBuffer();
   			
   				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
   				{
   					sBuf.append(rsRecord.getString(cptCols)+"-");
   										
   				}
   				resultat.add(sBuf.toString());
   				
   			}
   			
   			
   			stmt.close();
   		}
   		catch (SQLException exception)
   		{
   			exception.printStackTrace();
   		}
   		
   		return resultat;
   	}

    public static Vector<String> getAnagRisorse(String id_anagrafica)
   	{
   		
   		Vector<String> resultat = new Vector<String>();
   		//resultat.add("Altro...");
   		try
   		{
   			stmt = conn.createStatement();
   			ResultSet rsRecord = stmt.executeQuery(
   					"SELECT APP.ANAG_RISORSE.ID_RISORSA " +
   					"FROM APP.ANAG_RISORSE " +
   					"WHERE APP.ANAG_RISORSE.ID_ANAGRAFICA="+id_anagrafica);
   			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
   			StringBuffer sBuf;
   			while(rsRecord.next())
   			{
   				sBuf =new StringBuffer();
   			
   				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
   				{
   					sBuf.append(rsRecord.getString(cptCols)+"-");
   										
   				}
   				resultat.add(sBuf.toString());
   				
   			}
   			
   			
   			stmt.close();
   		}
   		catch (SQLException exception)
   		{
   			exception.printStackTrace();
   		}
   		
   		return resultat;
   	}

    
    
    
    public static Vector<String> getColumnsNameTable()
	{
		
		
		Vector <String>resultat = new Vector<String>();
		
		try
		{
			stmt = conn.createStatement();
			ResultSet getColumnsNameResultSet = stmt.executeQuery(
					"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.NOME,APP.ANAGRAFICA.COGNOME," +
							"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
						    "FROM " +
						    "APP.ANAGRAFICA ORDER BY APP.ANAGRAFICA.COGNOME"  );
			ResultSetMetaData getColumnsNameResultSetMetaData = getColumnsNameResultSet.getMetaData();
						
			for(int cptCols = 1; cptCols <= getColumnsNameResultSetMetaData.getColumnCount(); cptCols++)
			{
				resultat.add(getColumnsNameResultSetMetaData.getColumnName(cptCols));
			}
			
			getColumnsNameResultSet.close();
			stmt.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			resultat.add("SQLException: " + exception.getMessage());
		}
		
		return resultat;
	}
    
    
    
    public static Vector<String> getVectorAnagrafica(boolean is_for_combo,String id_risorse_soddisf)
	{
		
		Vector<String> resultat = new Vector<String>();
		if (is_for_combo) resultat.add("Tutti");
		if (id_risorse_soddisf==null || id_risorse_soddisf.equals("Non trovate")  ){
			resultat.add("Risorse non trovate");
			return resultat;
		}
			
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery(is_for_combo?
					"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.COGNOME,APP.ANAGRAFICA.NOME," +
					"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
					"FROM APP.ANAGRAFICA ORDER BY APP.ANAGRAFICA.COGNOME":
						
					"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.COGNOME,APP.ANAGRAFICA.NOME," +
					"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
					"FROM APP.ANAGRAFICA WHERE APP.ANAGRAFICA.ID IN " +
					"(" +id_risorse_soddisf+") "+
					"ORDER BY APP.ANAGRAFICA.COGNOME"	
						);
			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
			StringBuffer sBuf;
			while(rsRecord.next())
			{
				sBuf =new StringBuffer();
			
				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
				{
					sBuf.append(rsRecord.getString(cptCols)+"-");
										
				}
				resultat.add(sBuf.toString());
				
			}
			
			
			stmt.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return resultat;
	}
    
    public static Vector<String> getVectorAnagrafica(boolean isPrivacy)
	{
		
		Vector<String> resultat = new Vector<String>();
		
			
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery(isPrivacy?
					"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.PROFESSIONE "+
					"FROM APP.ANAGRAFICA ORDER BY APP.ANAGRAFICA.ETA":
						
						"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.COGNOME,APP.ANAGRAFICA.NOME," +
						"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
						"FROM APP.ANAGRAFICA ORDER BY APP.ANAGRAFICA.COGNOME"
						);
			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
			StringBuffer sBuf;
			while(rsRecord.next())
			{
				sBuf =new StringBuffer();
			
				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
				{
					sBuf.append(rsRecord.getString(cptCols)+"#");
										
				}
				resultat.add(sBuf.toString());
				
			}
			
			
			stmt.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
		
		return resultat;
	}
    
    
    public static String getIDRisorseSoddisf(String IDanagrafica)
	{
		String IDrisorse="-1";
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery(
					"SELECT APP.ANAGRAFICA.ID_RISORSE_SODDISF " +
					"FROM APP.ANAGRAFICA " +
					"WHERE APP.ANAGRAFICA.ID="+IDanagrafica);
			rsRecord.next();
			IDrisorse = rsRecord.getString(1);
			stmt.close();
			rsRecord.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			
			
		}
		
		return IDrisorse;
	}
    
    
    public static Vector<Vector<String>> getTableDataAnagrafica(boolean isBisogni,String where,String order)
	{
		
		
		Vector<Vector<String>> resultat = new Vector<Vector<String>>();
		
		try
		{
			stmt = conn.createStatement();
			
			ResultSet getTableDataResultSet = isBisogni?
					stmt.executeQuery(
							!where.equals("")?
									"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.NOME,APP.ANAGRAFICA.COGNOME," +
									"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
									"FROM " +
									"APP.BISOGNI JOIN APP.ANAGRAFICA JOIN APP.ANAG_BISOGNI ON APP.ANAGRAFICA.ID = APP.ANAG_BISOGNI.ID_ANAGRAFICA ON APP.BISOGNI.ID = APP.ANAG_BISOGNI.ID_BISOGNO "+
									"WHERE APP.ANAG_BISOGNI.ID_BISOGNO = "+where+" ORDER BY APP.ANAGRAFICA.COGNOME":

										"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.NOME,APP.ANAGRAFICA.COGNOME," +
										"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
										"FROM APP.ANAGRAFICA" +	" ORDER BY "+order
							):
								stmt.executeQuery(
										!where.equals("")?
												"SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.NOME,APP.ANAGRAFICA.COGNOME," +
												"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
												"FROM "+
											    "APP.RISORSE JOIN APP.ANAGRAFICA JOIN APP.ANAG_RISORSE ON APP.ANAGRAFICA.ID = APP.ANAG_RISORSE.ID_ANAGRAFICA ON APP.RISORSE.ID = APP.ANAG_RISORSE.ID_RISORSA "+
											    "WHERE APP.ANAG_RISORSE.ID_RISORSA = "+where+"  ORDER BY APP.ANAGRAFICA.COGNOME":

											    "SELECT APP.ANAGRAFICA.ID,APP.ANAGRAFICA.ETA,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.NOME,APP.ANAGRAFICA.COGNOME," +
												"APP.ANAGRAFICA.APPARTENENZA,APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.INDIRIZZO,APP.ANAGRAFICA.CELLULARE,APP.ANAGRAFICA.EMAIL "+
												"FROM APP.ANAGRAFICA" +	" ORDER BY "+order
										)

										;
							ResultSetMetaData getTableDataResultSetMetaData = getTableDataResultSet.getMetaData();

							while(getTableDataResultSet.next())
							{
								Vector<String> temp = new Vector<String>();			

								for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
								{

									temp.add(getTableDataResultSet.getString(cptCols));


								}

								resultat.add(temp);
							}

							getTableDataResultSet.close();
							stmt.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();



		}

		return resultat;
	}
    
    public static ResultSet getRecordAnagrafica(String id_anagrafica)
	{
		ResultSet rsRecord=null;
		
		try
		{
			stmt = conn.createStatement();
			rsRecord = stmt.executeQuery(
					"SELECT APP.ANAGRAFICA.ID, APP.ANAGRAFICA.NOME, APP.ANAGRAFICA.COGNOME,"+
					  "APP.ANAGRAFICA.ETA, APP.ANAGRAFICA.PROFESSIONE, APP.ANAGRAFICA.APPARTENENZA,"+
					  "APP.ANAGRAFICA.INDIRIZZO, APP.ANAGRAFICA.CITTA, APP.ANAGRAFICA.TELEFONO,"+
					  "APP.ANAGRAFICA.CELLULARE, APP.ANAGRAFICA.EMAIL, APP.ANAGRAFICA.NOTE,APP.ANAGRAFICA.ID_RISORSE_SODDISF"+
					  " FROM APP.ANAGRAFICA"+ 
					  " WHERE APP.ANAGRAFICA.ID="+id_anagrafica);
			//stmt.close();
			//rsRecord.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			
			
			
		}
		
		return rsRecord;
	}
    
    public static boolean deleteItemAnagrafica (String id_anagrafica) {
		boolean eseguito=false;
		try {
		stmt=conn.createStatement();
		stmt.execute("DELETE FROM APP.ANAGRAFICA WHERE APP.ANAGRAFICA.ID="+id_anagrafica);
		stmt.execute("DELETE FROM APP.ANAG_RISORSE WHERE APP.ANAG_RISORSE.ID_ANAGRAFICA="+id_anagrafica);
		stmt.execute("DELETE FROM APP.ANAG_BISOGNI WHERE APP.ANAG_BISOGNI.ID_ANAGRAFICA="+id_anagrafica);
		stmt.close();
		eseguito=true;
		}catch (SQLException exception)
		{	
			exception.printStackTrace();
			
				
		}
		return eseguito;
	}
    
    public static long createItemAnagrafica (String nome,String cognome,String eta,String sesso,String professione,String appartenenza,
    										String indirizzo,String citta,
    										String telefono,String cellulare,String email,String note,String id_risorse_soddisf,
    										List<Integer> id_risorse,List<Integer> id_bisogni) {
		long id_anagrafica=getIDAnagraficaMax();
		try {
			if (nome.equals("")) nome="Non fornito";
			if (sesso.equals("")) sesso="Non fornito";
	    	if (cognome.equals("")) cognome="Non fornito";
	    	if (eta.equals("")) eta=null;
	    	if (professione.equals("")) professione="Non fornito";
	    	if (appartenenza.equals("")) appartenenza="Non fornito";
	    	if (indirizzo.equals("")) indirizzo="Non fornito";
	    	if (citta.equals("")) citta="Non fornito";
	    	if (telefono.equals("")) telefono="Non fornito";
	    	if (cellulare.equals("")) cellulare="Non fornito";
	    	if (email.equals("")) email="Non fornito";
	    	if (note.equals("")) note="Non fornito";
	    	if (id_risorse_soddisf.equals("")) id_risorse_soddisf="Non trovate";
			
			stmt=conn.createStatement();
			
						
			stmt.execute("INSERT INTO APP.ANAGRAFICA " +
					"(APP.ANAGRAFICA.NOME, APP.ANAGRAFICA.COGNOME, APP.ANAGRAFICA.ETA," +
					"APP.ANAGRAFICA.PROFESSIONE,APP.ANAGRAFICA.APPARTENENZA,"+
					"APP.ANAGRAFICA.INDIRIZZO, APP.ANAGRAFICA.CITTA,"+
					"APP.ANAGRAFICA.TELEFONO, APP.ANAGRAFICA.CELLULARE, " +
					"APP.ANAGRAFICA.EMAIL,APP.ANAGRAFICA.NOTE,APP.ANAGRAFICA.SESSO,APP.ANAGRAFICA.ID_RISORSE_SODDISF" +
					") " +
					"VALUES " +
					"('"+replace(nome,"'","''")+"','"+replace(cognome,"'","''")+
					"',"+eta+",'"+replace(professione,"'","''")+"','"+
					replace(appartenenza,"'","''")+"','"+
					replace(indirizzo,"'","''")+"','"+replace(citta,"'","''")+"','"
					+replace(telefono,"'","''")+"','"+replace(cellulare,"'","''")+"','"+replace(email,"'","''")+"','"+
					replace(note,"'","''")+"','"+replace(sesso,"'","''")+"','"+id_risorse_soddisf+"')");
		
		
		for (int id:id_risorse)
		stmt.execute("INSERT INTO APP.ANAG_RISORSE " +
				"(APP.ANAG_RISORSE.ID_ANAGRAFICA, APP.ANAG_RISORSE.ID_RISORSA) " +
				 "VALUES " +
				 "("+id_anagrafica+","+id+")");
		for (int id:id_bisogni)
		stmt.execute("INSERT INTO APP.ANAG_BISOGNI " +
				"(APP.ANAG_BISOGNI.ID_ANAGRAFICA, APP.ANAG_BISOGNI.ID_BISOGNO) " +
				 "VALUES " +
				 "("+id_anagrafica+","+id+")");
		
		stmt.close();
		
		}catch (SQLException exception)
		{	
			exception.printStackTrace();
			
				
		}
		return id_anagrafica;
	}
    
    public static boolean updateItemAnagrafica (long id_anagrafica,
    		String nome,String cognome,String eta,String sesso,String professione,String appartenenza,
			String indirizzo,String citta,
			String telefono,String cellulare,String email,String note,String id_risorse_soddisf,
			List<Integer> id_risorse,List<Integer> id_bisogni) {
				
    			boolean eseguito=false;
				try {
					if (eta.equals("")) eta=null;
					stmt=conn.createStatement();
					stmt.execute("UPDATE APP.ANAGRAFICA SET " +
					"APP.ANAGRAFICA.NOME ='" +replace(nome,"'","''")+"',"+
					"APP.ANAGRAFICA.COGNOME ='" +replace(cognome,"'","''")+"',"+
					"APP.ANAGRAFICA.ETA ="+eta+","+
					"APP.ANAGRAFICA.PROFESSIONE ='" +replace(professione,"'","''")+"',"+
					"APP.ANAGRAFICA.APPARTENENZA ='" +replace(appartenenza,"'","''")+"',"+
					"APP.ANAGRAFICA.INDIRIZZO ='" +replace(indirizzo,"'","''")+"',"+
					"APP.ANAGRAFICA.CITTA ='" +replace(citta,"'","''")+"',"+
					"APP.ANAGRAFICA.TELEFONO ='" +replace(telefono,"'","''")+"',"+
					"APP.ANAGRAFICA.CELLULARE ='" +replace(cellulare,"'","''")+"',"+
					"APP.ANAGRAFICA.EMAIL ='" +replace(email,"'","''")+"',"+
					"APP.ANAGRAFICA.SESSO ='" +replace(sesso,"'","''")+"',"+
					"APP.ANAGRAFICA.ID_RISORSE_SODDISF ='" +id_risorse_soddisf+"',"+
					"APP.ANAGRAFICA.NOTE ='" +replace(note,"'","''")+"'"+
					" WHERE APP.ANAGRAFICA.ID="+ id_anagrafica);
					
					stmt.execute("DELETE FROM APP.ANAG_RISORSE WHERE APP.ANAG_RISORSE.ID_ANAGRAFICA="+id_anagrafica);
					stmt.execute("DELETE FROM APP.ANAG_BISOGNI WHERE APP.ANAG_BISOGNI.ID_ANAGRAFICA="+id_anagrafica);
					
					
					for (int id:id_risorse)
						stmt.execute("INSERT INTO APP.ANAG_RISORSE " +
								"(APP.ANAG_RISORSE.ID_ANAGRAFICA, APP.ANAG_RISORSE.ID_RISORSA) " +
								 "VALUES " +
								 "("+id_anagrafica+","+id+")");
						for (int id:id_bisogni)
						stmt.execute("INSERT INTO APP.ANAG_BISOGNI " +
								"(APP.ANAG_BISOGNI.ID_ANAGRAFICA, APP.ANAG_BISOGNI.ID_BISOGNO) " +
								 "VALUES " +
								 "("+id_anagrafica+","+id+")");
										
					
					stmt.close();
					eseguito=true;
				}catch (SQLException exception)
				{	
				exception.printStackTrace();
				
				
				}
				return eseguito;
}
    public static void createItemBisognoRisorsa(boolean isBisogno,String nome) {
    	try {   	

    		stmt=conn.createStatement();

    		if (isBisogno)
    			stmt.execute("INSERT INTO APP.BISOGNI " +
    				"(APP.BISOGNI.BISOGNO) " +
    				"VALUES " +
    				"('"+replace(nome,"'","''")+"')");
    		else
    			stmt.execute("INSERT INTO APP.RISORSE " +
        				"(APP.RISORSE.RISORSA) " +
        				"VALUES " +
        				"('"+replace(nome,"'","''")+"')");
        		

    		stmt.close();

    	}catch (SQLException exception)
    	{	
    		exception.printStackTrace();


    	}

    }
    
    public static void updateItemBisognoRisorsa(boolean isBisogno,String nome,String id_bisogno_risorsa) {
    	try {   	

    		stmt=conn.createStatement();

    		if (isBisogno)
    			stmt.execute("UPDATE APP.BISOGNI SET " +
    					"APP.BISOGNI.BISOGNO ='" +replace(nome,"'","''")+"'"+
    					" WHERE APP.BISOGNI.ID="+ id_bisogno_risorsa);
    		else
    			stmt.execute("UPDATE APP.RISORSE SET " +
    					"APP.RISORSE.RISORSA ='" +replace(nome,"'","''")+"'"+
    					" WHERE APP.RISORSE.ID="+ id_bisogno_risorsa);
        		

    		stmt.close();

    	}catch (SQLException exception)
    	{	
    		exception.printStackTrace();


    	}

    	
    	
    	
    }
    
    public static boolean deleteItemBisognoRisorsa (boolean isBisogno,String id) {
		boolean eseguito=false;
		try {
		stmt=conn.createStatement();
		if (isBisogno){
			stmt.execute("DELETE FROM APP.BISOGNI WHERE APP.BISOGNI.ID="+id);
			stmt.execute("DELETE FROM APP.ANAG_BISOGNI WHERE APP.ANAG_BISOGNI.ID_BISOGNO="+id);
			
		} else {
			stmt.execute("DELETE FROM APP.RISORSE WHERE APP.RISORSE.ID="+id);
			stmt.execute("DELETE FROM APP.ANAG_RISORSE WHERE APP.ANAG_RISORSE.ID_RISORSA="+id);
			
		}
		stmt.close();
		eseguito=true;
		}catch (SQLException exception)
		{	
			exception.printStackTrace();
			
				
		}
		return eseguito;
	}	
    
    public static long getIDAnagraficaMax()
	{
		long ID=-1;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery("SELECT MAX(APP.ANAGRAFICA.ID) FROM APP.ANAGRAFICA ");
			rsRecord.next();
			ID = rsRecord.getLong(1);
			stmt.close();
			rsRecord.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			
			
		}
		
		return ID+1;
	}
    
    private static String getBisognoRisorsa(String id,boolean is_bisogno)
	{
		String bisogno=null;
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rsRecord = stmt.executeQuery(is_bisogno?
					"SELECT APP.BISOGNI.BISOGNO FROM APP.BISOGNI WHERE APP.BISOGNI.ID="+id:
					"SELECT APP.RISORSE.RISORSA FROM APP.RISORSE WHERE APP.RISORSE.ID="+id	);
			rsRecord.next();
			bisogno = rsRecord.getString(1);
			stmt.close();
			rsRecord.close();
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			
			
		}
		
		return bisogno;
	}
    
    public static Vector<String> getVectorCount(boolean is_bisogni)
   	{
   		
   		Vector<String> resultat = new Vector<String>();
   		
   			
   		try
   		{
   			stmt = conn.createStatement();
   			ResultSet rsRecord = stmt.executeQuery( is_bisogni?
   					"SELECT  ID_BISOGNO,COUNT(ID_ANAGRAFICA) AS NUM_PERSONE "+
   					"FROM APP.ANAG_BISOGNI "+
   					"GROUP BY ID_BISOGNO ":
					
   					"SELECT  ID_RISORSA,COUNT(ID_ANAGRAFICA) AS NUM_PERSONE "+
					"FROM APP.ANAG_RISORSE "+
					"GROUP BY ID_RISORSA "	);
   			ResultSetMetaData getTableDataResultSetMetaData = rsRecord.getMetaData();
   			StringBuffer sBuf;
   			while(rsRecord.next())
   			{
   				sBuf =new StringBuffer();
   			
   				for(int cptCols = 1; cptCols <= getTableDataResultSetMetaData.getColumnCount(); cptCols++)
   				{
   					if (cptCols==1)
   						if (is_bisogni)
   							sBuf.append(getBisognoRisorsa(rsRecord.getString(cptCols),true)+"#");
   						else
   							sBuf.append(getBisognoRisorsa(rsRecord.getString(cptCols),false)+"#");
   							
   					else
   						sBuf.append(rsRecord.getString(cptCols)+"#");
   					
   				}
   				resultat.add(sBuf.toString());
   				
   			}
   			
   			
   			stmt.close();
   		}
   		catch (SQLException exception)
   		{
   			exception.printStackTrace();
   		}
   		
   		return resultat;
   	}
    
    
    
    private static String replace(String str, String pattern, String replace) {
  	  if (str==null) return null;
      int s = 0;
  	  int e = 0;
  	  StringBuffer result = new StringBuffer();
  	  if(pattern == null || pattern.equals("")) return str;
  	    while ((e = str.indexOf(pattern, s)) >= 0) {
  	      result.append(str.substring(s, e));
  	      result.append(replace);
  	      s = e+pattern.length();
  	      }
  	      result.append(str.substring(s));
  	      return result.toString();
  	    
 
 } 
    
    
    
    
}
