/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
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
package com.ibm.ws.annocache.test.scan.errors;

import org.junit.Test;

public class AnnotationTargets_PackageMismatch_ErrorTest extends AnnotationTargets_TestBase_ScanError {

    @Override
    public String getModName() {
        return AnnotationTargets_ErrorData.WAR_NAME_PACKAGE_MISMATCH;
    }

    @Override
    public String getModSimpleName() {
        return AnnotationTargets_ErrorData.WAR_SIMPLE_NAME_PACKAGE_MISMATCH;
    }

    @Test
    public void testAnnotationTest_Error_PackageMismatch() throws Exception {
        runSuiteTest( getBaseCase() ); // 'runSuiteTest' throws Exception
    }
}