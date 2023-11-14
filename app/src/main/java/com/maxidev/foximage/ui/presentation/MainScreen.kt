package com.maxidev.foximage.ui.presentation

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.maxidev.foximage.R
import com.maxidev.foximage.data.model.FoxModel
import com.maxidev.foximage.ui.viewmodel.FoxViewModel
import com.maxidev.foximage.utils.NetworkStatus

@Composable
fun MainScreen(
    viewModel: FoxViewModel = viewModel(factory = FoxViewModel.Factory)
) {
    NetCheck(
        status = viewModel.netStatus,
        onClick = { viewModel.singleFox() }
    )
}

@Composable
private fun NetCheck(status: NetworkStatus, onClick: () -> Unit) {
    when (status) {
        is NetworkStatus.Success -> ColumnWithImage(
            model = status.onSuccess as FoxModel,
            onClick = onClick
        )
        is NetworkStatus.Loading -> LoadingScreen()
        is NetworkStatus.Error -> ErrorScreen()
    }
}

@Composable
private fun ColumnWithImage(
    model: FoxModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        FoxTextWithIcon(
            text = R.string.photo_fox,
            drawable = R.drawable.pngwing_com_1_
        )
        Spacer(modifier = Modifier.height(100.dp))
        FoxImage(
            image = model.image,
            onClick = onClick
        )
        LoadLottie()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoxImage(
    image: String,
    onClick: () -> Unit,
    context: Context = LocalContext.current,
    cardElevation: CardElevation = CardDefaults.cardElevation(6.dp),
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(10)
) {
    val imageRequest = ImageRequest.Builder(context)
        .data(image)
        .crossfade(true)
        .allowConversionToBitmap(true)
        .build()

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(4.dp, MaterialTheme.colorScheme.onSurface),
                RoundedCornerShape(10)
            ),
        elevation = cardElevation,
        shape = roundedCornerShape
    ) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = stringResource(id = R.string.fox_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .clip(roundedCornerShape)
            )
        }
    }
}

@Composable
private fun FoxTextWithIcon(
    @StringRes text: Int,
    @DrawableRes drawable: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
        )
    }
}

@Composable
private fun LoadLottie() {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.fox_animation)
    )

    LottieAnimation(composition = lottieComposition)
}