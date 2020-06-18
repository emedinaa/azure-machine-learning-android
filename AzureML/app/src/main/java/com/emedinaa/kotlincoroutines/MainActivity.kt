package com.emedinaa.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var age:Int =0
    private var anemia:Int =0
    private var diabetes:Int =0
    private var smoking:Int =0
    private var sex =-1

    private val repository:Repository by  lazy {
        Repository(RemoteDataSource())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroupSex.setOnCheckedChangeListener { _, checkedId ->
            sex = when(checkedId){
                R.id.radioButtonM -> 1
                R.id.radioButtonF -> 0
                else -> -1
            }
        }
        button.setOnClickListener {
            //processData()
            if(validateForm()){
                processData()
            }
        }
    }

    private fun validateForm():Boolean{
        age = editTextAge.text.toString().toInt()
        anemia = if(switchAnemia.isChecked)1 else 0
        diabetes = if(switchDiabetes.isChecked)1 else 0
        smoking = if(switchSmoking.isChecked)1 else 0
        return true
    }
    private fun processData(){
        val input = JSONObject()
        input.put("age",age)//90
        input.put("anaemia",anemia)//0
        input.put("creatinine_phosphokinase","")
        input.put("diabetes",diabetes)//
        input.put("ejection_fraction","")
        input.put("creatinine_phosphokinase","")
        input.put("high_blood_pressure",1)
        input.put("platelets","")
        input.put("serum_creatinine","")
        input.put("serum_sodium","")
        input.put("sex",sex)//1
        input.put("smoking",smoking)//1
        input.put("time","")
        input.put("DEATH_EVENT","")

        val inputData = JSONArray()
        inputData.put(input)

        val obj = JSONObject()
        obj.put("input1",inputData)

        val empty = JSONObject()
        val jsonObject = JSONObject()
        jsonObject.put("Inputs",obj)
        jsonObject.put("GlobalParameters",empty)

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.processData(jsonObject)
            }
            Log.v("CONSOLE", "result $result  ${result?.output1}")
            result?.let {
                val data = it.output1
                if(data.isNotEmpty()){
                    val label = data[0].label
                    val labelStr:String = if(label==1)"Falleció :(" else "Sobrevivió :)"
                    textViewResult.text = labelStr
                }else{
                    textViewResult.text = "Error"
                }
            }?:run{
                textViewResult.text = "Error"
            }
        }
    }

}
