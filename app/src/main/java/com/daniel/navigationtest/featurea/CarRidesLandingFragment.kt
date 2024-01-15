package com.daniel.navigationtest.featurea

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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daniel.navigationtest.BaseComposeFragment
import com.daniel.navigationtest.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarRidesLandingFragment : BaseComposeFragment() {

    @Composable
    override fun ScreenContent() {
        CarRidesLandingScreen(onClickBooking = { findNavController().navigate(R.id.action_carRidesFragment_to_bookingFragment) }) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}

@Composable
fun CarRidesLandingScreen(
    onClickBooking: () -> Unit,
    onClickBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.DarkGray.copy(green = 0.1f, blue = 0.2f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "You are now At CarRides Landing Now", modifier = Modifier.background(Color.Transparent), color = Color.Yellow, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickBooking) {
            Text(text = "GO TO Booking", Modifier.background(Color.Transparent))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickBack) {
            Text(text = "Back")
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    CarRidesLandingScreen({
    }, {
    })
}
