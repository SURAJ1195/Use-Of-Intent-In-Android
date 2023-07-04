package com.example.typesofintentinandroid

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.typesofintentinandroid.ui.theme.TypesOfIntentInAndroidTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TypesOfIntentInAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //todo only uri that are come from internet will shown
                       viewModel.uri?.let {
                           AsyncImage(model = viewModel.uri, contentDescription = "")
                       }

                        Button(onClick = {
                            // todo explicit intent to the same app activity
                            val intent = Intent(applicationContext,SecondActivity::class.java).also {
                                startActivity(it) }
                        }) {
                            Text(text = "click for own activity")
                        }
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )


                        Button(onClick = {
                          Intent(Intent.ACTION_MAIN).also {

                              //todo explicit intent to other applications
                          it.`package` = "com.google.android.youtube"
//                              if(it.resolveActivity(packageManager)!=null){
//                                  startActivity(it)
//                              }
                              try{
                                  startActivity(it)
                              }catch (e:Exception){
                                    Toast.makeText(applicationContext,"Exception",Toast.LENGTH_LONG).show()
                              }

                          }
                        }) {
                            Text(text = "click for youTube activity")
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                              val intent =   Intent(Intent.ACTION_SEND).apply {
                                // todo type means the mime type
                                    type = "text/plane"

                                    //todo putExtra re ame always key value pair re value store karantideithae
                                    //todo for emails amaku kichi key already
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf("kumarsuraj@gmail.com"))
                                    putExtra(Intent.EXTRA_SUBJECT, "this is my subject")
                                    putExtra(Intent.EXTRA_TEXT, "this is the content of my email")

                                }

                                //todo eithi ame check karile i.e is there any app that can satisfy the condition to receive the intent
                                //todo
                                try {
                                    startActivity(intent)
                                }catch(e:Exception){
                                    Toast.makeText(applicationContext,"ff",Toast.LENGTH_LONG).show()
                                }
                                //todo this statement to check to if the package is present or not..

                                if(intent.resolveActivity(packageManager)!=null){

                                }
                        }) {

                        }
                    }
                }
              }
            }
        }

    //todo this is only hit if we send intent from outer system apps
    override fun onNewIntent(intent: Intent?) {
        Log.d("suraj", "this method is called" )
        super.onNewIntent(intent)
        val uri = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            intent?.getParcelableExtra(Intent.EXTRA_STREAM,Uri::class.java)
        }else{
           intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        Log.d("suraj", "this method is called and $uri" )
        viewModel.updateUri(uri)
    }
    }


