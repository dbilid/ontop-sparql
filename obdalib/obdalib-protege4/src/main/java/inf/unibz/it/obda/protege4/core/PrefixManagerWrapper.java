package inf.unibz.it.obda.protege4.core;

import inf.unibz.it.obda.api.io.AbstractPrefixManager;

import java.util.Map;

import org.protege.editor.owl.ui.prefix.PrefixMapper;

/**
 * The prefix manager is administrating the prefixes for ontolgyie. It allows to
 * register and unregister prefixes for ontolgies and to query them.
 * 
 * 
 */

public class PrefixManagerWrapper extends AbstractPrefixManager {

	PrefixMapper	owlmapper;

	public PrefixManagerWrapper(PrefixMapper owlmapper) {
		this.owlmapper = owlmapper;
	}

	@Override
	public void addUri(String uri, String prefix) {
		owlmapper.addPrefixMapping(prefix, uri);
		
	}

	@Override
	public String getPrefixForURI(String uri) {
		return owlmapper.getPrefix(uri);
	}

	@Override
	public Map<String, String> getPrefixMap() {
		return owlmapper.getPrefixMap();
	}

	@Override
	public String getURIForPrefix(String prefix) {
		return owlmapper.getValue(prefix);
	}
	
}