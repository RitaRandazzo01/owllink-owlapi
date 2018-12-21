package org.coode.owlapi.owlxmlparser;

import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.UnloadableImportException;

/**
 * @author Matthew Horridge, The University of Manchester, Information Management
 *         Group, Date: 08-Oct-2009
 */
public class SWRLDataPropertyAtomElementHandler extends SWRLAtomElementHandler {

    private OWLDataPropertyExpression prop;
    private SWRLIArgument arg0 = null;
    private SWRLDArgument arg1 = null;

    /**
     * @param handler
     *        owlxml handler
     */
    public SWRLDataPropertyAtomElementHandler(OWLXMLParserHandler handler) {
        super(handler);
    }

    @Override
    public void handleChild(OWLDataPropertyElementHandler handler)
            throws OWLXMLParserException {
        prop = handler.getOWLObject();
    }

    @Override
    public void handleChild(SWRLVariableElementHandler handler)
            throws OWLXMLParserException {
        if (arg0 == null) {
            arg0 = handler.getOWLObject();
        } else if (arg1 == null) {
            arg1 = handler.getOWLObject();
        }
    }

    @Override
    public void handleChild(OWLLiteralElementHandler handler)
            throws OWLXMLParserException {
        arg1 = getOWLDataFactory().getSWRLLiteralArgument(
                handler.getOWLObject());
    }

    @Override
    public void handleChild(OWLIndividualElementHandler _handler)
            throws OWLXMLParserException {
        arg0 = getOWLDataFactory().getSWRLIndividualArgument(
                _handler.getOWLObject());
    }

    @Override
    public void handleChild(OWLAnonymousIndividualElementHandler _handler)
            throws OWLXMLParserException {
        arg0 = getOWLDataFactory().getSWRLIndividualArgument(
                _handler.getOWLObject());
    }

    @Override
    public void endElement() throws OWLParserException,
            UnloadableImportException {
        setAtom(getOWLDataFactory().getSWRLDataPropertyAtom(prop, arg0, arg1));
        getParentHandler().handleChild(this);
    }
}
