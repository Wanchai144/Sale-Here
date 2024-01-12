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
        with(vm.socketManager){
            connect()
            subscribe()

            testUpdateBadges()
            socket.on(Socket.EVENT_CONNECT_ERROR) {
                val error = it[0] as Exception
                Log.e("SOCKET_NOTIFICATION", "Connection Error: ${error.message}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
//        vm.socketManager.clearSession()
    }

}