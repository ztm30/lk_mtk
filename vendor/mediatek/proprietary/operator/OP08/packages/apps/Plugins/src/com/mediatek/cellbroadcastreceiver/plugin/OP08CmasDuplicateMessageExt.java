/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 *
 * MediaTek Inc. (C) 2012. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */

package com.mediatek.cellbroadcastreceiver.plugin;

import android.content.Context;
import android.database.Cursor;
import android.telephony.CellBroadcastMessage;
import com.mediatek.cellbroadcastreceiver.CellBroadcastContentProvider;
import com.mediatek.cellbroadcastreceiver.Comparer;
import com.mediatek.cmas.ext.DefaultCmasDuplicateMessageExt;
import com.mediatek.cmas.ext.ICmasDuplicateMessageExt;
import com.mediatek.common.PluginImpl;
import com.mediatek.xlog.Xlog;

@PluginImpl(interfaceName = "com.mediatek.cmas.ext.ICmasDuplicateMessageExt")
public class OP08CmasDuplicateMessageExt extends DefaultCmasDuplicateMessageExt {
    private static final String TAG = "CellBroadcastReceiver/Op08CmasDuplicateMessageExt";
    private boolean langChanged = false;

    public OP08CmasDuplicateMessageExt(Context context) {
        super(context);
    }

    public void setCmasLocaleChange(boolean status) {
        Xlog.i(TAG, "set locale change:" + status);
        langChanged = status;
    }

    /**
     * Handle duplicate message after power on or airplane offline off
     */
    public int handleDuplicateMessage(CellBroadcastContentProvider provider,
            CellBroadcastMessage cbm) {
        Cursor c = provider.getAllCellBroadcastCursor();
        Comparer oldList = Comparer.createFromCursor(c);
        
        if (oldList == null) {
            Xlog.d(TAG, "oldList null so NEW CMAS");
            return ICmasDuplicateMessageExt.NEW_CMAS_PROCESS;
        }

        if (oldList.isIdentifyMsgOver12H(cbm)) {
            Xlog.d(TAG, "OVER_12_HOURS_IDENTIFY_MSG");
            return ICmasDuplicateMessageExt.OVER_12_HOURS_IDENTIFY_MSG;
        }

        if (oldList.isReadForDuplicateMessage(cbm)) {

            if (langChanged) {
                Xlog.d(TAG, "[isReadForDuplicateMessage]:language Changed so PRESENT CMAS");
                langChanged = false;
                return ICmasDuplicateMessageExt.PRESENT_CMAS_PROCESS;
            }
            /**
             * if the message is a duplicate message and the message read
             * before. So, for T-mobile, this message should Discard
             */
            Xlog.d(TAG, "[isReadForDuplicateMessage]:Discard CMAS");
            return ICmasDuplicateMessageExt.DISCARD_CMAS_PROCESS;
        }

        /**
         * if the message is not duplicate or is a duplicate message but not
         * read before, the message should present, and the message which not
         * duplicate must add to the list
         */

        if (oldList.add(cbm)) {
            Xlog.d(TAG, "the message is not duplicate so NEW CMAS");
            return ICmasDuplicateMessageExt.NEW_CMAS_PROCESS;
        }

        Xlog.d(TAG, "the message is duplicate but not read before so PRESENT CMAS");
        return ICmasDuplicateMessageExt.PRESENT_CMAS_PROCESS;
    }
    
    /**
     * Send Delete Message with Delay: 12Hour Identify Message
     */
    public boolean sendDelayedMsgToDelete() {
        return true;
    }
}
