/**
 * 
 */
package com.kant.design.patterns.facade;

import java.sql.Connection;

/**
 * Facade design pattern is more like a helper for client applications, it
 * doesn’t hide subsystem interfaces from the client. Whether to use Facade or
 * not is completely dependent on client code. Facade design pattern can be
 * applied at any point of development, usually when the number of interfaces
 * grow and system gets complex. Subsystem interfaces are not aware of Facade
 * and they shouldn’t have any reference of the Facade interface. Facade design
 * pattern should be applied for similar kind of interfaces, its purpose is to
 * provide a single interface rather than multiple interfaces that does the
 * similar kind of jobs. We can use Factory pattern with Facade to provide
 * better interface to client systems.
 * 
 * @author shaskant
 *
 */
public class FacadePatternTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String tableName = "Employee";

		// generating MySql HTML report and Oracle PDF report without using
		// Facade
		Connection con = MySqlHelper.getMySqlDBConnection();
		MySqlHelper mySqlHelper = new MySqlHelper();
		mySqlHelper.generateMySqlHTMLReport(tableName, con);

		Connection con1 = OracleHelper.getOracleDBConnection();
		OracleHelper oracleHelper = new OracleHelper();
		oracleHelper.generateOraclePDFReport(tableName, con1);

		// generating MySql HTML report and Oracle PDF report using Facade
		HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL,
				HelperFacade.ReportTypes.HTML, tableName);
		HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE,
				HelperFacade.ReportTypes.PDF, tableName);
	}

}
