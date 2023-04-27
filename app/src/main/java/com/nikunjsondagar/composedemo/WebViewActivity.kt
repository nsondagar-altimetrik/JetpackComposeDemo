package com.nikunjsondagar.composedemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView

class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            intent?.extras?.let {
                it.getString(ProfileURL)?.let { url ->
                    DisplayWebViewUI(profileURL = url)
                } ?: kotlin.run {
                    showToastMessage(context = this)
                    val activity = (LocalLifecycleOwner.current as ComponentActivity)
                    activity.finish()
                }
            }
        }
    }

    companion object {

        const val ProfileURL = "profileURL"

        fun startWebViewActivity(activity: Activity, bundle: Bundle) {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}

@Composable
fun showToastMessage(context: Context) {
    Toast.makeText(context, stringResource(id = R.string.web_view_error_message), Toast.LENGTH_LONG)
        .show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayWebViewUI(profileURL: String) {
    val visibility = remember { mutableStateOf(true) }
    Scaffold(
        topBar = { WebViewTopAppBar() },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                OpenUserProfileInWebView(
                    userProfileURL = profileURL,
                    paddingValues = it,
                    visibility
                )
                if (visibility.value) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        })
}

@Composable
fun WebViewTopAppBar() {
    val activity = (LocalLifecycleOwner.current as ComponentActivity)
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
    navigationIcon = {
        IconButton(onClick = {
            activity.finish()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_button_accessibility_text)
            )
        }
    })
}

@Composable
fun OpenUserProfileInWebView(
    userProfileURL: String,
    paddingValues: PaddingValues,
    visibility: MutableState<Boolean>
) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    visibility.value = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    visibility.value = false
                }
            }
            loadUrl(userProfileURL)
        }
    }, update = {
        it.loadUrl(userProfileURL)
    }, modifier = Modifier.padding(paddingValues))
}
