/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
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
package com.ibm.ws.jaxrs.fat.jerseywithinjection;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Dependent
public class TestEntityMessageBodyWriter2 implements MessageBodyWriter<Object> {

    @Resource(description = "Application Data Source", name = "jdbc/TestDataSource")
    private DataSource datasource;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
                               MediaType mediaType) {
        System.out.println("TestEntityMessageBodyWriter2(isWriteable) - datasource " + datasource.toString());
        return type == TestEntity2.class;
    }

    @Override
    public long getSize(Object entity, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType) {
        System.out.println("getSize");
        return -1;
    }

    @Override
    public void writeTo(Object entity, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
                    throws IOException, WebApplicationException {
        System.out.println("TestEntityMessageBodyWriter2(writeTo)");
    }

}
