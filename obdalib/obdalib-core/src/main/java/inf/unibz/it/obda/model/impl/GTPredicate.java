package inf.unibz.it.obda.model.impl;

import inf.unibz.it.obda.model.impl.GTPredicate;
import inf.unibz.it.obda.model.impl.PredicateImp;

import java.net.URI;

import org.obda.query.domain.ComparisonOperatorPredicate;

public class GTPredicate extends ComparisonOperatorPredicate {

	private URI name = URI.create("http://www.obda.org/ucq/predicate/operator/comparison#GT");
	private int identifier = -1;
	
	public int getArity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public URI getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public boolean equals(Object obj){
		if(obj == null|| !(obj instanceof PredicateImp)){
			return false;
		}else{
			return this.hashCode() == obj.hashCode();
		}	
	}
	
	public int hashCode(){
		return identifier;
	}

	public GTPredicate copy() {
		return new GTPredicate();
	}

}