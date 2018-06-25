package com.example.example.demolevel5;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetQuoteOfTheDayTask extends AsyncTask<Void, Void, DayQuoteItem> {
    private static final String BASE_URL = "http://numbersapi.com/";
    private WeakReference<MainActivity> mMainActivityWeakReference;

    public GetQuoteOfTheDayTask(MainActivity mainActivity) {
        // Put the reference to the main reference in a weakReference, otherwise possible memory leaks
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    protected void onPreExecute() {
        // Get the mainActivity from the weakReference
        MainActivity mainActivity = mMainActivityWeakReference.get();
        // Set progressbar to visible
        if (mainActivity.getCurrentFocus() != null) {
            Snackbar.make(mainActivity.getCurrentFocus(), "Loading quote of the day",
                    Snackbar.LENGTH_LONG)
                    .show(); // Inform the user the task is starting
        }
    }

    @Override
    protected DayQuoteItem doInBackground(Void... voids) {
        // Build retrofit instance, with giving the base url and converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Get the interface where the http requests are in.
        NumbersApiService service = retrofit.create(NumbersApiService.class);

        DayQuoteItem dayQuoteItem = null;
        try {
            dayQuoteItem = getTodaysQuote(service);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dayQuoteItem;
    }

    private DayQuoteItem getTodaysQuote(NumbersApiService numbersApiService) throws IOException {
        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // We call execute on the getRandomTriviaItem, this means this function is called synchronous.
        // Also the body is taken in the body is the trivial item we just got from the numbers api.
        return numbersApiService.getTodaysQuote(month, dayOfMonth).execute().body();
    }

    @Override
    protected void onPostExecute(DayQuoteItem dayQuoteItem) {
        // Called when doInBackground finishes
        MainActivity mainActivity = mMainActivityWeakReference.get();
        if (dayQuoteItem == null) {
            Toast.makeText(mainActivity.getApplicationContext(), "Downloading the quote failed", Toast.LENGTH_SHORT).show();
            mainActivity.setQuoteTextView("No quote is found, try to reopen the application");
            //Further execution of the method is not needed
            return;
        }
        mainActivity.setQuoteTextView(dayQuoteItem.getText());
    }
}
