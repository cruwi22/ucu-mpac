package com.ucu.ucu_mpac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.SimpleAdapter;

public class BooksFragment extends Fragment implements SearchView.OnQueryTextListener {

	public BooksFragment() {
	}

	DatabaseHandler db;
	ListView lstview;
	SimpleAdapter adapter;
	List<HashMap<String, String>> booklist;
	JSONAsyncTask load;
	SearchView searchView;

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
		lstview.setTextFilterEnabled(true);

		return rootView;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.searchview, menu);

		SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
		searchView.setQueryHint("Search by Title/Author");
		searchView.setOnQueryTextListener(this);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		
		if(TextUtils.isEmpty(newText.toString())) {
			populate();
		} else {
			searchCollection(newText);
		}
		
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		
		return false;
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
		getActivity().overridePendingTransition(R.animator.anim_in,
				R.animator.anim_out);
	}

	public void populate() {

		List<Collection> list = db.getAllCollection();

		booklist = new ArrayList<HashMap<String, String>>();

		for (Collection c : list) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("id", c.getId() + "");
			map.put("accessno", c.getAccessno());
			map.put("title", c.getTitle());
			map.put("author", c.getAuthor());
			map.put("publisher", c.getPublisher());
			map.put("edition", c.getEdition());
			map.put("volume", c.getVolume());
			map.put("pages", c.getPages());
			map.put("cyear", c.getCyear());
			map.put("csection", c.getCsection());
			map.put("copies", c.getCopies());
			map.put("babarcode", c.getBabarcode());
			map.put("completecn", c.getCompletecn());
			map.put("format", c.getFormat());

			booklist.add(map);
		}

		adapter = new SimpleAdapter(getActivity(), booklist, R.layout.listview_layout, from, to);
		lstview.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		lstview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String date = null;

				if (null == date) {
					date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
							.format(new Date());
				}

				String accessno = booklist.get(position).get("accessno");
				String title = booklist.get(position).get("title");
				String author = booklist.get(position).get("author");
				String publisher = booklist.get(position).get("publisher");
				String edition = booklist.get(position).get("edition");
				String volume = booklist.get(position).get("volume");
				String pages = booklist.get(position).get("pages");
				String cyear = booklist.get(position).get("cyear");
				String csection = booklist.get(position).get("csection");
				String copies = booklist.get(position).get("copies");
				String babarcode = booklist.get(position).get("babarcode");
				String completecn = booklist.get(position).get("completecn");
				String format = booklist.get(position).get("format");

				db.addHistory(new History(date, accessno, title, author,
						publisher, edition, volume, pages, cyear, csection,
						copies, babarcode, completecn, format));

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
	}
	
	public void searchCollection(String query) {
		
		List<Collection> list = db.getCollection(query.toString());
		
		booklist = new ArrayList<HashMap<String, String>>();
		
		for (Collection c : list) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("id", c.getId() + "");
			map.put("accessno", c.getAccessno());
			map.put("title", c.getTitle());
			map.put("author", c.getAuthor());
			map.put("publisher", c.getPublisher());
			map.put("edition", c.getEdition());
			map.put("volume", c.getVolume());
			map.put("pages", c.getPages());
			map.put("cyear", c.getCyear());
			map.put("csection", c.getCsection());
			map.put("copies", c.getCopies());
			map.put("babarcode", c.getBabarcode());
			map.put("completecn", c.getCompletecn());
			map.put("format", c.getFormat());

			booklist.add(map);
		}
		
		adapter = new SimpleAdapter(getActivity(), booklist, R.layout.listview_layout, from, to);
		lstview.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
