package it.unibz.inf.ontop.sesame;

/*
 * #%L
 * ontop-quest-sesame
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

import it.unibz.inf.ontop.model.OBDAModel;
import it.unibz.inf.ontop.ontology.Assertion;
import it.unibz.inf.ontop.ontology.Ontology;
import it.unibz.inf.ontop.owlrefplatform.core.abox.QuestMaterializer;
import it.unibz.inf.ontop.sesame.SesameStatementIterator;

import java.util.Iterator;

public class SesameMaterializer {

    private Iterator<Assertion> assertions = null;
    private QuestMaterializer materializer;

    public SesameMaterializer(OBDAModel model, boolean doStreamResults) throws Exception {
        this(model, null, doStreamResults);
    }

    public SesameMaterializer(OBDAModel model, Ontology onto, boolean doStreamResults) throws Exception {
        materializer = new QuestMaterializer(model, onto, doStreamResults);
        assertions = materializer.getAssertionIterator();
    }

    public SesameStatementIterator getIterator() {
        return new SesameStatementIterator(assertions);
    }

    public void disconnect() {
        materializer.disconnect();
    }

    public long getTriplesCount() {
        try {
            return materializer.getTriplesCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getVocabularySize() {
        return materializer.getVocabSize();
    }
}
