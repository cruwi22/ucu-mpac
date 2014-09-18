package com.ucu.ucu_mpac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;

public class BooksFragment extends Fragment {

	public BooksFragment() {}
	
	ListView lstview;
	ArrayList<Collection> booklist;
	CollectionAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_books, container, false);
		setHasOptionsMenu(true);
		
		booklist = new ArrayList<Collection>();
		new JSONAsyncTask().execute("http://ucu-library.comxa.com/collections.json");
		
		lstview = (ListView) rootView.findViewById(R.id.lstView);
		adapter = new CollectionAdapter(getActivity(), R.layout.listview_layout, booklist);		
		lstview.setFastScrollEnabled(true);
		lstview.setAdapter(adapter);
		
		lstview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				String accessno = booklist.get(position).getAccessno();
				String title = booklist.get(position).getTitle();
				String author = booklist.get(position).getAuthor();
				String publisher = booklist.get(position).getPublisher();
				String edition = booklist.get(position).getEdition();
				String volume = booklist.get(position).getVolume();
				String pages = booklist.get(position).getPages();
				String cyear = booklist.get(position).getCyear();
				String csection = booklist.get(position).getCsection();
				String copies = booklist.get(position).getCopies();
				String babarcode = booklist.get(position).getBabarcode();
				String completecn = booklist.get(position).getCompletecn();
				String format = booklist.get(position).getFormat();
				
				Intent intent = new Intent(getActivity(), BookSearch.class);
				intent.putExtra("accessno", accessno);
				intent.putExtra("title", title);
				intent.putExtra("author", author);
				intent.putExtra("publisher", publisher);
				intent.putExtra("edition", edition);
				intent.putExtra("volume", volume);
				intent.putExtra("pages", pages);
				intent.putExtra("cyear", cyear);
				intent.putExtra("csection", csection);
				intent.putExtra("copies", copies);
				intent.putExtra("babarcode", babarcode);
				intent.putExtra("completecn", completecn);
				intent.putExtra("format", format);
				startActivity(intent);
				getActivity().overridePendingTransition(R.animator.anim_leftout, R.animator.anim_rightin);
			}
			
		});
	
		return rootView;

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
	
	class JSONAsyncTask extends AsyncTask<String, Void, String> {
		
		ProgressDialog dialog;
		
		@Override
		protected String doInBackground(String... urls) {
			
			try {
				
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);
				
				HttpEntity entity = response.getEntity();
				String data = EntityUtils.toString(entity, "UTF-8");
					
				JSONObject collectionObject = new JSONObject(data);
				JSONArray bookarray = collectionObject.getJSONArray("book");
					
				for (int i = 0; i < bookarray.length(); i++) {
					JSONObject object = bookarray.getJSONObject(i);
					
					Collection book = new Collection();
					
					book.setAccessno(object.getString("_accessno"));
					book.setTitle(object.getString("title"));
					book.setAuthor(object.getString("author"));
					book.setPublisher(object.getString("publisher"));
					
					book.setEdition(object.getString("edition"));
					book.setVolume(object.getString("volume"));
					book.setPages(object.getString("pages"));
					book.setCyear(object.getString("cyear"));
					book.setCsection(object.getString("csection"));
					book.setCopies(object.getString("copies"));
					book.setBabarcode(object.getString("babarcode"));
					book.setCompletecn(object.getString("completecn"));
					book.setFormat(object.getString("format"));
						
					booklist.add(book);
					
					Collections.sort(booklist, new Comparator<Collection>() {

						@Override
						public int compare(Collection one, Collection two) {
							return one.getTitle().compareTo(two.getTitle());
						}
						
					});
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
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
			dialog.setMessage("Loading, Please wait...");
			dialog.setTitle("Connecting to server");
			dialog.show();
			dialog.setCancelable(false);
		}
		
		protected void onPostExecute(String result) {
			adapter.notifyDataSetChanged();
			dialog.cancel();
		}
	}
	
	@Override
	public void onStart() {
        super.onStart();
	    getActivity().overridePendingTransition(R.animator.anim_in, R.animator.anim_out);     
	  }
}
