package br.com.fitnesstracker.view.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.models.SingleFisicalAvaliation;
import br.com.fitnesstracker.util.WidgetUtil;

public class WidgetListRemoteFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private int appWidgetId;

    public WidgetListRemoteFactory(Context context, Intent intent) {
        mContext = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID) - LastRegisterWidget.randomNumber;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        FisicalAvaliation fisicalAvaliation = WidgetUtil.getFisicalAvaliation(mContext);
        if (fisicalAvaliation == null)
            return 0;
        else
            return fisicalAvaliation.getSingleAvaliationArray(mContext).size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        FisicalAvaliation fisicalAvaliation = WidgetUtil.getFisicalAvaliation(mContext);
        if (fisicalAvaliation != null) {
            ArrayList<SingleFisicalAvaliation> avaliations = fisicalAvaliation.getSingleAvaliationArray(mContext);
            RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.widget_layout);
            row.setTextViewText(R.id.key, avaliations.get(position).getName());
            row.setTextViewText(R.id.value, avaliations.get(position).getValue());
            return(row);
        }
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
