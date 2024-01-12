package com.example.mytestapp.presentation.feature.socket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapp.databinding.FragmentSocketBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterGoal
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment

import io.socket.client.Socket
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SocketFragment : BaseFragment(){
    private val binding by lazy { FragmentSocketBinding.inflate(layoutInflater) }
    private val vm: SocketViewModel by viewModel()

    private val adapter: AdapterGoal by lazy {
        AdapterGoal(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callSocket()
    }

    private fun callSocket(){
        with(vm.socketManager) {
            connect()
            subscribe()
            socket.on(Socket.EVENT_CONNECT) { args ->
                activity?.runOnUiThread {
                    Log.d("SOCKET_NOTIFICATION", "EVENT_CONNECT: $args ")
                }
            }

            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                activity?.runOnUiThread {
                    Log.e("SOCKET_NOTIFICATION", "EVENT_CONNECT_ERROR: ${args[0]}")
                }
            }


        }
    }

    private fun updateBadgeCounter(it: JSONObject) {
        Log.d("SOCKET_NOTIFICATION", "updateBadgeCounter: $it ")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.socketManager.clearSession()
    }

}