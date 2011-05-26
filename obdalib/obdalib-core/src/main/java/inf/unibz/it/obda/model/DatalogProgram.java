package inf.unibz.it.obda.model;

import inf.unibz.it.obda.domain.Query;

import java.util.List;

public interface DatalogProgram extends Query {

	public List<CQIE> getRules();

	public void appendRule(CQIE rule);

	public void appendRule(List<CQIE> rule);

	public void removeRule(CQIE rule);

	public void removeRules(List<CQIE> rule);

	public boolean isUCQ();

	/***
	 * Returns all the rules that have the given predicate in their heads
	 * 
	 * @param headPredicate
	 * @return
	 */
	public List<CQIE> getRules(Predicate headPredicate);
	
	
}