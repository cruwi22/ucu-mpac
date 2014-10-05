package com.ucu.ucu_mpac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

public class BooksFragment extends Fragment {

	public BooksFragment() { }
	
	DatabaseHandler db;
	ListView lstview;
	SimpleAdapter adapter;
	List<HashMap<String, String>> booklist;
	JSONAsyncTask load;
	
	String[] from = new String[] { "accessno", "title", "author", "publisher",
			"edition", "volume", "pages", "cyear", "csection", "copies",
			"babarcode", "completecn", "format" };
	int[] to = new int[] { R.id.accessno, R.id.title, R.id.author,
			R.id.publisher, R.id.edition, R.id.volume, R.id.pages, R.id.cyear,
			R.id.csection, R.id.copies, R.id.babarcode, R.id.completecn,
			R.id.format };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_books, container, false);
		setHasOptionsMenu(true);
		
		db = new DatabaseHandler(getActivity());
		
		load = new JSONAsyncTask();
		load.execute();

		lstview = (ListView) rootView.findViewById(R.id.lstView);
		lstview.setFastScrollEnabled(true);
		
		return rootView;

	}
	
	class JSONAsyncTask extends AsyncTask<String, Void, String> {

		ProgressDialog dialog;

		@Override
		protected String doInBackground(String... urls) {
			String line = "";
			
			try {

				StringBuilder finalstring = new StringBuilder();
				InputStream is = getResources().openRawResource(R.raw.collection);

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				while((line = br.readLine()) != null){
					finalstring.append(line);
				}

				JSONObject collectionObject = new JSONObject(finalstring + "");
				JSONArray bookarray = collectionObject.getJSONArray("book");
				
				booklist = new ArrayList<HashMap<String, String>>();
				
				for (int i = 0; i < bookarray.length(); i++) {
					JSONObject object = bookarray.getJSONObject(i);
					
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("accessno", object.getString("_accessno"));
					map.put("title", object.getString("title"));
					map.put("author", object.getString("author"));
					map.put("publisher", object.getString("publisher"));
					map.put("edition", object.getString("edition"));
					map.put("volume", object.getString("volume"));
					map.put("pages", object.getString("pages"));
					map.put("cyear", object.getString("cyear"));
					map.put("csection", object.getString("csection"));
					map.put("copies", object.getString("copies"));
					map.put("babarcode", object.getString("babarcode"));
					map.put("completecn", object.getString("completecn"));
					map.put("format", object.getString("format"));

					booklist.add(map);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Loading");
			dialog.setMessage("Please wait...");
			dialog.show();
			dialog.setCancelable(false);
		}
		
		protected void onPostExecute(String result) {
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.searchview, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
		searchView.setQueryHint("Search by Title/Author");

		
	}

	/**
	 * On selecting action bar icons
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_search:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		getActivity().overridePendingTransition(R.animator.anim_in, R.animator.anim_out);
	}
}
