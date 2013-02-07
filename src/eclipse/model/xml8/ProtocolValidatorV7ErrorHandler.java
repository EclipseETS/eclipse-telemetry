package eclipse.model.xml8;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ProtocolValidatorV7ErrorHandler implements ErrorHandler {
	
	static Logger logger = Logger.getLogger("xml");

	public void error(SAXParseException parseException) throws SAXException {
		printErrorException(parseException);
		throw new SAXException();
	}

	public void fatalError(SAXParseException parseException) throws SAXException {
		printFatalErrorException(parseException);
		throw new SAXException();
	}

	public void warning(SAXParseException parseException) throws SAXException {
		printWarningException(parseException);
		throw new SAXException();
	}

	private void printErrorException(SAXParseException exception) {
		logger.error("\t (line " + exception.getLineNumber() + " Col " + exception.getColumnNumber() + ") " + exception.getMessage());
	}
	
	private void printFatalErrorException(SAXParseException exception) {
		logger.fatal("\t (line " + exception.getLineNumber() + " Col " + exception.getColumnNumber() + ") " + exception.getMessage());
	}
	
	private void printWarningException(SAXParseException exception) {
		logger.warn("\t (line " + exception.getLineNumber() + " Col " + exception.getColumnNumber() + ") " + exception.getMessage());
	}

}
