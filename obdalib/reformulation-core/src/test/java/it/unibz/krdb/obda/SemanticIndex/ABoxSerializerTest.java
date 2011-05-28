package it.unibz.krdb.obda.SemanticIndex;

import it.unibz.krdb.obda.owlrefplatform.core.abox.ABoxSerializer;
import it.unibz.krdb.obda.owlrefplatform.core.abox.DAG;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyCreationException;

/**
 * @author Sergejs Pugacs
 */
public class ABoxSerializerTest extends TestCase {

    SemanticIndexHelper helper = new SemanticIndexHelper();
    private static final String select_fmt = "SELECT * FROM %s ORDER BY IDX";

    private void serialize_abox(String ontoname)
            throws OWLOntologyCreationException, SQLException {

        Set<OWLOntology> ontologies = helper.load_onto(ontoname);
        DAG dag = helper.load_dag(ontoname);

        ABoxSerializer.recreate_tables(helper.conn);
        ABoxSerializer.ABOX2DB(ontologies, dag, helper.conn);

        Statement stmt = helper.conn.createStatement();

        List<String[]> res_abox = new LinkedList<String[]>();

        ResultSet abox_rows = stmt.executeQuery(String.format(select_fmt, ABoxSerializer.class_table));
        while (abox_rows.next()) {
            String uri = abox_rows.getString(1);
            String idx = Integer.toString(abox_rows.getInt(2));
            res_abox.add(new String[]{uri, idx});
        }

        abox_rows = stmt.executeQuery(String.format(select_fmt, ABoxSerializer.objectprop_table));
        while (abox_rows.next()) {
            String uri1 = abox_rows.getString(1);
            String uri2 = abox_rows.getString(2);
            String idx = Integer.toString(abox_rows.getInt(3));
            res_abox.add(new String[]{uri1, uri2, idx});
        }

        abox_rows = stmt.executeQuery(String.format(select_fmt, ABoxSerializer.dataprop_table));

        while (abox_rows.next()) {
            String uri = abox_rows.getString(1);
            String lit = abox_rows.getString(2);
            String idx = Integer.toString(abox_rows.getInt(3));
            res_abox.add(new String[]{uri, lit, idx});
        }

        List<String[]> expected_abox = helper.get_abox(ontoname);
        assertEquals(expected_abox.size(), res_abox.size());

        for (int i = 0; i < expected_abox.size(); i++) {
            String[] e_s = expected_abox.get(i);
            String[] r_s = res_abox.get(i);
            assertEquals(e_s.length, r_s.length);
            for (int j = 0; j < e_s.length; ++j) {
                assertEquals(e_s[j], r_s[j]);
            }
        }
    }

    public void test_1_0_0() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_0_0";
        serialize_abox(testname);
    }

    public void test_1_0_1() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_0_1";
        serialize_abox(testname);
    }

    public void test_1_2_0() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_2_0";
        serialize_abox(testname);
    }

    public void test_1_3_0() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_3_0";
        serialize_abox(testname);
    }

    public void test_1_4_0() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_4_0";
        serialize_abox(testname);
    }

    public void test_1_5_0() throws OWLOntologyCreationException, SQLException {
        String testname = "test_1_5_0";
        serialize_abox(testname);
    }

}