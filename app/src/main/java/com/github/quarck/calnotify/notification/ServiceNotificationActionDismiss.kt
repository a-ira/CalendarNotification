//
//   Calendar Notifications Plus  
//   Copyright (C) 2016 Sergey Parshin (s.parshin.sc@gmail.com)
//
//   This program is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation; either version 3 of the License, or
//   (at your option) any later version.
// 
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//   along with this program; if not, write to the Free Software Foundation,
//   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
//

package com.github.quarck.calnotify.notification

import android.app.IntentService
import android.content.Intent
import com.github.quarck.calnotify.Consts
import com.github.quarck.calnotify.EventsManager
import com.github.quarck.calnotify.logs.DebugTransactionLog
import com.github.quarck.calnotify.logs.Logger

class ServiceNotificationActionDismiss : IntentService("ServiceNotificationActionDismiss") {
    override fun onHandleIntent(intent: Intent?) {
        logger.debug("onHandleIntent")

        if (intent != null) {
            var notificationId = intent.getIntExtra(Consts.INTENT_NOTIFICATION_ID_KEY, -1)
            var eventId = intent.getLongExtra(Consts.INTENT_EVENT_ID_KEY, -1)

            if (notificationId != -1 && eventId != -1L) {
                EventsManager.dismissEvent(this, eventId, notificationId)
                DebugTransactionLog(this).log("ServiceNotificationActionDismiss", "remove", "Event dismissed by user: $eventId")
            } else {
                logger.error("notificationId=${notificationId}, eventId=${eventId}, or type is null")
            }
        } else {
            logger.error("Intent is null!")
        }
    }

    companion object {
        private val logger = Logger("DiscardNotificationService")
    }
}
