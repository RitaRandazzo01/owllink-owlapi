/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.coode.owlapi.owlxmlparser;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormatFactory;
import org.semanticweb.owlapi.io.AbstractOWLParser;
import org.semanticweb.owlapi.io.IRIDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyInputSourceException;
import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLDocumentFormatFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChangeException;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLRuntimeException;
import org.semanticweb.owlapi.model.UnloadableImportException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Matthew Horridge, The University Of Manchester, Bio-Health Informatics Group, Date:
 *         13-Dec-2006
 */
public class OWLXMLParser extends AbstractOWLParser {

    @Override
    public OWLDocumentFormat parse(IRI documentIRI, OWLOntology ontology) {
        return parse(new IRIDocumentSource(documentIRI), ontology,
            new OWLOntologyLoaderConfiguration());
    }

    @Override
    public OWLDocumentFormat parse(OWLOntologyDocumentSource documentSource, OWLOntology ontology,
        OWLOntologyLoaderConfiguration configuration)
        throws OWLParserException, OWLOntologyChangeException, UnloadableImportException {
        try {
            System.setProperty("entityExpansionLimit", "100000000");
            OWLXMLDocumentFormat format = new OWLXMLDocumentFormat();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
            SAXParser parser = factory.newSAXParser();
            InputSource isrc = null;
            try {
                isrc = getInputSource(documentSource, configuration);
                OWLXMLParserHandler handler = new OWLXMLParserHandler(ontology, configuration);
                parser.parse(isrc, handler);
                Map<String, String> prefix2NamespaceMap = handler.getPrefixName2PrefixMap();
                for (String prefix : prefix2NamespaceMap.keySet()) {
                    format.setPrefix(prefix, prefix2NamespaceMap.get(prefix));
                }
            } finally {
                if (isrc != null && isrc.getByteStream() != null) {
                    isrc.getByteStream().close();
                } else if (isrc != null && isrc.getCharacterStream() != null) {
                    isrc.getCharacterStream().close();
                }
            }
            return format;
        } catch (ParserConfigurationException e) {
            // What the hell should be do here? In serious trouble if this
            // happens
            throw new OWLRuntimeException(e);
        } catch (TranslatedOWLParserException e) {
            throw e.getParserException();
        } catch (TranslatedUnloadableImportException e) {
            throw e.getUnloadableImportException();
        } catch (SAXException | OWLOntologyInputSourceException | IOException e) {
            // General exception
            throw new OWLParserException(e);
        }
    }

    @Override
    public OWLDocumentFormatFactory getSupportedFormat() {
        return new OWLXMLDocumentFormatFactory();
    }
}
