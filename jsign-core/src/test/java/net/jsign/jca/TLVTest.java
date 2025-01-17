/**
 * Copyright 2023 Emmanuel Bourg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.jsign.jca;

import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import static org.junit.Assert.*;

public class TLVTest {

    @Test
    public void testParseList() throws Exception {
        byte[] data = Hex.decodeHex("01 01 86 02 02 05 05 08 04 01 26 9A 33".replaceAll(" ", ""));
        TLV tlv = TLV.parse(ByteBuffer.wrap(data));

        assertNull("Root tag", tlv.tag());
        assertEquals("Length", 3, tlv.children().size());
        assertEquals("Tag 1", "1", tlv.children().get(0).tag());
        assertEquals("Tag 2", "2", tlv.children().get(1).tag());
        assertEquals("Tag 3", "8", tlv.children().get(2).tag());
        assertEquals("Value 1", "86", Hex.encodeHexString(tlv.children().get(0).value()));
        assertEquals("Value 2", "0505", Hex.encodeHexString(tlv.children().get(1).value()));
        assertEquals("Value 3", "01269a33", Hex.encodeHexString(tlv.children().get(2).value()));
    }
}
