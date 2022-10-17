package com.technipixl.eval4.views

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.technipixl.eval4.databinding.FragmentNewExpenseBinding
import com.technipixl.eval4.models.Expense
import com.technipixl.eval4.models.ExpenseType
import com.technipixl.eval4.models.ExpenseViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewExpenseFragment : Fragment() {

	private var _binding: FragmentNewExpenseBinding? = null
	private val binding get() = _binding!!

	private val viewModel: ExpenseViewModel by activityViewModels()

	private var date: Date? = null
	private var type: ExpenseType? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentNewExpenseBinding.inflate(inflater, container, false)

		binding.date.setOnClickListener { openDatePicker() }
		binding.type.setOnClickListener { typeSelection() }
		binding.saveButton.setOnClickListener { saveButtonTapped() }

		return binding.root
	}

	private fun openDatePicker() {
		val calendar = Calendar.getInstance()
		var dialog: DatePickerDialog? = null

		dialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
				view, year, month, dayOfMonth -> dateListener(dayOfMonth, month, year)
			dialog?.dismiss()
		}, calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DAY_OF_MONTH))

		dialog.show()
	}

	private fun dateListener(day: Int, month: Int, year: Int) {
		var date = Date()
		SimpleDateFormat("dd-MM-yyyy").parse("$day-$month-$year")?.let {
			date = it
		}
		binding.date.setText(DateFormat.getDateInstance().format(date))
		this.date = date
	}

	private fun typeSelection() {
		viewModel.getTypes()?.let { typeList ->
			val types = typeList.map { it.name }.toTypedArray()
			AlertDialog.Builder(requireContext())
				.setTitle("Choose a type")
				.setItems(types, DialogInterface.OnClickListener { dialog, which ->
					this.type = typeList[which]
					binding.type.setText(this.type?.name)
				}).show()
		}

	}

	private fun saveButtonTapped() {
		listOf(binding.name, binding.amount, binding.date, binding.type).forEach {
			if (isNotFilled(it)) return
		}

		viewModel.newExpense(
			Expense(
			name = binding.name.text.toString(),
			value = binding.amount.text.toString().toFloat(),
			date = date!!,
			type = type!!.typeId
		))
	}

	private fun isNotFilled(view: EditText) : Boolean {
		if (view.text.isNullOrEmpty()) {
			view.error = "Required"
			return true
		}

		view.error = null
		return false
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
		date = null
	}
}