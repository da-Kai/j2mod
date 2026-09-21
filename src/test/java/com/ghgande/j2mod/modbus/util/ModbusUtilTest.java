package com.ghgande.j2mod.modbus.util;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ModbusUtilTest {

    @Test
    public void toHex_encodesEmptyArrayAndNull() {
        assertEquals("", ModbusUtil.toHex(new byte[0]));
        assertEquals("", ModbusUtil.toHex((byte[]) null));
        assertEquals("", ModbusUtil.toHex(null, 0, 10));
    }

    @Test
    public void toHex_encodesBytesAsUppercaseSpaceSeparatedHex() {
        byte[] data = {0x00, 0x0F, 0x10, 0x7F, (byte) 0x80, (byte) 0xFF};
        assertEquals("00 0F 10 7F 80 FF", ModbusUtil.toHex(data));
    }

    @Test
    public void toHex_endExclusiveRange() {
        byte[] data = {0x00, 0x11, 0x22, 0x33, 0x44};

        assertEquals("11 22 33", ModbusUtil.toHex(data, 1, 4));
    }

    @Test
    public void toHex_supportsSingleByteRange() {
        byte[] data = {0x12, (byte) 0xAB, 0x34};

        assertEquals("AB", ModbusUtil.toHex(data, 1, 2));
    }

    @Test
    public void toHex_returnsEmptyStringForEmptyRange() {
        byte[] data = {0x12, 0x34};

        assertEquals("", ModbusUtil.toHex(data, 1, 1));
    }

    @Test
    public void toHex_clampsEndToArrayLength() {
        byte[] data = {0x12, 0x34, 0x56};

        assertEquals("34 56", ModbusUtil.toHex(data, 1, 99));
    }

    @Test
    public void toHexBytes_intEncodesLowerByteAsAscii() {
        assertArrayEquals(
                "00".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(0x00));

        assertArrayEquals(
                "0F".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(0x0F));

        assertArrayEquals(
                "10".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(0x10));

        assertArrayEquals(
                "FF".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(0xFF));
    }

    @Test
    public void toHexBytes_intUsesOnlyTheLowByte() {
        assertArrayEquals(
                "AB".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(0x12AB));

        assertArrayEquals(
                "FF".getBytes(StandardCharsets.US_ASCII),
                ModbusUtil.toHexBytes(-1));
    }
}
