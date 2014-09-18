package com.ucu.ucu_mpac;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CollectionAdapter extends ArrayAdapter<Collection> {
	
	ArrayList<Collection> booklist;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	
	public CollectionAdapter(Context context, int resource, ArrayList<Collection> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		booklist = objects;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.accessno = (TextView) v.findViewById(R.id.accessno);
			holder.title = (TextView) v.findViewById(R.id.title);
			holder.author = (TextView) v.findViewById(R.id.author);
			holder.publisher = (TextView) v.findViewById(R.id.publisher);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		holder.accessno.setText(booklist.get(position).getAccessno());
		holder.title.setText(booklist.get(position).getTitle());
		holder.author.setText(booklist.get(position).getAuthor());
		holder.publisher.setText(booklist.get(position).getPublisher());
		
		return v;

	}
	
	static class ViewHolder {
		public TextView accessno;
		public TextView title;
		public TextView author;
		public TextView publisher;

	}

}
