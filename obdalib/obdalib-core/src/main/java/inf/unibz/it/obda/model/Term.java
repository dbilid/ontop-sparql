package inf.unibz.it.obda.model;

import inf.unibz.it.obda.model.Term;

/**
 * This class defines the basic component of the proposition. A proposition
 * is a particular kind of sentence, in which the subject and predicate are
 * combined. In this scenario, term means the subject (or sometimes can be
 * the object) of a preposition.
 */
public interface Term {

	/**
	 * Duplicate the object by performing a deep cloning.
	 *
	 * @return the copy of the object.
	 */
	public Term copy();

	/**
	 * Get the name of the term object.
	 *
	 * @return a string name.
	 */
	public String getName();

	/**
	 * Get the string representation of the term object.
	 *
	 * @return a string text.
	 */
	public String toString();
}