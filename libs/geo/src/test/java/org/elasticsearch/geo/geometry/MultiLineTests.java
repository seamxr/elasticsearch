/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.geo.geometry;

import org.elasticsearch.geo.utils.WellKnownText;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiLineTests extends BaseGeometryTestCase<MultiLine> {

    @Override
    protected MultiLine createTestInstance(boolean hasAlt) {
        int size = randomIntBetween(1, 10);
        List<Line> arr = new ArrayList<Line>();
        for (int i = 0; i < size; i++) {
            arr.add(randomLine(hasAlt));
        }
        return new MultiLine(arr);
    }

    public void testBasicSerialization() throws IOException, ParseException {
        WellKnownText wkt = new WellKnownText(true, true);
        assertEquals("multilinestring ((3.0 1.0, 4.0 2.0))", wkt.toWKT(
            new MultiLine(Collections.singletonList(new Line(new double[]{1, 2}, new double[]{3, 4})))));
        assertEquals(new MultiLine(Collections.singletonList(new Line(new double[]{1, 2}, new double[]{3, 4}))),
            wkt.fromWKT("multilinestring ((3 1, 4 2))"));

        assertEquals("multilinestring EMPTY", wkt.toWKT(MultiLine.EMPTY));
        assertEquals(MultiLine.EMPTY, wkt.fromWKT("multilinestring EMPTY)"));
    }
}
