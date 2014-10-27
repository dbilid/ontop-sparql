package it.unibz.krdb.obda.ontology.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.ontology.BasicClassDescription;
import it.unibz.krdb.obda.ontology.DataType;
import it.unibz.krdb.obda.ontology.Description;
import it.unibz.krdb.obda.ontology.OClass;
import it.unibz.krdb.obda.ontology.Property;
import it.unibz.krdb.obda.ontology.PropertySomeRestriction;
import it.unibz.krdb.obda.ontology.SubClassOfAxiom;
import it.unibz.krdb.obda.ontology.SubPropertyOfAxiom;

/*
 * #%L
 * ontop-obdalib-core
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */



public class SubClassOfAxiomImpl implements SubClassOfAxiom {

	private static final long serialVersionUID = -7590338987239580423L;

	private final BasicClassDescription including; // righthand side
	private final BasicClassDescription included;
	private final String string;
	
	SubClassOfAxiomImpl(BasicClassDescription subDesc, BasicClassDescription superDesc) {
		if (subDesc == null || superDesc == null) {
			throw new RuntimeException("Recieved null in concept inclusion");
		}
		included = subDesc;
		including = superDesc;
		StringBuilder bf = new StringBuilder();
		bf.append(included.toString());
		bf.append(" ISA ");
		bf.append(including.toString());
		string = bf.toString();
	}

	@Override
	public BasicClassDescription getSub() {
		return included;
	}

	@Override
	public BasicClassDescription getSuper() {
		return including;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SubClassOfAxiomImpl)) {
			return false;
		}
		SubClassOfAxiomImpl inc2 = (SubClassOfAxiomImpl) obj;
		if (!including.equals(inc2.including)) {
			return false;
		}
		return (included.equals(inc2.included));
	}
	
	@Override
	public Set<Predicate> getReferencedEntities() {
		Set<Predicate> res = new HashSet<Predicate>();
		for (Predicate p : getPredicates(included)) {
			res.add(p);
		}
		for (Predicate p : getPredicates(including)) {
			res.add(p);
		}
		return res;
	}

	private Set<Predicate> getPredicates(BasicClassDescription desc) {
		Set<Predicate> preds = new HashSet<Predicate>();
		if (desc instanceof OClass) {
			preds.add(((OClass) desc).getPredicate());
		} else if (desc instanceof PropertySomeRestriction) {
			preds.add(((PropertySomeRestriction) desc).getPredicate());
		} else if (desc instanceof DataType) {
			preds.add(((DataType) desc).getPredicate());
		} else {
			throw new UnsupportedOperationException("Cant understand: " + desc.toString());
		}
		return preds;
	}

	@Override
	public int hashCode() {
		return string.hashCode();
	}

	@Override
	public String toString() {
		return string;
	}
	
}