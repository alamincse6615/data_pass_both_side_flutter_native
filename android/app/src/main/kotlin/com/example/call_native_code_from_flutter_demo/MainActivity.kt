package com.example.call_native_code_from_flutter_demo
import android.widget.Toast
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
class MainActivity: FlutterActivity() {
    private val channelName = "alamin"
    private var msg: String = "defaultNativeValue"
    private var count: Int = 0
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val channel= MethodChannel(flutterEngine.dartExecutor.binaryMessenger,channelName)
        channel.setMethodCallHandler { call, result ->
            val args = call.arguments as Map<*, *>

            if(args["toastMsg"] != null){
                msg = args["toastMsg"].toString()
            }
            if(args["counterVal"] != null){
                count =  args["counterVal"].toString().toInt()
            }

            if(call.method == "showToast"){
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
            }

            if(call.method == "showToast"){
                count += 1
                result.success(count)
            }
        }

    }
}
