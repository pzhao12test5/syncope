/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.persistence.dao;

import java.util.Date;
import java.util.List;

import org.apache.syncope.core.persistence.beans.AbstractAttrValue;
import org.apache.syncope.core.persistence.beans.AbstractSubject;
import org.apache.syncope.core.persistence.beans.ExternalResource;
import org.apache.syncope.core.util.AttributableUtil;

public interface SubjectDAO extends DAO {

    Date findLastChange(Long id);

    <T extends AbstractSubject> List<T> findByAttrValue(String schemaName,
            AbstractAttrValue attrValue, AttributableUtil attrUtil);

    <T extends AbstractSubject> T findByAttrUniqueValue(String schemaName,
            AbstractAttrValue attrUniqueValue, AttributableUtil attrUtil);

    <T extends AbstractSubject> List<T> findByDerAttrValue(String schemaName, String value,
            AttributableUtil attrUtil);

    <T extends AbstractSubject> List<T> findByResource(ExternalResource resource, Class<T> reference);
}
