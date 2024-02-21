![image](https://github.com/alamincse6615/data_pass_both_side_flutter_native/assets/48465962/39a3ae06-f474-477f-a77c-401746ac6dad)


![image](https://github.com/alamincse6615/data_pass_both_side_flutter_native/assets/48465962/d5d8a889-f852-4a11-b63f-66193a84c021)


Data pass from Flutter to native (Kotlin) with parametter and return kotlin to flutter with MethodChannel

  flutter section :
  
      int _counter = 0;
      var channel = const MethodChannel("alamin");
      showToast()async{
        var res =await channel.invokeMethod("showToast",{"toastMsg":"message from flutter side to show native code","counterVal":_counter.toString()});
        setState(() {
    
          _counter = int.tryParse(res.toString())??0;
        });
    
      }

  
  native(kotlin) section :
  
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
