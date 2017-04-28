package org.camunda.bpm.getstarted.loanapproval;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.sql.*;

public class Driver implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

  public void execute(DelegateExecution execution) throws Exception {
	  //LOGGER.info("Processing request by '"+execution.getVariable("customerId")+"'...");
	  
	  LOGGER.info("llego a aqui");
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Dissi", "root", "root");
		  Statement statement = connection.createStatement();
		  statement.executeUpdate("INSERT INTO Clientes (Nombre, RFC, Domicilio, Correo, Tel) VALUES ('"+execution.getVariable("nombre")+"','"+execution.getVariable("rfc")+"','"+execution.getVariable("dom")+"','"+execution.getVariable("correo")+"','"+execution.getVariable("tel")+"');");
		  //ResultSet set = statement.executeQuery("select * from table1");
		  LOGGER.info("CustomerId \t Amount \t Rate");
		  /*while(set.next()){
			  LOGGER.info(set.getString(1)+"\t"+set.getDouble(2)+set.getDouble(3));
		  }*/
		  connection.close();
	  }
	  catch(Exception e){
		  LOGGER.info(e.toString());
	  }
  }

}