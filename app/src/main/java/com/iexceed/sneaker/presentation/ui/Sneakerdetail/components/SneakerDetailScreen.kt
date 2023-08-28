package com.iexceed.sneaker.presentation.ui.Sneakerdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.iexceed.bitcoinmca.presentation.ui.coindetail.components.ColourDetailFlow
import com.iexceed.bitcoinmca.presentation.ui.coindetail.components.SizeDetailFlow
import com.iexceed.sneaker.domain.model.SneakerCart
import com.iexceed.sneaker.presentation.ui.Sneakerdetail.SneakerAddEvent.InsertSneaker
import com.iexceed.sneaker.presentation.ui.Sneakerdetail.SneakerDetailViewModel
import com.iexceed.sneaker.ui.theme.colorPrimary
import com.iexceed.sneaker.ui.theme.dark_gray
import com.iexceed.sneaker.ui.theme.white
import kotlin.random.Random

@Composable
fun SneakerDetailScreen(
    navHostController: NavHostController,
    viewModel: SneakerDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val sizeList = listOf("6", "7", "8")
    val colourList = listOf("beige", "red", "blue")
    Box(modifier = Modifier.fillMaxSize()) {
        state.Sneaker?.let { Sneaker ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(white)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.TopStart,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(white)
                    ) {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(
                                modifier = Modifier.size(44.dp, 44.dp),
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "",
                                tint = colorPrimary
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(white)
                    ) {
                        IconButton(onClick = { navHostController.navigate("Sneaker_checkout_screen") }) {
                            Icon(
                                modifier = Modifier.size(40.dp, 40.dp),
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "",
                                tint = colorPrimary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
// can add corousel
                    AsyncImage(modifier = Modifier.size(200.dp), model = Sneaker.image.imageUrl, contentDescription = "one image", contentScale = ContentScale.Crop)
                }
                Spacer(modifier = Modifier.height(44.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(60.dp, 60.dp, 0.dp, 0.dp))
                        .shadow(10.dp)
                        .background(white)
                        .padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${Sneaker.title} ${Sneaker.brand}  ${Sneaker.year} ",
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 28.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "info",
                            tint = colorPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = Sneaker.name,
                        color = dark_gray,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Size (uk) :",
                            color = dark_gray,
                            fontSize = 24.sp
                        )
                        FlowRow(
                            crossAxisSpacing = 10.dp,
                            mainAxisSpacing = 10.dp
                        ) {
                            sizeList.forEach { tag ->
                                SizeDetailFlow(tag = tag)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Colour :",
                            color = dark_gray,
                            fontSize = 24.sp
                        )
                        FlowRow(
                            crossAxisSpacing = 10.dp,
                            mainAxisSpacing = 10.dp
                        ) {
                            colourList.forEach { tag ->
                                ColourDetailFlow(tag = tag)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Price :",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = dark_gray
                        )
                        Text(
                            text = "$ ${Sneaker.price} ",
                            fontSize = 24.sp,
                            color = colorPrimary
                        )
                        Button(
                            modifier = Modifier
                                .wrapContentSize(Alignment.CenterEnd),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colorPrimary

                            ),
                            onClick = {
                                viewModel.onEvent(
                                    InsertSneaker(
                                        SneakerCart(
                                            id = Random.nextInt(61) + 20,
                                            SneakerId = state.Sneaker.SneakerId,
                                            name = state.Sneaker.name,
                                            image = state.Sneaker.image.imageUrl,
                                            brand = state.Sneaker.brand,
                                            year = state.Sneaker.price,
                                            title = state.Sneaker.title,
                                            price = state.Sneaker.price
                                        )
                                    )
                                )
                            }
                        ) {
                            Text(
                                text = "Add to cart",
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
