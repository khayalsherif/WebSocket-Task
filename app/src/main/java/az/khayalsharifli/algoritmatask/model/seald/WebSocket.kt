package az.khayalsharifli.algoritmatask.model.seald


sealed class WebSocket<T>(
    val data: T? = null,
    val message: String? = null
) {
    class NewMessage<T>(data: T?) : WebSocket<T>(data)
    class Connect<T> : WebSocket<T>()
    class Disconnect<T> : WebSocket<T>()
    class ConnectError<T>(message: String?, data: T? = null) : WebSocket<T>(data, message)
    class HasNotInternet<T>(message: String?) : WebSocket<T>(message = message)
}