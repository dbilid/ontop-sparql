package it.unibz.krdb.obda.owlrefplatform.core.ontology.imp;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.BasicConceptDescription;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.GeneralConceptDescription;


public class NegatedBasicConceptDescriptionImpl implements GeneralConceptDescription{

	private BasicConceptDescription basicConcept = null;

	protected NegatedBasicConceptDescriptionImpl(BasicConceptDescription basicConceptDescriptionImpl){
		this.basicConcept = basicConceptDescriptionImpl;
	}

	public BasicConceptDescription getBasicConceptDescriptionImpl(){
		return basicConcept;
	}

	public Predicate getPredicate() {
		return basicConcept.getPredicate();
	}

	public boolean isInverse() {

		return basicConcept.isInverse();
	}
}