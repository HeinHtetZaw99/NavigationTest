package com.daniel.navigationtest.home

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.daniel.navigationtest.BaseComposeFragment
import com.daniel.navigationtest.R

class PromoFragment : BaseComposeFragment() {

    private lateinit var viewModel: HomeViewModel

    @Composable
    override fun ScreenContent() {
        PromoCodeScreen(onClickCarRides = { findNavController().navigate(R.id.action_promoFragment_to_featureAActivity)}) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}

@Composable
fun PromoCodeScreen(
    onClickCarRides: () -> Unit,
    onClickBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Blue.copy(green = 0.2f, blue = 0.2f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "You are now at PromoCodes", modifier = Modifier.background(Color.Transparent), color = Color.Yellow, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickCarRides) {
            Text(text = "GO To CarRides")
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickBack) {
            Text(text = "Back")
        }
    }
}

@Preview
@Composable
fun HomeBPreview() {
    PromoCodeScreen({}, {})
}
