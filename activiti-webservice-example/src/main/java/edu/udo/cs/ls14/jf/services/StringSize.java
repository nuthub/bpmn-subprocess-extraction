package edu.udo.cs.ls14.jf.services;

import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class StringSize {

	private static final Logger LOG = Logger.getLogger(StringSize.class
			.getName());

	/**
	 * Expects xmi of definitions object
	 * 
	 * @param string
	 * @return
	 */
	@WebMethod
	/** Important, otherwise, Activiti has no access to return value **/
	@WebResult(name = "result")
	public int getSize(String string) {
		if (string == null) {
			LOG.info("Received null input");
			return -1;
		}
		LOG.info("Received: " + string);
		int size = string.length();
		LOG.info("Size is " + size);
		return size;
	}
}
