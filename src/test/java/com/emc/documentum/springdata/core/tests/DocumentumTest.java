package com.emc.documentum.springdata.core.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.authentication.UserCredentials;

import com.documentum.fc.client.DfIdentityException;
import com.documentum.fc.client.DfServiceException;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.emc.documentum.springdata.core.Documentum;

public class DocumentumTest {
	
	private static UserCredentials credentials;
	private static Documentum doc;

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		credentials = new UserCredentials("dmadmin", "password");
		doc = new Documentum(credentials);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetCredentials() throws DfException {
		assertEquals(doc.getCredentials(),credentials);
		
	}
	
	
	@Test 
	public void testGetSession() throws DfException {
		
			String docBase = "FPIRepo";
			IDfSession session = doc.getSession(docBase);
			assertEquals(session.getDocbaseName(), docBase);
	}
	
	/**
	 * DfServiceException raised if no host and port information given either as constructor
	 * arguments or in dfc.properties
	 * @throws DfException 
	 */
//	@Test
//	public void testGetSessionThrowsDfServiceException() throws DfException {
//	try {
//		Documentum docWithoutHostAndPort = new Documentum(credentials);
//		String docBase = "FPIRepo";
//		docWithoutHostAndPort.getSession(docBase);
//	}
//	catch(Exception e) {
//		
//		expected.expect(DfServiceException.class);
//	}
//	}
//	

	@Test
	public void testGetSessionThrowsDfIdentityException() throws DfException {
	try {
		UserCredentials wrongCredentials = new UserCredentials("admin", "passwrd");
		Documentum docWithWrongCredentials = new Documentum(wrongCredentials);
		String docBase = "FPIRepo";
		docWithWrongCredentials.getSession(docBase);
	}
	catch(Exception e) {
		
		expected.expect(DfIdentityException.class);
	}
	}
	

	@Test
	public void testDocumentumUserCredentialsStringString() throws DfException {
		Documentum docCreatedWithPrimaryHostAndPort = new Documentum(credentials, "10.31.157.9", "1589" );
	    String docBase = "FPIRepo";
		IDfSession session = docCreatedWithPrimaryHostAndPort.getSession(docBase);
		assertEquals(session.getDocbaseName(), docBase);
	}


}