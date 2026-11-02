package com.ghgande.j2mod.modbus.io;

import com.ghgande.j2mod.modbus.msg.ModbusMessage;
import com.ghgande.j2mod.modbus.msg.ModbusRequest;
import com.ghgande.j2mod.modbus.msg.ModbusResponse;
import com.ghgande.j2mod.modbus.net.AbstractModbusListener;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import org.junit.Test;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModbusSerialTransportTest {

    @Test
    public void testSerialTransport() {
        final SerialConnection port = new SerialConnection();

        final double transmissionTime8 = ModbusSerialTransport.calculateTransmissionTime(8, port);
        assertTrue(transmissionTime8 > 0);
        assertEquals(8_333_333.3, transmissionTime8, 0.1);

        final double transmissionTime64 = ModbusSerialTransport.calculateTransmissionTime(64, port);
        assertTrue(transmissionTime64 > 0);
        assertEquals(66_666_666.6, transmissionTime64, 0.1);

        final double transmissionTime254 = ModbusSerialTransport.calculateTransmissionTime(254, port);
        assertTrue(transmissionTime254 > 0);
        assertEquals(264_583_333.3, transmissionTime254, 0.1);

        final double transmissionTimeExtreme = ModbusSerialTransport.calculateTransmissionTime(256_000, port);
        assertTrue(transmissionTimeExtreme > 0);
        assertEquals(266_666_666_666.6, transmissionTimeExtreme, 0.1);
    }

}
