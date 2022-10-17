package com.technipixl.eval4.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.eval4.MainActivity
import com.technipixl.eval4.databinding.FragmentExpenseListBinding
import com.technipixl.eval4.models.ExpenseViewModel
import com.technipixl.eval4.views.adapters.ExpenseListAdapter

class ExpenseListFragment : Fragment() {

	private var _binding: FragmentExpenseListBinding? = null
	private val binding get() = _binding!!

	private val viewModel : ExpenseViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentExpenseListBinding.inflate(inflater, container, false)

		binding.lifecycleOwner = viewLifecycleOwner
		binding.viewModel = viewModel
		binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
		binding.recyclerView.adapter = ExpenseListAdapter()

		binding.addNewButton.setOnClickListener { toNewExpenseFragment() }

		return binding.root
	}

	private fun toNewExpenseFragment() {
		NavHostFragment.findNavController(this)
			.navigate(ExpenseListFragmentDirections.actionExpenseListFragmentToNewExpenseFragment())
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}