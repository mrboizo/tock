/*
 * Copyright (C) 2019 VSCT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.vsct.tock.bot.connector.businesschat

import fr.vsct.tock.bot.connector.ConnectorMessage
import fr.vsct.tock.bot.connector.businesschat.model.input.BusinessChatConnectorImageMessage
import fr.vsct.tock.bot.connector.businesschat.model.input.BusinessChatConnectorMessage
import fr.vsct.tock.bot.connector.businesschat.model.input.BusinessChatConnectorTextMessage
import fr.vsct.tock.bot.engine.BotBus

/**
 * Adds a Business Chat [ConnectorMessage] if the current connector is Business Chat.
 * You need to call [BotBus.send] or [BotBus.end] later to send this message.
 */
fun BotBus.withBusinessChat(messageProvider: () -> BusinessChatConnectorMessage): BotBus {
    return withMessage(businessChatConnectorType, messageProvider)
}

/**
 * Creates a [BusinessChatText].
 *
 * @param text the text sent
 *
 */
fun BotBus.businessChatText(
    text: String
): BusinessChatConnectorMessage =
        BusinessChatConnectorTextMessage(
                sourceId = botId.id,
                destinationId = userId.id,
                body = translate(text).toString()
        )

/**
 * Creates a [BusinessChatConnectorImageMessage]
 *
 * @param attachment an array of bytes containing an image
 * @param mimeType the mime type of the image, which is image/png by default
 */
fun BotBus.businessChatAttachement(
        attachment: ByteArray,
        mimeType: String = "image/png"
): BusinessChatConnectorMessage =
        BusinessChatConnectorImageMessage(
                sourceId = botId.id,
                destinationId = userId.id,
                bytes = attachment,
                mimeType = mimeType
        )