package org.camunda.bpm.getstarted.loanapproval;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.*;

public class Ingeniero implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

  public void execute(DelegateExecution execution) throws Exception {	  
	  
	  LOGGER.info("llego a aqui");
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Dissi", "root", "root");
		  Statement statement = connection.createStatement();
		  ResultSet set = statement.executeQuery("select ID from Clientes where Nombre  ='"+execution.getVariable("nombre")+"'");
		  set.first();
		  int IDcliente = set.getInt(1);
		  statement.executeUpdate("INSERT INTO Proyectos (Encargado, Cliente, Nombre) VALUES ("+execution.getVariable("ingeniero")+","+IDcliente+",'"+execution.getVariable("nombreproyecto")+"');");
		  connection.close();
	  }
	  catch(Exception e){
		  LOGGER.info(e.toString() + " fallo aqui");
	  }
  }

}