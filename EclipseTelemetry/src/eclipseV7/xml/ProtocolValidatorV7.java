package eclipseV7.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import core.ProtocolValidator;

public class ProtocolValidatorV7 implements ProtocolValidator {

	File xmlFile = null;
	File schemaFile = null;
	
	Schema schema = null;
	Source xml = null;
	
	Validator validator = null;
	
	static Logger logger = Logger.getLogger("xml");
	
	public ProtocolValidatorV7(String XMLLocation, String schemaLocation) throws NullPointerException {		
		xmlFile = new File(XMLLocation);
		schemaFile = new File(schemaLocation);
		
	}
	
	@Override
	public boolean xmlIsValid() {
		// Check if schema is valid
		if (schemaIsValid()) {
				
			// Load XML
			Source xml = new StreamSource(xmlFile);
	
			try {
				// Check the document
				validator.validate(xml);
				
				} catch (IOException e) {
					logger.fatal("Protocol XML file not found.");
					return false;
					
				} catch (SAXException e) {
					logger.fatal("Protocol XML file is invalid");
					return false;
			}
			logger.info("Protocol XML file appears to be valid.");
			return true;
		}
		else {
			return false;
		}

	}
	
	private boolean schemaIsValid() {
		// W3C XML Schema language is used
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		
		// Load and validate Schema
		try {
			schema = factory.newSchema(schemaFile);
			validator = schema.newValidator();
			validator.setErrorHandler(new ProtocolValidatorV7ErrorHandler());
			logger.info("Protocol XML Schema appears to be valid.");
			return true;
			
		} catch (SAXException e) {
			logger.fatal("Schema " + schemaFile.getAbsolutePath() +" is invalid or not found.");
			return false;
		}
	}
	

}
