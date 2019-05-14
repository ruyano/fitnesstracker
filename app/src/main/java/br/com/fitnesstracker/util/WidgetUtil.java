package br.com.fitnesstracker.util;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.view.widget.LastRegisterWidget;

public class WidgetUtil {

    private static final String WIDGET_DATA = "WIDGET_DATA";
    private static final String FISICAL_AVALIATION = WIDGET_DATA + "_FISICAL_AVALIATION";

    public static void setFisicalAvaliation(Context context, FisicalAvaliation fisicalAvaliation) {
        SharedPreferences.Editor editor = context.getSharedPreferences(WIDGET_DATA, Context.MODE_PRIVATE).edit();
        editor.remove(FISICAL_AVALIATION);
        editor.putString(FISICAL_AVALIATION, new Gson().toJson(fisicalAvaliation));
        editor.apply();
        updateWidget(context);
    }

    public static FisicalAvaliation getFisicalAvaliation(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(WIDGET_DATA, Context.MODE_PRIVATE);
        String avaliationString = preferences.getString(FISICAL_AVALIATION, null);
        return avaliationString == null ? null : new Gson().fromJson(avaliationString, FisicalAvaliation.class);
    }

    private static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(context, LastRegisterWidget.class));
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }
}
