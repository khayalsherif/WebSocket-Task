package az.khayalsharifli.algoritmatask.ui.content.home

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import az.khayalsharifli.algoritmatask.base.BaseViewModel
import az.khayalsharifli.algoritmatask.data.mapper.DataMapper.fromRemoteToLocal
import az.khayalsharifli.algoritmatask.data.repository.HomeRepository
import az.khayalsharifli.algoritmatask.model.LocalDto
import az.khayalsharifli.algoritmatask.model.RemoteDto
import az.khayalsharifli.algoritmatask.model.seald.WebSocket
import az.khayalsharifli.algoritmatask.tools.Utils.jsonToModel
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, val socket: Socket, val repository: HomeRepository) :
    BaseViewModel(application) {


    private var _socketResponse =
        MutableStateFlow<WebSocket<List<LocalDto>>>(WebSocket.Disconnect())
    val socketResponse: StateFlow<WebSocket<List<LocalDto>>> get() = _socketResponse.asStateFlow()

    init {
        getDataFromLocal()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun connectWebSocket() {
        viewModelScope.launch {
            if (hasInternetConnection()) {
                addEventWebSocket()
                socket.connect()
            } else {
                _socketResponse.emit(WebSocket.HasNotInternet("Has not internet connection"))
            }
        }
    }

    fun disConnectWebSocket() {
        viewModelScope.launch {
            socket.disconnect()
        }
    }

    private fun addEventWebSocket() {
        socket.on(Socket.EVENT_CONNECT, onConnect)
        socket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        socket.on(io.socket.engineio.client.Socket.EVENT_MESSAGE, onNewMessage)
        socket.on("new message", onNewMessage)
    }

    private val onConnect = Emitter.Listener {
        viewModelScope.launch {
            _socketResponse.emit(WebSocket.Connect())
        }
    }


    private val onConnectError = Emitter.Listener {
        viewModelScope.launch {
            val errorMessage = it.toList().joinToString()
            _socketResponse.emit(WebSocket.ConnectError(errorMessage))
        }
    }


    private val onDisconnect = Emitter.Listener {
        viewModelScope.launch {
            _socketResponse.emit(WebSocket.Disconnect())
        }
    }

    private val onNewMessage =
        Emitter.Listener { args ->
            viewModelScope.launch {
                val remoteDto = jsonToModel<RemoteDto>(args[0].toString())
                val localList = remoteDto.result.map { fromRemoteToLocal(it) }
                repository.sync(localList)
            }
        }

    private fun getDataFromLocal() {
        viewModelScope.launch {
            repository.observeData().collect {
                _socketResponse.emit(WebSocket.NewMessage(it))
            }
        }

    }
}