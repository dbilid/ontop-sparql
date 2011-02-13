package org.obda.owlrefplatform.core.unfolding;

import org.obda.query.domain.DatalogProgram;

/**
 * The unfolder for the direct partial evaluation
 * 
 * @author obda
 *
 */
public class DirectMappingUnfolder implements UnfoldingMechanism {

	public DatalogProgram unfold(DatalogProgram query) {
		return query;
	}


}