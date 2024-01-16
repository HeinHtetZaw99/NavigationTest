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
import androidx.navigation.fragment.navArgs
import com.daniel.navigationtest.BaseComposeFragment
import com.daniel.navigationtest.R

class DealsFragment : BaseComposeFragment() {

    private lateinit var viewModel: HomeViewModel
    private var dealsFeatureEntryMode: Boolean = false

    @Composable
    override fun ScreenContent() {
        DealsScreen(
            dealsFeatureEntryMode,
            onClickPromoCodes = {
                findNavController().navigate(R.id.action_dealsFragment_to_promoFragment)
            },
            onClickBack = {
                if (!findNavController().popBackStack()) {
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        dealsFeatureEntryMode = navArgs<DealsFragmentArgs>().value.dealFeatureEntry
    }
}

@Composable
fun DealsScreen(
    dealsOnlyMode: Boolean = false,
    onClickPromoCodes: () -> Unit,
    onClickBack: () -> Unit,
) {
    Column {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.Blue.copy(green = 0.1f, blue = 0.2f, red = 0.1f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "You are now at Deals", modifier = Modifier.background(Color.Transparent), color = Color.Yellow, fontSize = 18.sp)

            if (!dealsOnlyMode) {
                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    modifier = Modifier.padding(10.dp).height(48.dp),
                    onClick = onClickPromoCodes,
                ) {
                    Text(text = "GO To PromoCodes")
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(modifier = Modifier.padding(10.dp).height(48.dp), onClick = onClickBack) {
                Text(text = "Back")
            }
        }
    }
}

@Preview
@Composable
fun HomeAPreview() {
    DealsScreen(false, {}, {})
}
