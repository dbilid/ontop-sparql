package it.unibz.krdb.obda.api.io;

import it.unibz.krdb.obda.io.PrefixManager;
import it.unibz.krdb.obda.io.SimplePrefixManager;
import it.unibz.krdb.obda.model.Atom;
import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.DatalogProgram;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.impl.AtomImpl;
import it.unibz.krdb.obda.model.impl.CQIEImpl;
import it.unibz.krdb.obda.model.impl.DatalogProgramImpl;
import it.unibz.krdb.obda.model.impl.FunctionalTermImpl;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;

public class PrefixRendererTest extends TestCase {

	PrefixManager	pm;
	DatalogProgram	query;
	CQIE			rule1;

	// EntityNameRenderer rend;

	@Before
	public void setUp() throws Exception {
		pm = new SimplePrefixManager();
		// rend = new EntityNameRenderer(pm);

		OBDADataFactory pfac = OBDADataFactoryImpl.getInstance();
		OBDADataFactory tfac = OBDADataFactoryImpl.getInstance();
		query = tfac.getDatalogProgram();

		LinkedList<Term> innerterms = new LinkedList<Term>();
		innerterms.add(tfac.getVariable("id"));

		List<Term> terms = new LinkedList<Term>();
		terms.add(tfac.getFunctionalTerm(pfac.getPredicate(URI.create("http://obda.org/onto.owl#person-individual"), 1), innerterms));

		Atom body = tfac.getAtom(pfac.getPredicate(URI.create("http://obda.org/onto.owl#Person"), 1), terms);

		terms = new LinkedList<Term>();
		terms.add(tfac.getVariable("id"));
		Atom head = tfac.getAtom(pfac.getPredicate(URI.create("http://obda.org/predicates#q"), 1), terms);

		rule1 = tfac.getCQIE(head, Collections.singletonList(body));
		query.appendRule(rule1);
	}

	/***
	 * Checking that the atoms that use the default namespace are renderered in
	 * short form and those who don't have it are renderered with the full uri
	 */
	public void testNamespace1() {
		pm.setDefaultNamespace("http://obda.org/onto.owl#");
		String name = pm.getShortForm(query.getRules().get(0).getHead().getPredicate().toString(), true);
		assertTrue(name, name.equals("http://obda.org/predicates#q"));

		name = pm.getShortForm(query.getRules().get(0).getBody().get(0).getPredicate().toString(), true);
		assertTrue(name, name.equals("Person"));

		name = pm
				.getShortForm(((FunctionalTermImpl) query.getRules().get(0).getBody().get(0).getTerms().get(0)).getName().toString(), true);
		assertTrue(name, name.equals("person-individual"));

		pm.setDefaultNamespace("http://obda.org/predicates#");
		name = pm.getShortForm(query.getRules().get(0).getHead().getPredicate().toString(), true);
		assertTrue(name, name.equals("q"));

		name = pm.getShortForm(query.getRules().get(0).getBody().get(0).getPredicate().toString(), true);
		assertTrue(name, name.equals("http://obda.org/onto.owl#Person"));

		name = pm
				.getShortForm(((FunctionalTermImpl) query.getRules().get(0).getBody().get(0).getTerms().get(0)).getName().toString(), true);
		assertTrue(name, name.equals("http://obda.org/onto.owl#person-individual"));
	}

	/***
	 * This test checks if the prefix are properly handled
	 */
	public void testPrefix1() {
		pm.setDefaultNamespace("http://obda.org/onto.owl#");
		pm.addUri("http://obda.org/predicates#", "obdap");

		String name = pm.getShortForm(query.getRules().get(0).getHead().getPredicate().toString(), true);
		assertTrue(name, name.equals("obdap:q"));

		name = pm.getShortForm(query.getRules().get(0).getBody().get(0).getPredicate().toString(), true);
		assertTrue(name, name.equals("Person"));

		name = pm
				.getShortForm(((FunctionalTermImpl) query.getRules().get(0).getBody().get(0).getTerms().get(0)).getName().toString(), true);
		assertTrue(name, name.equals("person-individual"));

		pm.setDefaultNamespace("http://obda.org/predicates#");
		pm.addUri("http://obda.org/onto.owl#", "onto");
		name = pm.getShortForm(query.getRules().get(0).getHead().getPredicate().toString(), true);
		assertTrue(name, name.equals("q"));

		name = pm.getShortForm(query.getRules().get(0).getBody().get(0).getPredicate().toString(), true);
		assertTrue(name, name.equals("onto:Person"));

		name = pm
				.getShortForm(((FunctionalTermImpl) query.getRules().get(0).getBody().get(0).getTerms().get(0)).getName().toString(), true);
		assertTrue(name, name.equals("onto:person-individual"));

	}

}