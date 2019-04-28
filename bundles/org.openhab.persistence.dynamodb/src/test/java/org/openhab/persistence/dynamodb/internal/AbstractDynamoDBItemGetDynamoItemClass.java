/**
 * Copyright (c) 2010-2019 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.persistence.dynamodb.internal;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.eclipse.smarthome.core.library.items.CallItem;
import org.eclipse.smarthome.core.library.items.ColorItem;
import org.eclipse.smarthome.core.library.items.ContactItem;
import org.eclipse.smarthome.core.library.items.DateTimeItem;
import org.eclipse.smarthome.core.library.items.DimmerItem;
import org.eclipse.smarthome.core.library.items.LocationItem;
import org.eclipse.smarthome.core.library.items.NumberItem;
import org.eclipse.smarthome.core.library.items.RollershutterItem;
import org.eclipse.smarthome.core.library.items.StringItem;
import org.eclipse.smarthome.core.library.items.SwitchItem;
import org.junit.Test;

/**
 * Test for AbstractDynamoDBItem.getDynamoItemClass
 *
 * @author Sami Salonen
 */
public class AbstractDynamoDBItemGetDynamoItemClass {

    @Test
    public void testCallItem() throws IOException {
        assertEquals(DynamoDBStringItem.class, AbstractDynamoDBItem.getDynamoItemClass(CallItem.class));
    }

    @Test
    public void testContactItem() throws IOException {
        assertEquals(DynamoDBBigDecimalItem.class, AbstractDynamoDBItem.getDynamoItemClass(ContactItem.class));
    }

    @Test
    public void testDateTimeItem() throws IOException {
        assertEquals(DynamoDBStringItem.class, AbstractDynamoDBItem.getDynamoItemClass(DateTimeItem.class));
    }

    @Test
    public void testStringItem() throws IOException {
        assertEquals(DynamoDBStringItem.class, AbstractDynamoDBItem.getDynamoItemClass(StringItem.class));
    }

    @Test
    public void testLocationItem() throws IOException {
        assertEquals(DynamoDBStringItem.class, AbstractDynamoDBItem.getDynamoItemClass(LocationItem.class));
    }

    @Test
    public void testNumberItem() throws IOException {
        assertEquals(DynamoDBBigDecimalItem.class, AbstractDynamoDBItem.getDynamoItemClass(NumberItem.class));
    }

    @Test
    public void testColorItem() throws IOException {
        assertEquals(DynamoDBStringItem.class, AbstractDynamoDBItem.getDynamoItemClass(ColorItem.class));
    }

    @Test
    public void testDimmerItem() throws IOException {
        assertEquals(DynamoDBBigDecimalItem.class, AbstractDynamoDBItem.getDynamoItemClass(DimmerItem.class));
    }

    @Test
    public void testRollershutterItem() throws IOException {
        assertEquals(DynamoDBBigDecimalItem.class, AbstractDynamoDBItem.getDynamoItemClass(RollershutterItem.class));
    }

    @Test
    public void testOnOffTypeWithSwitchItem() throws IOException {
        assertEquals(DynamoDBBigDecimalItem.class, AbstractDynamoDBItem.getDynamoItemClass(SwitchItem.class));
    }
}
