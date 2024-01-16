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
import com.daniel.navigationtest.home.DealsFragmentDirections

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ActiveBookingFragment : BaseComposeFragment() {

    @Composable
    override fun ScreenContent() {
        ActiveBookingScreen(
            onClickEndTrip = {
                findNavController().navigate(R.id.action_activeBookingFragment_to_endTripFragment)
            },
            onClickDeals = {
                findNavController().navigate(ActiveBookingFragmentDirections.actionGlobalToMainActivity("deeplink://deals"))
            },
        ) {
            findNavController().popBackStack()
        }
    }
}

@Composable
fun ActiveBookingScreen(
    onClickEndTrip: () -> Unit,
    onClickDeals: () -> Unit,
    onClickBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.DarkGray.copy(green = 0.18f, blue = 0.28f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "You are now At Booking", modifier = Modifier.background(Color.Transparent), color = Color.Yellow, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickEndTrip) {
            Text(text = "GO TO ActiveBookingScreen", Modifier.background(Color.Transparent))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickDeals) {
            Text(text = "GO TO Deals", Modifier.background(Color.Transparent))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickBack) {
            Text(text = "Go back to ShopFront")
        }
    }
}

@Preview
@Composable
fun ActiveBookingScreenPreview() {
    ActiveBookingScreen({
    }, {
    }, {
    })
}
