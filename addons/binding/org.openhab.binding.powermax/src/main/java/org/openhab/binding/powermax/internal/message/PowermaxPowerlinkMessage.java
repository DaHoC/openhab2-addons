/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.powermax.internal.message;

import org.openhab.binding.powermax.internal.state.PowermaxState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for POWERLINK message handling
 *
 * @author Laurent Garnier
 * @since 1.9.0
 */
public class PowermaxPowerlinkMessage extends PowermaxBaseMessage {

    private final Logger logger = LoggerFactory.getLogger(PowermaxPowerlinkMessage.class);

    /**
     * Constructor
     *
     * @param message
     *            the received message as a buffer of bytes
     */
    public PowermaxPowerlinkMessage(byte[] message) {
        super(message);
    }

    @Override
    public PowermaxState handleMessage() {
        super.handleMessage();

        PowermaxState updatedState = null;

        byte[] message = getRawData();
        byte subType = message[2];

        PowermaxCommDriver comm = PowermaxCommDriver.getTheCommDriver();
        if (subType == 0x03) {
            // keep alive message
            comm.sendAck(this, (byte) 0x02);
            updatedState = new PowermaxState();
            updatedState.setLastKeepAlive(System.currentTimeMillis());
        } else if (subType == 0x0A && message[4] == 0x01) {
            logger.info("Powermax alarm binding: Enrolling Powerlink");
            comm.enrollPowerlink();
            updatedState = new PowermaxState();
            updatedState.setDownloadSetupRequired(true);
        } else {
            comm.sendAck(this, (byte) 0x02);
        }

        return updatedState;
    }

    @Override
    public String toString() {
        String str = super.toString();

        byte[] message = getRawData();
        byte subType = message[2];

        if (subType == 0x03) {
            str += "\n - sub type = keep alive";
        } else if (subType == 0x0A) {
            str += "\n - sub type = enroll";
            str += "\n - enroll = " + String.format("%02X", message[4]);
        } else {
            str += "\n - sub type = " + String.format("%02X", subType);
        }

        return str;
    }

}
