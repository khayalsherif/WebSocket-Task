package az.khayalsharifli.algoritmatask.ui.content.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import az.khayalsharifli.algoritmatask.R
import az.khayalsharifli.algoritmatask.databinding.FragmentHomeBinding
import az.khayalsharifli.algoritmatask.model.seald.WebSocket
import az.khayalsharifli.algoritmatask.ui.adapter.HomeAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private val homeAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        integrationRcView()
        observeData()

        binding.buttonConnect.setOnClickListener {
            viewModel.connectWebSocket()
        }

        binding.buttonDisconnect.setOnClickListener {
            viewModel.disConnectWebSocket()
        }

    }

    private fun integrationRcView() {
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.adapter = homeAdapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.socketResponse.collect { result ->
                when (result) {
                    is WebSocket.Connect -> {
                        showToast("Connect!")
                        binding.textState.text = getString(R.string.state_connect)
                    }
                    is WebSocket.Disconnect -> {
                        binding.textState.text = getString(R.string.state_disconnect)
                        showToast("Disconnect!")
                    }
                    is WebSocket.HasNotInternet -> {
                        println(result.message)
                    }
                    is WebSocket.NewMessage -> {
                        homeAdapter.setData(result.data!!)
                    }
                    is WebSocket.ConnectError -> {
                        println(result.message)
                    }

                }
            }
        }
    }

    private fun showToast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}