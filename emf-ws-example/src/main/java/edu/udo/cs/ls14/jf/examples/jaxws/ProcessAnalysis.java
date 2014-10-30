package edu.udo.cs.ls14.jf.examples.jaxws;

import org.eclipse.emf.ecore.resource.Resource;

public class ProcessAnalysis implements IProcessAnalysis {

	private String text = "";
	private Resource resource;

	/* (non-Javadoc)
	 * @see edu.udo.cs.ls14.jf.examples.jaxws.IProcessAnalysis#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	/* (non-Javadoc)
	 * @see edu.udo.cs.ls14.jf.examples.jaxws.IProcessAnalysis#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		this.text = text;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
