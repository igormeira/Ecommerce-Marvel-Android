package com.igormeira.comics.asynctask;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecuteListener<T> executeListener;
    private final FinishedListener<T> finishedListener;

    public BaseAsyncTask(ExecuteListener<T> executeListener,
                         FinishedListener<T> finishedListener) {
        this.executeListener = executeListener;
        this.finishedListener = finishedListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return executeListener.onExecute();
    }

    @Override
    protected void onPostExecute(T resultado) {
        super.onPostExecute(resultado);
        finishedListener.onFinished(resultado);
    }

    public interface ExecuteListener<T> {
        T onExecute();
    }

    public interface FinishedListener<T> {
        void onFinished(T resultado);
    }

}
