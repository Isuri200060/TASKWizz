package com.coding.meet.todo_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coding.meet.todo_app.databinding.ViewTaskGridLayoutBinding
import com.coding.meet.todo_app.databinding.ViewTaskListLayoutBinding
import com.coding.meet.todo_app.models.Task
import java.text.SimpleDateFormat
import java.util.Locale

// Adapter to handle different views (list/grid) for displaying tasks
class TaskRVVBListAdapter(
    private val isList: MutableLiveData<Boolean>,
    private val deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
) : ListAdapter<Task, RecyclerView.ViewHolder>(DiffCallback()) {

    // ViewHolder for displaying tasks in list layout
    class ListTaskViewHolder(private val binding: ViewTaskListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
        ) {
            // Setting task details (title and description)
            binding.titleTxt.text = task.title
            binding.descrTxt.text = task.description

            // Formatting the date and time to be displayed
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            binding.dateTxt.text = dateFormat.format(task.date)

            // Handling delete action when clicked
            binding.deleteImg.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("delete", adapterPosition, task)
                }
            }

            // Handling edit action when clicked
            binding.editImg.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("update", adapterPosition, task)
                }
            }
        }
    }

    // ViewHolder for displaying tasks in grid layout
    class GridTaskViewHolder(private val binding: ViewTaskGridLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            task: Task,
            deleteUpdateCallback: (type: String, position: Int, task: Task) -> Unit,
        ) {
            // Setting task details (title and description)
            binding.titleTxt.text = task.title
            binding.descrTxt.text = task.description

            // Formatting the date and time to be displayed
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a", Locale.getDefault())
            binding.dateTxt.text = dateFormat.format(task.date)

            // Handling delete action when clicked
            binding.deleteImg.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("delete", adapterPosition, task)
                }
            }

            // Handling edit action when clicked
            binding.editImg.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    deleteUpdateCallback("update", adapterPosition, task)
                }
            }
        }
    }

    // Inflating the appropriate layout depending on viewType (list or grid)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 1) {  // Grid view type
            GridTaskViewHolder(
                ViewTaskGridLayoutBinding.inflate(layoutInflater, parent, false)
            )
        } else {  // List view type
            ListTaskViewHolder(
                ViewTaskListLayoutBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    // Binding task data to the appropriate ViewHolder based on layout type (list/grid)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        if (isList.value == true) {
            (holder as ListTaskViewHolder).bind(task, deleteUpdateCallback)
        } else {
            (holder as GridTaskViewHolder).bind(task, deleteUpdateCallback)
        }
    }

    // Determining the view type based on the boolean flag (isList)
    override fun getItemViewType(position: Int): Int {
        return if (isList.value == true) 0 else 1  // 0 = List, 1 = Grid
    }

    // Callback class for efficient comparison between old and new Task objects
    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
