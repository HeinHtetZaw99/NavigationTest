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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.daniel.navigationtest.domain.UserPrefRepo

class ShopFrontFragment : BaseComposeFragment() {

    private lateinit var viewModel: HomeViewModel

    @Composable
    override fun ScreenContent() {
        val enableNgp by viewModel.shouldUseNgpFeature.collectAsState()

        ShopFrontScreen(
            enableNgp = enableNgp,
            onClickCarRides = {
                findNavController().navigate(R.id.action_shopFrontFragment_to_featureAActivity)
            },
            onClickDeals = {
                findNavController().navigate(R.id.action_shopFrontFragment_to_dealsFragment)
            },
            onClickPromoCode = {
                findNavController().navigate(R.id.action_shopFrontFragment_to_promoFragment)
            },
            onToggleNgpFeature = viewModel::onToggleNgpFeature
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}

@Composable
fun ShopFrontScreen(
    enableNgp: Boolean,
    onClickCarRides: () -> Unit,
    onClickDeals: () -> Unit,
    onClickPromoCode: () -> Unit,
    onToggleNgpFeature: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue.copy(green = 0.1f, blue = 0.2f)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "You are now At ShopFront",
            modifier = Modifier.background(Color.Transparent),
            color = Color.Yellow,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Use NGP Feature?",
            fontSize = 12.sp,
            color = Color.Gray,
        )
        Switch(checked = enableNgp, onCheckedChange = {
            onToggleNgpFeature(it)
        })
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(48.dp), onClick = onClickCarRides
        ) {
            Text(text = "GO TO CarRides", Modifier.background(Color.Transparent))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(48.dp), onClick = onClickPromoCode
        ) {
            Text(text = "GO TO PromoCodes")
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(48.dp), onClick = onClickDeals
        ) {
            Text(text = "GO TO Deals")
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    ShopFrontScreen(
        true,
        {},
        {},
        {},
        {},
    )
}
