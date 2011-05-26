/*
 * @(#)DatalogProgramParser 25/12/2010
 *
 * Copyright 2010 OBDA-API. All rights reserved.
 * Use is subject to license terms.
 */
package inf.unibz.it.obda.parser;

import inf.unibz.it.obda.model.CQIE;
import inf.unibz.it.obda.model.DatalogProgram;
import inf.unibz.it.obda.parser.DatalogLexer;
import inf.unibz.it.obda.parser.DatalogParser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;


/**
 * Executes the parsing process with a reference to a Parser object.
 *
 * @author Josef Hardi <josef.hardi@gmail.com>
 */
public class DatalogProgramParser {

	private DatalogParser parser;

	private DatalogProgram datalog;

	/**
	 * Default constructor;
	 */
	public DatalogProgramParser() { }

    /**
     * Returns the datalog object from the parsing process.
	 *
	 * @param query a string of Datalog query.
	 * @return the datalog object.
     * @throws RecognitionException the syntax is not supported yet.
	 */
	public DatalogProgram parse(String query) throws RecognitionException {

		ANTLRStringStream inputStream = new ANTLRStringStream(query);
		DatalogLexer lexer = new DatalogLexer(inputStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		parser = new DatalogParser(tokenStream);

		datalog = parser.parse();

		if (parser.getNumberOfSyntaxErrors() != 0)
			throw new RecognitionException();

		return datalog;
	}

	/**
	 * Returns the rule from the datalog object based on the index number.
	 *
	 * @param index the rule index.
	 * @return a conjunctive query object.
	 */
	public CQIE getRule(int index) {
		return datalog.getRules().get(index);
	}
}