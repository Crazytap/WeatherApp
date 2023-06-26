package com.example.weather.presentation.weather

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.data.mapOpenWeatherDataToWeather
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.presentation.model.Weather

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModels()
    private val args: WeatherFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun updateUi(weather: Weather) {
        Glide.with(requireContext()).load(weather.iconUrl).placeholder(R.drawable.off_cloud_24px)
            .into(binding.weatherIcon)

        binding.weatherDescription.text = weather.description
        binding.temperature.text =
            getString(R.string.weather_temperature_value, weather.temperature.toInt())
        binding.humidity.text = getString(R.string.weather_humidity_value, weather.humidity)
        binding.pressure.text = getString(R.string.weather_pressure_value, weather.pressure)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.city.text = args.city.name

        viewModel.getWeather(args.city)

        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val weather = mapOpenWeatherDataToWeather(it)
                updateUi(weather)
            } else {
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}