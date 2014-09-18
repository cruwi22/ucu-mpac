package com.ucu.ucu_mpac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FavoritesFragment extends Fragment {

	public FavoritesFragment() { }

	DatabaseHandler db;
	ListView lstview;
	TextView txtview;

	String[] from = new String[] { "accessno", "title", "author", "publisher",
			"edition", "volume", "pages", "cyear", "csection", "copies",
			"babarcode", "completecn", "format" };
	int[] to = new int[] { R.id.accessno, R.id.title, R.id.author,
			R.id.publisher, R.id.edition, R.id.volume, R.id.pages, R.id.cyear,
			R.id.csection, R.id.copies, R.id.babarcode, R.id.completecn,
			R.id.format };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
		
		db = new DatabaseHandler(getActivity());

		lstview = (ListView) rootView.findViewById(R.id.lstView);
		txtview = (TextView) rootView.findViewById(R.id.txtView);
		
		List<Favorites> list = db.getAllFavorites();
		
		if (list.size() == 0) {
			txtview.setVisibility(View.VISIBLE);
		} else {
			lstview.setVisibility(View.VISIBLE);
		}

		populateList();

		return rootView;
	}

	public void populateList() {

		List<Favorites> list = db.getAllFavorites();
		List<HashMap<String, String>> listahan = new ArrayList<HashMap<String, String>>();

		for (Favorites f : list) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("accessno", f.getAccessno());
			map.put("title", f.getTitle());
			map.put("author", f.getAuthor());
			map.put("publisher", f.getPublisher());
			map.put("edition", f.getEdition());
			map.put("volume", f.getVolume());
			map.put("pages", f.getPages());
			map.put("cyear", f.getCyear());
			map.put("csection", f.getCsection());
			map.put("copies", f.getCopies());
			map.put("babarcode", f.getBabarcode());
			map.put("completecn", f.getCompletecn());
			map.put("format", f.getFormat());

			listahan.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(getActivity(), listahan, R.layout.favorites_layout, from, to);
		lstview.setAdapter(adapter);

	}
}
