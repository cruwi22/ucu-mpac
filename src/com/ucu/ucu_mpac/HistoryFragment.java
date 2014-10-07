package com.ucu.ucu_mpac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HistoryFragment extends Fragment {

	public HistoryFragment() { }

	DatabaseHandler db;
	SimpleAdapter adapter;
	ListView lstview;
	TextView txtview;
	Button btnClear;
	SimpleDateFormat formatter = null;
	Date convertedDate = null;

	String[] from = new String[] { "id", "date", "accessno", "title", "author",
			"publisher", "edition", "volume", "pages", "cyear", "csection",
			"copies", "babarcode", "completecn", "format" };
	int[] to = new int[] { R.id.id, R.id.date, R.id.accessno, R.id.title,
			R.id.author, R.id.publisher, R.id.edition, R.id.volume, R.id.pages,
			R.id.cyear, R.id.csection, R.id.copies, R.id.babarcode,
			R.id.completecn, R.id.format };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_history, container, false);

		db = new DatabaseHandler(getActivity());

		lstview = (ListView) rootView.findViewById(R.id.lstView);
		txtview = (TextView) rootView.findViewById(R.id.txtView);
		btnClear = (Button) rootView.findViewById(R.id.btnClear);

		btnClear();
		
		return rootView;
	}
	
	public void btnClear() {
		btnClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				db.clearHistory();
				populateList();
			}
			
		});
	}

	public void populateList() {

		List<History> list = db.getAllHistory();

		if (list.size() == 0) {
			txtview.setVisibility(View.VISIBLE);
			btnClear.setVisibility(View.GONE);
		} else {
			lstview.setVisibility(View.VISIBLE);
			btnClear.setVisibility(View.VISIBLE);
		}

		List<HashMap<String, String>> listahan = new ArrayList<HashMap<String, String>>();

		for (History h : list) {
			String date = h.getDate();
			formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			try {
				convertedDate = (Date) formatter.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			HashMap<String, String> map = new HashMap<String, String>();

			map.put("id", h.getId() + "");
			map.put("date", new PrettyDate(convertedDate).toString());
			map.put("accessno", h.getAccessno());
			map.put("title", h.getTitle());
			map.put("author", h.getAuthor());
			map.put("publisher", h.getPublisher());
			map.put("edition", h.getEdition());
			map.put("volume", h.getVolume());
			map.put("pages", h.getPages());
			map.put("cyear", h.getCyear());
			map.put("csection", h.getCsection());
			map.put("copies", h.getCopies());
			map.put("babarcode", h.getBabarcode());
			map.put("completecn", h.getCompletecn());
			map.put("format", h.getFormat());

			listahan.add(map);
		}

		adapter = new SimpleAdapter(getActivity(), listahan, R.layout.listview_layout, from, to);
		lstview.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	@Override
	public void onStart() {
		super.onStart();
		populateList();
	}

}
