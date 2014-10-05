package com.ucu.ucu_mpac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesFragment extends Fragment {

	public FavoritesFragment() { }

	DatabaseHandler db;
	SimpleAdapter adapter;
	ListView lstview;
	TextView txtview;
	int position_id;

	String[] from = new String[] { "id", "accessno", "title", "author", "publisher",
			"edition", "volume", "pages", "cyear", "csection", "copies",
			"babarcode", "completecn", "format" };
	int[] to = new int[] { R.id.id, R.id.accessno, R.id.title, R.id.author,
			R.id.publisher, R.id.edition, R.id.volume, R.id.pages, R.id.cyear,
			R.id.csection, R.id.copies, R.id.babarcode, R.id.completecn,
			R.id.format };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
		
		db = new DatabaseHandler(getActivity());

		lstview = (ListView) rootView.findViewById(R.id.lstView);
		txtview = (TextView) rootView.findViewById(R.id.txtView);
		
		lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

				TextView txtId = (TextView) view.findViewById(R.id.id);
				position_id = Integer.parseInt(txtId.getText().toString());
				
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
				alertDialog.setTitle("Confirm Delete");
				alertDialog.setMessage("Are you sure you want delete this?");
				alertDialog.setIcon(R.drawable.ic_error);
				
				alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				
				alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int which) {
						
						db.deleteFavorites(new Favorites(position_id));
						populateList();
		                Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
		                db.close();
					}
				});
				
				alertDialog.show();
		        alertDialog.setCancelable(false);
				
				return false;
			}
			
		});

		return rootView;
	}

	public void populateList() {

		List<Favorites> list = db.getAllFavorites();
		
		if (list.size() == 0) {
			txtview.setVisibility(View.VISIBLE);
		} else {
			lstview.setVisibility(View.VISIBLE);
		}
		
		List<HashMap<String, String>> listahan = new ArrayList<HashMap<String, String>>();

		for (Favorites f : list) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("id", f.getId() + "");
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

		adapter = new SimpleAdapter(getActivity(), listahan, R.layout.listview_layout, from, to);
		lstview.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}
	
	@Override
    public void onStart(){
    	super.onStart();
    	populateList();
    }
}
