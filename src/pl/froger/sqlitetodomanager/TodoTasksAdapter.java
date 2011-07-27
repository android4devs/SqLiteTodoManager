package pl.froger.sqlitetodomanager;

import java.util.List;

import pl.froger.sqlitetodomanager.model.TodoTask;
import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoTasksAdapter extends ArrayAdapter<TodoTask> {
	private Activity context;
	private List<TodoTask> tasks;
	public TodoTasksAdapter(Activity context, List<TodoTask> tasks) {
		super(context, R.layout.todo_list_item, tasks);
		this.context = context;
		this.tasks = tasks;
	}

	static class ViewHolder {
		public TextView tvTodoDescription;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		View rowView = convertView;
		if(rowView == null) {
			LayoutInflater layoutInflater = context.getLayoutInflater();
			rowView = layoutInflater.inflate(R.layout.todo_list_item, null, true);
			viewHolder = new ViewHolder();
			viewHolder.tvTodoDescription = (TextView) rowView.findViewById(R.id.tvTodoDescription);
			rowView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) rowView.getTag();
		}
		TodoTask task = tasks.get(position);
		viewHolder.tvTodoDescription.setText(task.getDescription());
		if(task.isCompleted()) {
			viewHolder.tvTodoDescription
				.setPaintFlags(viewHolder.tvTodoDescription.getPaintFlags() | 
						Paint.STRIKE_THRU_TEXT_FLAG);
		} else {
			viewHolder.tvTodoDescription
				.setPaintFlags(viewHolder.tvTodoDescription.getPaintFlags() & 
						~Paint.STRIKE_THRU_TEXT_FLAG);
		}
		return rowView;
	}
}