package br.com.fitnesstracker.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import java.net.URI;
import java.net.URL;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.util.WidgetUtil;

/**
 * Implementation of App Widget functionality.
 */
public class LastRegisterWidget extends AppWidgetProvider {

    public static Integer randomNumber = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.last_register_widget);

        FisicalAvaliation fisicalAvaliation = WidgetUtil.getFisicalAvaliation(context);
        if (fisicalAvaliation == null) {
            views.setViewVisibility(R.id.empty_tv, View.VISIBLE);
            views.setViewVisibility(R.id.widget_list_view, View.GONE);
        } else {
            views.setViewVisibility(R.id.empty_tv, View.GONE);
            views.setViewVisibility(R.id.widget_list_view, View.VISIBLE);

            randomNumber = (int)(Math.random()*1000);

            Intent intent = new Intent(context, WidgetListRemoteViewsService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId + randomNumber);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter(R.id.widget_list_view, intent);

        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

