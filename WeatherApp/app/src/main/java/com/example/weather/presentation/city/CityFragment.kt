package com.example.weather.presentation.city

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.FragmentCityBinding
import com.example.weather.presentation.model.City

class CityFragment : Fragment(), CityItemListener {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.citiesRecyclerView.layoutManager = LinearLayoutManager(context)


        viewModel.getAllSavedCities()

        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            val adapter = CityAdapter(it, this)
            binding.citiesRecyclerView.adapter = adapter
        }

        binding.addCityButton.setOnClickListener {
            showAddCityDialog()
        }
    }

    private fun showAddCityDialog() {
        val builder = AlertDialog.Builder(context)
        val input = EditText(context)
        with(input) {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.createcity_cityhint)
        }
        builder.setTitle(R.string.createcity_title)
            .setView(input)
            .setPositiveButton(
                getString(R.string.createcity_positive)
            ) { _, _ ->
                val cityName = input.text.toString().trim()
                if (cityName.isNotEmpty()) {
                    viewModel.saveCity(City(name = cityName))
                    viewModel.getAllSavedCities()
                } else {
                    Toast.makeText(
                        requireContext(), "Provide correct city name!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton(
                getString(R.string.createcity_negative)
            ) { dialog, _ ->
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun showDeleteCityDialog(city: City) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(getString(R.string.deletecity_title, city.name))
            .setPositiveButton(getString(R.string.deletecity_positive))
            { _, _ ->
                viewModel.deleteCity(city)
                viewModel.getAllSavedCities()
            }
            .setNegativeButton(getString(R.string.deletecity_negative))
            { dialog, _ ->
                dialog.cancel()
            }

        builder.create().show()
    }

    override fun onCitySelected(city: City) {
        findNavController().navigate(CityFragmentDirections.actionCityFragmentToWeatherFragment(city))
    }

    override fun onCityDeleted(city: City) {
        showDeleteCityDialog(city)
    }

}