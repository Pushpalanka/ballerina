/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.nativeimpl.lang.datatables;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeEnum;
import org.ballerinalang.model.values.BDataTable;
import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.Attribute;
import org.ballerinalang.natives.annotations.BallerinaAnnotation;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

/**
 * Native function to get any object in their native toString in a given column index.
 * ballerina.model.datatables:getValueAsString(datatable, any)
 *
 * @since 0.88
 */
@BallerinaFunction(
        packageName = "ballerina.lang.datatables",
        functionName = "getValueAsString",
        args = {@Argument(name = "dt", type = TypeEnum.DATATABLE),
                @Argument(name = "column", type = TypeEnum.ANY)},
        returnType = {@ReturnType(type = TypeEnum.STRING)},
        isPublic = true
)
@BallerinaAnnotation(annotationName = "Description", attributes = {@Attribute(name = "value",
        value = "Retrieves the string value of the designated column in the current row."
                + " The value of type blob and binary columns will return as a Base64Encoded string.") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "dt",
        value = "The datatable object") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "column",
        value = "The column position of the result as index or name") })
@BallerinaAnnotation(annotationName = "Return", attributes = {@Attribute(name = "string",
        value = "The column value as a string") })
public class GetValueAsString extends AbstractNativeFunction {

    public BValue[] execute(Context ctx) {
        BDataTable dataTable = (BDataTable) getArgument(ctx, 0);
        BValue index = getArgument(ctx, 1);

        BValue[] result = null;
        if (index instanceof BInteger) {
            result = getBValues(new BString(dataTable.getObjectAsString(((BInteger) index).intValue())));
        } else if (index instanceof BString) {
            result = getBValues(new BString(dataTable.getObjectAsString(((BString) index).stringValue())));
        }
        return result;
    }
}
