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

import java.util.Arrays;
import java.util.LinkedList;
import org.apache.syncope.client.cli.messages.Messages;
import org.apache.syncope.client.cli.messages.Table;
import org.apache.syncope.client.cli.util.CommandUtils;
import org.apache.syncope.common.lib.to.LoggerTO;
import org.apache.syncope.common.lib.types.LoggerLevel;

public class ResultManager {

    public void notFoundError(final String parameter) {
        Messages.printNofFoundMessage("Logger", parameter);
    }

    public void typeNotValidError(final String parameter) {
        Messages.printTypeNotValidMessage("logger level", parameter, CommandUtils.fromEnumToArray(LoggerLevel.class));
    }

    public void commandOptionError(final String message) {
        Messages.printCommandOptionMessage(message);
    }

    public void genericError(final String... messages) {
        Messages.printMessage(messages);
    }

    public void fromList(final LinkedList<LoggerTO> loggerTOs) {
        fromCommandToView("list loggers", "level", loggerTOs);
    }

    public void fromRead(final LinkedList<LoggerTO> loggerTOs) {
        fromCommandToView("read loggers", "level", loggerTOs);
    }

    public void fromCreate(final LinkedList<LoggerTO> loggerTOs) {
        fromCommandToView("created loggers", "level", loggerTOs);
    }

    public void fromUpdate(final LinkedList<LoggerTO> loggerTOs) {
        fromCommandToView("updated loggers", "new level", loggerTOs);
    }

    public void fromDelete(final LinkedList<LoggerTO> loggerTOs) {
        fromCommandToView("deleted loggers", "new level", loggerTOs);
    }

    private void fromCommandToView(final String title,
            final String secondHeader,
            final LinkedList<LoggerTO> loggerTOs) {
        final Table.TableBuilder tableBuilder
                = new Table.TableBuilder(title).header("logger").header(secondHeader);
        for (final LoggerTO loggerTO : loggerTOs) {
            tableBuilder.rowValues(
                    new LinkedList(Arrays.asList(loggerTO.getKey(), loggerTO.getLevel().getLevel().name())));
        }
        tableBuilder.build().print();
    }

}
