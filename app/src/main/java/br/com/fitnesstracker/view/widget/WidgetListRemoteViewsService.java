package br.com.fitnesstracker.view.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetListRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListRemoteFactory(getApplicationContext(), intent);
    }

}
