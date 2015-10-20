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
package org.apache.syncope.client.cli.commands.logger;

import java.util.LinkedList;
import javax.xml.ws.WebServiceException;
import org.apache.syncope.client.cli.Input;
import org.apache.syncope.common.lib.SyncopeClientException;
import org.apache.syncope.common.lib.to.LoggerTO;
import org.apache.syncope.common.lib.types.LoggerLevel;
import org.apache.syncope.common.lib.types.LoggerType;

public class LoggerUpdateAll extends AbstractLoggerCommand {

    private static final String UPDATE_ALL_HELP_MESSAGE = "logger --update-all {LOG-LEVEL}";

    private final Input input;

    public LoggerUpdateAll(final Input input) {
        this.input = input;
    }

    public void updateAll() {
        if (input.parameterNumber() == 1) {
            final LinkedList<LoggerTO> loggerTOs = new LinkedList<>();
            boolean failed = false;
            for (final LoggerTO loggerTO : loggerService.list(LoggerType.LOG)) {
                try {
                    loggerTO.setLevel(LoggerLevel.valueOf(input.firstParameter()));
                    loggerService.update(LoggerType.LOG, loggerTO);
                    loggerTOs.add(loggerTO);
                } catch (final WebServiceException | SyncopeClientException | IllegalArgumentException ex) {
                    if (ex.getMessage().startsWith("No enum constant org.apache.syncope.common.lib.types.")) {
                        resultManager.typeNotValidError(input.firstParameter());
                    } else {
                        resultManager.genericError(ex.getMessage(), UPDATE_ALL_HELP_MESSAGE);
                    }
                    failed = true;
                    break;
                }
            }
            if (!failed) {
                resultManager.fromUpdate(loggerTOs);
            }
        } else {
            resultManager.commandOptionError(UPDATE_ALL_HELP_MESSAGE);
        }
    }
}
