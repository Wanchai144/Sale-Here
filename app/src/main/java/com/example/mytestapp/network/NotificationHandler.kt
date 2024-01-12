package com.example.mytestapp.network


import android.util.Log
import com.example.mytestapp.utils.Const
import com.example.mytestapp.utils.Const.SOCKET_SERVER_URL
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import org.json.JSONObject
import java.util.Collections.singletonMap

class NotificationHandler {

    private val SOCKET_SERVER_URL = "wss://px-socket-emitter.saleherethailand.com"
    private val UPDATE_ENDPOINT = "https://px-socket-emitter.saleherethailand.com/update"

    private val option: IO.Options by lazy {
        IO.Options.builder()
            .setTransports(arrayOf(WebSocket.NAME))
            .build()
    }

    fun connect() {
        socket.connect()
    }

    val socket: Socket by lazy {
        IO.socket(SOCKET_SERVER_URL, option)
    }

    fun removeEvent(event: String) {
        socket.off(event)
    }

    fun isConnected(): Boolean {
        return socket.connected()
    }

    fun subscribe() {
        socket.emit("new-notification")
        socket.on("new-notification") { args ->
            if (args.isNotEmpty()) {
                val notificationData = args[0] as JSONObject
                updateBadgeCounter(notificationData)
            }
        }
    }

    private fun updateBadgeCounter(it: JSONObject) {
        Log.d("SOCKET_NOTIFICATION", "updateBadgeCounter: $it ")
    }

    fun unSubscribe() {
        socket.emit("unsubscribe_notifications") // Adjust the event name as needed
        socket.off("new-notification")
    }

    fun clearSession() {
        socket.disconnect()
        unSubscribe()
        socket.off(Socket.EVENT_CONNECT)
    }

    fun testUpdateBadges() {
        IO.socket(UPDATE_ENDPOINT).emit("update")
    }
}