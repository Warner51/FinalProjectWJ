package com.example.finalprojectwj;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchBBC extends AsyncTask<Void, Void, ArrayList<String>> {

    private ArrayList<BBCItem> newsList = new ArrayList<>();
    private ListView myListView;

    public FetchBBC(ListView listView) {
        this.myListView = listView;
    }
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        ArrayList<String> newsList = new ArrayList<>();

        try {
            String apiUrl = "https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml";

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            InputStream inputStream = connection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(inputStream, "UTF-8");

            int eventType = xpp.getEventType();
            String title = "";
            String description = "";
            String link = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if ("title".equals(xpp.getName())) {
                        title = xpp.nextText();
                    } else if ("description".equals(xpp.getName())) {
                        link = xpp.nextText();
                    } else if ("link".equals(xpp.getName())) {
                        link = xpp.nextText();
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if ("item".equals(xpp.getName())) {
                        // Adding news item to the list
                        BBCItem newsItem = new BBCItem(title, link, description);
                        newsList.add(String.valueOf(newsItem));

                        title = "";
                        description = "";
                        link = "";
                    }
                }
                eventType = xpp.next();
            }

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    protected void onPostExecute(ArrayList<BBCItem> newsList) {
        if (myListView != null && newsList != null && !newsList.isEmpty()) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(myListView.getContext(), android.R.layout.simple_list_item_1);

            for (BBCItem newsItem : newsList) {
                // Format each item and add it to the ArrayAdapter
                String formattedItem = "Title: " + newsItem.getTitle() + "\n"
                        + "Link: " + newsItem.getLink() + "\n"
                        + "Description: " + newsItem.getDescription() + "\n\n";

                arrayAdapter.add(formattedItem);
            }

            myListView.setAdapter(arrayAdapter);
        }
    }
}
