/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.javaee.ddmodel.common;

import com.ibm.ws.javaee.dd.web.common.ResponseEncoding;
import com.ibm.ws.javaee.ddmodel.DDParser;
import com.ibm.ws.javaee.ddmodel.DDParser.ParsableListImplements;

/*
<xsd:complexType name="response-encodingType">
<xsd:simpleContent>
<xsd:extension base="javaee:xsdTokenType">
</xsd:extension>
</xsd:simpleContent>
</xsd:complexType>
*/

public class ResponseEncodingType extends XSDTokenType implements ResponseEncoding {

    public static class ListType extends ParsableListImplements<ResponseEncodingType, ResponseEncoding> {
        @Override
        public ResponseEncodingType newInstance(DDParser parser) {
            return new ResponseEncodingType();
        }
    }

}
