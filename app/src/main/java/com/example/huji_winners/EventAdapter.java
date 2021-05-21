package com.example.huji_winners;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

	public class ViewHolder extends RecyclerView.ViewHolder {
		// Your holder should contain a member variable
		// for any view that will be set as you render a row
		public Button nameTextView;
//		public Button messageButton;

		// We also create a constructor that accepts the entire item row
		// and does the view lookups to find each subview
		public ViewHolder(View itemView) {
			// Stores the itemView in a public final member variable that can be used
			// to access the context from any ViewHolder instance.
			super(itemView);

			nameTextView = (Button) itemView.findViewById(R.id.event_name);
//			messageButton = (Button) itemView.findViewById(R.id.message_button);
		}
	}
	// Store a member variable for the contacts
	private final List<Event> mEvents;
	private final Context cont;

	// Pass in the contact array into the constructor
	public EventAdapter(Context context, List<Event> contacts) {
		mEvents = contacts;
		cont = context;
	}

	// Usually involves inflating a layout from XML and returning the holder
	@NotNull
	@Override
	public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		// Inflate the custom layout
		View contactView = inflater.inflate(R.layout.event_contant, parent, false);

		// Return a new holder instance
		ViewHolder viewHolder = new ViewHolder(contactView);
		return viewHolder;
	}

	// Involves populating data into the item through holder
	@Override
	public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
		// Get the data model based on position
		Event event = mEvents.get(position);

		// Set item views based on your views and data model
		Button button = holder.nameTextView;
		button.setText(event.getName() + " | " + event.getDate() + " | " + event.getTime());
		button.setOnClickListener(v -> {
			Intent i = new Intent(this.cont, EventPage.class);
			i.putExtra("event", event);
			this.cont.startActivity(i); });
	}


	// Returns the total count of items in the list
	@Override
	public int getItemCount() {
		return mEvents.size();
	}

}
