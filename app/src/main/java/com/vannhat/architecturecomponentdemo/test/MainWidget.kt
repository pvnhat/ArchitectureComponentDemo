package com.vannhat.architecturecomponentdemo.test

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.vannhat.architecturecomponentdemo.Navigator.createLog
import com.vannhat.architecturecomponentdemo.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class MainWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
            createLog("widget update  ")
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == UPDATE_TIME_ACTION) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisAppWidgetComponentName = ComponentName(context?.packageName ?: "",
                this::class.java.name)
            val appWidgetIds = appWidgetManager.getAppWidgetIds((thisAppWidgetComponentName))
            createLog("widget receive  ")
            context?.let { onUpdate(it, appWidgetManager, appWidgetIds) }
        }

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        createLog("widget enable")
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        createLog("widget disable ")
    }

    companion object {

        @SuppressLint("SimpleDateFormat")

        private const val UPDATE_TIME_ACTION = "UPDATE_TIME_ACTION"


        @SuppressLint("SimpleDateFormat")
        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int) {

            val widgetText = SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().time)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.main_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // handle click
            views.setOnClickPendingIntent(R.id.rl_widget, getConfigPendingIntent(context))
            views.setOnClickPendingIntent(R.id.btn_update, getUpdatePendingIntent(context))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getConfigPendingIntent(context: Context): PendingIntent {
            return PendingIntent.getActivity(context, 0,
                TestIntent.newInstance(context), 0)
        }

        private fun getUpdatePendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, MainWidget::class.java)
            intent.action = UPDATE_TIME_ACTION
            return PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }
}

