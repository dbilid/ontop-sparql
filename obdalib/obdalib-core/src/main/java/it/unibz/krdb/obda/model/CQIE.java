package it.unibz.krdb.obda.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CQIE extends OBDAQuery {

	public Function getHead();

	public List<Function> getBody();

	public void updateHead(Function head);

	public void updateBody(List<Function> body);

	public CQIE clone();
	
	public Set<Variable> getReferencedVariables();
	
	public Map<Variable,Integer> getVariableCount();
}
